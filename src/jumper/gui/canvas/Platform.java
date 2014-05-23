package jumper.gui.canvas;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * Platforma. Podstawowy element planszy.
 * @author Maurycy
 *
 */
public class Platform extends BoardObject {
	
	private int ostatniaSkala;
	private Dimension ostatnieWymiary;
	
	public Platform (Point p) {
		super(p);
	}

	public void paintPlatform(Graphics g, int skala, Dimension dim) {
		if (ostatniaSkala != skala) {
			ostatniaSkala = skala;
			ostatnieWymiary = new Dimension(skala, skala);
		}
		g.fillRect(p.x * skala, p.y * skala, dim.width, dim.height);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(p.x * ostatniaSkala, p.y * ostatniaSkala,
				ostatnieWymiary.width, ostatnieWymiary.height);
	}
}
