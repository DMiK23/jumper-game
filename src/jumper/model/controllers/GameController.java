package jumper.model.controllers;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import jumper.gui.canvas.BoardCanvas;
import jumper.model.Board;
import jumper.model.ConfigFileOpener;

/**
 * Kontroluje plansze gry i przechowuje informacje
 * o osiagnieciach gracza na poprzednich poziomach.
 * 
 * @author Maurycy
 *
 */
public class GameController implements GameListener {
	
	private final ConfigFileOpener config;
	private final GameListener listener;
	private int score;
	private int lives;
	private int currentBoardIndex = -1;
	

	public GameController(String configFileName, GameListener listener) throws FileNotFoundException {
		this.config = new ConfigFileOpener(configFileName);
		this.listener = listener;
		this.score = 0;
		this.lives = config.getLiczbaZyc();
	}

	public void startGame() {
		loadNewBoard();
		setLives(lives);
	}

	@Override
	public void endGame(int gameScore) {
		// ignorujemy, nie spodziewamy sie takiego zdarzenia - my to zdarzenie wysylamy
	}

	@Override
	public void endBoard(int boardScore, boolean passed) {
		listener.endBoard(boardScore, passed);
		if (!passed) {
			lives--;
			setLives(lives);
			if (lives > 0) {
				loadBoardAgain();
			} else {
				fireEndGame();
			}
			
		} else {
			score += boardScore;
			if (currentBoardIndex + 1 < config.getLevelsList().size()) {
				loadNewBoard();
			} else {
				fireEndGame();
			}
		}
	}

	@Override
	public void startNewBoard(BoardCanvas canvas) {
		listener.startNewBoard(canvas);
	}

	@Override
	public void setPozostalyCzas(long czas) {
		listener.setPozostalyCzas(czas);
	}
	
	@Override
	public void setScore (int score) {
		listener.setScore(score);
	}
	@Override
	public void setLives(int lives) {
		listener.setLives(lives);		
	}
	@Override
	public void oneUp () {
		this.lives++;
		listener.setLives(this.lives);
	}
	
	private void loadBoardAgain () {
		Board board = config.getLevelsList().get(currentBoardIndex);
		BoardController controller = new BoardController(board, this);
		controller.startBoard();
	}
	
	/**
	 * Przesuwa licznik na nastepna plansze, tworzy nowy BoardController
	 * z nastepna plansza i podaje sie jako listenera.
	 */
	private void loadNewBoard() {
		++currentBoardIndex;
		Board board = config.getLevelsList().get(currentBoardIndex);
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
