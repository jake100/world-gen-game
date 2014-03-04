package game;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
/*
 * menu that is displayed when the level ends.
 * the gameBoard's turnUpdate method is being called faster than the user can advance turns.
 * the player has to press space to advance to the next level.
 */
public class ContinueMenu extends Button
{
	private Image buttonImage = null;
	private String[] text;
	public ContinueMenu(String[] text) throws SlickException
	{
		this.text = text;
		buttonImage = new Image("res/continue_menu.png");
		x = Game.ScaledTileSize * 7;
		y = Game.ScaledTileSize * 4;
		width = Game.ScaledTileSize * 27;
		height = Game.ScaledTileSize * 27;
	}
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		buttonImage.draw(x, y);
		g.setFont(Game.smallPrint);
		int textHeight = Game.smallPrint.getHeight();
		g.setColor(Color.white);
		for(int i = 0; i < text.length;i++)
		{
			int textWidth = Game.smallPrint.getWidth(text[i]);
			g.drawString(text[i], x + width / 2 - textWidth / 2, y + height / 2 - textHeight * text.length + textHeight * 2 * i);
		}
		
	}
}
