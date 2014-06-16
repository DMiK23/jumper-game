package jumper.gui.canvas;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;

import jumper.model.BonusTypeEnumerator;

/**
 * Klasa bonusu. Bonus ma typ i polozenie.
 * @author Maurycy
 *
 */
public class Bonus extends AbstractBoardObject {
	
	private final BonusTypeEnumerator type;
	private boolean active = true;
	
	/**
	 * Tworzy bonus z parametrami.
	 * @param p - po³o¿enie.
	 * @param dim - wymiary.
	 * @param type - typ.
	 */
	public Bonus(Point p, Dimension dim, BonusTypeEnumerator type) {
		super(p, dim);
		this.type = type;
	}

	/**
	 * Rysuje bonus za pomoc¹ grafiki.
	 * @param g - grafika.
	 */
	public void paintBonus(Graphics g) {
		g.drawImage(Toolkit.getDefaultToolkit().getImage("1.gif"),
				onScreenPoint.x, onScreenPoint.y,
				onScreenDim.width, onScreenDim.height, null);
	}

	/**
	 * Zwraca typ bonusu.
	 * @return - typ bonusu.
	 */
	public BonusTypeEnumerator getType() {
		return type;
	}

	/**
	 * Zwraca informacjê o tym czy bonus jest jeszcze do zdobycia.
	 * @return stan bonusu.
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Ustawia aktywnoœæ bonusu, czyli czy jest jeszcze do zdobycia.
	 * @param active - aktywnoœæ bonusu.
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
}
