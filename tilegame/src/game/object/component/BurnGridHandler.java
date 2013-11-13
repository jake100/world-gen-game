package game.object.component;

import game.Game;
import game.object.GameBoard;
import game.world.World;

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class BurnGridHandler extends BoardRender
{
	protected int animMax;
	public BurnGridHandler(GameBoard board) throws SlickException
	{
		super(board);
	}
	public void update(GameContainer gc, StateBasedGame sbg, World world, int delta) throws SlickException
	{

	}
	public void tileUpdate()
	{
		int[][] grid = board.getBurnGrid();
		int[][] terrainGrid = board.getTerrainGrid();
		for(int x = 0; x < grid.length;x++)
		{
			for(int y = 0; y < grid[0].length;y++)
			{
				if(grid[x][y] > 0)
				{
					 grid[x][y]--;
					 board.damage(x, y, 5);
					 if(terrainGrid[x][y] == board.grass)terrainGrid[x][y] = board.dirt;
				}
			}
		}
	}
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		int[][] grid = board.getBurnGrid();
		for(int x = 0; x < grid.length;x++)
		{
			for(int y = 0; y < grid[0].length;y++)
			{
				int animNum = grid[x][y];
				float transparensy = (float) animNum * .025f;
			    g.setColor(new Color(1f, new Random().nextFloat() * .4f, new Random().nextFloat() * .2f, transparensy));
			    g.fillRect(x * Game.ScaledTileSize, y * Game.ScaledTileSize, Game.ScaledTileSize, Game.ScaledTileSize);
			}
		}
		
	}
}
