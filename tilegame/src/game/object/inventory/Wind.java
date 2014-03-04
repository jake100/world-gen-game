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
	public Wind(GameBoard board) throws SlickException
	{
		super(board, "res/wind.png", 1);
		grid = board.getGrid();
	}

	public void update(GameContainer gc, StateBasedGame sbg, World world, int delta) throws SlickException
	{
		
	}

	public void fire(int x, int y) throws SlickException
	{
		int range = 5;
		dropBlock(0, x, y, Dir.Down, range);
		dropBlock(0, x, y, Dir.Up, range);
		dropBlock(0, x, y, Dir.Right, range);
		dropBlock(0, x, y, Dir.Left, range);
		board.setGrid(grid);
	}
	public void dropBlock(int id, int x, int y, Dir dir, int count) throws SlickException
	{
		int destX = x, destY = y;
		boolean canMove = false;
		switch(dir)
		{
		case Down:
			destY++;
			if(destY < Game.THeight)canMove = true;
			break;
		case Up:
			destY--;
			if(destY >= 0)canMove = true;
			break;
		case Right:
			destX++;
			if(destX < Game.TWidth)canMove = true;
			break;
		case Left:
			destX--;
			if(destX >= 0)canMove = true;
			break;
		}
		if(canMove)
		{
			//board.damage(x, y, 1);

			if(count > 0)
			{
				int block = grid[x][y];
				grid[x][y] = id;
				board.spawnCload(x, y, 1);
				count--;
				dropBlock(block, destX, destY, dir, count);
			}
		}
	}

	public void fire(int x, int y, Dir dir) throws SlickException
	{
	}
}
