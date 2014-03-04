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
            
            grid = readGrid();
            terrainGrid = readGrid();
            foliageGrid = readGrid();
            burnGrid = readGrid();
            insectGrid = readGrid();
            placeableGrid = readGrid();
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
	            
	            writeNum(board.getDollars());
	            writeNum(board.getTurn());
	            writeNum(board.getTurns());
	            writeNum(board.getStopTime());
	            int[] firstItem = new int[2], secondItem = new int[2];
	            firstItem = inventory.saveItem(0);
	            secondItem = inventory.saveItem(1);
	            writeNum(firstItem[0]);
	            writeNum(firstItem[1]);
	            writeNum(secondItem[0]);
	            writeNum(secondItem[1]);
	            
	            writeNum(gameInfo.getLevel());
	            
	            writeNum(gameInfo.getPoints());
	            
	            writeGrid(board.getGrid());
	            writeGrid(board.getTerrainGrid());
	            writeGrid(board.getFoliageGrid());
	            writeGrid(board.getBurnGrid());
	            writeGrid(board.getInsectGrid());
	            writeGrid(board.getPlaceableGrid());
	            System.out.println("saved...");
	            out.close();
	        }
	        catch(IOException e)
	        {
	            System.out.println("There was a promlem loading the file " + e);
	        }
	}
	public int[][] readGrid() throws NumberFormatException, IOException
	{
		int[][] grid = new int[Game.TWidth][Game.THeight];
        for(int x = 0; x < Game.TWidth;x++)
        {
            for(int y = 0; y < Game.THeight;y++)
            {
            	grid[x][y] = Integer.parseInt(in.readLine());
            }
        }
        return grid;
	}
	public void writeGrid(int[][] grid) throws IOException
	{
        for(int x = 0; x < Game.TWidth;x++)
        {
            for(int y = 0; y < Game.THeight;y++)
            {
            	writeNum(grid[x][y]);
            }
        }
	}
	public void writeNum(int num) throws IOException
	{
		out.write(num+"");
        out.newLine();
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
