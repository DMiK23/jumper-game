package jumper.model;

/**
 * Pojedyñczy wynik.
 * Sk³ada siê z liczby punktów i nicku gracza.
 * @author Maurycy
 *
 */
public class Score {
	
	private final int points;
	private final String playerNick;
	
	/**
	 * Tworzy Wynik z parametrami.
	 * @param points  - liczba punktów.
	 * @param playerNick - nick gracza.
	 */
	public Score (int points, String playerNick) {
		this.points = points;
		this.playerNick = playerNick;		
	}

	/**
	 * Zwraca liczbe punktów.
	 * @return liczba punktów.
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * Zwraca nick gracza.
	 * @return nick gracza.
	 */
	public String getNick() {
		return playerNick;
	}

}
