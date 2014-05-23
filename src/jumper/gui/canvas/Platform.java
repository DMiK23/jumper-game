package jumper.gui.canvas;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

/**
 * Platforma. Podstawowy element planszy.
 * @author Maurycy
 *
 */
public class Platform extends BoardObject {
	
	public Platform (Point p) {
		super(p);
	}

	public void paintPlatform(Graphics g, int skala, Dimension dim) {
		g.fillRect(p.x * skala, p.y * skala, dim.width, dim.height);
	}
}
