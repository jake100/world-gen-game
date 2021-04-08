package game;

import game.object.GameInfo;
import game.object.component.PlayerInput;
import game.object.inventory.Inventory;
import game.util.BasicImage;
import game.util.File;
import game.util.Keyboard;
import game.util.Util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFileChooser;


public class PauseMenu extends Button
{
	private BasicImage buttonImage = null;
	private String text = "Paused...";
	private MenuButton resumeButton, saveButton, quitButton;
	private boolean visible = false;
	private PlayerInput playerInput;
	private File file;
	private Inventory inventory;
	private GameInfo gameInfo;
	private int menuCount = 0;
	private ChooseFile chooseFile = new ChooseFile();
	public PauseMenu(File file, PlayerInput playerInput, Inventory inventory, GameInfo gameInfo)
	{
		this.file = file;
		buttonImage = Util.loadImage("res/pause_menu.png");
		x = Game.ScaledTileSize * 7;
		y = Game.ScaledTileSize * 4;
		width = Game.ScaledTileSize * 27;
		height = Game.ScaledTileSize * 27;
		resumeButton = new MenuButton("resume", y + Game.ScaledTileSize * 5);
		saveButton = new MenuButton("save", y + Game.ScaledTileSize * 10);
		quitButton = new MenuButton("quit", y + Game.ScaledTileSize * 15);
		this.playerInput = playerInput;
		this.inventory = inventory;
		this.gameInfo = gameInfo;
	}
	public void update(int delta)
	{
		super.update(delta);
//		Input input =  gc.getInput();
		Keyboard keyboard = new Keyboard();
		resumeButton.update(delta);
		saveButton.update(delta);
		quitButton.update(delta);
		int wait = 20;
		count = 0;
		if(playerInput != null && !visible && menuCount <= 0 && keyboard.isDown(KeyEvent.VK_ESCAPE))
		{
			visible = true;
			playerInput.setEnabled(false);
			menuCount = wait;
		}
		else if(visible && menuCount <= 0 && keyboard.isDown(KeyEvent.VK_ESCAPE))
		{
			visible = false;
			playerInput.setEnabled(true);
			menuCount = wait;
		}
		else
		{
			menuCount--;
		}
		if(visible)
		{
			if(resumeButton.isClicked())
			{
				visible = false;
				playerInput.setEnabled(true);
			}
			if(saveButton.isClicked())
			{
				int val = chooseFile.showSaveDialog(null);
				if (val == JFileChooser.APPROVE_OPTION) {
					   java.io.File otherFile = chooseFile.getSelectedFile();
					   file.save(otherFile.toString(), inventory, gameInfo);
					}
			}
			if(quitButton.isClicked())
			{
				 System.exit(0);
			}
		}
	}
	public void render(Graphics g)
	{
		if(visible)
		{
			buttonImage.draw(x, y, scale);
			resumeButton.render(g);
			saveButton.render(g);
			quitButton.render(g);
//			g.setFont(Game.smallPrint);
//			int textWidth = Game.smallPrint.getWidth(text);
//			g.setColor(Color.white);
//			g.setFont(Game.gameFont);
//			g.drawString(text, x + width / 2 - Game.gameFont.getWidth(text) / 2, y + Game.ScaledTileSize);
		}
	}
	
}
