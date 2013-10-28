package game.inventory;

import game.Game;
import game.inventory.Inventory.Dir;
import game.object.GameBoard;
import game.util.GameImage;
import game.world.World;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public abstract class Item
{
	protected Image img;
	protected GameImage gameimg = new GameImage();
	protected int count = 0, startCount;
	protected GameBoard board;
	protected Random rnd = new Random();
	public Item(GameBoard board, String path, int count) throws SlickException
	{
		img = gameimg.getImage(path).getScaledCopy(Game.Scale * 2f);
		startCount = count;
		this.board = board;
	}
	public abstract void update(GameContainer gc, StateBasedGame sbg, World world, int delta) throws SlickException;
	public abstract void fire(int x, int y) throws SlickException;
	public abstract void fire(int x, int y, Dir dir) throws SlickException;
	public int getCount()
	{
		return count;
	}
	public void setCount(int count)
	{
		this.count = count;
	}
	public Image getImg()
	{
		return img;
	}
	public void setImg(Image img)
	{
		this.img = img;
	}
	public void reset()
	{
		if(count < startCount)count = startCount;
		else count += startCount;
	}
}
