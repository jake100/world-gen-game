package game;


import game.util.Keyboard;

public abstract class Button extends BasicButton
{
	public void update(double delta)
	{
		super.update(delta);
		if(count <= 0)
		{

		}
	}
	public boolean getInput(Keyboard input, int key)
	{
		if(input.isDown(key))
		{
			count = wait;
			return true;
		}
		return false;
	}
}
