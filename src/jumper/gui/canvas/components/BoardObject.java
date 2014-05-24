package jumper.gui.canvas.components;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * Klasa abstrakcyjna po ktorej dziedzicza obiekty planszy.
 * Zajmuje sie skalowaniem wszystkich wymiarow i polozen.
 * @author Maurycy
 *
 */
public abstract class BoardObject {

	protected final Point p;
	protected final Dimension dim;
	protected Point onScreenPoint;
	protected Dimension onScreenDim;
	private Rectangle bounds;
	
	public BoardObject(int x, int y, int width, int height) {
		p = new Point(x, y);
		dim = new Dimension(width, height);
	}
	
	public BoardObject(Point p, Dimension dim) {
		this.p = p;
		this.dim = dim;
	}
	
	public int getX() {
		return p.x;
	}
	
	public int getY() {
		return p.y;
	}
	
	public void updateScaling(int newScale) {
		onScreenDim = new Dimension(dim.width * newScale, dim.height * newScale);
		onScreenPoint = new Point(p.x * newScale, p.y * newScale);
		bounds = new Rectangle(onScreenPoint, onScreenDim);
	}
	
	public void updateScaling(Dimension newOnScreenDimension, int newScale) {
		onScreenDim = newOnScreenDimension;
		onScreenPoint = new Point(p.x * newScale, p.y * newScale);
		bounds = new Rectangle(onScreenPoint, newOnScreenDimension);
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
}
