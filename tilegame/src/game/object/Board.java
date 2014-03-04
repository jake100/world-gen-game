package game.object;

import game.object.component.BoardComponent;
import game.object.component.BoardRender;
import game.world.World;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
/*
 * abstract class extended by the GameBoard class.
 * Initializes the differed grids, has a Random variable and other fields used by GameBoard.
 * Provides getters and setters for the different fields as well as a basic update and render method for use by GameBoard.
 */
public abstract class Board extends BasicGrid
{
	//grid ids
	public static final int air = 0, living = 10, typesOfTile = 19, boss = typesOfTile - 1, meteor = 99;
	public static final int stone = 0, dirt = 1, grass = 2, charred = 3, barren = 4, mountain = 5, poisoned = 6, typesOfTerrain = 7;
	public static final int no_foliage = 0, tall_grass = 1, plant = 2, coverPlant = 3, lifeGiver = 4, typesOfFoliage = 5;
	public static final int no_placeable = 0, villager = 1, canon = 2, typesOfPlaceable = 3;
	public static final int no_insect = 0, insect_drop = 1, insect = 2, typesOfInsect = 3;
	
	protected ArrayList<BoardComponent> boardComponents = new ArrayList<BoardComponent>();
	protected int[][] grid = newGrid();
	protected int[][] dropGrid = newGrid(), terrainGrid = newGrid();
	protected int[][] burnGrid = newGrid(), foliageGrid = newGrid();
	protected int[][] placeableGrid = newGrid(), insectGrid = newGrid();
	protected int dollars = 0, turns = 100, turn = 0, stopTime = 0, counter = 0;
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
