package game;


import game.world.World;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class SplashState
{
	private World world = null;
	private GameState gs = new GameState();
	public SplashState(int id)
	{
		
	}
	public void init()
	{
		
	}
	public void update(int delta) 
	{
		System.out.println("splash");
		if(world == null)
		{
			world = new World(false);
		}
		try
		{
			world.update(delta);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!world.isAlive())gs.enterState(1);
	}

	public void render(Graphics g) 
	{
		g.setAntiAlias(true);
		if(world != null)world.render(g);
	}

	public int getID() {return 0;}

}
