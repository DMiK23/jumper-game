package jumper.model.controllers;

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
	 * Sygnal o aktualych pubktoach gracza.
	 * @param score
	 */
	public void setScore (int score);
	
	public void setLives (int lives);
	
	public void oneUp ();
}
