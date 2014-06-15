package jumper.model.controllers;

import jumper.gui.canvas.components.Bonus;
import jumper.gui.canvas.components.Platform;
import jumper.gui.canvas.components.Player;

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
