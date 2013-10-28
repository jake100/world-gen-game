package game.object.component;

import game.Game;
import game.ShopButton;
import game.inventory.CanonPlacer;
import game.inventory.FireBomb;
import game.inventory.FireStorm;
import game.inventory.InsectDrop;
import game.inventory.Inventory;
import game.inventory.Item;
import game.inventory.Laser;
import game.inventory.PoisonBomb;
import game.inventory.RockDrop;
import game.inventory.ShopItem;
import game.inventory.StopTime;
import game.inventory.VillagerDrop;
import game.object.GameBoard;
import game.util.GameImage;
import game.world.World;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Shop extends BoardRender
{
	private Image iconBack, leftTile, midTile, rightTile;
	private int width = 18, height = 2;
	private Inventory inventory;
	private int firstItemX = Game.Width - Game.ScaledTileSize * 7, firstItemY = Game.Height - Game.ScaledTileSize * 7;
	private int secondItemX = Game.Width - Game.ScaledTileSize * 9, secondItemY = Game.Height - Game.ScaledTileSize * 7;
	private ShopItem[] shopItems;
	private int saleItemX = Game.ScaledTileSize, saleItemY = Game.Height - Game.ScaledTileSize * 7;
	public Shop(GameBoard board, Inventory inventory) throws SlickException
	{
		super(board);
		leftTile = new GameImage().getImage("res/left_tile.png").getScaledCopy(Game.Scale);
		midTile = new GameImage().getImage("res/mid_tile.png").getScaledCopy(Game.Scale);
		rightTile = new GameImage().getImage("res/right_tile.png").getScaledCopy(Game.Scale);
		iconBack = new GameImage().getImage("res/icon_back.png").getScaledCopy(Game.Scale * 2);
		this.inventory = inventory;
		shopItems = new ShopItem[9];
		int stopTimeCost = 1000, fireStormCost = 300, poisonCost = 100, fireCost = 100, laserCost = 50, villagerCost = 50, stoneCost = 25, insectCost = 25, canonCost = 25;
		shopItems[0] = new ShopItem(insectCost, board, inventory, new ShopButton(insectCost, saleItemX, saleItemY, "res/insect_icon.png", "res/lit_insect_icon.png"), new InsectDrop(board));
		shopItems[1] = new ShopItem(stoneCost, board, inventory, new ShopButton(stoneCost, saleItemX + Game.ScaledTileSize * 3, saleItemY, "res/obsidian_tile.png", "res/lit_obsidian_tile.png"), new RockDrop(board));
		shopItems[2] = new ShopItem(canonCost, board, inventory, new ShopButton(canonCost, saleItemX + Game.ScaledTileSize * 6, saleItemY, "res/canon_icon.png", "res/lit_canon_icon.png"), new CanonPlacer(board));
		shopItems[3] = new ShopItem(villagerCost, board, inventory, new ShopButton(villagerCost, saleItemX + Game.ScaledTileSize * 9, saleItemY, "res/villager_icon.png", "res/lit_villager_icon.png"), new VillagerDrop(board));
		shopItems[4] = new ShopItem(laserCost, board, inventory, new ShopButton(laserCost, saleItemX + Game.ScaledTileSize * 12, saleItemY, "res/laser_icon.png", "res/lit_laser_icon.png"), new Laser(board));
		shopItems[5] = new ShopItem(fireCost, board, inventory, new ShopButton(fireCost, saleItemX + Game.ScaledTileSize * 15, saleItemY, "res/bomb_icon.png", "res/lit_bomb_icon.png"), new FireBomb(board));
		shopItems[6] = new ShopItem(poisonCost, board, inventory, new ShopButton(poisonCost, saleItemX + Game.ScaledTileSize * 18, saleItemY, "res/poison_icon.png", "res/lit_poison_icon.png"), new PoisonBomb(board));
		shopItems[7] = new ShopItem(fireStormCost, board, inventory, new ShopButton(fireStormCost, saleItemX + Game.ScaledTileSize * 21, saleItemY, "res/fire_storm_icon.png", "res/lit_fire_storm_icon.png"), new FireStorm(board));
		shopItems[8] = new ShopItem(stopTimeCost, board, inventory, new ShopButton(stopTimeCost, saleItemX + Game.ScaledTileSize * 24, saleItemY, "res/stop_time_icon.png", "res/lit_stop_time_icon.png"), new StopTime(board));
	}
	public void update(GameContainer gc, StateBasedGame sbg, World world, int delta) throws SlickException
	{
		for(ShopItem shopItem : shopItems)
		{
			shopItem.update(gc, sbg, world, delta);
		}
	}
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		g.drawImage(leftTile, 0, Game.Height - Game.ScaledTileSize * 8);
		g.drawImage(rightTile, Game.Width - Game.ScaledTileSize, Game.Height - Game.ScaledTileSize * 8);
		for(int x = 1; x < Game.TWidth - 1; x++)
		{
			g.drawImage(midTile, x * Game.ScaledTileSize, Game.Height - Game.ScaledTileSize * 8);
		}
		Item[] items = inventory.getItems();
		g.drawImage(iconBack, firstItemX, firstItemY);
		g.drawImage(items[1].getImg(), firstItemX, firstItemY);
		g.setColor(Color.black);
		g.setFont(Game.smallPrint);
		String str = ""+items[1].getCount();
		int width = Game.smallPrint.getWidth(str);
		int height = Game.smallPrint.getHeight(str);
		g.drawString(str, firstItemX + (int)(Game.ScaledTileSize - width / 2.0), secondItemY + (int)(Game.ScaledTileSize - height / 2.0));
		for(ShopItem shopItem : shopItems)
		{
			shopItem.render(gc, sbg, g);
		}
		g.drawImage(iconBack, secondItemX, secondItemY);
		g.drawImage(items[0].getImg(), secondItemX, secondItemY);
		g.setColor(Color.black);
		g.setFont(Game.smallPrint);
		str = ""+items[0].getCount();
		width = Game.smallPrint.getWidth(str);
		height = Game.smallPrint.getHeight(str);
		g.drawString(str, secondItemX + (int)(Game.ScaledTileSize - width / 2.0), secondItemY + (int)(Game.ScaledTileSize - height / 2.0));
	}

	public int getFirstItemX()
	{
		return firstItemX;
	}

	public void setFirstItemX(int firstItemX)
	{
		this.firstItemX = firstItemX;
	}

	public int getFirstItemY()
	{
		return firstItemY;
	}

	public void setFirstItemY(int firstItemY)
	{
		this.firstItemY = firstItemY;
	}

	public int getSecondItemX()
	{
		return secondItemX;
	}

	public void setSecondItemX(int secondItemX)
	{
		this.secondItemX = secondItemX;
	}

	public int getSecondItemY()
	{
		return secondItemY;
	}

	public void setSecondItemY(int secondItemY)
	{
		this.secondItemY = secondItemY;
	}

	public int getWidth()
	{
		return width;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public int getHeight()
	{
		return height;
	}
	public void setHeight(int height)
	{
		this.height = height;
	}
	public void tileUpdate() throws SlickException
	{
	}
	
}

