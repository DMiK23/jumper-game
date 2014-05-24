package jumper.gui.canvas.components;

import java.awt.Point;
import java.awt.Rectangle;

/**
 * Klasa abstrakcyjna po ktorej dziedzicza obiekty planszy.
 * @author Maurycy
 *
 */
public abstract class BoardObject {

	protected final Point p;
	
	public BoardObject(int x, int y) {
		p = new Point(x, y);
	}
	
	public BoardObject(Point p) {
		this.p = p;
	}
	
	public int getX() {
		return p.x;
	}
	
	public int getY() {
		return p.y;
	}
	
	public abstract Rectangle getBounds();
}
