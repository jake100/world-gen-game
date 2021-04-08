package game.object.component;

import game.Game;
import game.object.GameBoard;
import game.util.GameImage;
import game.world.World;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class TileRender extends BoardRender
{
	private Image tileBack, tileMark, obsidianTile;
	private PlayerInput playerInput;
	private GameImage gameimg = new GameImage();
	private int[][] grid, terrainGrid, foliageGrid, placeableGrid, insectGrid;
	private Image[] img, terrainImage, foliageImage, placeableImage, insectImage;
	public TileRender(GameBoard board, PlayerInput playerInput)
	{
		super(board);
		tileBack = gameimg.getImage("res/back_tile.png").getScaledCopy(Game.Scale);
		tileMark = gameimg.getImage("res/mark_tile.png").getScaledCopy(Game.Scale);
		this.playerInput = playerInput;
		grid = board.getGrid();
		terrainGrid = board.getTerrainGrid();
		foliageGrid = board.getFoliageGrid();
		placeableGrid = board.getPlaceableGrid();
		insectGrid = board.getInsectGrid();
		img = new Image[GameBoard.typesOfTile];
		terrainImage = new Image[GameBoard.typesOfTerrain];
		foliageImage = new Image[GameBoard.typesOfFoliage];
		placeableImage = new Image[GameBoard.typesOfPlaceable];
		insectImage = new Image[GameBoard.typesOfInsect];
		for(int i = 1;i < GameBoard.typesOfTile;i++)
		{
			String str = "res/" + i + "_tile.png";
			img[i] = gameimg.getImage(str).getScaledCopy(Game.Scale);
		}
		for(int i = 0;i < GameBoard.typesOfTerrain;i++)
		{
			String str = "res/" + i + "_terrain.png";
			terrainImage[i] = gameimg.getImage(str).getScaledCopy(Game.Scale);
		}
		for(int i = 0;i < GameBoard.typesOfFoliage;i++)
		{
			String str = "res/" + i + "_foliage.png";
			foliageImage[i] = gameimg.getImage(str).getScaledCopy(Game.Scale);
		}
		for(int i = 0;i < GameBoard.typesOfPlaceable;i++)
		{
			String str = "res/" + i + "_placeable.png";
			placeableImage[i] = gameimg.getImage(str).getScaledCopy(Game.Scale);
		}
		for(int i = 0;i < GameBoard.typesOfInsect;i++)
		{
			String str = "res/" + i + "_insect.png";
			insectImage[i] = gameimg.getImage(str).getScaledCopy(Game.Scale);
		}
		obsidianTile = gameimg.getImage("res/obsidian_tile.png").getScaledCopy(Game.Scale);
	}
	public void update(GameContainer gc, StateBasedGame sbg, World world, int delta) throws SlickException
	{
		
	}
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		int clickedX = playerInput.getGridX();
		int clickedY = playerInput.getGridY();
		for(int x = 0; x < grid.length; x++)
		{
			for(int y = 0; y < grid[0].length; y++)
			{
				g.drawImage(terrainImage[terrainGrid[x][y]], x * Game.ScaledTileSize, y * Game.ScaledTileSize);
				if(grid[x][y] == GameBoard.meteor)
				{
					g.drawImage(obsidianTile, x * Game.ScaledTileSize, y * Game.ScaledTileSize);
				}
				else
				{
					if(grid[x][y] != GameBoard.air)g.drawImage(img[grid[x][y]], x * Game.ScaledTileSize, y * Game.ScaledTileSize);
				}
				if(foliageGrid[x][y] != GameBoard.no_foliage)g.drawImage(foliageImage[foliageGrid[x][y]], x * Game.ScaledTileSize, y * Game.ScaledTileSize);
				if(placeableGrid[x][y] != GameBoard.no_placeable)g.drawImage(placeableImage[placeableGrid[x][y]], x * Game.ScaledTileSize, y * Game.ScaledTileSize);
				if(insectGrid[x][y] != GameBoard.no_insect)g.drawImage(insectImage[insectGrid[x][y]], x * Game.ScaledTileSize, y * Game.ScaledTileSize);
			}
		}
		g.drawImage(tileMark, clickedX * Game.ScaledTileSize, clickedY * Game.ScaledTileSize);
	}
	public void tileUpdate()
	{
	}
}
