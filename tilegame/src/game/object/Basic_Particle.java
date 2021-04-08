package game.object;

import game.Game;
import game.util.BasicImage;
import game.util.Vector2f;
import game.world.World;

import java.awt.Graphics;



public class Basic_Particle extends GameObject
{
	public static final float scale = .225f;
	protected BasicImage img;
	protected Vector2f pos, dpos;
	protected int life;
	protected float rotation;
	public Basic_Particle(GameInfo gameInfo, BasicImage img, Vector2f pos, Vector2f dpos, int life)
	{
		super(gameInfo);
		this.pos = pos;
		this.dpos = dpos;
		this.life = life;
//		img.setFilter(Image.FILTER_NEAREST);
		this.img = img.getScaledCopy(Game.Scale * scale);
	}

	public void render(Graphics g)
	{
		g.drawImage(img, (int)pos.x, (int)pos.y, null);
	}

	public void update( World world, int delta)
	{
		life--;
		if(life <= 0)this.alive = false;
		pos.add(dpos);
	}

}
