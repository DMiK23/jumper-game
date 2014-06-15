package jumper.gui.canvas.components;

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
public class Bonus extends BoardObject {
	
	private final BonusTypeEnumerator type;
	private boolean active = true;
	
	public Bonus(Point p, Dimension dim, BonusTypeEnumerator type) {
		super(p, dim);
		this.type = type;
	}

	public void paintBonus(Graphics g) {
		g.drawImage(Toolkit.getDefaultToolkit().getImage("1.gif"),
				onScreenPoint.x, onScreenPoint.y,
				onScreenDim.width, onScreenDim.height, null);
	}

	public BonusTypeEnumerator getType() {
		return type;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
