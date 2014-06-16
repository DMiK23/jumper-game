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
	 * Tworzy obiekt i ustawia jego po³o¿enie i wymiary.
	 * @param p - po³o¿enie.
	 * @param dim - wymiary.
	 */
	public AbstractBoardObject(Point p, Dimension dim) {
		this.p = p;
		this.dim = dim;
		bounds = new Rectangle(p, dim);
	}
	
	/**
	 * Ustawia po³o¿enie w osi x.
	 * @param x - po³o¿enie w osi x.
	 */
	protected void setX(int x) {
		p.x = x;
		updateProperties();
		bounds = new Rectangle(p, dim);
	}
	
	/**
	 * Zwraca po³o¿enie w osi x.
	 * @return po³o¿enie w osi x.
	 */
	public int getX() {
		return p.x;
	}
	
	/**
	 * Ustawia po³o¿enie w osi y.
	 * @param y - po³o¿enie w osi y.
	 */
	protected void setY(int y) {
		p.y = y;
		updateProperties();
		bounds = new Rectangle(p, dim);
	}
	
	/**
	 * Zwraca po³o¿enie w osi y.
	 * @return po³o¿enie w osi y.
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
	 * Uaktualnia skale obiektuw zale¿noœci od proporcji ekranu.
	 * @param newOnScreenDimension - parametr zale¿ny on proporcji ekranu.
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
	 * Uaktualnia iformacje o po³o¿eniu obiektu na planszy i jego rozmiarach.
	 */
	protected void updateProperties() {
		updateProperties(new Dimension((int)(dim.width * lastScale), (int)(dim.height * lastScale)));
	}
	
	/**
	 * Uaktualnia iformacje o po³o¿eniu obiektu na planszy i jego rozmiarach.
	 */
	protected void updateProperties(Dimension newDim) {
		onScreenDim = newDim;
		onScreenPoint = new Point((int)(p.x * lastScale),(int)( p.y * lastScale));
	}
}
