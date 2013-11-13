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
		String msg = "$" + board.getDollars() + " Turns: " + board.getTurn() + "/" + board.getTurns() + " Level: " + gameInfo.getLevel() + str;
		g.setFont(Game.gameFont);
		g.setColor(Color.black);
		g.drawString(msg, 5, Game.Height - Game.ScaledTileSize * 4 + 5);
	}

	public void update(GameContainer gc, StateBasedGame sbg, World world, int delta) throws SlickException
	{
	}
	public void tileUpdate() throws SlickException
	{
	}
	
}
