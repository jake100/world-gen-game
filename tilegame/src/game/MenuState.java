package game;


import game.util.GameImage;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MenuState extends BasicGameState
{
	public static boolean load = false;
	private MenuButton playButton, loadButton, settingsButton, quitButton;
	private GameImage gameimg;
	private Image img;
	public MenuState(int id)
	{
	
	}
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		playButton = new MenuButton("New Game", 20);
		loadButton = new MenuButton("Load Game", 90);
		settingsButton = new MenuButton("Settings", 160);
		quitButton = new MenuButton("Quit Game", 230);
		gameimg = new GameImage();
		img = gameimg.getImage("res/18_tile.png").getScaledCopy(Game.Scale * 5);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
		playButton.update(gc, sbg, delta);
		loadButton.update(gc, sbg, delta);
		settingsButton.update(gc, sbg, delta);
		quitButton.update(gc, sbg, delta);
		if(playButton.isClicked()) 
		{
			load = false;
			sbg.enterState(2);
		}
		if(loadButton.isClicked())
		{
			load = true;
			sbg.enterState(2);
		}
		if(quitButton.isClicked()) System.exit(0);
	}
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		playButton.render(gc, sbg, g);
		loadButton.render(gc, sbg, g);
		settingsButton.render(gc, sbg, g);
		quitButton.render(gc, sbg, g);
		img.draw(Game.Width / 2 - Game.ScaledTileSize * 5 / 2, Game.Height / 2 + Game.gameFont.getHeight() * 6f + Game.ScaledTileSize / 2);
		g.setFont(Game.gameFont);
		g.setColor(Color.white);
		String str0 = "F: Default attack.";
		g.drawString(str0, Game.Width / 2 - Game.gameFont.getWidth(str0) / 2, Game.Height / 2);
		String str1 = "L Click: Primary attack.";
		g.drawString(str1, Game.Width / 2 - Game.gameFont.getWidth(str1) / 2, Game.Height / 2 + Game.gameFont.getHeight() * 1.5f);
		String str2 = "R Click: Secondary attack.";
		g.drawString(str2, Game.Width / 2 - Game.gameFont.getWidth(str2) / 2, Game.Height / 2 + Game.gameFont.getHeight() * 3f);
		String str3 = "Escape: Pause Game.";
		g.drawString(str3, Game.Width / 2 - Game.gameFont.getWidth(str3) / 2, Game.Height / 2 + Game.gameFont.getHeight() * 4.5f);
		g.setColor(Color.red);
	}
	public int getID() {return 1;}

}
