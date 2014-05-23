package jumper.gui.canvas;

import java.awt.Dimension;
import java.awt.Graphics;

/**
 * Platforma. Podstawowy element planszy.
 * @author Maurycy
 *
 */
public class Platform extends BoardObject {
	
	public Platform (int x, int y) {
		super(x, y);
	}

	public void paintPlatform(Graphics g, int skala, Dimension dim) {
		g.fillRect(getX() * skala, getY() * skala, dim.width, dim.height);
	}
}
