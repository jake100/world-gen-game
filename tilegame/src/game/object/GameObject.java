package game.object;

import game.GameClass;
import game.world.World;

import java.util.ArrayList;
import game.util.Vector2f;
/*
 * Owns a list of game components that can be added, removed, searched for, turned off or on, swapped and are updated when this class is.
 */
public abstract class GameObject<Vector2f> extends GameClass
{
	protected String id;
	protected Vector2f pos = Vector2f();
	protected float scale;
	protected ArrayList<GameClass> components = null;
	protected GameInfo gameInfo;
	public GameObject(GameInfo gameInfo)
	{
		this.gameInfo = gameInfo;
		components = new ArrayList<GameClass>();
		
	}
    public void addComponent(GameClass component)
    {
        component.setOwner(this);
        components.add(component);
    }
 
    public GameClass getComponent(String id)
    {
        for(GameClass comp : components)
        {
        	if (comp.getId().equalsIgnoreCase(id))return comp;
        }
	return null;
    }
    public void update(World world, int delta)
    {
        for(GameClass component : components)
        {
            if(component.getOwner() != null)component.update(world, delta);
        }
    }
	public ArrayList<GameClass> getComponents(){return components;}
	public void setComponents(ArrayList<GameClass> components){this.components = components;}
	public game.util.Vector2f getPos(){return pos;}
	public void setPos(game.util.Vector2f pos){this.pos = pos;}
	public float getScale(){return scale;}
	public void setScale(float scale){this.scale = scale;}
	
}
