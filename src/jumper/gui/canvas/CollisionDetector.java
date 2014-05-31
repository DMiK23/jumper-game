package jumper.gui.canvas;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import jumper.gui.canvas.components.Bonus;
import jumper.gui.canvas.components.Platform;
import jumper.gui.canvas.components.Player;
import jumper.model.controllers.CollisionListener;

public class CollisionDetector {
	private final List<Platform> platforms;
	private Bonus bonus;
	private final CollisionListener listener;

	public CollisionDetector(CollisionListener listener) {
		this.platforms = new ArrayList<>();
		this.listener = listener;
	}
	
	public void addPlatforms(List<Platform> platformList) {
		platforms.addAll(platformList);
	}
	
	public void setBonus(Bonus b) {
		this.bonus = b;
	}
	
	public boolean collision(Player player) {
		return collision(player.getBounds());
	}
	
	public boolean collision(Rectangle rect) {
		if (bonus != null && bonus.getBounds().intersects(rect)) {
			fireBonusTouched(bonus);
		}
		for (Platform platform : platforms) {
			if (platform.getBounds().intersects(rect)) {
				firePlatformTouched(platform);
				return true;
			}
		}
		return false;
	}
	
	private void firePlatformTouched(Platform p) {
		if (listener != null)
			listener.onPlatformTouched(p);
	}
	
	private void fireBonusTouched(Bonus b) {
		if (listener != null)
			listener.onBonusTouched(b);
	}
}
