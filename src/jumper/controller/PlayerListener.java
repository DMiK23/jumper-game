package jumper.controller;

/**
 * Informuje kiedy gracz spadnie.
 * 
 * @author Maurycy
 *
 */
public interface PlayerListener {

	public void onPlayerOutOfBoard();
	
	public void playerWantsToPause();
}
