package jumper.model.controllers;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import jumper.gui.canvas.components.Bonus;
import jumper.gui.canvas.components.Platform;
import jumper.gui.canvas.components.Player;

/**
 * Wykrywa kolizje gracza.
 * @author Maurycy
 *
 */

public class CollisionDetector {
	private final List<Platform> platforms;
	private Bonus bonus;
	private final CollisionListener listener;
	private Player player;
	private Platform podNogami = null;

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
	
	public void setPlayer(Player p) {
		player = p;
	}
		
	public boolean collision(Rectangle rect, boolean kierunek) {
		if (bonus != null && bonus.getBounds().intersects(rect)) {
			fireBonusTouched(bonus);
		}
		for (Platform platform : platforms) {
			if (platform.getBounds().intersects(rect) && !platform.isDisappeared()) {
				firePlatformTouched(platform);
				if ((podNogami != null)&&(podNogami != platform)) {
					listener.onPlayerLeavingPlatform(podNogami);
					podNogami = null;
				}
				podNogami = platform;
				return true;
			}
		}
		if ((podNogami != null)&&(kierunek)) {
			listener.onPlayerLeavingPlatform(podNogami);
			podNogami = null;
		}
		return false;
	}
	
	private void firePlatformTouched(Platform p) {
		if (listener != null)
			listener.onPlatformTouched(p);
	}
	
	private void fireBonusTouched(Bonus b) {
		if (listener != null)
			listener.onBonusTouched(b, player);
	}
}
