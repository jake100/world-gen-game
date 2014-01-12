package game.object.component;

import game.Game;
import game.object.GameBoard;
import game.object.inventory.FireBomb;
import game.object.inventory.FireStorm;
import game.object.inventory.InsectDrop;
import game.object.inventory.PoisonBomb;

import java.util.Random;

import org.newdawn.slick.SlickException;
/*
 * Class that handles world generation.
 * it starts as all stone then it adds mountains and dirt.
 * it then adds enemies to the board and simuates turns.
 */
public class BoardGen
{
	private GameBoard board;
	private int[][] grid, terrainGrid, burnGrid;
	private Random rnd = new Random();
	private int x, y, counter;
	//weopons used to modify the land
	private FireBomb fireBomb;
	private PoisonBomb poisonBomb;
	private InsectDrop insectDrop;
	private FireStorm fireStorm;
	
	public BoardGen(GameBoard board) throws SlickException
	{
		this.board = board;
		fireBomb = new FireBomb(board);
		poisonBomb = new PoisonBomb(board);
		insectDrop = new InsectDrop(board);
		fireStorm = new FireStorm(board);
		poisonBomb.setCount(1000);
		fireBomb.setCount(1000);
		insectDrop.setCount(1000);
		fireStorm.setCount(1000);
		grid = board.getGrid();
		terrainGrid = board.getTerrainGrid();
		burnGrid = board.getBurnGrid();
	}
	public void simulateTurns(int count) throws SlickException
	{
		for(int i = 0; i < count; i++)
		{
			board.tileUpdate();
			board.setTurn(0);
		}
	}
	public void prepareForGame() throws SlickException
	{
		board.setGrid(grid);
		board.setTerrainGrid(terrainGrid);
		board.getParticleGen().setParticleGen(true);
		for(int i = 0;i < 350;i++)
		{
			board.spawnCload(rnd.nextInt(Game.TWidth), rnd.nextInt(Game.THeight), rnd.nextInt(3) + 1);
		}
	}
	public void clearBurnGrid()
	{
		for(int x = 0; x < grid.length;x++)
		{
			for(int y = 0; y < grid[0].length;y++)
			{
				burnGrid[x][y] = 0;
			}
		}
	}
	public void weoponFire(int count) throws SlickException
	{
		for(int i = 0; i < count;i++)
		{
			fireBomb.fire(rnd.nextInt(Game.TWidth), rnd.nextInt(Game.THeight));
			poisonBomb.fire(rnd.nextInt(Game.TWidth), rnd.nextInt(Game.THeight));
			fireStorm.fire(rnd.nextInt(Game.TWidth), rnd.nextInt(Game.THeight));
		}

	}
	//removes certain tiles that are just by themselves and not touching the edge.
	public void removeSingleTiles()
	{
		for(int x = 0; x < Game.TWidth;x++)
		{
			for(int y = 0; y < Game.THeight;y++)
			{
				if(terrainGrid[x][y] == GameBoard.stone || terrainGrid[x][y] == GameBoard.mountain)
				{
					if(isAlone(x, y, GameBoard.stone, GameBoard.mountain))terrainGrid[x][y] = GameBoard.grass;
				}
				if(terrainGrid[x][y] == GameBoard.dirt || terrainGrid[x][y] == GameBoard.grass)
				{
					if(isAlone(x, y, GameBoard.dirt, GameBoard.grass))terrainGrid[x][y] = GameBoard.stone;
				}
			}
		}
	}
	public boolean isAlone(int x, int y, int id, int altId)
	{
		if(x - 1 < 0 || x + 1 >= Game.TWidth || y - 1 < 0 || y + 1 >= Game.THeight)return false;
		if(terrainGrid[x + 1][y] == id || terrainGrid[x + 1][y] == altId)return false;
		if(terrainGrid[x - 1][y] == id || terrainGrid[x - 1][y] == altId)return false;
		
		if(terrainGrid[x][y + 1] == id || terrainGrid[x][y + 1] == altId)return false;
		if(terrainGrid[x][y - 1] == id || terrainGrid[x][y - 1] == altId)return false;
		
		if(terrainGrid[x + 1][y + 1] == id || terrainGrid[x + 1][y + 1] == altId)return false;
		if(terrainGrid[x + 1][y - 1] == id || terrainGrid[x + 1][y - 1] == altId)return false;
		
		if(terrainGrid[x - 1][y + 1] == id || terrainGrid[x - 1][y + 1] == altId)return false;
		if(terrainGrid[x - 1][y - 1] == id || terrainGrid[x - 1][y - 1] == altId)return false;
		return true;
	}
	public void clearBoard()
	{
		for(int x = 0; x < grid.length;x++)
		{
			for(int y = 0; y < grid[0].length;y++)
			{
				grid[x][y] = GameBoard.air;
				burnGrid[x][y] = 0;
			}
		}
	}
	public void addEnemy()
	{
		rndTile();
		if(grid[x][y] == GameBoard.air)grid[x][y] = GameBoard.boss;
		else addEnemy();
	}
	public void addEnemies(int count)
	{
		for(int i = 0;i < count;i++)
		{
			addEnemy();
		}
	}
	public void addId(int id)
	{
		rndTile();
		switch(id)
		{
		case GameBoard.mountain:
			if(terrainGrid[x][y] == GameBoard.stone)terrainGrid[x][y] = GameBoard.mountain;
			else addMountain(x, y);
			break;
		case GameBoard.dirt:
			if(terrainGrid[x][y] == GameBoard.stone)terrainGrid[x][y] = GameBoard.dirt;
			else if(rnd.nextInt(100) < 95)addDirt(x, y);
			else addId(0);
			break;
		}
	}
	public void addObsidian()
	{
		rndTile();
		if(grid[x][y] == GameBoard.air)grid[x][y] = GameBoard.meteor;
		else addObsidian();
	}
	public void extendMountains(int count)
	{
		for(int i = 0;i < count;i++)
		{
			extendMountain();
		}
	}
	public void extendMountain()
	{
		rndTile();
		if(terrainGrid[x][y] == GameBoard.mountain)addMountain(x, y);
		else extendMountain();
	}
	public void addGrass()
	{
		rndTile();
		if(terrainGrid[x][y] == GameBoard.dirt)terrainGrid[x][y] = GameBoard.grass;
		else addGrass();
	}
	public void addMountain(int x, int y)
	{
		int num = rnd.nextInt(4);
		if(num == 0)x++;
		if(num == 1)x--;
		if(num == 2)y++;
		if(num == 3)y--;
		if(x < 0 || x >= grid.length || y < 0 || y >= grid[0].length)addId(GameBoard.mountain);
		else if(terrainGrid[x][y] == GameBoard.stone)terrainGrid[x][y] = GameBoard.mountain;
		else addId(GameBoard.mountain);
	}
	public void addDirt(int x, int y)
	{
		int num = rnd.nextInt(4);
		if(num == 0)x++;
		if(num == 1)x--;
		if(num == 2)y++;
		if(num == 3)y--;
		if(x < 0 || x >= grid.length || y < 0 || y >= grid[0].length)addId(GameBoard.dirt);
		else if(terrainGrid[x][y] == GameBoard.stone)terrainGrid[x][y] = GameBoard.dirt;
		else addId(GameBoard.dirt);
	}
	public void addGrass(int x, int y)
	{
		int num = rnd.nextInt(4);
		if(num == 0)x++;
		if(num == 1)x--;
		if(num == 2)y++;
		if(num == 3)y--;
		if(x < 0 || x >= grid.length || y < 0 || y >= grid[0].length)addGrass();
		else if(terrainGrid[x][y] == GameBoard.dirt)terrainGrid[x][y] = GameBoard.grass;
		else addGrass();
	}
	public void rndTile()
	{
		x = rnd.nextInt(grid.length);
		y = rnd.nextInt(grid[0].length);
	}
	public int getCounter()
	{
		return counter;
	}
	public void setCounter(int counter)
	{
		this.counter = counter;
	}
	
}
