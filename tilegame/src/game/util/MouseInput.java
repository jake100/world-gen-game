package game.util;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener{

	public int x;
	public int y;
	public boolean entered;

	public boolean clicked;
	public boolean rightClicked;
	public void mouseClicked(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		if(e.getButton() == e.BUTTON1)clicked = true;
		if(e.getButton() == e.BUTTON3)rightClicked = true;
	}

	public void mouseEntered(MouseEvent e) {
		entered = true;
	}

	public void mouseExited(MouseEvent e) {
		entered = false;
	}


	public void mousePressed(MouseEvent e) {
		
		
	}


	public void mouseReleased(MouseEvent e) {
		clicked = false;
		
	}

}
