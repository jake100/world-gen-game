package game.object;

import game.ChooseFile;
import game.Game;
import game.object.component.BoardComponent;
import game.object.component.GameOver;
import game.object.component.ParticleGenerator;
import game.object.component.PlayerInput;
import game.object.inventory.Inventory;
import game.util.Circle;
import game.util.File;
import game.world.World;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
/*
 * Class that does most of the work.
 * Either loads from file or the board gets randomly generated.
 * Loading from a file is way faster.
 * Different tile ids for each grid.
 */
public class GameBoard extends Board
{
	protected GameOver gameOver = null;
	protected BoardGenManager boardGenManager;
	protected ParticleGenerator particleGen = new ParticleGenerator(this);
	protected boolean playSound = false, godMode = false;
	protected File file;
	protected ChooseFile chooseFile = new ChooseFile();
	private boolean generating = false;
	private PlayerInput playerInput;
	public GameBoard(File file, Inventory inventory, GameInfo gameInfo, int enemies, int start, boolean loaded) throws SlickException, InterruptedException
	{
		super(gameInfo);
		boardGenManager = new BoardGenManager(this, enemies, start, 1);
		inventory.setOwner(this);
		if(loaded)inventory.reset();
		this.file = file;
		this.file.setOwner(this);
		this.loaded = loaded;
		turns = (int) (18 + enemies * 2 + start * 2);
		dollars = 250;
		if(loaded)
		{
			generating = true;
		}
		if(!loaded)
		{
			loaded = true;
    		int val = chooseFile.showOpenDialog(null);
			if (val == JFileChooser.APPROVE_OPTION)
			{
				   java.io.File otherFile = chooseFile.getSelectedFile();
				   loadGame(otherFile, inventory);
			}
			else
			{
					java.io.File otherFile = new java.io.File("res/save/tutorial level.dat");
				    loadGame(otherFile, inventory);
			}
		}
	}
	public void loadGame(java.io.File otherFile, Inventory inventory) throws SlickException
	{
		file.load(otherFile.toString());
	    int[] values = file.getValues();
		dollars = values[0];
		turn = values[1];
		turns = values[2];
		stopTime = values[3];
		inventory.loadItem(0, values[4], values[5]);
		inventory.loadItem(1, values[6], values[7]);
		gameInfo.setLevel(values[8]);
		gameInfo.setPoints(values[9]);
		grid = file.getGrid();
		terrainGrid = file.getTerrainGrid();
		foliageGrid = file.getFoliageGrid();
		insectGrid = file.getInsectGrid();
		placeableGrid = file.getPlaceableGrid();
		burnGrid = file.getBurnGrid();
	}
    public void update(GameContainer gc, StateBasedGame sbg, World world, int delta) throws SlickException
    {
    	if(!generating)
    	{
			super.update(gc, sbg, world, delta);
			if(gameOver == null)
	    	{
	    		addBoardComponent(particleGen);
	    		for(BoardComponent comp : boardComponents)
	    		{
	    			if(comp instanceof GameOver)gameOver = (GameOver) comp;
	    		}
	    	}
			if(playerInput != null && playerInput.isrKey())
			{
				alive = false;
			}
    	}
		else
		{
			boardGenManager.generate();
			if(boardGenManager.isGenerated())
			{
				generating = false;
				if(gameOver == null)
		    	{
		    		addBoardComponent(particleGen);
		    		for(BoardComponent comp : boardComponents)
		    		{
		    			if(comp instanceof GameOver)gameOver = (GameOver) comp;
		    		}
		    	}
				gameOver.setEnabled(false);
				for(BoardComponent comp : boardComponents)
				{
					if(comp instanceof PlayerInput)
					{
						this.playerInput = (PlayerInput) comp;
					}
				}
			}
		}
    }
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		super.render(gc, sbg, g);
		g.setFont(Game.smallPrint);
	}
    public void tileUpdate() throws SlickException
    {
    	if(stopTime == 0)
    	{
    		turn++;
        	for(int i = 0; i < grid.length; i++)
    		{
    			for(int j = 0; j < grid[0].length; j++)
    			{
    				if(insectGrid[i][j] == insect_drop)
    				{
    					insectGrid = placeAround(i, j, grid, insectGrid, foliageGrid, insect, true);
    				}
    				if(placeableGrid[i][j] == canon)
    				{
    					spawnCload(i, j, 10);
    					if(rnd.nextInt(3) == 0)shellArea(i, j, 15);
    					else shellArea(i, j, 12);
    					shellArea(i, j, 8);
  
    				}
    				if(insectGrid[i][j] == insect)
    				{
    					foliageGrid[i][j] = no_foliage;
    					if(terrainGrid[i][j] == grass)terrainGrid[i][j] = dirt;
    					if(grid[i][j] != air && grid[i][j] != meteor)damage(i, j, 3);
    					move(i, j, insectGrid, foliageGrid, insect);
    				}
    				if(placeableGrid[i][j] == villager)dollars += 5;
    				if(grid[i][j] != air)
    				{
    					placeableGrid[i][j] = no_placeable;
    				}
    				if(grid[i][j] >= living && grid[i][j] <= boss)
    				{
    					levelUp(i, j);
    				}
    				if(terrainGrid[i][j] == grass && rnd.nextInt(20) == 0)
    				{
    					spreadGrass(i, j);
    				}
    				if(foliageGrid[i][j] != no_foliage && rnd.nextInt(20) == 0)
    				{
    					spreadFoliage(i, j);
    				}
    				if(foliageGrid[i][j] == lifeGiver)
    				{
    					if(grid[i][j] < living && grid[i][j] != air)
    					{
    						int level = grid[i][j] + 1;
    						grid[i][j] = level;
    					}
    				}
    			}
    		}
        	for(BoardComponent comp : boardComponents)
        	{
        		comp.tileUpdate();
        	}
    	}
    	else
    	{
    		stopTime--;
    	}
    	if(godMode)godUpdate();
    }
    public void godUpdate()
    {
    	int dollarMin = 9999;
    	if(dollars < dollarMin)dollars = dollarMin;
    	turn = 0;
    }
    public void shellArea(int x, int y, int radius) throws SlickException
    {
    	Circle circle = new Circle();
    	int radius0 = 1;
    	Vector2f pos = new Vector2f(x, y);
    	if(radius > 1)
    	{
    		radius0 = rnd.nextInt(radius - 1) + 1;
    		pos = circle.getCircle(x, y, radius0).get(rnd.nextInt(circle.getCircle(x, y, radius0).size() - 1));
    	}
    	int xx = (int) pos.x;
		int yy = (int) pos.y;
		if(placeableGrid[xx][yy] == no_placeable && grid[xx][yy] != meteor)
		{
			damage(xx, yy, 18);
			spawnFire(xx, yy, 3);
		}
		else shellArea(x, y, radius);
    }
    public void move(int x, int y, int[][] placeGrid, int[][] checkGrid, int placeId)
    {
    	placeGrid[x][y] = 0;
    	boolean[] moves = new boolean[4];
    	for(int i = 0;i < moves.length;i++)moves[i] = true;
    	if(x - 1 < 0 || checkGrid[x - 1][y] == 0)moves[0] = false;
    	if(x + 1 >= Game.TWidth || checkGrid[x + 1][y] == 0)moves[1] = false;
    	if(y - 1 < 0 || checkGrid[x][y - 1] == 0)moves[2] = false;
    	if(y + 1 >= Game.THeight || checkGrid[x][y + 1] == 0)moves[3] = false;
    	
    	List<Integer> list = new ArrayList<Integer>();
    	for(int k = 0;k < moves.length;k++)
    	{
    		if(moves[k])list.add(new Integer(k));
    	}
    	if(list.size() >= 1)
    	{
    		int dir = list.get(rnd.nextInt(list.size())).intValue();
    		switch(dir)
    		{
    		case 0:
    			placeGrid[x - 1][y] = placeId;
    			break;
    		case 1:
    			placeGrid[x + 1][y] = placeId;
    			break;
    		case 2:
    			placeGrid[x][y - 1] = placeId;
    			break;
    		case 3:
    			placeGrid[x][y + 1] = placeId;
    			break;
    		}
    	}
    }
	public void spawnFire(int x, int y, int num) throws SlickException
	{
		if(terrainGrid[x][y] == grass || terrainGrid[x][y] == dirt) terrainGrid[x][y] = charred;
		for(int i = 0; i < num; i++)
		{
			particleGen.addParticle(Particle.getFireBall(gameInfo, new Vector2f(x * Game.ScaledTileSize + Game.ScaledTileSize / 2 - Basic_Particle.scale / 2, y * Game.ScaledTileSize + Game.ScaledTileSize / 2 - Basic_Particle.scale / 2)));
		}
	}
	public void spawnPoison(int x, int y, int num) throws SlickException
	{
		if(terrainGrid[x][y] == grass || terrainGrid[x][y] == dirt) terrainGrid[x][y] = poisoned;
		for(int i = 0; i < num; i++)
		{
			particleGen.addParticle(Particle.getPoison(gameInfo, new Vector2f(x * Game.ScaledTileSize + Game.ScaledTileSize / 2 - Basic_Particle.scale / 2, y * Game.ScaledTileSize + Game.ScaledTileSize / 2 - Basic_Particle.scale / 2)));
		}
	}
	public void spawnCload(int x, int y, int num) throws SlickException
	{
		for(int i = 0; i < num; i++)
		{
			particleGen.addParticle(Particle.getCload(gameInfo, new Vector2f(x * Game.ScaledTileSize + Game.ScaledTileSize / 2 - Basic_Particle.scale / 2, y * Game.ScaledTileSize + Game.ScaledTileSize / 2 - Basic_Particle.scale / 2)));
		}
	}
	public boolean validFireSpot(int x, int y)
	{
		if(x < 0 || x >= grid.length || y < 0 || y >= grid[0].length)return false;
		if(grid[x][y] == meteor)return false;
		return true;
	}
    public void levelUp(int x, int y) throws SlickException
    {
    	if(terrainGrid[x][y] == grass && foliageGrid[x][y] == no_foliage && rnd.nextInt(1000) == 0)
		{
    		foliageGrid[x][y] = rnd.nextInt(typesOfFoliage);
		}
		int num = rnd.nextInt(5);
    	int tX = x, tY = y;
    	if(num == 0)tY = y + 1;
		if(num == 1)tY = y - 1;
		if(num == 2)tX = x + 1;
		if(num == 3)tX = x - 1;
		boolean canLevelUp = false;
		if(validSpot(x, y) || validSpot(x, y + 1) || validSpot(x, y - 1) || validSpot(x + 1, y) || validSpot(x - 1, y))canLevelUp = true;
    	if(tX >= 0 && tY >= 0 && tX < Game.TWidth && tY < Game.THeight  && grid[tX][tY] != meteor && grid[tX][tY] != boss)
    	{
    		if(validFireSpot(tX, tY) && grid[x][y] == boss)
    		{
    			if(rnd.nextInt(5) == 0)
    			{
            		if(terrainGrid[tX][tY] == mountain)terrainGrid[tX][tY] = stone;
            		if(terrainGrid[tX][tY] == stone)terrainGrid[tX][tY] = dirt;
            		if(terrainGrid[tX][tY] == charred)terrainGrid[tX][tY] = dirt;
    			}
        		if(terrainGrid[tX][tY] == dirt && rnd.nextInt(500) == 0)terrainGrid[tX][tY] = grass;
    		}

    		if(grid[tX][tY] < boss)
    		{
    			if(terrainGrid[tX][tY] == dirt)grid[tX][tY]++;
    			if(terrainGrid[tX][tY] == grass)
    			{
    				if(foliageGrid[x][y] == tall_grass || foliageGrid[tX][tY] == tall_grass) grid[tX][tY] += 2;
    				grid[tX][tY] += 2;
    				if(grid[tX][tY] > boss)grid[tX][tY] = boss;
    			}
    			for(int i = 0; i < 1; i++)
            	{
            		float speed = .06f * Game.Scale;
            		int life = 35;
            		float newDx = (float) (speed * Math.cos(Math.random() * 2 * Math.PI));
              		float newDy = (float) (speed * Math.sin(Math.random() * 2 * Math.PI));
              		particleGen.addParticle(new Basic_Particle(gameInfo, new Image("res/smoke_tile.png"), new Vector2f(x * Game.ScaledTileSize + Game.ScaledTileSize / 2 - Basic_Particle.scale / 2, y * Game.ScaledTileSize + Game.ScaledTileSize / 2 - Basic_Particle.scale / 2), new Vector2f(newDx, newDy), life));
            	}
    		}
    	}
    	else
    	{
    		if(canLevelUp)levelUp(x, y);
    	}
    }
    public void spreadGrass(int x, int y) throws SlickException
    {
		int num = rnd.nextInt(4);
    	int tX = x, tY = y;
    	if(num == 0)tY = y + 1;
		if(num == 1)tY = y - 1;
		if(num == 2)tX = x + 1;
		if(num == 3)tX = x - 1;
		boolean canGrow = false;
		if(validGrassSpot(x, y) || validGrassSpot(x, y + 1) || validGrassSpot(x, y - 1) || validGrassSpot(x + 1, y) || validGrassSpot(x - 1, y))canGrow = true;
    	if(tX >= 0 && tY >= 0 && tX < Game.TWidth && tY < Game.THeight && validGrassSpot(tX, tY))
    	{
    		terrainGrid[tX][tY] = grass;
    	}
    	else
    	{
    		 if(canGrow)spreadGrass(x, y);
    	}
    }
    public void spreadFoliage(int x, int y) throws SlickException
    {
		int num = rnd.nextInt(4);
    	int tX = x, tY = y;
    	if(num == 0)tY = y + 1;
		if(num == 1)tY = y - 1;
		if(num == 2)tX = x + 1;
		if(num == 3)tX = x - 1;
		boolean canGrow = false;
		if(validFoliageSpot(x, y) || validFoliageSpot(x, y + 1) || validFoliageSpot(x, y - 1) || validFoliageSpot(x + 1, y) || validFoliageSpot(x - 1, y))canGrow = true;
    	if(tX >= 0 && tY >= 0 && tX < Game.TWidth && tY < Game.THeight && validFoliageSpot(tX, tY))
    	{
    		foliageGrid[tX][tY] = foliageGrid[x][y];
    	}
    	else
    	{
    		 if(canGrow)spreadFoliage(x, y);
    	}
    }
    public boolean validGrassSpot(int x, int y)
    {
    	if(x < 0 || y < 0 || x >= grid.length || y >= grid[0].length)return false;
    	if(terrainGrid[x][y] == dirt || terrainGrid[x][y] == charred || (terrainGrid[x][y] == poisoned && rnd.nextInt(3) == 0))return true;
    	return false;
    }
    public boolean validFoliageSpot(int x, int y)
    {
    	if(x < 0 || y < 0 || x >= grid.length || y >= grid[0].length)return false;
    	if(grid[x][y] == meteor)return false;
    	if(terrainGrid[x][y] == grass && foliageGrid[x][y] == no_foliage)return true;
    	return false;
    }
    public boolean validSpot(int x, int y)
    {
    	if(x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || terrainGrid[x][y] == stone)return false;
    	if(grid[x][y] == meteor || grid[x][y] == boss)return false;
    	return true;
    }


	public void damage(int x, int y, int amount)
	{
		if(grid[x][y] != meteor && grid[x][y] != air)
		{
			if(foliageGrid[x][y] == plant)
			{
				amount -= 10;
				if(amount < 0)amount = 0;
			}
			grid[x][y] -= amount;
			if(grid[x][y] < 0)grid[x][y] = 0;
		}
		foliageGrid[x][y] = no_foliage;
	}
	public void stopTime(int num)
	{
		stopTime+=num;
	}
	public void addBoardComponent(BoardComponent component)
	{
	    component.setOwner(this);
	    boardComponents.add(component);
	}
    public BoardComponent getBoardComponent(String id)
    {
        for(BoardComponent comp : boardComponents)
        {
        	if (comp.getId() != null && comp.getId().equalsIgnoreCase(id))return comp;
        }
	return null;
    }
	public int getTurn()
	{
		return turn;
	}
	public void setTurn(int turn)
	{
		this.turn = turn;
	}
	public int getTurns()
	{
		return turns;
	}
	public void setTurns(int turns)
	{
		this.turns = turns;
	}
	public ParticleGenerator getParticleGen()
	{
		return particleGen;
	}
	public void setParticleGen(ParticleGenerator particleGen)
	{
		this.particleGen = particleGen;
	}
	public int getStopTime()
	{
		return stopTime;
	}
	public boolean isPlaySound()
	{
		return playSound;
	}
	public void setPlaySound(boolean playSound)
	{
		this.playSound = playSound;
	}
	public void setStopTime(int stopTime)
	{
		this.stopTime = stopTime;
	}
	public File getFile()
	{
		return file;
	}
	public void setFile(File file)
	{
		this.file = file;
	}
	public boolean isGenerating()
	{
		return generating;
	}
	public void setGenerating(boolean generating)
	{
		this.generating = generating;
	}
	
}