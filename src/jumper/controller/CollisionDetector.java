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
	 * Tworzy detektor kolizji. Tworzy pust� list� platform.
	 * @param listener -  zapami�tuje kto nas�uchuje od niego zdarze�.
	 */
	public CollisionDetector(CollisionListener listener) {
		this.platforms = new ArrayList<>();
		this.listener = listener;
	}
	
	/**
	 * Zapami�tuje platformy na planszy.
	 * @param platformList - platformy na planszy.
	 */
	public void addPlatforms(List<Platform> platformList) {
		platforms.addAll(platformList);
	}
	
	/**
	 * Zapami�tuje bonus.
	 * @param b - bonus.
	 */
	public void setBonus(Bonus b) {
		this.bonus = b;
	}
	
	/**
	 * Zapami�tuje gracza.
	 * @param p - gracz.
	 */
	public void setPlayer(Player p) {
		player = p;
	}
	
	/**
	 * Wykrywa kolizj� i informuje czy gracz opu�ci� platform�.
	 * @param rect po�o�enie gracza.
	 * @param kierunek - kierunek ruchu gracza.
	 * @return informacja czy nastapi�a kolizja.
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
	 * Infomuje listenera, kt�ra platforma zosta�a dotkni�ta.
	 * @param p - platforma.
	 */
	private void firePlatformTouched(Platform p) {
		if (listener != null)
			listener.onPlatformTouched(p);
	}
	
	/**
	 * Infomuje listenera, �e bonus zosta� dotkni�ty przez gracza.
	 * @param b
	 */
	private void fireBonusTouched(Bonus b) {
		if (listener != null)
			listener.onBonusTouched(b, player);
	}
}
