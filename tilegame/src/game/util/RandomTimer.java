package game.util;

import game.object.GameObject;

public class RandomTimer extends Timer
{
	private int min, max;
	public RandomTimer(GameObject owner, boolean restartTimer, int min, int max)
	{
		super(restartTimer, min + (int)(Math.random() * ((max - min) + 1)));
		this.min = min;
		this.max = max;
	}
	public void restart()
	{
		time = min + (int)(Math.random() * ((max - min) + 1));
		super.restart();
		
	}
}
