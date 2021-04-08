package game;

import game.util.MouseInput;

public abstract class BasicButton extends BasicUI
{
	protected boolean clicked = false, altClicked = false;
	public void update(double delta)
	{
		super.update(delta);
		MouseInput mi = new MouseInput();
		if(entered && mi.clicked)
		{
			clicked = true;
			count = wait;
		}
		if(entered && mi.rightClicked)
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
