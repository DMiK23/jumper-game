package jumper.gui.canvas;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * Klasa abstrakcyjna po ktorej dziedzicza obiekty planszy.
 * Zajmuje sie skalowaniem wszystkich wymiarow i polozen.
 * @author Maurycy
 *
 */
public abstract class AbstractBoardObject {

	private final Point p;
	private final Dimension dim;
	protected Point onScreenPoint;
	protected Dimension onScreenDim;
	protected double lastScale;
	protected Rectangle bounds;
	
	/**
	 * Tworzy obiekt i ustawia jego po這瞠nie i wymiary.
	 * @param p - po這瞠nie.
	 * @param dim - wymiary.
	 */
	public AbstractBoardObject(Point p, Dimension dim) {
		this.p = p;
		this.dim = dim;
		bounds = new Rectangle(p, dim);
	}
	
	/**
	 * Ustawia po這瞠nie w osi x.
	 * @param x - po這瞠nie w osi x.
	 */
	protected void setX(int x) {
		p.x = x;
		updateProperties();
		bounds = new Rectangle(p, dim);
	}
	
	/**
	 * Zwraca po這瞠nie w osi x.
	 * @return po這瞠nie w osi x.
	 */
	public int getX() {
		return p.x;
	}
	
	/**
	 * Ustawia po這瞠nie w osi y.
	 * @param y - po這瞠nie w osi y.
	 */
	protected void setY(int y) {
		p.y = y;
		updateProperties();
		bounds = new Rectangle(p, dim);
	}
	
	/**
	 * Zwraca po這瞠nie w osi y.
	 * @return po這瞠nie w osi y.
	 */
	public int getY() {
		return p.y;
	}
	
	/**
	 * Uaktualnia skale obiektu.
	 * @param newScale - skala obiektu.
	 */
	public void updateScaling(double newScale) {
		lastScale = newScale;
		updateProperties();
	}
	
	/**
	 * Uaktualnia skale obiektuw zale積o�ci od proporcji ekranu.
	 * @param newOnScreenDimension - parametr zale積y on proporcji ekranu.
	 * @param newScale - nowa skala obiektu.
	 */
	public void updateScaling(Dimension newOnScreenDimension, double newScale) {
		lastScale = newScale;
		updateProperties(newOnScreenDimension);
	}
	
	/**
	 * Zwraca granice obiektu.
	 * @return granice obiektu.
	 */
	public Rectangle getBounds() {
		return bounds;
	}
	
	/**
	 * Uaktualnia iformacje o po這瞠niu obiektu na planszy i jego rozmiarach.
	 */
	protected void updateProperties() {
		updateProperties(new Dimension((int)(dim.width * lastScale), (int)(dim.height * lastScale)));
	}
	
	/**
	 * Uaktualnia iformacje o po這瞠niu obiektu na planszy i jego rozmiarach.
	 */
	protected void updateProperties(Dimension newDim) {
		onScreenDim = newDim;
		onScreenPoint = new Point((int)(p.x * lastScale),(int)( p.y * lastScale));
	}
}
