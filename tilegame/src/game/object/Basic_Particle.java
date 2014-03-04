package game.object;

import game.Game;
import game.world.World;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class Basic_Particle extends GameObject
{
	public static final float scale = .225f;
	protected Image img;
	protected Vector2f pos, dpos;
	protected int life;
	protected float rotation;
	public Basic_Particle(GameInfo gameInfo, Image img, Vector2f pos, Vector2f dpos, int life)
	{
		super(gameInfo);
		this.pos = pos;
		this.dpos = dpos;
		this.life = life;
		img.setFilter(Image.FILTER_NEAREST);
		this.img = img.getScaledCopy(Game.Scale * scale);
	}

	public void render(GameContainer gc, Graphics g)throws SlickException
	{
		g.drawImage(img, pos.x, pos.y);
	}

	public void update(GameContainer gc, StateBasedGame sbg, World world, int delta) throws SlickException
	{
		life--;
		if(life <= 0)this.alive = false;
		pos.add(dpos);
	}

}
