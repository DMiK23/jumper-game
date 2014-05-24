package jumper.gui.canvas.components;

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
		System.out.println("platf. stworzona");
	}

	public void paintPlatform(Graphics g, int skala, Dimension dim) {
		System.out.println("platf. malowana");
		if (ostatniaSkala != skala) {
			ostatniaSkala = skala;
			ostatnieWymiary = dim;
		}
		g.fillRect(p.x * skala, p.y * skala, dim.width, dim.height);
		System.out.printf("platf. namalowana, wymiary:\tx: %d\ty: %d\n", dim.width, dim.height);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(p.x * ostatniaSkala, p.y * ostatniaSkala,
				ostatnieWymiary.width, ostatnieWymiary.height);
	}
}
