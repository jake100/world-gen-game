package game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public abstract class Button
{
	protected int x, y, width, height, count = 50;
	protected float scale;
	protected boolean entered = false, clicked = false, altClicked = false, spaceClicked = false, escapedClicked = false;

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
		entered = false;
		clicked = false;
		altClicked = false;
		spaceClicked = false;
		escapedClicked = false;
		
		if(count <= 0)
		{
			Input input = gc.getInput();
			int mx = input.getMouseX();
			int my = input.getMouseY();
			if((mx > x && mx < x + width * scale) && (my > y && my < y + height * scale)) entered = true;
			int wait = 25;
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
			if(input.isKeyDown(Input.KEY_SPACE))
			{
				spaceClicked = true;
				count = wait;
			}
			if(input.isKeyDown(Input.KEY_ESCAPE))
			{
				escapedClicked = true;
				count = wait;
			}
		}
		else
		{
			count--;
		}
	}
	public boolean isEntered() {return entered;}
	public void setEntered(boolean entered) {this.entered = entered;}
	public boolean isClicked() {return clicked;}
	public void setClicked(boolean clicked) {this.clicked = clicked;}
	public boolean isAltClicked()
	{
		return altClicked;
	}
	public void setAltClicked(boolean altClicked)
	{
		this.altClicked = altClicked;
	}
	public boolean isSpaceClicked()
	{
		return spaceClicked;
	}
	public void setSpaceClicked(boolean spaceClicked)
	{
		this.spaceClicked = spaceClicked;
	}
	public boolean isEscapedClicked()
	{
		return escapedClicked;
	}
	public void setEscapedClicked(boolean escapedClicked)
	{
		this.escapedClicked = escapedClicked;
	}
}
