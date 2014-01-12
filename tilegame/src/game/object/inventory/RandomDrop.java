package game.object.inventory;

import game.Game;
import game.object.GameBoard;
import game.object.inventory.Inventory.Dir;
import game.world.World;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class RandomDrop extends Item
{
	private InsectDrop insectDrop;
	private RockDrop rockDrop;
	private CanonPlacer canonPlacer;
	private VillagerDrop villagerDrop;
	private FireBomb fireBomb;
	private PoisonBomb poisonBomb;
	private int min = 6, max = 18;
	
	public RandomDrop(GameBoard board) throws SlickException
	{
		super(board, "res/random_drop.png", 1);
		insectDrop = new InsectDrop(board);
		rockDrop = new RockDrop(board);
		canonPlacer = new CanonPlacer(board);
		villagerDrop = new VillagerDrop(board);
		fireBomb = new FireBomb(board);
		poisonBomb = new PoisonBomb(board);
		insectDrop.setCount(max);
		rockDrop.setCount(max);
		canonPlacer.setCount(max);
		villagerDrop.setCount(max);
		fireBomb.setCount(max);
		poisonBomb.setCount(max);
		
	}

	public void update(GameContainer gc, StateBasedGame sbg, World world, int delta) throws SlickException
	{
		
	}
	public void fire(int x, int y) throws SlickException
	{
		int drops = min + (int)(Math.random() * ((max - min) + 1));
		for(int i = 0;i < drops;i++)
		{
			int dropId = rnd.nextInt(7);
			if(dropId == 0)
			{
				int[][] grid = board.getGrid();
				grid[rnd.nextInt(Game.TWidth)][rnd.nextInt(Game.THeight)] = GameBoard.mountain;
			}
			if(dropId == 1)
			{
				rockDrop.fire(rnd.nextInt(Game.TWidth), rnd.nextInt(Game.THeight));
			}
			if(dropId == 2)
			{
				insectDrop.fire(rnd.nextInt(Game.TWidth), rnd.nextInt(Game.THeight));
			}
			if(dropId == 3)
			{
				canonPlacer.fire(rnd.nextInt(Game.TWidth), rnd.nextInt(Game.THeight));
			}
			if(dropId == 4)
			{
				villagerDrop.fire(rnd.nextInt(Game.TWidth), rnd.nextInt(Game.THeight));
			}
			if(dropId == 5)
			{
				fireBomb.fire(rnd.nextInt(Game.TWidth), rnd.nextInt(Game.THeight));
			}
			if(dropId == 6)
			{
				poisonBomb.fire(rnd.nextInt(Game.TWidth), rnd.nextInt(Game.THeight));
			}
		}
	}
	public void fire(int x, int y, Dir dir) throws SlickException
	{
	}

}
