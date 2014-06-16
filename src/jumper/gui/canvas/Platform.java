package jumper.gui.canvas;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

/**
 * Platforma. Podstawowy element planszy.
 * @author Maurycy
 *
 */
public class Platform extends AbstractBoardObject {
	
	private boolean active = true;
	private final boolean last;
	private boolean disappeared = false;
	
	/**
	 * Tworzy platformê z parametrami.
	 * @param p - po³o¿enie.
	 * @param dim - wymiary.
	 * @param last - informacja o tym czy platforma jest met¹.
	 */
	
	public Platform (Point p, Dimension dim, boolean last) {
		super(p, dim);
		this.last = last;
	}

	/**
	 * Rysuje platformê za pomoc¹ grafiki.
	 * @param g - grafika.
	 */
	public void paintPlatform(Graphics g) {
		g.fillRect(onScreenPoint.x, onScreenPoint.y,
				onScreenDim.width, onScreenDim.height);
	}

	/**
	 * Zwraca informacjê o tym czy platforma by³a dotkniêta.
	 * @return stan platformy.
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Ustawia informacjê o tym czy platforma by³a dotkniêta.
	 * @param active - informacja o tym czy platforma by³a dotkniêta.
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	
	/**
	 * Sprawdza informacjê o tym czy platforma jest met¹.
	 * @return informacja o tym czy platforma jest met¹.
	 */
	public boolean isLast() {
		return last;
	}

	/**
	 * Sprawdza informacjê o tym czy platforma znikne³a.
	 * @return informacja o tym czy platforma znikne³a.
	 */
	public boolean isDisappeared() {
		return disappeared;
	}

	/**
	 * Ustawia informacjê o tym czy platforma znikne³a.
	 * @param disappeared - informacja o tym czy platforma znikne³a.
	 */
	public void setDisappeared(boolean disappeared) {
		this.disappeared = disappeared;
	}
	
	
}
