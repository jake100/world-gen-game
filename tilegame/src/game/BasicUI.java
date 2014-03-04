package game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public abstract class BasicUI
{
	protected int x, y, width, height, count = 50, wait = 25;
	protected float scale;
	protected boolean entered = false, enabled = true;
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
		entered = false;
		if(enabled && count <= 0)
		{
			Input input = gc.getInput();
			int mx = input.getMouseX();
			int my = input.getMouseY();
			if((mx > x && mx < x + width * scale) && (my > y && my < y + height * scale)) entered = true;
		}
		else
		{
			count--;
		}
	}
	public boolean isEntered() {return entered;}
	public boolean isEnabled()
	{
		return enabled;
	}
	public void setEnabled(boolean enabled)
	{
		this.enabled = enabled;
	}
}
