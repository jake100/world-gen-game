package game.inventory;

import game.inventory.Inventory.Dir;
import game.object.GameBoard;
import game.util.SoundBank;
import game.world.World;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class PoisonBomb extends Item
{
	int[][] grid, terrainGrid;
	public PoisonBomb(GameBoard board) throws SlickException
	{
		super(board, "res/poison_icon.png", 1);
		grid = board.getGrid();
		terrainGrid = board.getTerrainGrid();
	}

	public void update(GameContainer gc, StateBasedGame sbg, World world, int delta) throws SlickException
	{
		
	}

	public void fire(int x, int y) throws SlickException
	{
		if(count > 0)
		{
			board.tileUpdate();
			count--;
			board.damage(x, y, 5);
			if(board.isPlaySound())SoundBank.fireSound.playAsSoundEffect(1, 1, false);
			board.spawnFire(x, y, 5);
			int firstDamage = 1, diagonalDamage = 1, secondDamage = 1;
			int firstFire = 6, diagonalFire = 3, secondFire = 2;
			if(validFireSpot(x + 1, y))
			{
				board.damage(x + 1, y, firstDamage);
				board.spawnPoison(x + 1, y, firstFire);
			}
			if(validFireSpot(x - 1, y))
			{
				board.damage(x - 1, y, firstDamage);
				board.spawnPoison(x - 1, y, firstFire);
			}
			if(validFireSpot(x, y + 1))
			{
				board.damage(x, y + 1, firstDamage);
				board.spawnPoison(x, y + 1, firstFire);
			}
			if(validFireSpot(x, y - 1))
			{
				board.damage(x, y - 1, firstDamage);
				board.spawnPoison(x, y - 1, firstFire);
			}
			//diagonal
			if(validFireSpot(x + 1, y + 1))
			{
				board.damage(x + 1, y + 1, diagonalDamage);
				board.spawnPoison(x + 1, y + 1, diagonalFire);
			}
			if(validFireSpot(x + 1, y - 1))
			{
				board.damage(x + 1, y - 1, diagonalDamage);
				board.spawnPoison(x + 1, y - 1, diagonalFire);
			}
			if(validFireSpot(x - 1, y + 1))
			{
				board.damage(x - 1, y + 1, diagonalDamage);
				board.spawnPoison(x - 1, y + 1, diagonalFire);
			}
			if(validFireSpot(x - 1, y - 1))
			{
				board.damage(x - 1, y - 1, diagonalDamage);
				board.spawnPoison(x - 1, y - 1, diagonalFire);
			}
			//two blocks away
			
			if(validFireSpot(x + 2, y))
			{
				board.damage(x + 2, y, secondDamage);
				board.spawnPoison(x + 2, y, secondFire);
			}
			if(validFireSpot(x - 2, y))
			{
				board.damage(x - 2, y, secondDamage);
				board.spawnPoison(x - 2, y, secondFire);
			}
			if(validFireSpot(x, y + 2))
			{
				board.damage(x, y + 2, secondDamage);
				board.spawnPoison(x, y + 2, secondFire);
			}
			if(validFireSpot(x, y - 2))
			{
				board.damage(x, y - 2, secondDamage);
				board.spawnPoison(x, y - 2, secondFire);
			}
			//adjacent to two blocks away
			if(validFireSpot(x + 2, y + 1))
			{
				board.damage(x + 2, y + 1, secondDamage);
				board.spawnPoison(x + 2, y + 1, secondFire);
			}
			if(validFireSpot(x + 2, y - 1))
			{
				board.damage(x + 2, y - 1, secondDamage);
				board.spawnPoison(x + 2, y - 1, secondFire);
			}
			if(validFireSpot(x - 2, y + 1))
			{
				board.damage(x - 2, y + 1, secondDamage);
				board.spawnPoison(x - 2, y + 1, secondFire);
			}
			if(validFireSpot(x - 2, y - 1))
			{
				board.damage(x - 2, y - 1, secondDamage);
				board.spawnPoison(x - 2, y - 1, secondFire);
			}
			if(validFireSpot(x + 1, y + 2))
			{
				board.damage(x + 1, y + 2, secondDamage);
				board.spawnPoison(x + 1, y + 2, secondFire);
			}
			if(validFireSpot(x - 1, y + 2))
			{
				board.damage(x - 1, y + 2, secondDamage);
				board.spawnPoison(x - 1, y + 2, secondFire);
			}
			if(validFireSpot(x + 1, y - 2))
			{
				board.damage(x + 1, y - 2, secondDamage);
				board.spawnPoison(x + 1, y - 2, secondFire);
			}
			if(validFireSpot(x - 1, y - 2))
			{
				board.damage(x - 1, y - 2, secondDamage);
				board.spawnPoison(x - 1, y - 2, secondFire);
			}
			//three blocks away
			if(validFireSpot(x + 3, y))
			{
				board.damage(x + 3, y, secondDamage);
				board.spawnPoison(x + 3, y, secondFire);
			}
			if(validFireSpot(x - 3, y))
			{
				board.damage(x - 3, y, secondDamage);
				board.spawnPoison(x - 3, y, secondFire);
			}
			if(validFireSpot(x, y + 3))
			{
				board.damage(x, y + 3, secondDamage);
				board.spawnPoison(x, y + 3, secondFire);
			}
			if(validFireSpot(x, y - 3))
			{
				board.damage(x, y - 3, secondDamage);
				board.spawnPoison(x, y - 3, secondFire);
			}
		}
	}

	public void fire(int x, int y, Dir dir) throws SlickException
	{
		
	}
	public boolean validFireSpot(int x, int y)
	{
		if(x < 0 || x >= grid.length || y < 0 || y >= grid[0].length)return false;
		if(grid[x][y] == GameBoard.meteor)return false;
		return true;
	}
}
