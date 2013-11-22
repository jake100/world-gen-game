package game.object.inventory;

import game.object.GameBoard;
import game.object.component.BoardRender;
import game.world.World;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Inventory extends BoardRender
{
	public static enum Dir{Left, Right, Up, Down, Not_Moving}
	private Item[] items;
	public Inventory(GameBoard owner) throws SlickException
	{
		super(owner);
		items = new Item[3];
		id = "Inventory";
		items[0] = new VillagerDrop(owner);
		items[0].setCount(10);
		items[1] = new NothingEquiped(owner);
		
		items[2] = new NothingEquiped(owner);
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
	}
	public int[] saveItem(int item) throws SlickException
	{
		int[] array = new int[2];
		array[0] = getId(item);
		array[1] = items[item].getCount();
		return array;
		
	}
	public int getId(int item) throws SlickException
	{
		if(items[item] instanceof VillagerDrop)return 0;
		if(items[item] instanceof InsectDrop)return 1;
		if(items[item] instanceof RockDrop)return 2;
		if(items[item] instanceof Laser)return 3;
		if(items[item] instanceof FireBomb)return 4;
		if(items[item] instanceof PoisonBomb)return 5;
		if(items[item] instanceof FireStorm)return 6;
		if(items[item] instanceof StopTime)return 7;
		if(items[item] instanceof NothingEquiped)return 8;
		return 8;
	}
	public void loadItem(int item, int id, int count) throws SlickException
	{
		if(id == 0)
		{
			items[item] = new VillagerDrop(board);
		}
		if(id == 1)
		{
			items[item] = new InsectDrop(board);
		}
		if(id == 2)
		{
			items[item] = new RockDrop(board);
		}
		if(id == 3)
		{
			items[item] = new Laser(board);
		}
		if(id == 4)
		{
			items[item] = new FireBomb(board);
		}
		if(id == 5)
		{
			items[item] = new PoisonBomb(board);
		}
		if(id == 6)
		{
			items[item] = new FireStorm(board);
		}
		if(id == 7)
		{
			items[item] = new StopTime(board);
		}
		if(id == 8)
		{
			items[item] = new NothingEquiped(board);
		}
		items[item].setCount(count);
	}
	public void update(GameContainer gc, StateBasedGame sbg, World world, int delta) throws SlickException
	{
		if(items[0] != null)items[0].update(gc, sbg, world, delta);
		if(items[1] != null)items[1].update(gc, sbg, world, delta);
	}
	public void firstFire(int x, int y) throws SlickException
	{
		if(items[0] != null)items[0].fire(x, y);
	}
	public void secondFire(int x, int y) throws SlickException
	{
		if(items[1] != null)items[1].fire(x, y);
	}
	public void dirFire(int x, int y, Dir dir) throws SlickException
	{
		if(items[2] != null)items[2].fire(x, y, dir);
	}
	public void tileUpdate() throws SlickException
	{
		
	}
	public void setFirstItem(Item item)
	{
		items[0] = item;
	}
	public void setSecondItem(Item item)
	{
		items[1] = item;
	}
	public Item[] getItems()
	{
		return items;
	}
	public boolean firstHasItem(Item checkItem)
	{
		if(items[0] == checkItem) return true;
		return false;
	}
	public boolean secondHasItem(Item checkItem)
	{
		if(items[1] == checkItem) return true;
		return false;
	}
	public void setItems(Item[] items)
	{
		this.items = items;
	}
	public void reset() throws SlickException
	{
		items[0] = new VillagerDrop(board);
		items[0].setCount(10);
		items[1] = new NothingEquiped(board);
		
		items[2] = new NothingEquiped(board);
	}
}
