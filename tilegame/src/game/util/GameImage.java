package game.util;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class GameImage
{
	/*
	 * makes scaled images less blurry
	 */
	public Image getImage(String path) throws SlickException
	{
		Image temp = null;
		if(path != null)
		{
			temp = new Image(path);
			temp.setFilter(Image.FILTER_NEAREST);
		}
		return temp;
	}
}
