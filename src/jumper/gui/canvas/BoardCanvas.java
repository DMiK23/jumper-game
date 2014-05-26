package jumper.gui.canvas;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
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
import jumper.gui.panels.GamePanel;
import jumper.model.Board;

/**
 * Plansza z gra. Rysuje elementy planszy przy pomocy podwojnego bufora.
 * @author Maurycy
 *
 */
@SuppressWarnings("serial")
public class BoardCanvas extends Canvas implements Runnable {
	
	private static final Dimension platformDim = new Dimension(8, 2);
	private static final Dimension playerDim = new Dimension(2, 2);
	private static final Dimension bonusDim = new Dimension(1, 1);
	private Graphics offScreenGraphics = null;	
	private final Player player;
	private final Bonus bonus;
	private final List<Platform> platforms;
	private final Dimension defaultSize = new Dimension(600, 200);
	private Image offScreen = null;
	private Thread thread;
	private boolean gameOver;
	private final GamePanel gameOverListener;
	private final Timer countDown;
	private final Board board;
	private final CollisionDetector detector;
	
	/**
	 * Tworzy elementy graficzne
	 * @param board - obiekt przechowujacy parametry poziomu.
	 */
	public BoardCanvas (final Board board, final GamePanel gameOverListener) {
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
		offScreenGraphics.setColor(Color.yellow);
        for (Platform p : platforms) {
        	p.paintPlatform(offScreenGraphics);
        }
        bonus.paintBonus(offScreenGraphics);
        player.paintPlayer(offScreenGraphics);
    }

	public void modifyLocation () {

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
        int w = getWidth() >> 7;
        bonus.updateScaling(w);
        player.updateScaling(w);
        Dimension platformOnScreenDim = new Dimension(platformDim.width * w, platformDim.height * w);
        for (Platform p : platforms) {
        	p.updateScaling(platformOnScreenDim, w);
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
