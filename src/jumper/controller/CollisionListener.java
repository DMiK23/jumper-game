package jumper.controller;

import jumper.gui.canvas.Bonus;
import jumper.gui.canvas.Platform;
import jumper.gui.canvas.Player;

/**
 * Nasluchuje zdarzen detektora kolizji, takich jak:
 * dotkniecie platformy przez gracza,
 * dotkniecie bonusu przez gracza.
 * @author Maurycy
 *
 */
public interface CollisionListener {

	/**
	 * Sygnal o dotkni�ciu pltformy.
	 * @param p dotkni�ta platforma.
	 */
	public void onPlatformTouched(Platform p);
	
	/**
	 * sygna� o dotkni�ciu bonusa.
	 * @param b - ditkni�ty bonus.
	 * @param p - gracz.
	 */
	public void onBonusTouched(Bonus b, Player p);
	
	/**
	 * Sygna� o opuszczeniu platformy przez gracza.
	 * @param p - platforma.
	 */
	public void onPlayerLeavingPlatform (Platform p);
}
