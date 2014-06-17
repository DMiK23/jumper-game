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
	 * Sygnal o dotkniêciu pltformy.
	 * @param p dotkniêta platforma.
	 */
	public void onPlatformTouched(Platform p);
	
	/**
	 * sygna³ o dotkniêciu bonusa.
	 * @param b - ditkniêty bonus.
	 * @param p - gracz.
	 */
	public void onBonusTouched(Bonus b, Player p);
	
	/**
	 * Sygna³ o opuszczeniu platformy przez gracza.
	 * @param p - platforma.
	 */
	public void onPlayerLeavingPlatform (Platform p);
}
