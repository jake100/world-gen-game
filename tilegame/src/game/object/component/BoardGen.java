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
 * Class that handles world generation by first randomly scattering mountains, extends them then it randomly scatters dirt.
 * The next part of world generation simulates turns with particle generation turned off and weopons are used on the land.
 * Then it just adds finishing touches like getting rid of single tiles.
 */
public class BoardGen
{
	private GameBoard board;
	private int[][] grid, terrainGrid, burnGrid;
	private Random rnd = new Random();
	private int x, y, enemies, start, counter;
	//weopons used to modify the world
	private FireBomb fireBomb;
	private PoisonBomb poisonBomb;
	private InsectDrop insectDrop;
	private FireStorm fireStorm;
	public BoardGen(GameBoard board, int enemies, int start) throws SlickException
	{
		this.board = board;
		this.enemies = enemies;
		this.start = start;
		fireBomb = new FireBomb(board);
		poisonBomb = new PoisonBomb(board);
		insectDrop = new InsectDrop(board);
		fireStorm = new FireStorm(board);
		poisonBomb.setCount(1000);
		fireBomb.setCount(1000);
		insectDrop.setCount(1000);
		fireStorm.setCount(1000);
		generateLand();
		modLand();
		prepareForGame();
	}
	public void generateLand() throws SlickException
	{
		board.getParticleGen().setParticleGen(false);
		grid = board.getGrid();
		terrainGrid = board.getTerrainGrid();
		burnGrid = board.getBurnGrid();
		
		for(int i = 0; i < 50;i++)
		{
			addMountain();
		}
		for(int i = 0; i < 250;i++)
		{
			extendMountain();
		}
		for(int i = 0; i < 60;i++)
		{
			addDirt();
		}
	}
	public void modLand() throws SlickException
	{
		for(int i = 0; i < 15;i++)
		{
			addEnemy();
		}
		for(int i = 0; i < 260; i++)
		{
			board.tileUpdate();
			board.setTurn(0);
		}
		clearBoard();
		for(int i = 0; i < 150; i++)
		{
			board.tileUpdate();
			board.setTurn(0);
		}
		for(int i = 0;i < 6;i++)
		{
			fireBomb.fire(rnd.nextInt(Game.TWidth), rnd.nextInt(Game.THeight));
			fireBomb.fire(rnd.nextInt(Game.TWidth), rnd.nextInt(Game.THeight));
			poisonBomb.fire(rnd.nextInt(Game.TWidth), rnd.nextInt(Game.THeight));
			poisonBomb.fire(rnd.nextInt(Game.TWidth), rnd.nextInt(Game.THeight));
			fireStorm.fire(rnd.nextInt(Game.TWidth), rnd.nextInt(Game.THeight));
			fireStorm.fire(rnd.nextInt(Game.TWidth), rnd.nextInt(Game.THeight));
		}
		for(int i = 0; i < 70; i++)
		{
			board.tileUpdate();
			board.setTurn(0);
		}
	}
	public void prepareForGame() throws SlickException
	{
		for(int x = 0; x < grid.length;x++)
		{
			for(int y = 0; y < grid[0].length;y++)
			{
				burnGrid[x][y] = 0;
			}
		}
		removeSingleTiles();
		for(int i = 0; i < enemies;i++)
		{
			addEnemy();
		}
		board.setGrid(grid);
		board.setTerrainGrid(terrainGrid);
		for(int i = 0; i < start; i++)
		{
			board.tileUpdate();
			board.setTurn(0);
		}
		board.getParticleGen().setParticleGen(true);
	}
	public void removeSingleTiles()
	{
		for(int x = 0; x < Game.TWidth;x++)
		{
			for(int y = 0; y < Game.THeight;y++)
			{
				if(terrainGrid[x][y] == GameBoard.stone || terrainGrid[x][y] == GameBoard.mountain)
				{
					if(alone(x, y, GameBoard.stone, GameBoard.mountain))terrainGrid[x][y] = GameBoard.grass;
				}
				if(terrainGrid[x][y] == GameBoard.dirt || terrainGrid[x][y] == GameBoard.grass)
				{
					if(alone(x, y, GameBoard.dirt, GameBoard.grass))terrainGrid[x][y] = GameBoard.stone;
				}
			}
		}
	}
	public boolean alone(int x, int y, int id, int altId)
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
	public void addObsidian()
	{
		rndTile();
		if(grid[x][y] == GameBoard.air)grid[x][y] = GameBoard.meteor;
		else addObsidian();
	}
	public void addDirt()
	{
		rndTile();
		if(terrainGrid[x][y] == GameBoard.stone)terrainGrid[x][y] = GameBoard.dirt;
		else if(rnd.nextInt(100) < 95)addDirt(x, y);
		else addDirt();
	}
	public void addMountain()
	{
		rndTile();
		if(terrainGrid[x][y] == GameBoard.stone)terrainGrid[x][y] = GameBoard.mountain;
		else addMountain(x, y);
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
		if(x < 0 || x >= grid.length || y < 0 || y >= grid[0].length)addMountain();
		else if(terrainGrid[x][y] == GameBoard.stone)terrainGrid[x][y] = GameBoard.mountain;
		else addMountain();
	}
	public void addDirt(int x, int y)
	{
		int num = rnd.nextInt(4);
		if(num == 0)x++;
		if(num == 1)x--;
		if(num == 2)y++;
		if(num == 3)y--;
		if(x < 0 || x >= grid.length || y < 0 || y >= grid[0].length)addDirt();
		else if(terrainGrid[x][y] == GameBoard.stone)terrainGrid[x][y] = GameBoard.dirt;
		else addDirt();
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
