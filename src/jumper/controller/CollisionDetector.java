package jumper.controller;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import jumper.gui.canvas.Bonus;
import jumper.gui.canvas.Platform;
import jumper.gui.canvas.Player;

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

	/**
	 * Tworzy detektor kolizji. Tworzy pust¹ listê platform.
	 * @param listener -  zapamiêtuje kto nas³uchuje od niego zdarzeñ.
	 */
	public CollisionDetector(CollisionListener listener) {
		this.platforms = new ArrayList<>();
		this.listener = listener;
	}
	
	/**
	 * Zapamiêtuje platformy na planszy.
	 * @param platformList - platformy na planszy.
	 */
	public void addPlatforms(List<Platform> platformList) {
		platforms.addAll(platformList);
	}
	
	/**
	 * Zapamiêtuje bonus.
	 * @param b - bonus.
	 */
	public void setBonus(Bonus b) {
		this.bonus = b;
	}
	
	/**
	 * Zapamiêtuje gracza.
	 * @param p - gracz.
	 */
	public void setPlayer(Player p) {
		player = p;
	}
	
	/**
	 * Wykrywa kolizjê i informuje czy gracz opuœci³ platformê.
	 * @param rect po³o¿enie gracza.
	 * @param kierunek - kierunek ruchu gracza.
	 * @return informacja czy nastapi³a kolizja.
	 */
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
	
	/**
	 * Infomuje listenera, która platforma zosta³a dotkniêta.
	 * @param p - platforma.
	 */
	private void firePlatformTouched(Platform p) {
		if (listener != null)
			listener.onPlatformTouched(p);
	}
	
	/**
	 * Infomuje listenera, ¿e bonus zosta³ dotkniêty przez gracza.
	 * @param b
	 */
	private void fireBonusTouched(Bonus b) {
		if (listener != null)
			listener.onBonusTouched(b, player);
	}
}
