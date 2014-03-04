package game.object.component;

import game.Game;
import game.object.GameBoard;
import game.object.GameInfo;
import game.util.GameImage;
import game.world.World;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
/*
 * Renders information taken from gameInfo and the game board on three types of tiles, uses the game font defined in the game class.
 */
public class Hud extends BoardRender
{
	private Image leftTile, midTile, rightTile;
	private GameInfo gameInfo = null;
	public Hud(GameBoard board, GameInfo gameInfo) throws SlickException
	{
		super(board);
		this.gameInfo = gameInfo;
		leftTile = new GameImage().getImage("res/left_tile.png").getScaledCopy(Game.Scale);
		midTile = new GameImage().getImage("res/mid_tile.png").getScaledCopy(Game.Scale);
		rightTile = new GameImage().getImage("res/right_tile.png").getScaledCopy(Game.Scale);
	}
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		g.drawImage(leftTile, 0, Game.Height - Game.ScaledTileSize * 4);
		g.drawImage(rightTile, Game.Width - Game.ScaledTileSize, Game.Height - Game.ScaledTileSize * 4);
		for(int x = 1; x < Game.TWidth - 1; x++)
		{
			g.drawImage(midTile, x * Game.ScaledTileSize, Game.Height - Game.ScaledTileSize * 4);
		}
		g.setColor(new Color(0f, 0f, 0f, 1));
		String str = "";
		if(board.getStopTime() != 0)str = " Stop Time: " + board.getStopTime();
		g.setFont(Game.smallPrint);
		g.setColor(Color.black);
		g.drawString("$" + board.getDollars(), Game.ScaledTileSize * 31, Game.Height - Game.ScaledTileSize * 4 + 5);
		g.drawString("Turns: " + board.getTurn() + "/" + board.getTurns(), Game.ScaledTileSize * 31, Game.Height - Game.ScaledTileSize * 4 + 20);
		g.drawString("Level: " + gameInfo.getLevel(), Game.ScaledTileSize * 31, Game.Height - Game.ScaledTileSize * 4 + 35);
		g.drawString(str, Game.ScaledTileSize * 31, Game.Height - Game.ScaledTileSize * 4 + 35);
	}

	public void update(GameContainer gc, StateBasedGame sbg, World world, int delta) throws SlickException
	{
	}
	public void tileUpdate() throws SlickException
	{
	}
	
}
