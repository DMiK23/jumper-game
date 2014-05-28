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

	private final Point p;
	private final Dimension dim;
	protected Point onScreenPoint;
	protected Dimension onScreenDim;
	protected double lastScale;
	protected Rectangle bounds;
	
	public BoardObject(int x, int y, int width, int height) {
		p = new Point(x, y);
		dim = new Dimension(width, height);
	}
	
	public BoardObject(Point p, Dimension dim) {
		this.p = p;
		this.dim = dim;
		bounds = new Rectangle(p, dim);
	}
	
	protected void setX(int x) {
		p.x = x;
		updateProperties();
		bounds = new Rectangle(p, dim);
	}
	
	public int getX() {
		return p.x;
	}
	
	protected void setY(int y) {
		p.y = y;
		updateProperties();
		bounds = new Rectangle(p, dim);
	}
	
	public int getY() {
		return p.y;
	}
	
	public void updateScaling(double newScale) {
		lastScale = newScale;
		updateProperties();
	}
	
	public void updateScaling(Dimension newOnScreenDimension, double newScale) {
		lastScale = newScale;
		updateProperties(newOnScreenDimension);
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	protected void updateProperties() {
		updateProperties(new Dimension((int)(dim.width * lastScale), (int)(dim.height * lastScale)));
	}
	
	protected void updateProperties(Dimension newDim) {
		onScreenDim = newDim;
		onScreenPoint = new Point((int)(p.x * lastScale),(int)( p.y * lastScale));
	}
}
