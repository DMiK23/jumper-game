package jumper.model.controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import jumper.gui.canvas.BoardCanvas;
import jumper.gui.canvas.components.Bonus;
import jumper.gui.canvas.components.Platform;
import jumper.gui.canvas.components.Player;
import jumper.model.Board;

/**
 * Kontroluje postep gracza na planszy oraz zdarzenia
 * zwiazane z bonusami, limitem czasu i wypadnieciem
 * poza plansze.
 * @author Maurycy
 *
 */
public class BoardController implements CollisionListener, PlayerListener {
	
	private final GameListener listener;
	private final Board board;
	private BoardThread thread;
	private int boardScore = 0;
	private static final long premiaCzasowa = 7000;
	
	public BoardController(Board board, GameListener listener) {
		this.board = board;
		this.listener = listener;
	}
	
	public void startBoard() {
		boardSetup();
		thread.start();
	}

	@Override
	public void onPlatformTouched(Platform p) {
		if (p.isActive()) {
			addBoardScore(board.getPunktyPlatforma());
			if (p.isLast()) {
				thread.passed = true;
				thread.gameOver = true;
			}
		}
		p.setActive(false);
	}

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
	
	@Override
	public void onPlayerLeavingPlatform(Platform p) {
		p.setDisappeared(true);
	}

	@Override
	public void onPlayerOutOfBoard() {
		thread.passed = false;
		thread.gameOver = true;
	}
	
	private void addBoardScore (int i) {
		boardScore += i;
		listener.setScore(boardScore);		
	}
	
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
	
	private void fireEndBoard(boolean passed) {
		// obliczenie wynikow etc.
		listener.endBoard(boardScore, passed); //TODO  
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
		
		private BoardThread(BoardCanvas canvas) {
			this.canvas = canvas;
			this.countDown = new Timer();
		}
		
		public void threadPause (boolean b) {
			isPaused = b;
		}
		
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
		
		private void sleep() {
	        try {
	            Thread.sleep(30);
	        } catch (InterruptedException ie) {
	        }
	    }
		
		private void onTimeout() {
			passed = false;
			gameOver = true;
		}

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

}
