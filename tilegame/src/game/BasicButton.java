package game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public abstract class BasicButton extends BasicUI
{
	protected boolean clicked = false, altClicked = false;
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
		super.update(gc, sbg, delta);
		Input input = gc.getInput();
		if(entered && input.isMouseButtonDown(0))
		{
			clicked = true;
			count = wait;
		}
		if(entered && input.isMouseButtonDown(1))
		{
			altClicked = true;
			count = wait;
		}
	}
	//getters and setters
	public boolean isClicked()
	{
		return clicked;
	}
	public void setClicked(boolean clicked)
	{
		this.clicked = clicked;
	}
	public boolean isAltClicked()
	{
		return altClicked;
	}
	public void setAltClicked(boolean altClicked)
	{
		this.altClicked = altClicked;
	}
	
}
