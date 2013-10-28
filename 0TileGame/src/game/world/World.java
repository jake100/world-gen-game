package game.world;
import game.PauseMenu;
import game.inventory.Inventory;
import game.object.GameBoard;
import game.object.GameInfo;
import game.object.GameObject;
import game.object.component.BurnGridHandler;
import game.object.component.DayNightCycle;
import game.object.component.GameOver;
import game.object.component.Hud;
import game.object.component.PlayerInput;
import game.object.component.Shop;
import game.object.component.TileRender;
import game.util.File;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class World
{
	public static String boardID = "Board";
	private GameInfo gameInfo = new GameInfo();
	private GameBoard board;
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private boolean loadGame, alive = true;
	private int maxLevel = 50;
	private PauseMenu pauseMenu;
	private File file;
	private PlayerInput playerInput;
	public World(boolean loadGame) throws SlickException, InterruptedException
	{
		this.loadGame = loadGame;
		if(!loadGame)board = getLevel(gameInfo.getLevel(), !loadGame);
		else
		{
			board = getLevel(1, !loadGame);
		}
		
	}
	public GameBoard getLevel(int i, boolean loaded) throws SlickException, InterruptedException
	{
		file = new File(board);
		Inventory inventory = new Inventory(board);
		GameBoard gameBoard = new GameBoard(file, inventory, gameInfo, 10, Math.min(i + 5, maxLevel + 5), loaded);
		Shop shop = new Shop(gameBoard, inventory);
		playerInput = new PlayerInput(gameBoard, shop);
		
		pauseMenu = new PauseMenu(file, playerInput, inventory, gameInfo);
		DayNightCycle dayNightCycle = new DayNightCycle(gameBoard);
		GameOver gameOver = new GameOver(gameInfo, gameBoard, playerInput, dayNightCycle);
		BurnGridHandler burnGridHandler = new BurnGridHandler(gameBoard);
		Hud hud = new Hud(gameBoard, gameInfo);
		TileRender tileRender = new TileRender(gameBoard, playerInput);
		

		
		gameBoard.addBoardComponent(playerInput);
		gameBoard.addBoardComponent(tileRender);
		gameBoard.addBoardComponent(gameOver);
		gameBoard.addBoardComponent(shop);
		gameBoard.addBoardComponent(hud);
		gameBoard.addBoardComponent(dayNightCycle);
		gameBoard.addBoardComponent(burnGridHandler);
		gameBoard.addBoardComponent(inventory);
		gameBoard.addBoardComponent(file);
		gameBoard.setFile(file);
		return gameBoard;
	}
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException, InterruptedException
	{
		if(board.isAlive())
		{
			board.update(gc, sbg, this, delta);
			if(pauseMenu != null)pauseMenu.update(gc, sbg, delta);
		}
		else
		{
			if(gameInfo.getLevel() <= maxLevel)
			{
				board = getLevel(gameInfo.getLevel(), true);
			}
			else
			{
				alive = false;
			}
			gameInfo.increaseLevel();
		}
	    for(int i = 0; i < objects.size();i++)
	    {
	        if(!objects.get(i).isAlive())
	        {
	        	objects.get(i).deathUpdate(this);
	        	objects.remove(i);
	        }
	    }
        for(int i = 0; i < objects.size();i++)
        {
        	objects.get(i).update(gc, sbg, this, delta);
        }
	}
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
        if(board != null)board.render(gc, sbg, g);
        if(pauseMenu != null)pauseMenu.render(gc, sbg, g);
	}
	public void spawn(GameObject object)
	{
		objects.add(object);
	}
	public ArrayList<GameObject> getObjects()
	{
		ArrayList<GameObject> objects = new ArrayList<GameObject>();
		return objects;
	}
	public GameBoard getBoard()
	{
		 return board;
	}
	public GameInfo getGameInfo()
	{
		return gameInfo;
	}
	public void setGameInfo(GameInfo gameInfo)
	{
		this.gameInfo = gameInfo;
	}
	public boolean isAlive()
	{
		return alive;
	}
	public void setAlive(boolean alive)
	{
		this.alive = alive;
	}
	public int getBoardGenCounter()
	{
		return board.getBoardGenCounter();
	}
}
