package game;

import java.awt.Graphics;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import game.util.Keyboard;

public class Game implements Runnable
{
	public static final int Scale = 1, TileSize = 15, ScaledTileSize = (int)(TileSize * Scale);
	public static final int TWidth = 75, THeight = 55;
	public static final int Width = ScaledTileSize * TWidth, Height = ScaledTileSize * THeight + ScaledTileSize * 8;
	public static final int Splash = 0, MENU = 1, PLAY = 2;
	public static final String Title = "Tile Infection"; //temporary name
	public MenuState menuState = new MenuState(0);
	public PlayState playState = new PlayState(0);
	public TileGameContainer tgc = new TileGameContainer();
	public static Graphics g;
	public Game()
	{
		menuState.init();
	}
    public void run () {
    	long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		while(true)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			render();
			while(delta >= 1)
			{
				try {
					update(delta);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				delta--;
			}
			
			if(System.currentTimeMillis() - timer > 1000)
			{
				timer += 1000;
			}
		}
    }
    public void update(double delta) throws InterruptedException
    {
    	menuState.update(delta);
    }
    public void render()
    {
    	
    	menuState.render(g);
    }
	public static int rndX()
	{
		return new Random().nextInt(TWidth);
	}
	public static int rndY()
	{
		return new Random().nextInt(THeight);
	}
	public static void main(String args[])
	{
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Width, Height);
        frame.setLocationRelativeTo(null);

        Keyboard keyboard = Keyboard.getInstance();
        frame.addKeyListener(keyboard);

        JPanel panel = new JPanel();
        frame.add(panel);
        
        g = panel.getGraphics();
        new Thread(new Game()).start();
	}

}
