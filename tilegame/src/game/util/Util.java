package game.util;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;


public class Util {

    private static HashMap<String, BasicImage> cache = new HashMap<String, BasicImage>();

    public static BasicImage loadImage(String path) {
    	BasicImage image = new BasicImage();

		if (cache.containsKey(path)) {
			return cache.get(path);
		}

		try {
			image.img = ImageIO.read(new File(path));

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
