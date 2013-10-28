package game.inventory;

import game.inventory.Inventory.Dir;
import game.object.GameBoard;
import game.util.SoundBank;
import game.world.World;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class StopTime extends Item
{
	public StopTime(GameBoard board) throws SlickException
	{
		super(board, "res/stop_time_icon.png", 1);
	}

	public void update(GameContainer gc, StateBasedGame sbg, World world, int delta) throws SlickException
	{
		
	}
	public void fire(int x, int y) throws SlickException
	{
		if(count > 0)
		{
			if(board.isPlaySound())SoundBank.chimeSound.playAsSoundEffect(1, 1, false);
			count--;
			board.stopTime(1);
		}
	}
	public void fire(int x, int y, Dir dir) throws SlickException
	{
	}
}
