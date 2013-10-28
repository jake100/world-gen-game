package game.inventory;

import game.inventory.Inventory.Dir;
import game.object.GameBoard;
import game.util.SoundBank;
import game.world.World;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class FireBomb extends Item
{
	private int[][] grid, terrainGrid;
	public FireBomb(GameBoard board) throws SlickException
	{
		super(board, "res/bomb_icon.png", 1);
		grid = board.getGrid();
		terrainGrid = board.getTerrainGrid();
	}
	public void update(GameContainer gc, StateBasedGame sbg, World world, int delta) throws SlickException
	{
		
	}
	public boolean validFireSpot(int x, int y)
	{
		if(x < 0 || x >= grid.length || y < 0 || y >= grid[0].length)return false;
		if(grid[x][y] == GameBoard.meteor)return false;
		return true;
	}

	public void fire(int x, int y) throws SlickException
	{
		if(count > 0)
		{
			board.tileUpdate();
			count--;
			board.damage(x, y, 22);
			if(board.isPlaySound())SoundBank.flameSound.playAsSoundEffect(1, 1, false);
			board.spawnFire(x, y, 7);
			int firstDamage = 20, diagonalDamage = 18, secondDamage = 16;
			int firstFire = 6, diagonalFire = 3, secondFire = 2;
			if(validFireSpot(x + 1, y))
			{
				board.damage(x + 1, y, firstDamage);
				board.spawnFire(x + 1, y, firstFire);
			}
			if(validFireSpot(x - 1, y))
			{
				board.damage(x - 1, y, firstDamage);
				board.spawnFire(x - 1, y, firstFire);
			}
			if(validFireSpot(x, y + 1))
			{
				board.damage(x, y + 1, firstDamage);
				board.spawnFire(x, y + 1, firstFire);
			}
			if(validFireSpot(x, y - 1))
			{
				board.damage(x, y - 1, firstDamage);
				board.spawnFire(x, y - 1, firstFire);
			}
			//diagonal
			
			if(validFireSpot(x + 1, y + 1) && (validFireSpot(x + 1, y) || validFireSpot(x, y + 1)))
			{
				board.damage(x + 1, y + 1, diagonalDamage);
				board.spawnFire(x + 1, y + 1, diagonalFire);
			}
			if(validFireSpot(x + 1, y - 1) && (validFireSpot(x + 1, y) || validFireSpot(x, y - 1)))
			{
				board.damage(x + 1, y - 1, diagonalDamage);
				board.spawnFire(x + 1, y - 1, diagonalFire);
			}
			if(validFireSpot(x - 1, y + 1) && (validFireSpot(x - 1, y) || validFireSpot(x, y + 1)))
			{
				board.damage(x - 1, y + 1, diagonalDamage);
				board.spawnFire(x - 1, y + 1, diagonalFire);
			}
			if(validFireSpot(x - 1, y - 1) && (validFireSpot(x - 1, y) || validFireSpot(x, y - 1)))
			{
				board.damage(x - 1, y - 1, diagonalDamage);
				board.spawnFire(x - 1, y - 1, diagonalFire);
			}
			//two blocks away
			
			if(validFireSpot(x + 2, y) && validFireSpot(x + 1, y))
			{
				board.damage(x + 2, y, secondDamage);
				board.spawnFire(x + 2, y, secondFire);
			}
			if(validFireSpot(x - 2, y) && validFireSpot(x - 1, y))
			{
				board.damage(x - 2, y, secondDamage);
				board.spawnFire(x - 2, y, secondFire);
			}
			if(validFireSpot(x, y + 2) && validFireSpot(x, y + 1))
			{
				board.damage(x, y + 2, secondDamage);
				board.spawnFire(x, y + 2, secondFire);
			}
			if(validFireSpot(x, y - 2) && validFireSpot(x, y - 1))
			{
				board.damage(x, y - 2, secondDamage);
				board.spawnFire(x, y - 2, secondFire);
			}
		}
	}
	@Override
	public void fire(int x, int y, Dir dir) throws SlickException
	{
	}
}
