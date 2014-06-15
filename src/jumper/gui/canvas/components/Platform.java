package jumper.gui.canvas.components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

/**
 * Platforma. Podstawowy element planszy.
 * @author Maurycy
 *
 */
public class Platform extends BoardObject {
	
	private boolean active = true;
	private final boolean last;
	private boolean disappeared = false;
	
	public Platform (Point p, Dimension dim, boolean last) {
		super(p, dim);
		this.last = last;
	}

	public void paintPlatform(Graphics g) {
		g.fillRect(onScreenPoint.x, onScreenPoint.y,
				onScreenDim.width, onScreenDim.height);
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	public boolean isLast() {
		return last;
	}

	public boolean isDisappeared() {
		return disappeared;
	}

	public void setDisappeared(boolean disappeared) {
		this.disappeared = disappeared;
	}
	
	
}
