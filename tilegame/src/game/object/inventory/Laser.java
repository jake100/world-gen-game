package game.object.inventory;

import game.Game;
import game.object.GameBoard;
import game.object.inventory.Inventory.Dir;
import game.util.SoundBank;
import game.world.World;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Laser extends Item
{
	private int[][] grid, terrainGrid, burnGrid, centerBurnGrid;
	public Laser(GameBoard board) throws SlickException
	{
		super(board, "res/laser_icon.png", 1);
		grid = board.getGrid();
		terrainGrid = board.getTerrainGrid();
		burnGrid = board.getBurnGrid();
		centerBurnGrid = new int[Game.TWidth][Game.THeight];
	}
	public void update(GameContainer gc, StateBasedGame sbg, World world, int delta) throws SlickException
	{
		for(int x = 0; x < burnGrid.length; x++)
		{
			for(int y = 0; y < burnGrid[0].length;y++)
			{
				if(centerBurnGrid[x][y] == 1)
				{
					if(burnGrid[x][y] != GameBoard.air)board.spawnFire(x, y, 1);
					else centerBurnGrid[x][y] = 0;
				}
			}
		}
		
	}
	public void fire(int x, int y) throws SlickException
	{
		if(count > 0)
		{
			count--;
			int[][] grid = board.getGrid();
			if(board.isPlaySound())SoundBank.sizzleSound.playAsSoundEffect(1, 1, false);
			if(grid[x][y] == GameBoard.meteor)grid[x][y] = GameBoard.air;
			board.damage(x, y, 18);
			spawnBurn(x, y);
			if(terrainGrid[x][y] == GameBoard.charred) terrainGrid[x][y] = GameBoard.barren;
			int firstDamage = 18;
			if(board.validFireSpot(x + 1, y))
			{
				board.damage(x + 1, y, firstDamage);
				spawnBurn(x + 1, y);
			}
			if(board.validFireSpot(x - 1, y))
			{
				board.damage(x - 1, y, firstDamage);
				spawnBurn(x - 1, y);
			}
			if(board.validFireSpot(x, y + 1))
			{
				board.damage(x, y + 1, firstDamage);
				spawnBurn(x, y + 1);
			}
			if(board.validFireSpot(x, y - 1))
			{
				board.damage(x, y - 1, firstDamage);
				spawnBurn(x, y - 1);
			}
			centerBurnGrid[x][y] = 1;
		}
	}
	public void spawnBurn(int x, int y) throws SlickException
	{
		if(terrainGrid[x][y] == GameBoard.grass || terrainGrid[x][y] == GameBoard.dirt) terrainGrid[x][y] = GameBoard.charred;
		burnGrid[x][y] = 40;
	}
	public void fire(int x, int y, Dir dir) throws SlickException
	{
	}

}
