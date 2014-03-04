package game;


import java.awt.Font;
import java.io.InputStream;
import java.util.Random;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

public class Game extends StateBasedGame 
{
	public static final int Scale = 1, TileSize = 15, ScaledTileSize = (int)(TileSize * Scale);
	public static final int TWidth = 75, THeight = 55;
	public static final int Width = ScaledTileSize * TWidth, Height = ScaledTileSize * THeight + ScaledTileSize * 8;
	public static final int Splash = 0, MENU = 1, PLAY = 2;
	public static final String Title = "Tile Infection"; //temporary name
	public static TrueTypeFont gameFont, smallPrint; // 2 different sizes of the same font
	public static GameContainer gc;
	public Game() throws SlickException
	{
		super(Title);
		
		this.addState(new MenuState(MENU));
		this.addState(new PlayState(PLAY));
	}
	public void initStatesList(GameContainer gc) throws SlickException 
	{
		gc.setShowFPS(false);
		getState(MENU).init(gc, this);
		getState(PLAY).init(gc, this);
		this.gc = gc;
		gc.setMouseCursor("res/cursor.png",0,0);
		try 
		{
			InputStream inputStream	= ResourceLoader.getResourceAsStream("res/Minecraftia.ttf");
	 
			Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			awtFont2 = awtFont2.deriveFont(22f);
			gameFont = new TrueTypeFont(awtFont2, false);
			awtFont2 = awtFont2.deriveFont(12f);
			smallPrint = new TrueTypeFont(awtFont2, false);
	 
		}
		catch (Exception e) {e.printStackTrace();}	
		enterState(MENU);
	}
	public static int rndX()
	{
		return new Random().nextInt(TWidth);
	}
	public static int rndY()
	{
		return new Random().nextInt(THeight);
	}
	public static void main(String args[]) throws SlickException
	{
		 AppGameContainer app = new AppGameContainer(new Game());
		 app.setDisplayMode(Width, Height, false);
		 app.start();
	}

}
