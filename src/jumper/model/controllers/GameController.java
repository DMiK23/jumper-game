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
	private final List<Integer> scores;
	private int currentBoardIndex = -1;

	public GameController(String configFileName, GameListener listener) throws FileNotFoundException {
		this.config = new ConfigFileOpener(configFileName);
		this.listener = listener;
		this.scores = new ArrayList<Integer>(config.getLevelsList().size());
	}

	public void startGame() {
		loadNewBoard();
	}

	@Override
	public void endGame(int gameScore) {
		// ignorujemy, nie spodziewamy sie takiego zdarzenia - my to zdarzenie wysylamy
	}

	@Override
	public void endBoard(int boardScore) {
		scores.add(boardScore);
		listener.endBoard(boardScore);
		if (currentBoardIndex + 1 < config.getLevelsList().size()) {
			loadNewBoard();
		} else {
			fireEndGame();
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
		int totalScore = 0;
		for (Integer score : scores) {
			totalScore += score;
		}
		listener.endGame(totalScore);
	}
}
