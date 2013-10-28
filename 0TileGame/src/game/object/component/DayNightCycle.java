package game.object.component;

import game.Game;
import game.object.GameBoard;
import game.world.World;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class DayNightCycle extends BoardRender
{
	private double morning = .5;
	private double day = 1;
	private double time = day;
	private double step = .0001;
	private boolean reverse = false;
	public DayNightCycle(GameBoard board)
	{
		super(board);
	}
	public DayNightCycle(GameBoard board, double morning, double day, double time, double step)
	{
		super(board);
		this.morning = morning;
		this.day = day;
		this.time = time;
		this.step = step;
	}
	public void update(GameContainer gc, StateBasedGame sbg, World world, int delta) throws SlickException
    {
	}
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		float brightness = (float) time;
	    g.setColor(new Color(0f, 0f, 0f, 1f-brightness));
	    g.fillRect(0, 0, Game.TWidth * Game.ScaledTileSize, Game.THeight * Game.ScaledTileSize);
	}
	public void timeCycle()
	{
		if(!reverse)time+=step;
		else time-=step;
		if(time>day)
		{
			time = day;
			reverse=true;
		}
		if(time<morning)
		{
			time = morning;
			reverse=false;
		}
	}
	public void timeCycle(float step)
	{
		if(!reverse)time+=step;
		else time-=step;
		if(time>day)
		{
			time = day;
			reverse=true;
		}
		if(time<morning)
		{
			time = morning;
			reverse=false;
		}
	}
	public void tileUpdate()
	{
		timeCycle();
	}
}
