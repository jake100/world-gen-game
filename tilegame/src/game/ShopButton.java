package game;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
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
	public ShopButton(int value, int x, int y, String buttonPath, String litButtonPath) throws SlickException
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
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		backButton.draw(x, y, scale);
		if(entered)
		{
			litButtonImage.draw(x, y, scale);
			g.setFont(Game.smallPrint);
			g.setColor(Color.black);
			String str = "$" + value;
			int textWidth = Game.smallPrint.getWidth(str);
			int textHeight = Game.smallPrint.getHeight(str);
			g.drawString(str, x + width * scale / 2 - textWidth / 2, y + height * scale / 2 - textHeight / 2);
		}
		else buttonImage.draw(x, y, scale);
	}
}
