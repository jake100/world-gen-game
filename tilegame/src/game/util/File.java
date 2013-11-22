package game.util;

import game.Game;
import game.object.GameBoard;
import game.object.GameInfo;
import game.object.component.BoardComponent;
import game.object.inventory.Inventory;
import game.world.World;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class File extends BoardComponent
{
	private BufferedReader in;
	private BufferedWriter out;
	private int[] values = new int[10];
	protected int[][] grid = new int[Game.TWidth][Game.THeight];
	protected int[][] dropGrid = new int[Game.TWidth][Game.THeight];
	protected int[][] terrainGrid = new int[Game.TWidth][Game.THeight];
	protected int[][] burnGrid = new int[Game.TWidth][Game.THeight];
	protected int[][] foliageGrid = new int[Game.TWidth][Game.THeight];
	protected int[][] placeableGrid = new int[Game.TWidth][Game.THeight];
	protected int[][] insectGrid = new int[Game.TWidth][Game.THeight];

	public File(GameBoard board)
	{
		super(board);
	}
	public void load(String path) throws NumberFormatException, SlickException
	{
        try 
        {
            in = new BufferedReader(new FileReader(path));

            values[0] = Integer.parseInt(in.readLine());
            values[1] = Integer.parseInt(in.readLine());
            values[2] = Integer.parseInt(in.readLine());
            values[3] = Integer.parseInt(in.readLine());
            
            values[4] = Integer.parseInt(in.readLine());
            values[5] = Integer.parseInt(in.readLine());
            values[6] = Integer.parseInt(in.readLine());
            values[7] = Integer.parseInt(in.readLine());
            
            values[8] = Integer.parseInt(in.readLine());
            values[9] = Integer.parseInt(in.readLine());
            
            for(int x = 0; x < Game.TWidth;x++)
            {
                for(int y = 0; y < Game.THeight;y++)
                {
                	grid[x][y] = Integer.parseInt(in.readLine());
                }
            }
            for(int x = 0; x < Game.TWidth;x++)
            {
                for(int y = 0; y < Game.THeight;y++)
                {
                	terrainGrid[x][y] = Integer.parseInt(in.readLine());
                }
            }
            for(int x = 0; x < Game.TWidth;x++)
            {
                for(int y = 0; y < Game.THeight;y++)
                {
                	foliageGrid[x][y] = Integer.parseInt(in.readLine());
                }
            }
            for(int x = 0; x < Game.TWidth;x++)
            {
                for(int y = 0; y < Game.THeight;y++)
                {
                	burnGrid[x][y] = Integer.parseInt(in.readLine());
                }
            }
            for(int x = 0; x < Game.TWidth;x++)
            {
                for(int y = 0; y < Game.THeight;y++)
                {
                	insectGrid[x][y] = Integer.parseInt(in.readLine());
                }
            }
            for(int x = 0; x < Game.TWidth;x++)
            {
                for(int y = 0; y < Game.THeight;y++)
                {
                	placeableGrid[x][y] = Integer.parseInt(in.readLine());
                }
            }
            in.close();
        }
        catch(IOException e)
        {
            System.out.println("There was a promlem loading the file " + e);
        }
	}
	public void save(String path, Inventory inventory, GameInfo gameInfo) throws SlickException
	{
		 try 
	        {
	            out = new BufferedWriter(new FileWriter(path));
	            
	            out.write(board.getDollars()+"");
	            out.newLine();
	            out.write(board.getTurn()+"");
	            out.newLine();
	            out.write(board.getTurns()+"");
	            out.newLine();
	            out.write(board.getStopTime()+"");
	            out.newLine();
	            int[] firstItem = new int[2], secondItem = new int[2];
	            firstItem = inventory.saveItem(0);
	            secondItem = inventory.saveItem(1);
	            out.write(firstItem[0]+"");
	            out.newLine();
	            out.write(firstItem[1]+"");
	            out.newLine();
	            out.write(secondItem[0]+"");
	            out.newLine();
	            out.write(secondItem[1]+"");
	            out.newLine();
	            
	            out.write(gameInfo.getLevel()+"");
	            out.newLine();
	            
	            out.write(gameInfo.getPoints()+"");
	            out.newLine();
	            
	            for(int x = 0; x < Game.TWidth;x++)
	            {
	                for(int y = 0; y < Game.THeight;y++)
	                {
	                	int[][] grid = board.getGrid();
	                	out.write(grid[x][y]+"");
	     	            out.newLine();
	                }
	            }
	            for(int x = 0; x < Game.TWidth;x++)
	            {
	                for(int y = 0; y < Game.THeight;y++)
	                {
	                	int[][] grid = board.getTerrainGrid();
	                	out.write(grid[x][y]+"");
	     	            out.newLine();
	                }
	            }
	            for(int x = 0; x < Game.TWidth;x++)
	            {
	                for(int y = 0; y < Game.THeight;y++)
	                {
	                	int[][] grid = board.getFoliageGrid();
	                	out.write(grid[x][y]+"");
	     	            out.newLine();
	                }
	            }
	            for(int x = 0; x < Game.TWidth;x++)
	            {
	                for(int y = 0; y < Game.THeight;y++)
	                {
	                	int[][] grid = board.getBurnGrid();
	                	out.write(grid[x][y]+"");
	     	            out.newLine();
	                }
	            }
	            for(int x = 0; x < Game.TWidth;x++)
	            {
	                for(int y = 0; y < Game.THeight;y++)
	                {
	                	int[][] grid = board.getInsectGrid();
	                	out.write(grid[x][y]+"");
	     	            out.newLine();
	                }
	            }
	            for(int x = 0; x < Game.TWidth;x++)
	            {
	                for(int y = 0; y < Game.THeight;y++)
	                {
	                	int[][] grid = board.getPlaceableGrid();
	                	out.write(grid[x][y]+"");
	     	            out.newLine();
	                }
	            }
	            System.out.println("saved...");
	            out.close();
	        }
	        catch(IOException e)
	        {
	            System.out.println("There was a promlem loading the file " + e);
	        }
	}
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, World world,
			int delta) throws SlickException
	{
	}
	@Override
	public void tileUpdate() throws SlickException
	{
	}
	public int[] getValues()
	{
		return values;
	}
	public void setValues(int[] values)
	{
		this.values = values;
	}
	public int[][] getGrid()
	{
		return grid;
	}
	public void setGrid(int[][] grid)
	{
		this.grid = grid;
	}
	public int[][] getDropGrid()
	{
		return dropGrid;
	}
	public void setDropGrid(int[][] dropGrid)
	{
		this.dropGrid = dropGrid;
	}
	public int[][] getTerrainGrid()
	{
		return terrainGrid;
	}
	public void setTerrainGrid(int[][] terrainGrid)
	{
		this.terrainGrid = terrainGrid;
	}
	public int[][] getBurnGrid()
	{
		return burnGrid;
	}
	public void setBurnGrid(int[][] burnGrid)
	{
		this.burnGrid = burnGrid;
	}
	public int[][] getFoliageGrid()
	{
		return foliageGrid;
	}
	public void setFoliageGrid(int[][] foliageGrid)
	{
		this.foliageGrid = foliageGrid;
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
	
}
