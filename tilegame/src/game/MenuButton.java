package game;


import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class MenuButton extends Button
{
	private Image buttonImage = null;
	private Image litButtonImage = null;
	private String text;
	public MenuButton(String str, int y) throws SlickException
	{
		this.text = str;
		this.y = y;
		scale = 2f;
		buttonImage = new Image("res/button.png");
		litButtonImage = new Image("res/button_lit.png");
		width = buttonImage.getWidth();
		height = buttonImage.getHeight();
		x = (int) ((Game.Width / 2 - buttonImage.getWidth()) * scale / 2);
	}
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
		super.update(gc, sbg, delta);
		
	}
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		if(entered)litButtonImage.draw(x, y, scale);
		else buttonImage.draw(x, y, scale);
		g.setFont(Game.smallPrint);
		g.setColor(Color.white);
		g.drawString(text, x + buttonImage.getWidth() * scale / 2 - Game.smallPrint.getWidth(text) / 2, y + buttonImage.getHeight() * scale / 2 - Game.smallPrint.getHeight() / 2);
	}
}
