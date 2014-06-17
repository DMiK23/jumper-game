package jumper.controller;

import java.io.FileNotFoundException;

import jumper.gui.canvas.BoardCanvas;
import jumper.model.BoardModel;
import jumper.model.GameConfiguration;

/**
 * Kontroluje plansze gry i przechowuje informacje
 * o osiagnieciach gracza na poprzednich poziomach.
 * 
 * @author Maurycy
 *
 */
public class GameController implements GameListener {
	
	private final GameConfiguration config;
	private final GameListener listener;
	private int score;
	private int lives;
	private int currentBoardIndex = -1;
	

	/**
	 * Tworzy kontroler.
	 * @param configFileName - nazwa pliku konfiguracyjnego.
	 * @param listener - listener zdarze� kotrolera gry.
	 * @throws FileNotFoundException - nie znaleziono pliku konfiguracyjnego.
	 */
	public GameController(String configFileName, GameListener listener) throws FileNotFoundException {
		this.config = new GameConfiguration(configFileName);
		this.listener = listener;
		this.score = 0;
		this.lives = config.getLiczbaZyc();
	}

	/**
	 * �aduje pierwsz� plansz�.
	 */
	public void startGame() {
		loadNewBoard();
		setLivesAndTotal(lives, score);
	}

	@Override
	public void endGame(int gameScore) {
		// ignorujemy, nie spodziewamy sie takiego zdarzenia - my to zdarzenie wysylamy
	}

	/**
	 * Gdy plansza si� skoczy decyduje czy za�adowa� ja od nowa,
	 * czy za�adowa� nast�pn�, czy sko�czy� gr�.
	 */
	@Override
	public void endBoard(int boardScore, boolean passed) {
		listener.endBoard(boardScore, passed);
		if (!passed) {
			listener.setLivesAndTotal(--lives, score);
			if (lives > 0) {
				loadBoardAgain();
			} else {
				fireEndGame();
			}
			
		} else {
			score += boardScore;
			if (currentBoardIndex + 1 < config.getLevelsList().size()) {
				loadNewBoard();
				listener.setLivesAndTotal(lives, score);
			} else {
				fireEndGame();
			}
		}
	}

	/**
	 * ��da rozpocz�cia nowej planszy.
	 */
	@Override
	public void startNewBoard(BoardCanvas canvas) {
		listener.startNewBoard(canvas);
	}

	/**
	 * Przekazuje ile czasu zosta�o graczowi.
	 */
	@Override
	public void setPozostalyCzas(long czas) {
		listener.setPozostalyCzas(czas);
	}
	
	/**
	 * Przekazuje ile punkt�w ma gracz na poziomie.
	 */
	@Override
	public void setScore (int score) {
		listener.setScore(score);
	}
	
	/**
	 * Sygnal o aktualych �yciach i sumarycznych punktach gracza.
	 * @param lives - liczba �y�.
	 * @param score - wynik.
	 */
	@Override
	public void setLivesAndTotal(int lives, int score) {
		listener.setLivesAndTotal(lives, score);		
	}
	
	/**
	 * Dodaje dodatkowe �ycie.
	 */
	@Override
	public void oneUp () {
		this.lives++;
		listener.setLivesAndTotal(this.lives, score);
	}
	
	/**
	 * ��da rozpocz�cia planszy od nowa,tworzy nowy BoardController
	 * z plansza i podaje sie jako listenera.
	 */
	private void loadBoardAgain () {
		BoardModel board = config.getLevelsList().get(currentBoardIndex);
		BoardController controller = new BoardController(board, this);
		controller.startBoard();
	}
	
	/**
	 * Przesuwa licznik na nastepna plansze, tworzy nowy BoardController
	 * z nastepna plansza i podaje sie jako listenera.
	 */
	private void loadNewBoard() {
		++currentBoardIndex;
		BoardModel board = config.getLevelsList().get(currentBoardIndex);
		BoardController controller = new BoardController(board, this);
		controller.startBoard();
	}

	/**
	 * Wylicza calkowity wynik i odpala koniec gry.
	 */
	private void fireEndGame() {
		listener.endGame(score);
	}
}
