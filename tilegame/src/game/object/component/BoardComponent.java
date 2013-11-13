package game.object.component;

import game.object.GameBoard;
import game.world.World;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public abstract class BoardComponent
{
	protected String id;
	  protected GameBoard board;
	  protected boolean enabled = true;
	  public BoardComponent(GameBoard board)
	  {
	  		this.board = board;
	  }
	  public abstract void update(GameContainer gc, StateBasedGame sbg, World world, int delta) throws SlickException;
	  public boolean isEnabled() {return enabled;}
	  public void setEnabled(boolean enabled) {this.enabled = enabled;}
	  public void setOwner(GameBoard board){this.board = board;}
	  public GameBoard getBoard(){return board;}
	  public String getId(){return id;}
	public void setId(String id)
	{
		this.id = id;
	}
	public abstract void tileUpdate() throws SlickException;
	  
}
