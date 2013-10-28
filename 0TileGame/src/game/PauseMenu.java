package game;

import game.inventory.Inventory;
import game.object.GameInfo;
import game.object.component.PlayerInput;
import game.util.File;

import javax.swing.JFileChooser;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class PauseMenu extends Button
{
	private Image buttonImage = null;
	private String text = "Paused...";
	private MenuButton resumeButton, saveButton, quitButton;
	private boolean visible = false;
	private PlayerInput playerInput;
	private File file;
	private Inventory inventory;
	private GameInfo gameInfo;
	private int menuCount = 0;
	private ChooseFile chooseFile = new ChooseFile();
	public PauseMenu(File file, PlayerInput playerInput, Inventory inventory, GameInfo gameInfo) throws SlickException
	{
		this.file = file;
		buttonImage = new Image("res/pause_menu.png");
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
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
		super.update(gc, sbg, delta);
		resumeButton.update(gc, sbg, delta);
		saveButton.update(gc, sbg, delta);
		quitButton.update(gc, sbg, delta);
		int wait = 20;
		count = 0;
		if(playerInput != null && !visible && menuCount <= 0 && escapedClicked)
		{
			visible = true;
			playerInput.setEnabled(false);
			menuCount = wait;
		}
		else if(visible && menuCount <= 0 && escapedClicked)
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
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		if(visible)
		{
			buttonImage.draw(x, y);
			resumeButton.render(gc, sbg, g);
			saveButton.render(gc, sbg, g);
			quitButton.render(gc, sbg, g);
			g.setFont(Game.smallPrint);
			int textWidth = Game.smallPrint.getWidth(text);
			g.setColor(Color.white);
			g.setFont(Game.gameFont);
			g.drawString(text, x + width / 2 - Game.gameFont.getWidth(text) / 2, y + Game.ScaledTileSize);
		}
	}
	
}
