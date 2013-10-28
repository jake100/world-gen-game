package game.object.component;

import game.Game;
import game.object.GameBoard;
import game.util.Timer;
import game.world.World;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class NullInput extends PlayerInput
{
	private Timer timer;
	public NullInput(GameBoard board, Shop shop)
	{
		super(board, shop);
		timer = new Timer(true, 300);
	}
	public void update(GameContainer gc, StateBasedGame sbg, World world, int delta) throws SlickException
	{
		timer.update(gc, sbg, world, delta);
		if(timer.isOver())
		{
			board.tileUpdate();
			System.out.println("null input");
		}
	}
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		int width = Game.gameFont.getWidth(Game.Title);
		int height = Game.gameFont.getHeight(Game.Title);
		int x = Game.Width / 2 - width / 2;
		int y = Game.Height / 2 - height / 2;
		g.drawString(Game.Title, x, y);
	}
}
