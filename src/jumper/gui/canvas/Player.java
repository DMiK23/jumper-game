package jumper.gui.canvas;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Gracz.
 * @author Maurycy
 *
 */
public class Player extends BoardObject implements KeyListener {
	
	public Player (Point p) {
		super(p);
	}
	
	public void paintPlayer(Graphics g, int skala)
	{
		g.drawImage(Toolkit.getDefaultToolkit().getImage("0.gif"),
				p.x * skala, p.y * skala,
				skala, skala, null);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("player got KeyEvent Pressed");
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("player got KeyEvent Released");
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("player got KeyEvent Typed");
	}

}
