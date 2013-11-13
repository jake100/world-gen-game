package game.object;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class Cload extends Fire
{
	public Cload(GameInfo gameInfo, Vector2f pos) throws SlickException
	{
		super(gameInfo, pos);
		anim[0] = new Image("res/cload_0.png");
		anim[1] = new Image("res/cload_1.png");
		anim[2] = new Image("res/cload_2.png");
	}

}
