package game.object.inventory;

import game.Game;
import game.object.GameBoard;
import game.object.inventory.Inventory.Dir;
import game.world.World;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Wind extends Item
{
	int[][] grid;
	public Wind(GameBoard board, int count) throws SlickException
	{
		super(board, "res/wind.png", count);
		grid = board.getGrid();
	}

	public void update(GameContainer gc, StateBasedGame sbg, World world, int delta) throws SlickException
	{
		
	}

	public void fire(int x, int y) throws SlickException
	{
			  for(int i = y; i >= 0; i--)
			  {
				  if(grid[x][i] != GameBoard.air) {dropBlock(x, i, Dir.Down);}
			  }
			  for(int i = y; i < Game.THeight; i++)
			  {
				  if(grid[x][i] != GameBoard.air){dropBlock(x, i, Dir.Up);}
			  }
			  for(int i = x; i >= 0; i--)
			  {
				  if(grid[i][y] != GameBoard.air){dropBlock(i, y, Dir.Right);}
			  }
			  for(int i = x; i < Game.TWidth; i++)
			  {
				  if(grid[i][y] != GameBoard.air){dropBlock(i, y, Dir.Left);}
			  }
		  board.setGrid(grid);
	}
	public void dropBlock(int x, int y, Dir dir)
	{
		if(dir == Dir.Down)
		{
			if(y + 1 < Game.THeight)
			{
				if(grid[x][y + 1] == GameBoard.air)
				{
					grid[x][y + 1] = replace(x, y);
					dropBlock(x, y + 1, dir);
				}
			}
		}
		if(dir == Dir.Up)
		{
			if(y - 1 >= 0)
			{
				if(grid[x][y - 1] == GameBoard.air)
				{
					grid[x][y - 1] = replace(x, y);
					dropBlock(x, y - 1, dir);
				}
			}
		}
		if(dir == Dir.Right)
		{
			if(x + 1 < Game.TWidth)
			{
				if(grid[x + 1][y] == GameBoard.air)
				{
					grid[x + 1][y] = replace(x, y);
					dropBlock(x + 1, y, dir);
				}
			}
		}
		if(dir == Dir.Left)
		{
			if(x - 1 >= 0)
			{
				if(grid[x - 1][y] == GameBoard.air)
				{
					grid[x - 1][y] = replace(x, y);
					dropBlock(x - 1, y, dir);
				}
			}
		}
	}

	public void fire(int x, int y, Dir dir) throws SlickException
	{
		deleteBlock(x, y, dir);
	}
	public void deleteBlock(int x, int y, Dir dir)
	{
		 
	}
	public int replace(int x, int y)
	{
		board.damage(x, y, 1);
		int block = grid[x][y];
		if(block < 0)block = 0;
		grid[x][y] = GameBoard.air;
		return block;
	}
}
