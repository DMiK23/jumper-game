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
	 * Tworzy platform� z parametrami.
	 * @param p - po�o�enie.
	 * @param dim - wymiary.
	 * @param last - informacja o tym czy platforma jest met�.
	 */
	
	public Platform (Point p, Dimension dim, boolean last) {
		super(p, dim);
		this.last = last;
	}

	/**
	 * Rysuje platform� za pomoc� grafiki.
	 * @param g - grafika.
	 */
	public void paintPlatform(Graphics g) {
		g.fillRect(onScreenPoint.x, onScreenPoint.y,
				onScreenDim.width, onScreenDim.height);
	}

	/**
	 * Zwraca informacj� o tym czy platforma by�a dotkni�ta.
	 * @return stan platformy.
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Ustawia informacj� o tym czy platforma by�a dotkni�ta.
	 * @param active - informacja o tym czy platforma by�a dotkni�ta.
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	
	/**
	 * Sprawdza informacj� o tym czy platforma jest met�.
	 * @return informacja o tym czy platforma jest met�.
	 */
	public boolean isLast() {
		return last;
	}

	/**
	 * Sprawdza informacj� o tym czy platforma znikne�a.
	 * @return informacja o tym czy platforma znikne�a.
	 */
	public boolean isDisappeared() {
		return disappeared;
	}

	/**
	 * Ustawia informacj� o tym czy platforma znikne�a.
	 * @param disappeared - informacja o tym czy platforma znikne�a.
	 */
	public void setDisappeared(boolean disappeared) {
		this.disappeared = disappeared;
	}
	
	
}
