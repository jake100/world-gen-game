package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

/*
 * menu that is displayed when the level ends.
 * the gameBoard's turnUpdate method is being called faster than the user can advance turns.
 * the player has to press space to advance to the next level.
 */
public class ContinueMenu extends Button
{
	private Image buttonImage;
	private String[] text;
	public ContinueMenu(String[] text)
	{
		this.text = text;
		x = Game.ScaledTileSize * 7;
		y = Game.ScaledTileSize * 4;
		width = Game.ScaledTileSize * 27;
		height = Game.ScaledTileSize * 27;
	}
	public void render(Graphics g)
	{

		g.setColor(Color.white);
		for(int i = 0; i < text.length;i++)
		{

			g.drawString(text[i], x + width / 2, y + height / 2  *text.length * 2 * i);
		}
		
	}
}
