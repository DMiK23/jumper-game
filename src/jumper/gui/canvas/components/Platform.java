package jumper.gui.canvas.components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

/**
 * Platforma. Podstawowy element planszy.
 * @author Maurycy
 *
 */
public class Platform extends BoardObject {
	
	public Platform (Point p, Dimension dim) {
		super(p, dim);
	}

	public void paintPlatform(Graphics g) {
		g.fillRect(onScreenPoint.x, onScreenPoint.y,
				onScreenDim.width, onScreenDim.height);
	}
}
