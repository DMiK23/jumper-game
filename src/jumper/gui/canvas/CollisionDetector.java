package jumper.gui.canvas;

import java.util.List;

import jumper.gui.canvas.components.Bonus;
import jumper.gui.canvas.components.Platform;
import jumper.gui.canvas.components.Player;

public class CollisionDetector {
	private final List<Platform> platforms;
	private final Player player;
	private final Bonus bonus;

	public CollisionDetector(List<Platform> platforms, Player player, Bonus bonus) {
		this.platforms = platforms;
		this.player = player;
		this.bonus = bonus;
	}
	
	public void onPlayerMove() {
		// TODO
		if (player.getBounds().intersects(bonus.getBounds())) {
			
		}
	}
}
