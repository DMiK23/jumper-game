package jumper.gui.canvas;

import java.awt.Point;

/**
 * Klasa abstrakcyjna po ktorej dziedzicza obiekty planszy.
 * @author Maurycy
 *
 */
public abstract class BoardObject {

	private final Point p;
	
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
}
