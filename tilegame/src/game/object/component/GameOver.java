package game.object.component;

import game.ContinueMenu;
import game.object.GameBoard;
import game.object.GameInfo;
import game.util.SoundBank;
import game.world.World;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class GameOver extends BoardRender
{
	public static enum Type{Time_Up, Kill_All}
	private boolean over = false, playSound = false;
	private int counter = 0, time = 200;
	private Image img;
	private PlayerInput playerInput;
	private GameInfo gameInfo;
	private DayNightCycle dayNightCycle;
	protected Random rnd = new Random();
	protected boolean mouseSet = false;
	protected ContinueMenu continueMenu = null;
	public GameOver(GameInfo gameInfo, GameBoard board, PlayerInput playerInput, DayNightCycle dayNightCycle) throws SlickException
	{
		super(board);
		this.gameInfo = gameInfo;
		this.playerInput = playerInput;
		this.dayNightCycle = dayNightCycle;
	}

	public void update(GameContainer gc, StateBasedGame sbg, World world, int delta) throws SlickException
	{
		if(over && enabled)
		{
			if(continueMenu == null)
			{
				String[] str = new String[4];
				str[0] = "Game over. Space to continue... ";
				str[1] = "Level: " +gameInfo.getLevel() + " ";
				str[2] = "Points: " +gameInfo.getPoints() + " ";
				str[3] = "$" + board.getDollars();
				continueMenu = new ContinueMenu(str);
			}
			if(!mouseSet)
			{
				gc.setMouseCursor("res/cursor_over.png",0,0);
				mouseSet = true;
			}
		//	if(!playSound)SoundBank.endSound.playAsSoundEffect(1, 1, true);
			playSound = true;
			dayNightCycle.timeCycle(.0005f);
			playerInput.setEnabled(false);
			counter++;
			if(counter % 2 == 0)board.tileUpdate();
			continueMenu.update(gc, sbg, delta);
			if(continueMenu.isSpaceClicked())
			{
				SoundBank.endSound.stop();
				board.setAlive(false);
			}
		}
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		if(enabled && continueMenu != null)continueMenu.render(gc, sbg, g);
		
	}
	public void start(Type type) throws SlickException
	{
		over = true;
		if(type == Type.Kill_All)
		{
			//img = new Image("res/kill_all.png");
		}
		if(type == Type.Time_Up)
		{
			//img = new Image("res/time_up.png");
		}
	}

	public boolean isOver()
	{
		return over;
	}

	public void setOver(boolean over)
	{
		this.over = over;
	}
	public void tileUpdate() throws SlickException
	{
    	if(enabled)
    	{
    		if(board.getTurn() >= board.getTurns())start(Type.Time_Up);
        	boolean livingTiles = false;
        	int[][] grid = board.getGrid();
        	for(int i = 0; i < grid.length; i++)
    		{
    			for(int j = 0; j < grid[0].length; j++)
    			{
    				if(grid[i][j] >= GameBoard.living && grid[i][j] != GameBoard.meteor)
    				{
    					livingTiles = true;
    				}
    			}
    		}
        	if(!livingTiles && !over)
        	{
        		gameInfo.addPoints(5000);
        		start(Type.Kill_All);
        		over = true;
        	}
    	}
	}
	
}
