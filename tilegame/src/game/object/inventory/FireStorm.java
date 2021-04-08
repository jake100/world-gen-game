package game.object.inventory;

import game.object.GameBoard;
import game.object.inventory.Inventory.Dir;
import game.util.SoundBank;
import game.world.World;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class FireStorm extends Item
{
	public FireStorm(GameBoard board)
	{
		super(board, "res/fire_storm_icon.png", 1);
	}
	public void update(World world, int delta)
	{
	}
	public void fire(int x, int y)
	{
		if(count > 0)
		{
			if(board.isPlaySound())SoundBank.stormSound.playAsSoundEffect(1, 1, false);
			for(int i = 0; i < 300;i++)
			{
				board.shellArea(x, y, 10);
			}
			board.tileUpdate();
			count--;
		}
	}
	public void fire(int x, int y, Dir dir)
	{
		
	}

}
