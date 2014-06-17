package jumper.controller;

/**
 * Informuje kiedy gracz spadnie.
 * 
 * @author Maurycy
 *
 */
public interface PlayerListener {

	/**
	 * Sygnal o wypadniêciu gracza z planszy.
	 */
	public void onPlayerOutOfBoard();
	
	/**
	 * Sygnal o ¿¹daniu pauzy.
	 */
	public void playerWantsToPause();
}
