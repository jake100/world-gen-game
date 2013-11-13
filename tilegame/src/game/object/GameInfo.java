package game.object;

public class GameInfo
{
	protected int level = 1, points = 0, multiplier = 1;
	public GameInfo()
	{
		
	}
	public void addPoints(int points)
	{
		this.points += points * multiplier;
	}
	public void multiPoints(int times)
	{
		points *= times;
	}
	public int getPoints()
	{
		return points;
	}
	public int getLevel()
	{
		return level;
	}
	public void increaseLevel()
	{
		level++;
	}
	public void setLevel(int level)
	{
		this.level = level;
	}
	public void setPoints(int points)
	{
		this.points = points;
	}
	
}