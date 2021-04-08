package game;


import game.util.BasicImage;
import game.util.GameImage;
import game.util.Util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class MenuState
{
	public static boolean load = false;
	private MenuButton playButton, loadButton, settingsButton, quitButton;
	private GameImage gameimg;
	private BasicImage img;
	public MenuState(int id)
	{
		
	}
	public void init() 
	{
		playButton = new MenuButton("New Game", 20);
		loadButton = new MenuButton("Load Game", 90);
		settingsButton = new MenuButton("Settings", 160);
		quitButton = new MenuButton("Quit Game", 230);
		gameimg = new GameImage();
		img = Util.loadImage("res/18_tile.png");
	}

	public void update(double delta)
	{
		playButton.update(delta);
		loadButton.update(delta);
		settingsButton.update(delta);
		quitButton.update(delta);
		if(playButton.isClicked()) 
		{
			load = false;
//			sbg.enterState(2);
		}
		if(loadButton.isClicked())
		{
			load = true;
//			sbg.enterState(2);
		}
		if(quitButton.isClicked()) System.exit(0);
	}
	public void render(Graphics g)
	{
		playButton.render(g);
		loadButton.render(g);
		settingsButton.render(g);
		quitButton.render(g);

//		g.drawImage(img, Game.Width / 2 - Game.ScaledTileSize * 5 / 2, Game.Height / 2 * 6f + Game.ScaledTileSize / 2);

		g.setColor(Color.white);
		String str0 = "F: Default attack.";
		g.drawString(str0, Game.Width / 2, Game.Height / 2);
		String str1 = "L Click: Primary attack.";
		g.drawString(str1, (int)(Game.Width / 2), (int)(Game.Height / 2 * 1.5f));
		String str2 = "R Click: Secondary attack.";
		g.drawString(str2, (int)(Game.Width  / 2), (int)(Game.Height / 2 * 3f));
		String str3 = "Escape: Pause Game.";
		g.drawString(str3, (int)(Game.Width / 2), (int)(Game.Height / 2 * 4.5f));
		g.setColor(Color.red);
	}
	public int getID() {return 1;}

}
