package game.object.component;

import game.Game;
import game.object.GameBoard;
import game.object.inventory.Inventory;
import game.util.Keyboard;
import game.util.SoundBank;
import game.world.World;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
/*
 * Handles key, mouse clicks and sets a delay.
 */
public class PlayerInput extends BoardComponent
{
	private int mx, my, gridX, gridY, clickCount;
	private Keyboard keyboard = new Keyboard();
	private boolean up = false, down = false, left = false, right = false, rKey, fKey, click, altClick;
	private boolean mouseWait = false;
	private Inventory inventory;
	private Shop shop;
	public PlayerInput(GameBoard board, Shop shop)
	{
		super(board);
		this.setId("Player Input");
		this.shop = shop;
		click();
	}
	public void update(World world, int delta)
	{
		up = false;
		down = false;
		left = false;
		right = false;
		click = false;
		altClick = false;
		fKey = false;
		rKey = false;
		if(enabled)
		{
			for(int i = 0; i < shop.getWidth();i++)
			{
				for(int j = 0; j < shop.getHeight();j++)
				{
					boolean entered = false;
					if((mx > i * Game.ScaledTileSize && mx < i  * Game.ScaledTileSize  + Game.ScaledTileSize) && (my > j * Game.ScaledTileSize && my < j  * Game.ScaledTileSize + Game.ScaledTileSize)) entered = true;
					if(entered)
					{
						gridX = i;
						gridY = j;
					}
				}
			}
			if(clickCount > 0)
			{
				clickCount -= delta;
				if(!mouseWait)
				{
					
//					gc.setMouseCursor("res/cursor_wait.png",0,0);
					mouseWait = true;
				}
			}
			else
			{
				if(mouseWait)
				{
//					gc.setMouseCursor("res/cursor.png",0,0);
					mouseWait = false;
				}
			}
			if(keyboard.isDown(Input.KEY_R))rKey = true;
			PointerInfo a = MouseInfo.getPointerInfo();
			Point b = a.getLocation();
			mx = (int) b.getX();
			my = (int) b.getY();
			
			int[][] grid = board.getGrid();

			if(grid != null)
			{
				for(int i = 0; i < grid.length;i++)
				{
					for(int j = 0; j < grid[0].length;j++)
					{
						boolean entered = false;
						if((mx > i * Game.ScaledTileSize && mx < i  * Game.ScaledTileSize  + Game.ScaledTileSize) && (my > j * Game.ScaledTileSize && my < j  * Game.ScaledTileSize + Game.ScaledTileSize)) entered = true;
						if(entered)
						{
							gridX = i;
							gridY = j;
						}
						if(entered && clickCount <= 0 && enabled)
						{
							if(keyboard.isDown(Input.MOUSE_LEFT_BUTTON)){click = true;}
							if(keyboard.isDown(Input.MOUSE_RIGHT_BUTTON)){altClick = true;}
							if(keyboard.isDown(Input.KEY_W)){up = true;}
							if(keyboard.isDown(Input.KEY_S)){down = true;}
							if(keyboard.isDown(Input.KEY_A)){left = true;}
							if(keyboard.isDown(Input.KEY_D)){right = true;}
							if(keyboard.isDown(Input.KEY_F)){fKey = true;}
							inventory = (Inventory) board.getBoardComponent("Inventory");
							if(isClick())
							{
								if(inventory != null)inventory.firstFire(i, j);
							}
							if(isAltClick())
							{
								if(inventory != null)inventory.secondFire(i, j);
							}
							if(isRight())
							{
								inventory.dirFire(i, j, Inventory.Dir.Right);
					    		board.tileUpdate();
							}
							if(isLeft())
							{
								inventory.dirFire(i, j, Inventory.Dir.Left);
					    		board.tileUpdate();
							}
							if(isUp())
							{
								inventory.dirFire(i, j, Inventory.Dir.Up);
					    		board.tileUpdate();
							}
							if(isDown())
							{
								inventory.dirFire(i, j, Inventory.Dir.Down);
					    		board.tileUpdate();
							}
							if(isFKey())
							{
								board.damage(i, j, 15);
								board.spawnFire(i, j, 3);
					    		board.tileUpdate();
								if(board.isPlaySound())SoundBank.fireSound.playAsSoundEffect(1, 1, false);
							}
							if(hasInput())click();
						}
					}
				}
			}
		}
	}
	public void setEnabled(boolean enabled)
	{
		this.enabled = enabled;
		click();
	}
	public void click()
	{
		clickCount = 180;

	}
	public boolean hasInput()
	{
		return click || up || down || left || right || altClick || fKey;
	}
	public boolean isClick()
	{
		return click && !up && !down && !left && !right && !altClick && !fKey;
	}
	public void setClick(boolean click)
	{
		this.click = click;
	}
	public boolean isAltClick()
	{
		return altClick && !up && !down && !left && !right && !click && !fKey;
	}
	public void setAltClick(boolean altClick)
	{
		this.altClick = altClick;
	}
	public boolean isUp()
	{
		return up && !down && !left && !right && !click  && !altClick && !fKey;
	}
	public void setUp(boolean up)
	{
		this.up = up;
	}
	public boolean isDown()
	{
		return down && !up && !left && !right && !click  && !altClick && !fKey;
	}
	public void setDown(boolean down)
	{
		this.down = down;
	}
	public boolean isLeft()
	{
		return left && !down && !up && !right && !click  && !altClick && !fKey;
	}
	public void setLeft(boolean left)
	{
		this.left = left;
	}
	public boolean isRight()
	{
		return right && !down && !left && !up && !click  && !altClick && !fKey;
	}
	public boolean isFKey()
	{
		return fKey && !down && !left && !up && !click  && !altClick && !right;
	}
	public void setRight(boolean right)
	{
		this.right = right;
	}
	public int getClickCount()
	{
		return clickCount;
	}
	public void setClickCount(int clickCount)
	{
		this.clickCount = clickCount;
	}
	public int getGridX()
	{
		return gridX;
	}
	public void setGridX(int gridX)
	{
		this.gridX = gridX;
	}
	public int getGridY()
	{
		return gridY;
	}
	public void setGridY(int gridY)
	{
		this.gridY = gridY;
	}
	public void tileUpdate()
	{
	}
	public boolean isrKey()
	{
		return rKey;
	}
	public void setrKey(boolean rKey)
	{
		this.rKey = rKey;
	}

	
}
