package game.object;

import game.object.component.BoardGen;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class BoardGenManager
{
	private int count = 0, enemies, start, difficulty;
	private BoardGen boardGen;
	private GameBoard board;
	private boolean generated = false;
	public BoardGenManager(GameBoard board, int enemies, int start, int difficulty) throws SlickException
	{
		boardGen = new BoardGen(board);
		board.getParticleGen().setParticleGen(false);
		this.enemies = enemies;
		this.start = start;
		this.board = board;
		this.difficulty = difficulty;
	}
	public void generate() throws SlickException
	{
		System.out.println(count);
		ArrayList<Vector2f> terrainList = getGridList(board.getTerrainGrid()), enemyList = getGridList(board.getGrid());
		switch(count)
		{
		case 0:
			for(int i = 0; i < 500 + difficulty * 50;i++)
			{
				terrainList = getGridList(board.getTerrainGrid());
				boardGen.addId(GameBoard.dirt, terrainList);
			}
			break;
		case 1:
			for(int i = 0; i < 1750;i++)
			{
				terrainList = getGridList(board.getTerrainGrid());
				boardGen.addId(GameBoard.mountain, terrainList);
			}
			break;
		case 2:
			boardGen.extendMountains(1500 - difficulty * 10, terrainList);
			break;
		case 3:
			boardGen.extendMountains(1500 - difficulty * 10, terrainList);
			break;
		case 4:
			boardGen.extendMountains(1500 - difficulty * 10, terrainList);
			break;
		case 5:
			boardGen.addEnemies(20, enemyList);
			break;
		case 6:
			boardGen.simulateTurns(80);
			break;
		case 7:
			boardGen.simulateTurns(80);
			break;
		case 8:
			boardGen.addEnemies(difficulty * 10, enemyList);
			break;
		case 9:
			boardGen.simulateTurns(80);
			break;
		case 10:
			boardGen.simulateTurns(80);
			break;
		case 11:
			boardGen.clearBoard();
			break;
		case 12:
			boardGen.simulateTurns(80);
			break;
		case 13:
			boardGen.simulateTurns(80);
			break;
		case 14:
			boardGen.removeSingleTiles();
			break;
		case 15:
			boardGen.simulateTurns(40);
			break;
		case 16:
			boardGen.simulateTurns(40);
			break;
		case 17:
			boardGen.weoponFire(6);
			break;
		case 18:
			boardGen.simulateTurns(40);
			break;
		case 19:
			boardGen.simulateTurns(40);
			break;
		case 20:
			boardGen.clearBurnGrid();
			break;
		case 21:
			boardGen.addEnemies(enemies, enemyList);
			break;
		case 22:
			boardGen.simulateTurns(start);
			break;
		case 23:
			boardGen.prepareForGame();
			generated = true;
			break;
		}
		count++;
	}
	public ArrayList<Vector2f> getGridList(int[][] grid)
	{
		ArrayList<Vector2f> list = new ArrayList<Vector2f>();
		for(int x = 0;x < grid.length;x++)
		{
			for(int y = 0;y < grid[0].length;y++)
			{
				list.add(new Vector2f(x, y));
			}
		}
		return list;
	}
	public boolean isGenerated()
	{
		return generated;
	}
	public void setGenerated(boolean generated)
	{
		this.generated = generated;
	}
	
}
