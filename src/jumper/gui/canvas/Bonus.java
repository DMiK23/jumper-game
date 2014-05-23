package jumper.gui.canvas;

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
	
	public Bonus(Point p, BonusTypeEnumerator type) {
		super(p);
		this.type = type;
	}

	public void paintBonus(Graphics g, int skala) {
		g.drawImage(Toolkit.getDefaultToolkit().getImage("1.gif"),
				p.x * skala, p.y * skala,
				skala, skala, null);
	}
}
