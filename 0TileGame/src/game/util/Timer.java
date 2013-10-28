package game.util;

import game.GameClass;
import game.world.World;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Timer extends GameClass
{
	protected int time, counter;
	protected boolean over = false, restartTimer;
	public Timer(boolean restartTimer, int time)
	{
		this.time = time;
		this.restartTimer = restartTimer;
		counter = time;
	}
	public void update(GameContainer gc, StateBasedGame sbg, World world, int delta) throws SlickException
	{
		if(restartTimer && over)restart();
		counter -= delta;
		if(counter <= 0)over = true;
	}
	public void restart()
	{
		over = false;
		counter = time;
	}
	public boolean isOver()
	{
		return over;
	}
}
