package game.object.inventory;

import game.object.GameBoard;
import game.object.inventory.Inventory.Dir;
import game.util.GameImage;
import game.world.World;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public abstract class Item
{
	protected BufferedImage img;
	protected GameImage gameimg = new GameImage();
	protected int count = 0, startCount;
	protected GameBoard board;
	protected Random rnd = new Random();
	public Item(GameBoard board, String path, int count) throws IOException
	{
		// read image from folder
        File folderInput = new File(path);
        img = ImageIO.read(folderInput);

        
		startCount = count;
		this.board = board;
	}
	public abstract void update(World world, int delta);
	public abstract void fire(int x, int y);
	public abstract void fire(int x, int y, Dir dir);
	public int getCount()
	{
		return count;
	}
	public void setCount(int count)
	{
		this.count = count;
	}
	public BufferedImage getImg()
	{
		return img;
	}
	public void setImg(BufferedImage img)
	{
		this.img = img;
	}
	public void reset()
	{
		if(count < startCount)count = startCount;
		else count += startCount;
	}
}
