package game.object.component;

import game.object.GameBoard;
import game.object.Particle;
import game.world.World;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class ParticleGenerator extends BoardRender
{
	private GameContainer gc = null;
	private StateBasedGame sbg = null;
	private World world = null;
	private int delta;
	public ParticleGenerator(GameBoard board)
	{
		super(board);
	}
	protected ArrayList<Particle> particles = new ArrayList<Particle>();
	protected boolean isParticleGen = true;
	protected int maxParticles = 2000;
	public void update(GameContainer gc, StateBasedGame sbg, World world, int delta) throws SlickException
	{
		this.gc = gc;
		this.sbg = sbg;
		this.world = world;
		this.delta = delta;
	 	for(int i = 0; i < particles.size(); i++)
    	{
			particles.get(i).update(gc, sbg, world, delta);
    		if(!particles.get(i).isAlive())particles.remove(i);
    	}
	}
	public void tileUpdate() throws SlickException
	{
		
	}
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
    	if(isParticleGen)
    	{
    		for(int i = 0; i < particles.size(); i++)
            {
                particles.get(i).render(gc, g);
            }
    	}
	}
	public void addParticle(Particle particle)
	{
		if(isParticleGen)
		{
			if(particles.size() >= maxParticles)
			{
				particles.remove(0);
			}
			particles.add(particle);
		}
	}
	public boolean isParticleGen()
	{
		return isParticleGen;
	}
	public void setParticleGen(boolean isParticleGen)
	{
		this.isParticleGen = isParticleGen;
	}
	
}
