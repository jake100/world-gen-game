package game.object;

import game.ChooseFile;
import game.Game;
import game.inventory.Inventory;
import game.object.component.BoardComponent;
import game.object.component.BoardGen;
import game.object.component.GameOver;
import game.object.component.ParticleGenerator;
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

public class GameBoard extends Board
{
	//grid ids
	public static final int air = 0, living = 10, typesOfTile = 19, boss = typesOfTile - 1, meteor = 99;
	//terrain ids
	public static final int stone = 0, dirt = 1, grass = 2, charred = 3, barren = 4, mountain = 5, poisoned = 6, typesOfTerrain = 7;
	//drop ids
	public static final int no_foliage = 0, tall_grass = 1, plant = 2, typesOfFoliage = 3;
	public static final int no_placeable = 0, villager = 1, canon = 2, typesOfPlaceable = 3;
	public static final int no_insect = 0, insect_drop = 1, insect = 2, typesOfInsect = 3;
	protected int turns = 100, turn = 0, stopTime = 0,counter = 0;
	protected GameOver gameOver = null;
	protected BoardGen boardGen;
	protected ParticleGenerator particleGen = new ParticleGenerator(this);
	protected boolean playSound = false, godMode = false;
	protected File file;
	protected ChooseFile chooseFile = new ChooseFile();
	public GameBoard(File file, Inventory inventory, GameInfo gameInfo, int enemies, int start, boolean loaded) throws SlickException, InterruptedException
	{
		super(gameInfo);
		boardGen = new BoardGen(this, enemies, start);
		inventory.setOwner(this);
		if(loaded)inventory.reset();
		this.file = file;
		this.file.setOwner(this);
		this.loaded = loaded;
		turns = (int) (18 + enemies * 2 + start * 2);
		dollars = 100;
		if(!loaded)
		{
			loaded = true;
    		int val = chooseFile.showOpenDialog(null);
			if (val == JFileChooser.APPROVE_OPTION)
			{
				   java.io.File otherFile = chooseFile.getSelectedFile();
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
			else
			{
					java.io.File otherFile = new java.io.File("res/save/demo level.dat");
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
		}
		else
		{
			boardGen.state = BoardGen.State.BasicGenerate;
			boardGen.generate();
		}
		playSound = true;
		if(godMode)godUpdate();
	}
    public void update(GameContainer gc, StateBasedGame sbg, World world, int delta) throws SlickException
    {
    	super.update(gc, sbg, world, delta);
    	boardGen.generate();
    	if(gameOver == null)
    	{
    		addBoardComponent(particleGen);
    		for(BoardComponent comp : boardComponents)
    		{
    			if(comp instanceof GameOver)gameOver = (GameOver) comp;
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
    		gameInfo.addPoints(50);
        	for(int i = 0; i < grid.length; i++)
    		{
    			for(int j = 0; j < grid[0].length; j++)
    			{
    				if(insectGrid[i][j] == insect_drop)
    				{
    					insectGrid[i][j] = insect;
    					if(i + 1 < Game.TWidth && foliageGrid[i + 1][j] != GameBoard.no_foliage && insectGrid[i + 1][j] == GameBoard.no_insect && grid[i + 1][j] == GameBoard.air)
    					{
    						insectGrid[i + 1][j] = insect;
    					}
    					if(i - 1 > 0 && foliageGrid[i - 1][j] != GameBoard.no_foliage && insectGrid[i - 1][j] == GameBoard.no_insect && grid[i - 1][j] == GameBoard.air)
    					{
    						insectGrid[i - 1][j] = insect;
    					}
    					
    					if(j + 1 < Game.THeight && foliageGrid[i][j + 1] != GameBoard.no_foliage && insectGrid[i][j + 1] == GameBoard.no_insect && grid[i][j + 1] == GameBoard.air)
    					{
    						insectGrid[i][j + 1] = insect;
    					}
    					if(j - 1 > 0 && foliageGrid[i][j - 1] != GameBoard.no_foliage && insectGrid[i][j - 1] == GameBoard.no_insect && grid[i][j - 1] == GameBoard.air)
    					{
    						insectGrid[i][j - 1] = insect;
    					}
    				}
    				if(placeableGrid[i][j] == canon)
    				{
    					spawnCload(i, j, 10);
    					if(rnd.nextInt(3) == 0)shell(i, j, 15);
    					else shell(i, j, 12);
    					shell(i, j, 8);
  
    				}
    				if(insectGrid[i][j] == insect)
    				{
    					foliageGrid[i][j] = no_foliage;
    					if(terrainGrid[i][j] == grass)terrainGrid[i][j] = dirt;
    					if(grid[i][j] != air && grid[i][j] != meteor)damage(i, j, 3);
    					moveInsect(i, j);
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
    public void shell(int x, int y, int radius) throws SlickException
    {
    	Circle circle = new Circle();
    	int radius0 = rnd.nextInt(radius - 1) + 1;
    	Vector2f pos = circle.getCircle(x, y, radius0).get(rnd.nextInt(circle.getCircle(x, y, radius0).size() - 1));
    	int xx = (int) pos.x;
		int yy = (int) pos.y;
		if(placeableGrid[xx][yy] == no_placeable && grid[xx][yy] != meteor)
		{
			damage(xx, yy, 18);
			spawnFire(xx, yy, 3);
		}
		else shell(x, y, radius);
    }
    public void moveInsect(int i, int j)
    {
    	insectGrid[i][j] = no_insect;
    	boolean[] moves = new boolean[4];
    	if(i - 1 < 0 || foliageGrid[i - 1][j] == no_foliage)moves[0] = false;
    	else moves[0] = true;
    	if(i + 1 >= Game.TWidth || foliageGrid[i + 1][j] == no_foliage)moves[1] = false;
    	else moves[1] = true;
    	if(j - 1 < 0 || foliageGrid[i][j - 1] == no_foliage)moves[2] = false;
    	else moves[2] = true;
    	if(j + 1 >= Game.THeight || foliageGrid[i][j + 1] == no_foliage)moves[3] = false;
    	else moves[3] = true;
    	
    	List<Integer> list = new ArrayList<Integer>();
    	for(int k = 0;k < moves.length;k++)
    	{
    		if(moves[k])list.add(new Integer(k));
    	}
    	if(list.size() >= 1)
    	{
    		move(i, j, list.get(rnd.nextInt(list.size())).intValue());
    	}
    }
    public void move(int x, int y, int dir)
    {
    	if(dir == 0)
    	{
    		insectGrid[x - 1][y] = insect;
    	}
    	if(dir == 1)
    	{
    		insectGrid[x + 1][y] = insect;
    	}
    	if(dir == 2)
    	{
    		insectGrid[x][y - 1] = insect;
    	}
    	if(dir == 3)
    	{
    		insectGrid[x][y + 1] = insect;
    	}
    }
	public void spawnFire(int x, int y, int num) throws SlickException
	{
		if(terrainGrid[x][y] == grass || terrainGrid[x][y] == dirt) terrainGrid[x][y] = charred;
		for(int i = 0; i < num; i++)
		{
			particleGen.addParticle(new Fire(gameInfo, new Vector2f(x * Game.ScaledTileSize + Game.ScaledTileSize / 2 - Particle.scale / 2, y * Game.ScaledTileSize + Game.ScaledTileSize / 2 - Particle.scale / 2)));
		}
	}
	public void spawnPoison(int x, int y, int num) throws SlickException
	{
		if(terrainGrid[x][y] == grass || terrainGrid[x][y] == dirt) terrainGrid[x][y] = poisoned;
		for(int i = 0; i < num; i++)
		{
			particleGen.addParticle(new Poison(gameInfo, new Vector2f(x * Game.ScaledTileSize + Game.ScaledTileSize / 2 - Particle.scale / 2, y * Game.ScaledTileSize + Game.ScaledTileSize / 2 - Particle.scale / 2)));
		}
	}
	public void spawnCload(int x, int y, int num) throws SlickException
	{
		for(int i = 0; i < num; i++)
		{
			particleGen.addParticle(new Cload(gameInfo, new Vector2f(x * Game.ScaledTileSize + Game.ScaledTileSize / 2 - Particle.scale / 2, y * Game.ScaledTileSize + Game.ScaledTileSize / 2 - Particle.scale / 2)));
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
    		if(validFireSpot(tX, tY) && terrainGrid[tX][tY] == mountain && grid[x][y] == boss && rnd.nextInt(5) == 0)terrainGrid[tX][tY] = stone;
    		if(validFireSpot(tX, tY) && terrainGrid[tX][tY] == stone && grid[x][y] == boss && rnd.nextInt(5) == 0)terrainGrid[tX][tY] = dirt;
    		if(validFireSpot(tX, tY) && terrainGrid[tX][tY] == charred && grid[x][y] == boss && rnd.nextInt(5) == 0)terrainGrid[tX][tY] = dirt;
    		if(validFireSpot(tX, tY) && terrainGrid[tX][tY] == dirt && grid[x][y] == boss && rnd.nextInt(500) == 0)terrainGrid[tX][tY] = grass;
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
              		particleGen.addParticle(new Particle(gameInfo, new Image("res/smoke_tile.png"), new Vector2f(x * Game.ScaledTileSize + Game.ScaledTileSize / 2 - Particle.scale / 2, y * Game.ScaledTileSize + Game.ScaledTileSize / 2 - Particle.scale / 2), new Vector2f(newDx, newDy), life));
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
	public int getBoardGenCounter()
	{
		return boardGen.getCounter();
	}
}