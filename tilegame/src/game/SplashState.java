package game;


import game.world.World;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class SplashState extends BasicGameState
{
	private World world = null;
	public SplashState(int id) throws SlickException
	{
		
	}
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		
	}
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException 
	{
		System.out.println("splash");
		if(world == null)
		{
			try
			{
				world = new World(false);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try
		{
			world.update(gc, sbg, delta);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!world.isAlive())sbg.enterState(1);
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		g.setAntiAlias(true);
		if(world != null)world.render(gc, sbg, g);
	}

	public int getID() {return 0;}

}
