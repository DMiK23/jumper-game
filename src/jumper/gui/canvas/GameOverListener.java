package jumper.gui.canvas;

/**
 * Nasluchuje zmian w pozostalym czasie gry,
 * i reaguje na zakonczenie.
 * @author Maurycy
 *
 */
public interface GameOverListener {

	public void gameOver();
	
	public void setTimer(long milisecsLeft);
}
