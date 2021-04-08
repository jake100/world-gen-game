package game.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;


public class BasicImage extends Image{


	protected boolean inited = false;
    protected float centerX; 
    /** The y coordinate of the centre of rotation */
    protected float centerY; 
    
	protected Texture texture;
	/** The width of the image */
	public int width;
	/** The height of the image */
	public int height;
	
	protected float angle;
	
	public Image img;
	
	protected float textureWidth;
	/** The texture coordinate height to use to find our image */
	protected float textureHeight;
	/** The x texture offset to use to find our image */
	protected float textureOffsetX;
	/** The y texture offset to use to find our image */
	protected float textureOffsetY;
	
	protected String ref;
	protected float alpha = 1.0f;
	
	protected final void init() {
		if (inited) {
			return;
		}
		inited = true;
		if (texture != null) {
			width = texture.getImageWidth();
			height = texture.getImageHeight();
			textureOffsetX = 0;
			textureOffsetY = 0;
			textureWidth = texture.getWidth();
			textureHeight = texture.getHeight();
		}
		

		
	
		centerX = width / 2;
		centerY = height / 2;
	}
	
	public BasicImage copy() {
		init();
		return getSubImage(0,0,width,height);
	}

	/**
	 * Get a scaled copy of this image with a uniform scale
	 * 
	 * @param scale The scale to apply
	 * @return The new scaled image
	 */
	public BasicImage getScaledCopy(float scale) {
		init();
		return getScaledCopy((int) (width*scale),(int) (height*scale));
	}
	
	/**
	 * Get a scaled copy of this image
	 * 
	 * @param width The width of the copy
	 * @param height The height of the copy
	 * @return The new scaled image
	 */
	public BasicImage getScaledCopy(int width, int height) {
		init();
		BasicImage image = copy();
		image.width = width;
		image.height = height;
		image.centerX = width / 2;
		image.centerY = height / 2;
		return image;
	}
	public BasicImage getSubImage(int x,int y,int width,int height) {
		init();
		
		float newTextureOffsetX = ((x / (float) this.width) * textureWidth) + textureOffsetX;
		float newTextureOffsetY = ((y / (float) this.height) * textureHeight) + textureOffsetY;
		float newTextureWidth = ((width / (float) this.width) * textureWidth);
		float newTextureHeight = ((height / (float) this.height) * textureHeight);
		
		BasicImage sub = new BasicImage();
		sub.inited = true;
		sub.texture = this.texture;
		sub.textureOffsetX = newTextureOffsetX;
		sub.textureOffsetY = newTextureOffsetY;
		sub.textureWidth = newTextureWidth;
		sub.textureHeight = newTextureHeight;
		
		sub.width = width;
		sub.height = height;
		sub.ref = ref;
		sub.centerX = width / 2;
		sub.centerY = height / 2;
		
		return sub;
	}
	public void draw(float x,float y,float scale) {
		init();
		draw(x,y,width*scale,height*scale);
	}
	public void draw(float x,float y,float scale,Color filter) {
		init();
		draw(x,y,width*scale,height*scale,filter);
	}
	public void draw(float x,float y,float width,float height) {
		init();
		draw(x,y,width,height,Color.white);
	}
	 public void draw(float x,float y,float width,float height, Color filter) { 
	    Graphics g =this.getGraphics();
	       
	     g.drawImage(img, (int)x, (int)y, (int)width, (int)height, this);
	
	 }
	public Graphics getGraphics() {
		// TODO Auto-generated method stub
		return this.getGraphics();
	}

	@Override
	public int getHeight(ImageObserver observer) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getProperty(String name, ImageObserver observer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImageProducer getSource() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getWidth(ImageObserver observer) {
		// TODO Auto-generated method stub
		return 0;
	}
}
