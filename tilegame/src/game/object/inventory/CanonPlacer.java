package game.object.inventory;

import game.object.GameBoard;
import game.object.inventory.Inventory.Dir;
import game.util.SoundBank;
import game.world.World;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class CanonPlacer extends Item
{
	public CanonPlacer(GameBoard board) throws SlickException
	{
		super(board, "res/canon_icon.png", 1);
	}
	public void update(World world, int delta)
	{
		
	}
	public void fire(int x, int y)
	{
		if(count > 0)
		{
			int[][] grid = board.getGrid();
			int[][] terrainGrid = board.getTerrainGrid();
			int[][] foliageGrid = board.getFoliageGrid();
			int[][] placeableGrid = board.getPlaceableGrid();
			if(grid[x][y] != GameBoard.meteor)
			{
				grid[x][y] = GameBoard.air;
				if(board.isPlaySound())SoundBank.thudSound.playAsSoundEffect(2, 1, false);
				if(terrainGrid[x][y] == GameBoard.grass)terrainGrid[x][y] = GameBoard.dirt;
				foliageGrid[x][y] = GameBoard.no_foliage;
				placeableGrid[x][y] = GameBoard.canon;
				int firstDamage = 9;
				if(board.validFireSpot(x + 1, y))
				{
					board.damage(x + 1, y, firstDamage);
					board.spawnCload(x + 1, y, 4);
				}
				if(board.validFireSpot(x - 1, y))
				{
					board.damage(x - 1, y, firstDamage);
					board.spawnCload(x - 1, y, 4);
				}
				if(board.validFireSpot(x, y + 1))
				{
					board.damage(x, y + 1, firstDamage);
					board.spawnCload(x, y + 1, 4);
				}
				if(board.validFireSpot(x, y - 1))
				{
					board.damage(x, y - 1, firstDamage);
					board.spawnCload(x, y - 1, 4);
				}
				count--;
				board.tileUpdate();
			}
			board.setGrid(grid);
		}
	}
	public void fire(int x, int y, Dir dir)
	{
	}
}
