package jumper.controller;

/**
 * Informuje kiedy gracz spadnie.
 * 
 * @author Maurycy
 *
 */
public interface PlayerListener {

	/**
	 * Sygnal o wypadni�ciu gracza z planszy.
	 */
	public void onPlayerOutOfBoard();
	
	/**
	 * Sygnal o ��daniu pauzy.
	 */
	public void playerWantsToPause();
}
