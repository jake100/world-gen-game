package game.object;

import game.object.component.BoardGen;

import org.newdawn.slick.SlickException;

public class BoardGenManager
{
	private int count = 0, enemies, start, difficulty;
	private BoardGen boardGen;
	private GameBoard board;
	private boolean generated = false;
	public BoardGenManager(GameBoard board, int enemies, int start, int difficulty) throws SlickException
	{
		boardGen = new BoardGen(board);
		board.getParticleGen().setParticleGen(false);
		this.enemies = enemies;
		this.start = start;
		this.board = board;
		this.difficulty = difficulty;
	}
	public void generate() throws SlickException
	{
		System.out.println(count);
		switch(count)
		{
		case 0:
			for(int i = 0; i < 50;i++)
			{
				boardGen.addId(GameBoard.mountain);
			}
			break;
		case 1:
			boardGen.extendMountains(125 - difficulty * 5);
			break;
		case 2:
			boardGen.extendMountains(125);
			break;
		case 3:
			for(int i = 0; i < 50 + difficulty * 5;i++)
			{
				boardGen.addId(GameBoard.dirt);
			}
			break;
		case 4:
			boardGen.addEnemies(5);
			boardGen.simulateTurns(75);
			break;
		case 5:
			boardGen.addEnemies(difficulty * 5);
			boardGen.simulateTurns(75);
			break;
		case 6:
			boardGen.simulateTurns(75);
			boardGen.clearBoard();
			break;
		case 7:
			boardGen.simulateTurns(75);
			break;
		case 8:
			boardGen.simulateTurns(75);
			break;
		case 9:
			boardGen.removeSingleTiles();
			boardGen.weoponFire(2);
			break;
		case 10:
			boardGen.simulateTurns(20);
			break;
		case 11:
			boardGen.weoponFire(2);
			boardGen.simulateTurns(20);
			break;
		case 12:
			boardGen.weoponFire(2);
			break;
		case 13:
			boardGen.simulateTurns(40);
			break;
		case 14:
			boardGen.clearBurnGrid();
			boardGen.addEnemies(enemies);
			break;
		case 15:
			boardGen.simulateTurns(start);
			break;
		case 16:
			boardGen.prepareForGame();
			generated = true;
			break;
		}
		count++;
	}
	public boolean isGenerated()
	{
		return generated;
	}
	public void setGenerated(boolean generated)
	{
		this.generated = generated;
	}
	
}
