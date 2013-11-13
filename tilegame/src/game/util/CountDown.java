package game.util;

import game.GameClass;

public class CountDown extends GameClass
{
	private int count;
	private boolean over;
	public CountDown(int count)
	{
		this.count = count;
	}
	public void decrement()
	{
		count--;
		if(count <= 0)over = true;
	}
}
