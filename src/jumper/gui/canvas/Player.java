package jumper.gui.canvas;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;

/**
 * Gracz.
 * @author Maurycy
 *
 */
public class Player extends BoardObject {
	
	public Player (Point p) {
		super(p);
	}
	
	public void paintPlayer(Graphics g, int skala)
	{
		g.drawImage(Toolkit.getDefaultToolkit().getImage("0.gif"),
				getX() * skala, getY() * skala,
				skala, skala, null);
	}

}
