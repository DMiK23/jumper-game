package jumper.gui.canvas;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;

import jumper.model.BonusTypeEnumerator;

/**
 * Klasa bonusu. Bonus ma typ i polozenie.
 * @author Maurycy
 *
 */
public class Bonus extends BoardObject {
	
	private final BonusTypeEnumerator type;
	private int ostatniaSkala;
	private Dimension ostatnieWymiary;
	
	public Bonus(Point p, BonusTypeEnumerator type) {
		super(p);
		this.type = type;
	}

	public void paintBonus(Graphics g, int skala) {
		if (ostatniaSkala != skala) {
			ostatniaSkala = skala;
			ostatnieWymiary = new Dimension(skala, skala);
		}
		g.drawImage(Toolkit.getDefaultToolkit().getImage("1.gif"),
				p.x * skala, p.y * skala,
				skala, skala, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(p.x * ostatniaSkala, p.y * ostatniaSkala,
				ostatnieWymiary.width, ostatnieWymiary.height);
	}
}
