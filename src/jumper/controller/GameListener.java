package jumper.controller;

import jumper.gui.canvas.BoardCanvas;

/**
 * Nasluchuje na zdarzenia gry, takie jak zakonczenie,
 * czy zmiana planszy.
 * @author Maurycy
 *
 */
public interface GameListener {

	/**
	 * Sygnal o zakonczeniu gry.
	 * @param gameScore Wynik osiagniety w calej grze.
	 */
	public void endGame(int gameScore);
	
	
	/**
	 * Sygnal zakonczenia planszy.
	 * @param boardScore Wynik gracza na planszy.
	 * @param passed - czy plansza zosta³a zaliczona.
	 */
	public void endBoard(int boardScore, boolean passed);
	
	/**
	 * Synal o rozpoczeciu nowego poziomu.
	 * @param canvas BoardCanvas nowego poziomu.
	 */
	public void startNewBoard(BoardCanvas canvas);
	
	/**
	 * Sygnal o ilosci pozostalego czasu gry na obecnej planszy.
	 * @param czas Pozostaly czas w milisekundach.
	 */
	public void setPozostalyCzas(long czas);
	
	/**
	 * Sygnal o aktualych punktach gracza.
	 * @param score punkty gracza.
	 */
	public void setScore (int score);
	
	/**
	 * Sygnal o aktualych ¿yciach i sumarycznych punktach gracza.
	 * @param lives - liczba ¿yæ.
	 * @param score - wynik.
	 */
	public void setLivesAndTotal (int lives, int score);
	
	public void oneUp ();
	// ignorujemy, nie spodziewamy sie takiego zdarzenia - my to zdarzenie wysylamy
}
