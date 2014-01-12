package game;
/*
 * abstract button class, need improving but does the job so far.
 */
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public abstract class Button extends BasicButton
{
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
		super.update(gc, sbg, delta);
		if(count <= 0)
		{
			Input input = gc.getInput();

		}
	}
	public boolean getInput(Input input, int key)
	{
		if(input.isKeyDown(key))
		{
			count = wait;
			return true;
		}
		return false;
	}
}
