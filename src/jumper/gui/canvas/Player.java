package jumper.gui.canvas;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Gracz.
 * @author Maurycy
 *
 */
public class Player extends BoardObject implements KeyListener {

	private int ostatniaSkala;
	private Dimension ostatnieWymiary;
	
	public Player (Point p) {
		super(p);
	}
	
	public void paintPlayer(Graphics g, int skala)
	{
		if (ostatniaSkala != skala) {
			ostatniaSkala = skala;
			ostatnieWymiary = new Dimension(skala, skala);
		}
		g.drawImage(Toolkit.getDefaultToolkit().getImage("0.gif"),
				p.x * skala, p.y * skala,
				skala, skala, null);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_RIGHT : p.x += 1; break;
		case KeyEvent.VK_LEFT : p.x -= 1; break;
		}
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

	@Override
	public Rectangle getBounds() {
		return new Rectangle(p.x * ostatniaSkala, p.y * ostatniaSkala,
				ostatnieWymiary.width, ostatnieWymiary.height);
	}

}
