package game.util;

import game.world.World;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class TwoStageTimer extends Timer
{
	private int firstTime, secondTime;
	private boolean isFirst = true, secondOver = false;
	public TwoStageTimer(boolean restartTimer, int firstTime, int secondTime)
	{
		super(restartTimer, firstTime);
		this.firstTime = firstTime;
		this.secondTime = secondTime;
		over = true;
	}
	public void update(GameContainer gc, StateBasedGame sbg, World world, int delta) throws SlickException
	{
		if(restartTimer && over || secondOver)restart();
		counter -= delta;
		if(counter <= 0)
		{
			if(isFirst)over = true;
			else secondOver = true;
		}
	}
	public void restart()
	{
		if(isFirst)
		{
			secondOver = false;
			isFirst = false;
			time = secondTime;
		}
		else
		{
			over = false;
			isFirst = true;
			time = firstTime;
		}
		counter = time;
	}
	public boolean isSecondOver()
	{
		return secondOver;
	}

}
