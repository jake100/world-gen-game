package game;

import java.awt.Graphics;

import game.world.World;


public class PlayState
{
	private World world = null;
	public PlayState(int id)
	{

	}
	public void init() 
	{
	}
	public void update(int delta) throws InterruptedException
	{
		if(world == null)
		{
			world = new World(MenuState.load);
		}
		try
		{
			world.update(delta);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	public void render(Graphics g)
	{
//		g.setAntiAlias(true);
		if(world != null)
		{
			world.render(g);
		}
	}
	public int getID() {return 2;}
}
