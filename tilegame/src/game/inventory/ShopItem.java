package game.inventory;

import game.GameClass;
import game.ShopButton;
import game.object.GameBoard;
import game.world.World;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class ShopItem extends GameClass
{
	private ShopButton shopButton;
	private Item item;
	private Inventory inventory;
	private GameBoard board;
	private int count = 0, value;
	public ShopItem(int value, GameBoard board, Inventory inventory, ShopButton shopButton, Item item)
	{
		this.shopButton = shopButton;
		this.item = item;
		this.inventory = inventory;
		this.board = board;
		this.value = value;
	}
	public void update(GameContainer gc, StateBasedGame sbg, World world, int delta) throws SlickException
	{
		shopButton.update(gc, sbg, delta);
		int dollars = board.getDollars();
		if(shopButton.isClicked() && !inventory.secondHasItem(item) && count <= 0 && dollars >= value)
		{
			if(inventory.getItems()[0] != item)inventory.setFirstItem(item);
			item.reset();
			count = 200;
			dollars -= value;
			board.setDollars(dollars);
		}
		if(shopButton.isAltClicked() && !inventory.firstHasItem(item) && count <= 0 && dollars >= value)
		{
			if(inventory.getItems()[1] != item)inventory.setSecondItem(item);
			item.reset();
			count = 200;
			dollars -= value;
			board.setDollars(dollars);
		}
		count -= delta;
	}
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		shopButton.render(gc, sbg, g);
	}
}
