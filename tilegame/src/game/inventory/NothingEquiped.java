package game.inventory;

import game.inventory.Inventory.Dir;
import game.object.GameBoard;
import game.world.World;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class NothingEquiped extends Item
{
	public NothingEquiped(GameBoard board) throws SlickException
	{
		super(board, "res/nothing.png", 0);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, World world,
			int delta) throws SlickException
	{
	}

	@Override
	public void fire(int x, int y) throws SlickException
	{
	}

	@Override
	public void fire(int x, int y, Dir dir) throws SlickException
	{
	}

}
