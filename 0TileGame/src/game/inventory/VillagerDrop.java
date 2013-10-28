package game.inventory;

import game.inventory.Inventory.Dir;
import game.object.GameBoard;
import game.util.SoundBank;
import game.world.World;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class VillagerDrop extends Item
{
	public VillagerDrop(GameBoard board) throws SlickException
	{
		super(board, "res/villager_icon.png", 1);
	}

	public void update(GameContainer gc, StateBasedGame sbg, World world, int delta) throws SlickException
	{
		
	}
	public void fire(int x, int y) throws SlickException
	{
		if(count > 0)
		{
			int[][] grid = board.getPlaceableGrid();
			if(board.isPlaySound())SoundBank.clickSound.playAsSoundEffect(1, 1, false);
			if(grid[x][y] == GameBoard.no_placeable)
			{
				grid[x][y] = GameBoard.villager;
				count--;
				board.tileUpdate();
			}
			board.setPlaceableGrid(grid);
		}
	}
	public void fire(int x, int y, Dir dir) throws SlickException
	{
	}

}
