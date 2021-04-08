package game;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import game.util.BasicImage;
import game.util.Util;

public class MenuButton extends Button implements ImageObserver
{
	private BasicImage buttonImage = new BasicImage();
	private BasicImage litButtonImage = new BasicImage();
	private String text;
	
	public MenuButton(String str, int y)
	{
		this.text = str;
		this.y = y;
		scale = 2f;
		buttonImage = Util.loadImage("res/button.png");
		litButtonImage = Util.loadImage("res/button_lit.png");
		width = buttonImage.width;
		height = buttonImage.height;
		x = (int) ((Game.Width / 2 - buttonImage.getWidth(null)) * scale / 2);
	}
	public void update(double delta)
	{
		super.update(delta);
	}
	public void render(Graphics g)
	{
	
		
//		if(entered)g.drawImage(litButtonImage, x, y, null);
//		else g.drawImage(buttonImage.img, x, y, null);
//		g.setColor(Color.white);
//		g.drawString(text, (int)(x + buttonImage.width * scale / 2), (int)(y * scale / 2));
//		
		if(entered)g.drawImage(litButtonImage, x, y, null);
		else g.drawImage(buttonImage, x, y, null);
		int width = g.getFontMetrics().stringWidth(text);
		g.setColor(Color.white);
		g.drawString(text, (int)(x + buttonImage.width * scale / 2 - width / 2), (int)(y + buttonImage.height * scale / 2 - width / 2));
	}
	@Override
	public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		// TODO Auto-generated method stub
		return false;
	}
}
