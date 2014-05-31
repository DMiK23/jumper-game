package jumper.gui.canvas;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.SwingUtilities;

import jumper.gui.canvas.components.Bonus;
import jumper.gui.canvas.components.Platform;
import jumper.gui.canvas.components.Player;
import jumper.model.Board;
import jumper.model.Board.BoardFactory;

// TODO trzeba wszystkie funkcje i atrybuty zwiazane z obsulga zdarzen gry
// TODO przeniesc do osobnej klasy, np. GameController,
// TODO zajmujace sie zliczaniem punktow, obsluga bonusow, znikaniem platform
// TODO a byc moze rowniez obsluga kolizji (skads trzeba wiedziec,
// TODO kiedy zniknac platforme).

/**
 * Plansza z gra. Rysuje elementy planszy przy pomocy podwojnego bufora.
 * @author Maurycy
 *
 */
@SuppressWarnings("serial")
public class BoardCanvas extends Canvas implements Runnable {
	
	public static final int scaleX = BoardFactory.scaleX; 
	public static final int scaleY = BoardFactory.scaleY; 
	public static final double ratio = BoardFactory.ratio;
	private static final Dimension platformDim = new Dimension(scaleX/16, scaleY/((int)(64*ratio)));
	private static final Dimension playerDim = new Dimension(scaleX/64, scaleY/((int)(64*ratio)));
	private static final Dimension bonusDim = new Dimension(scaleX/128, scaleY/((int)(128*ratio)));
	private Graphics offScreenGraphics = null;	
	private final Player player;
	private final Bonus bonus;
	private final List<Platform> platforms;
	private final Dimension defaultSize = new Dimension(600, 200);
	private Image offScreen = null;
	private Thread thread;
	private boolean gameOver;
	private final GameOverListener gameOverListener;
	private final Timer countDown;
	private final Board board;
	private final CollisionDetector detector;
	private final Image img = Toolkit.getDefaultToolkit().createImage("background.jpg");
	
	/**
	 * Tworzy elementy graficzne
	 * @param board - obiekt przechowujacy parametry poziomu.
	 * @param gameOverListener - obiekt nasluchujacy zdarzen w grze
	 */
	public BoardCanvas (final Board board, final GameOverListener gameOverListener) {
		this.board = board;
		this.gameOver = false;
		this.gameOverListener = gameOverListener;
		countDown = new Timer();
		this.bonus = new Bonus(board.getPolozenieBonusu(), bonusDim, board.getTypBonusu());
		this.platforms = new ArrayList<Platform>(board.getPolozeniePlatform().size());
		for (Point p : board.getPolozeniePlatform()) {
			platforms.add(new Platform(p, platformDim));
		}
		this.detector = new CollisionDetector(platforms, bonus);
		this.player = new Player(board.getPolozeniePoczatkoweGracza(), playerDim, detector);
		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent ce) {
                BoardCanvas.this.updateSize();
            }
		});
		addKeyListener(player);
	}
	
	public void addNotify() {
        super.addNotify();
        offScreen = createImage(defaultSize.width, defaultSize.height);
        offScreenGraphics = offScreen.getGraphics();
    }
	
	@Override
	public void update(Graphics g) {
		g.drawImage(offScreen, 0, 0, this);
	}
	
	@Override
	public void paint (Graphics g) {
		g.drawImage(offScreen, 0, 0, this);
	}
	
	void updateOffscreen() {
		offScreenGraphics.clearRect(0, 0, offScreen.getWidth(this), offScreen.getHeight(this));
		offScreenGraphics.drawImage (img, 0, 0, offScreen.getWidth(this), offScreen.getHeight(this), null);
		offScreenGraphics.setColor(Color.yellow);
        for (Platform p : platforms) {
        	p.paintPlatform(offScreenGraphics);
        }
        bonus.paintBonus(offScreenGraphics);
        player.paintPlayer(offScreenGraphics);
    }

	public void modifyLocation () {
		player.applyMovement();
	}

	void sleep() {
        try {
            Thread.sleep(30);
        } catch (InterruptedException ie) {
        }
    }

	public void run() {
		updateSize();
		countDown.scheduleAtFixedRate(new TimerTask() {
			private long counterMs = board.getCzasNaPrzejscie();
			@Override
			public void run() {
				BoardCanvas.this.gameOverListener.setTimer(counterMs-=100);
			}
		}, 100, 100);
        while (true) {
            modifyLocation();
        	updateOffscreen();
            repaint();
            sleep();
            if (gameOver)
            	break;
        }
        countDown.cancel();
        SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO przekazanie wynikow etc
				gameOverListener.gameOver();
			}
		});
    }
	
	/**
	 * Przelicza wielkosci zalezne od rozmiarow okna
	 * oraz tworzy obraz uzywany jako bufor (o dobrych wymiarach).
	 */
	public void updateSize() {
		int width = getWidth();
		int height = getHeight();
		double pxWidth = (double)width/scaleX;
		double pxHeight = (double)height/scaleY;
		double pxMaxSize = pxWidth < pxHeight ? pxWidth : pxHeight; 
        bonus.updateScaling(pxMaxSize);
        player.updateScaling(pxMaxSize);
        Dimension platformOnScreenDim = new Dimension((int)(platformDim.width * pxMaxSize),
        		(int)(platformDim.height * pxMaxSize));
        for (Platform p : platforms) {
        	p.updateScaling(platformOnScreenDim, pxMaxSize);
        }
        offScreen = createImage(getWidth(), getHeight());
        offScreenGraphics = offScreen.getGraphics();
    }

	public void endGame() {
		gameOver = true;
	}

	public void startGame() {
		thread = new Thread(this);
		gameOver = false;
		thread.start();
	}
}
