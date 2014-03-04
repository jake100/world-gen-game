package game.object;

import game.Game;

public class BasicGrid extends GameObject
{
	public BasicGrid(GameInfo gameInfo)
	{
		super(gameInfo);
	}
	//adds an id to a surrounding tile if the tile's checkGrid and placeGrid ids are 0
    //then if spawnEnemy or if there are no enemies at the tile the tile in the placeGrid will be set to the placeId.
    public int[][] placeAround(int x, int y, int[][] grid, int[][] checkGrid, int[][] placeGrid, int placeId, boolean spawnOn)
    {
    	int air = 0;
		if(x + 1 < Game.TWidth && checkGrid[x + 1][y] != air && placeGrid[x + 1][y] == air)
		{
			if(spawnOn || grid[x + 1][y] == air)place(x + 1, y, placeGrid, placeId);
		}
		if(x - 1 > 0 && checkGrid[x - 1][y] != air && placeGrid[x - 1][y] == air)
		{
			if(spawnOn || grid[x - 1][y] == air)place(x - 1, y, placeGrid, placeId);
		}
		
		if(y + 1 < Game.THeight && checkGrid[x][y + 1] != air && placeGrid[x][y + 1] == air)
		{
			if(spawnOn || grid[x][y + 1] == air)place(x, y + 1, placeGrid, placeId);
		}
		if(y - 1 > 0 && checkGrid[x][y - 1] != air && placeGrid[x][y - 1] == air)
		{
			if(spawnOn || grid[x][y - 1] == air)place(x, y - 1, placeGrid, placeId);
		}
		
		if(x + 1 < Game.TWidth && checkGrid[x + 1][y + 1] != air && placeGrid[x + 1][y + 1] == air)
		{
			if(spawnOn || grid[x + 1][y + 1] == air)place(x + 1, y + 1, placeGrid, placeId);
		}
		if(x + 1 < Game.TWidth && checkGrid[x + 1][y - 1] != air && placeGrid[x + 1][y - 1] == air)
		{
			if(spawnOn || grid[x + 1][y - 1] == air)place(x + 1, y - 1, placeGrid, placeId);
		}
		if(x - 1 < Game.TWidth && checkGrid[x - 1][y + 1] != air && placeGrid[x - 1][y + 1] == air)
		{
			if(spawnOn || grid[x - 1][y + 1] == air)place(x - 1, y + 1, placeGrid, placeId);
		}
		if(x - 1 < Game.TWidth && checkGrid[x - 1][y - 1] != air && placeGrid[x - 1][y - 1] == air)
		{
			if(spawnOn || grid[x - 1][y - 1] == air)place(x - 1, y - 1, placeGrid, placeId);
		}
		return placeGrid;
    }
    public int[][] place(int x, int y, int[][] placeGrid, int placeId)
    {
    	placeGrid[x][y] = placeId;
    	return placeGrid;
    }
	public int[][] newGrid()
	{
		return new int[Game.TWidth][Game.THeight];
	}
}
