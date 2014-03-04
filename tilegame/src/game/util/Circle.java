package game.util;

import game.Game;

import java.util.ArrayList;

import org.newdawn.slick.geom.Vector2f;
/*
 * returns the x and y coords in an arrayList of the tiles inside the circle.
 */
public class Circle
{
	public ArrayList<Vector2f> getCircle(int x0, int y0, int radius)
	{
		int f = 1 - radius;
	    int ddF_x = 0;
	    int ddF_y = -2 * radius;
	    int x = 0;
	    int y = radius;
	    ArrayList<Vector2f> circle = new ArrayList<Vector2f>();
	    
	    if(y0 + radius < Game.THeight)circle.add(new Vector2f(x0, y0 + radius));
	    if(y0 - radius > 0)circle.add(new Vector2f(x0, y0 - radius));
	    if(x0 + radius < Game.TWidth)circle.add(new Vector2f(x0 + radius, y0));
	    if(x0 - radius > 0)circle.add(new Vector2f(x0 - radius, y0));
	    while(x < y) 
	    {
	        if(f >= 0) 
	        {
	            y--;
	            ddF_y += 2;
	            f += ddF_y;
	        }
	        x++;
	        ddF_x += 2;
	        f += ddF_x + 1;
	        
	        if(x0 + x < Game.TWidth && y0 + y < Game.THeight)circle.add(new Vector2f(x0 + x, y0 + y));
	        if(x0 - x > 0 && y0 + y < Game.THeight)circle.add(new Vector2f(x0 - x, y0 + y));
	        if(x0 + x  < Game.TWidth && y0 - y > 0)circle.add(new Vector2f(x0 + x, y0 - y));
	        if(x0 - x > 0 && y0 - y > 0)circle.add(new Vector2f(x0 - x, y0 - y));
	        if(x0 + y < Game.TWidth && y0 + x < Game.THeight)circle.add(new Vector2f(x0 + y, y0 + x));
	        if(x0 - y > 0 && y0 + x < Game.THeight)circle.add(new Vector2f(x0 - y, y0 + x));
	        if(x0 + y  < Game.TWidth && y0 - x > 0)circle.add(new Vector2f(x0 + y, y0 - x));
	        if(x0 - y > 0 && y0 - x > 0)circle.add(new Vector2f(x0 - y, y0 - x));
	    }
		return circle;
	}
}
