package jumper.gui.canvas;

import java.awt.Rectangle;
import java.util.List;

import jumper.gui.canvas.components.Bonus;
import jumper.gui.canvas.components.Platform;
import jumper.gui.canvas.components.Player;

public class CollisionDetector {
	private final List<Platform> platforms;
	@SuppressWarnings("unused")
	private final Bonus bonus;

	public CollisionDetector(List<Platform> platforms, Bonus bonus) {
		this.platforms = platforms;
		this.bonus = bonus;
	}
	
	public boolean collision(Player player) {
		return collision(player.getBounds());
	}
	
	public boolean collision(Rectangle rect) {
		for (Platform platform : platforms) {
			if (platform.getBounds().intersects(rect))
				return true;
		}
		return false;
	}
}
