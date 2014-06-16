package jumper.controller;

import jumper.gui.canvas.Bonus;
import jumper.gui.canvas.Platform;
import jumper.gui.canvas.Player;

/**
 * Nasluchuje zdarzen detektora kolizji, takich jak:
 * dotkniecie platformy przez gracza
 * dotkniecie bonusu przez gracza
 * @author Maurycy
 *
 */
public interface CollisionListener {

	public void onPlatformTouched(Platform p);
	
	public void onBonusTouched(Bonus b, Player p);
	
	public void onPlayerLeavingPlatform (Platform p);
}
