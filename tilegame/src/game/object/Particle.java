package game.object;

import game.util.BasicImage;
import game.util.Image;
import game.util.MultiStageTimer;
import game.util.SoundBank;
import game.util.Util;
import game.util.Vector2f;
import game.world.World;

import java.awt.Graphics;
import java.util.Random;



public class Particle extends Basic_Particle
{
	protected BasicImage[] anim;
	protected int animNum = 0, animMax;
	protected static float speed = .0615f;
	protected float fireScale = (float) (Math.random() * 1.05f) + .01f, origFireScale = fireScale;
	private int[] time = {180, 80, 40};
	protected MultiStageTimer timer;
	protected static Random rnd = new Random();
	protected SoundBank fireSound;
	public Particle(GameInfo gameInfo, BasicImage[] anim, Vector2f pos)
	{
		super(gameInfo, anim[0], pos, randomDir(), rnd.nextInt(70));
		this.anim = anim;
		timer = new MultiStageTimer(time, rnd.nextInt(time.length));
		animMax = 3;
		anim = new Image[animMax];
		anim[0] = Util.loadImage("res/fire_0.png");
		anim[1] = Util.loadImage("res/fire_1.png");
		anim[2] = Util.loadImage("res/fire_2.png");
		rotation = new Random().nextInt(360);
		animNum = rnd.nextInt(3);
		anim[animNum].setRotation(new Random().nextInt(360));
	}
	public static Particle getFireBall(GameInfo gameInfo,game.util.Vector2f pos)
	{
		Image[] anim = new Image[]{Util.loadImage("res/fire_0.png"), Util.loadImage("res/fire_1.png"), Util.loadImage("res/fire_2.png")};
		return new Particle(gameInfo, anim, pos);
	}
	public static Particle getCload(GameInfo gameInfo, game.util.Vector2f pos)
	{
		Image[] anim = new Image[]{Util.loadImage("res/cload_0.png"), Util.loadImage("res/cload_1.png"), Util.loadImage("res/cload_2.png")};
		return new Particle(gameInfo, anim, pos);
	}
	public static Particle getPoison(GameInfo gameInfo, game.util.Vector2f pos)
	{
		BufferedImage[] anim = new BufferedImage[]{Util.loadImage("res/poison_0.png"), Util.loadImage("res/poison_1.png"), Util.loadImage("res/poison_2.png")};
		return new Particle(gameInfo, anim, pos);
	}
	public void update(World world, int delta)
	{
		super.update(world, delta);
		timer.update(world, delta);
		if(timer.isCurrent(0))
		{
			if(animNum != 0)fireScale = origFireScale;
			animNum = 0;
			anim[animNum].setRotation(new Random().nextInt(360));
		}
		if(timer.isCurrent(1))
		{
			if(animNum != 1)fireScale = origFireScale;
			animNum = 1;
			anim[animNum].setRotation(new Random().nextInt(360));
		}
		if(timer.isCurrent(2))
		{
			if(animNum != 2)fireScale = origFireScale;
			animNum = 2;
			anim[animNum].setRotation(new Random().nextInt(360));
		}
		//if((int)life % 10 == 0) anim[animNum].setRotation(new Random().nextInt(360));
		if(life <= 0) alive = false;
	}
	public static Vector2f randomDir()
	{
		return new Vector2f((float) (speed * Math.cos(Math.random() * 2 * Math.PI)), (float) (speed * Math.sin(Math.random() * 2 * Math.PI)));
	}
	public void render(Graphics g)
	{
		anim[animNum].draw((int)pos.x - anim[animNum].getWidth()/2, (int)pos.y - anim[animNum].getHeight()/2, fireScale);
	}
}
