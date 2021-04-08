package game.object.component;

import game.object.GameBoard;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public abstract class BoardRender extends BoardComponent
{
	 public BoardRender(GameBoard owner)
		{
			super(owner);
		}

	 public abstract void render(java.awt.Graphics g);
}
