package game;

import game.world.World;

import java.util.Random;
public abstract class GameClass
{
	protected GameClass owner;
	protected String id = "null";
	protected boolean enabled = true, alive = true;
	protected Random rnd = new Random();
	public String getId(){return id;}
	public void setId(String id){this.id = id;}
	public boolean isEnabled(){return enabled;}
	public void setEnabled(boolean enabled){this.enabled = enabled;}
	public boolean isAlive(){return alive;}
	public void setAlive(boolean alive){this.alive = alive;}
	public GameClass getOwner(){return owner;}
	public void setOwner(GameClass owner){this.owner = owner;}
	
	public void update(World world, int delta){}
	public void deathUpdate(World world){}
}
