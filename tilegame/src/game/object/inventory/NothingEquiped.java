package game.object.inventory;

import game.object.GameBoard;
import game.object.inventory.Inventory.Dir;
import game.world.World;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class NothingEquiped extends Item
{
	public NothingEquiped(GameBoard board)
	{
		super(board, "res/nothing.png", 0);
	}
	public void update(World world, int delta)
	{
		
	}
	public void fire(int x, int y)
	{
		
	}
	public void fire(int x, int y, Dir dir)
	{
		
	}

}
