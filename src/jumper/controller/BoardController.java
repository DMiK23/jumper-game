package jumper.controller;

import java.util.Timer;
import java.util.TimerTask;

import jumper.gui.canvas.BoardCanvas;
import jumper.gui.canvas.Bonus;
import jumper.gui.canvas.Platform;
import jumper.gui.canvas.Player;
import jumper.model.BoardModel;

/**
 * Kontroluje postep gracza na planszy oraz zdarzenia
 * zwiazane z bonusami, limitem czasu i wypadnieciem
 * poza plansze.
 * @author Maurycy
 *
 */
public class BoardController implements CollisionListener, PlayerListener {
	
	private final GameListener listener;
	private final BoardModel board;
	private BoardThread thread;
	private int boardScore = 0;
	private static final long premiaCzasowa = 7000;
	
	/**
	 * Tworzy kontrolera planczy z paramtrami.
	 * @param board - plansza.
	 * @param listener - listenr gry.
	 */
	public BoardController(BoardModel board, GameListener listener) {
		this.board = board;
		this.listener = listener;
	}
	
	/**
	 *  Zaczyna planszê.
	 */
	public void startBoard() {
		boardSetup();
		thread.start();
	}

	/**
	 * Reaguje na dotkniêcie platformy przez gracza.
	 * Przekazuje platformie, ze jest dotkniêta przez gracza.
	 * Jeœli jest to meta, ustawia flagi koñca poziomu.
	 */
	@Override
	public void onPlatformTouched(Platform p) {
		if (p.isActive()) {
			addBoardScore(board.getPunktyPlatforma());
			if (p.isLast()) {
				addBoardScore((int)(thread.counterMs/1000));
				thread.passed = true;
				thread.gameOver = true;
			}
		}
		p.setActive(false);
	}

	/**
	 * Reaguje na dotkniêcie bonusu przez gracza.
	 * Reaguje odpowiednio na dane bonusy.
	 */
	@Override
	public void onBonusTouched(Bonus b, Player p) {
		if (b.isActive()) {
			switch (b.getType()) {
			case ADD_POINTS: 
				addBoardScore(board.getPunktyPremia());
				break;
			case ADD_TIME: 
				thread.counterMs += premiaCzasowa;
				break;
			case BETTER_JUMP: 
				p.betterJump();
				break;
			case ONE_UP: 
				listener.oneUp();
				break;
			}		
		}
		b.setActive(false);
	}
	
	/**
	 * Gdy player opuœci platforme ustawi siê jej flaga znikniêcia.
	 */
	@Override
	public void onPlayerLeavingPlatform(Platform p) {
		p.setDisappeared(true);
	}

	/**
	 * Ustawia flagi koñca koñca poziomu, gdzy gracz spadnie.
	 */
	@Override
	public void onPlayerOutOfBoard() {
		thread.passed = false;
		thread.gameOver = true;
	}
	
	/**
	 * Zwieksza wynik za dan¹ planszê.
	 * @param i -  dodane do wyniku planszy.
	 */
	private void addBoardScore (int i) {
		boardScore += i;
		listener.setScore(boardScore);		
	}
	
	/**
	 * Ustawia wszytkie potrzebne listenery i detectory.
	 */
	private void boardSetup() {
		CollisionDetector detector = new CollisionDetector(this);
		BoardCanvas canvas = new BoardCanvas(board, detector);
		detector.addPlatforms(canvas.getPlatforms());
		detector.setBonus(canvas.getBonus());
		detector.setPlayer(canvas.getPlayer());
		canvas.getPlayer().setListener(this);
		thread = new BoardThread(canvas);
		listener.startNewBoard(canvas);
	}
	
	/**
	 * Koñczy planszê.
	 * @param passed wynik planszy (zaliczona lub nie).
	 */
	private void fireEndBoard(boolean passed) {
		listener.endBoard(boardScore, passed); 
	}
	
	/**
	 * Watek animujacy plansze.
	 * @author Maurycy
	 *
	 */
	private class BoardThread extends Thread {
		
		private final BoardCanvas canvas;
		private Timer countDown;
		private boolean gameOver = false;
		private boolean passed = true;
		private long counterMs = 0;
		private boolean isPaused = false;
		
		/**
		 * Tworzy w¹tek, timer i zapamiêtuje obraz planszy któr¹ bêdzie rysowa³.
		 * @param canvas
		 */
		private BoardThread(BoardCanvas canvas) {
			this.canvas = canvas;
			this.countDown = new Timer();
		}
		
		/**
		 * Ustawia flagê pauzy.
		 */
		public void threadPause () {
			isPaused = !isPaused;
		}
		
		/**
		 * Ustwia wszytko potrzebne do uruchomienia poziomu.
		 * Ustawia wielkoœæ, punkty pocz¹tkowe i timer.
		 */
		private void setup() {
			canvas.updateSize();
			listener.setScore(boardScore);
			counterMs = board.getCzasNaPrzejscie();
			countDown.scheduleAtFixedRate(new TimerTask() {
				@Override
				public void run() {
					listener.setPozostalyCzas(counterMs);
					counterMs -= isPaused ? 0 : 100;
					if (counterMs < 0) {
						onTimeout();
					}
				}
			}, 0, 100);
		}
		
		/**
		 * Usypia w¹tek.
		 */
		private void sleep() {
	        try {
	            Thread.sleep(30);
	        } catch (InterruptedException ie) {
	        }
	    }
		
		/**
		 * Gdy skoæzy siê czas ustawia flagi koñca poziomu.
		 */
		private void onTimeout() {
			passed = false;
			gameOver = true;
		}

		/**
		 * Petla animacji.
		 */
		@Override
		public void run() {
			setup();
			// petla animacyjna
	        while (!gameOver) {
	        	if (!isPaused) {
	        		canvas.modifyLocation();
	        		canvas.updateOffscreen();
	            	canvas.repaint();
	        	}
	            sleep();	            
	        }
	        // zakonczenie gry
	        countDown.cancel();
	        fireEndBoard(passed);
		}
	}

	/**
	 * Wywo³uje funkje pauzuj¹c¹.
	 */
	@Override
	public void playerWantsToPause() {
		thread.threadPause();		
	}
	

}
