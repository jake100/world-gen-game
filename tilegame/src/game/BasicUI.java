package game;

import game.util.MouseInput;

public abstract class BasicUI
{
	protected int x, y, width, height, count = 50, wait = 25;
	protected float scale;
	protected boolean entered = false, enabled = true;
	MouseInput mi = new MouseInput();
	public void update(double delta)
	{
		entered = false;
		if(enabled && count <= 0)
		{
			int mx = mi.x;
			int my = mi.y;
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
