package game.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;


public class GameImage
{
	/*
	 * makes scaled images less blurry
	 */
    private static HashMap<String, BufferedImage> cache = new HashMap<String, BufferedImage>();
    public static BufferedImage getImage (String path) {
        BufferedImage image = null;

		if (cache.containsKey(path)) {
			return cache.get(path);
		}

		try {
			image = ImageIO.read(new File(path));

			if (!cache.containsKey(path)) {
				cache.put(path, image);
			}
		} 
		catch (IOException e) {
		    e.printStackTrace();
        }

		return image;
	}
}
