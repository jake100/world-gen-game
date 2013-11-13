package game.inventory;

import game.inventory.Inventory.Dir;
import game.object.GameBoard;
import game.util.Circle;
import game.util.SoundBank;
import game.world.World;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class InsectDrop extends Item
{
	public InsectDrop(GameBoard board) throws SlickException
	{
		super(board, "res/insect_icon.png", 1);
	}
	public void update(GameContainer gc, StateBasedGame sbg, World world, int delta) throws SlickException
	{
		
	}
	public void fire(int x, int y) throws SlickException
	{
		if(count > 0)
		{
			int radius = 6;
			if(board.isPlaySound())SoundBank.chrunchSound.playAsSoundEffect(1, 1, false);
			int[][] insectGrid = board.getInsectGrid();
			int[][] foliageGrid = board.getFoliageGrid();
			Circle circle = new Circle();
	    	int radius0 = rnd.nextInt(radius - 1) + 1;
	    	for(int i = 0;i < 120;i++)
	    	{
	    		Vector2f pos = circle.getCircle(x, y, radius0).get(rnd.nextInt(circle.getCircle(x, y, radius0).size() - 1));
		    	int xx = (int) pos.x;
				int yy = (int) pos.y;
				if(foliageGrid[xx][yy] != GameBoard.no_foliage)
				{
					insectGrid[xx][yy] = GameBoard.insect;
				}
	    	}
			count--;
			board.tileUpdate();
		}
	}
	public void spawn(int x, int y)
	{
		int[][] grid = board.getGrid();
		int[][] foliageGrid = board.getFoliageGrid();
		int[][] insectGrid = board.getInsectGrid();
		if(foliageGrid[x][y] != GameBoard.no_foliage && insectGrid[x][y] == GameBoard.no_insect)
		{
			insectGrid[x][y] = GameBoard.insect_drop;
		}
	}
	public void fire(int x, int y, Dir dir) throws SlickException
	{
		
	}
}
