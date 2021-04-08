package game;

import java.awt.Color;
import java.awt.Graphics;

import game.util.Image;

/**
 * 
 * buttons used to buy things from the shop, gets 2 types of images from the item being sold, also displays the cost of the item being sold.
 *
 */
public class ShopButton extends Button
{
	private Image buttonImage = null;
	private Image litButtonImage = null;
	private Image backButton = null;
	private int value;
	public ShopButton(int value, int x, int y, String buttonPath, String litButtonPath)
	{
		this.x = x;
		this.value = value;
		this.y = y;
		this.buttonImage = new Image(buttonPath);
		this.litButtonImage = new Image(litButtonPath);
		scale = Game.Scale * 2;
		width = buttonImage.getWidth();
		height = buttonImage.getHeight();
		backButton = new Image("res/shop_button.png");
	}
	public void render(Graphics g)
	{
		backButton.draw(x, y, scale);
		if(entered)
		{
			litButtonImage.draw(x, y, scale);
			

			g.setColor(Color.black);
			String str = "$" + value;
			int width = g.getFontMetrics().stringWidth(str);
			int height = 10;
			g.drawString(str, (x + width * scale / 2 - width / 2 + "", y + height * scale / 2 - height / 2) );
		}
		else buttonImage.draw(x, y, scale);
	}
}
