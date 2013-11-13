package game.object;

import game.Game;
import game.object.component.BoardComponent;
import game.object.component.BoardRender;
import game.world.World;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public abstract class Board extends GameObject
{
	protected Random rnd = new Random();
	protected ArrayList<BoardComponent> boardComponents = new ArrayList<BoardComponent>();
	protected int[][] grid = new int[Game.TWidth][Game.THeight];
	protected int[][] dropGrid = new int[Game.TWidth][Game.THeight];
	protected int[][] terrainGrid = new int[Game.TWidth][Game.THeight];
	protected int[][] burnGrid = new int[Game.TWidth][Game.THeight];
	protected int[][] foliageGrid = new int[Game.TWidth][Game.THeight];
	protected int[][] placeableGrid = new int[Game.TWidth][Game.THeight];
	protected int[][] insectGrid = new int[Game.TWidth][Game.THeight];
	protected int dollars = 0;
	protected boolean loaded = false;
	public Board(GameInfo gameInfo)
	{
		super(gameInfo);
	}
    public void update(GameContainer gc, StateBasedGame sbg, World world, int delta) throws SlickException
    {
    	super.update(gc, sbg, world, delta);

    	for(BoardComponent boardComponent : boardComponents)
        {
        	boardComponent.update(gc, sbg, world, delta);
        }
	}
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		for(int i = 0; i < boardComponents.size(); i++)
    	{
			if(boardComponents.get(i) instanceof BoardRender)((BoardRender)boardComponents.get(i)).render(gc, sbg, g);
    	}
		
	}
	public int[][] getGrid()
	{
		return grid;
	}
	public void setGrid(int[][] grid)
	{
		this.grid = grid;
	}

	public int[][] getBurnGrid()
	{
		return burnGrid;
	}
	public void setBurnGrid(int[][] burnGrid)
	{
		this.burnGrid = burnGrid;
	}
	public int[][] getTerrainGrid()
	{
		return terrainGrid;
	}
	public void setTerrainGrid(int[][] terrainGrid)
	{
		this.terrainGrid = terrainGrid;
	}
	public int getDollars()
	{
		return dollars;
	}
	public void setDollars(int dollars)
	{
		this.dollars = dollars;
	}
	public int[][] getFoliageGrid()
	{
		return foliageGrid;
	}
	public void setFoliageGrid(int[][] fringeGrid)
	{
		this.foliageGrid = fringeGrid;
	}
	public int[][] getPlaceableGrid()
	{
		return placeableGrid;
	}
	public void setPlaceableGrid(int[][] placeableGrid)
	{
		this.placeableGrid = placeableGrid;
	}
	public int[][] getInsectGrid()
	{
		return insectGrid;
	}
	public void setInsectGrid(int[][] insectGrid)
	{
		this.insectGrid = insectGrid;
	}
	public boolean isLoaded()
	{
		return loaded;
	}
	public void setLoaded(boolean loaded)
	{
		this.loaded = loaded;
	}

}
