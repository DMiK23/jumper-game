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

import jumper.gui.canvas.components.Bonus;
import jumper.gui.canvas.components.Platform;
import jumper.gui.canvas.components.Player;
import jumper.model.Board;

/**
 * Plansza z gra. Rysuje elementy planszy przy pomocy podwojnego bufora.
 * @author Maurycy
 *
 */
@SuppressWarnings("serial")
public class BoardCanvas extends Canvas implements Runnable {
	
	private Image offScreen = null;
	private Graphics offScreenGraphics = null;	
	private final Board board;
	private final Player player;
	private final Bonus bonus;
	private final List<Platform> platforms;
	//private Thread trener = null;
	private final Dimension defaultSize = new Dimension(600, 200);
	private Dimension platformSize;
	private int skala;
	private int skalaPlatform;
	private Thread thread;
	private boolean gameOver;
	
	/**
	 * Tworzy elementy graficzne
	 * @param board - obiekt przechowujacy parametry poziomu.
	 */
	public BoardCanvas (Board board) {
		this.board = board;
		gameOver = false;
		player = new Player(board.getPolozeniePoczatkoweGracza());
		bonus = new Bonus(board.getPolozenieBonusu(), board.getTypBonusu());
		platforms = new ArrayList<Platform>(board.getPolozeniePlatform().size());
		for (Point p : board.getPolozeniePlatform()) {
			platforms.add(new Platform(p));
		}
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
        	p.paintPlatform(offScreenGraphics, skalaPlatform, platformSize);
        }
        bonus.paintBonus(offScreenGraphics, skalaPlatform);
        player.paintPlayer(offScreenGraphics, skalaPlatform);
    }

	public void modifyLocation () {
		//TODO
	}

	void sleep() {
        try {
            Thread.sleep(30);
        } catch (InterruptedException ie) {
        }
    }

	public void run() {
		updateSize();
        while (true) {
            modifyLocation();
        	updateOffscreen();
            repaint();
            sleep();
            if (gameOver)
            	return;
        }
    }
	
	/**
	 * Przelicza wielkosci zalezne od rozmiarow okna
	 * oraz tworzy obraz uzywany jako bufor (o dobrych wymiarach).
	 */
	public void updateSize() {
        skala = getWidth() >> 7;
        skalaPlatform = getWidth() >> 4;
        platformSize = new Dimension(skalaPlatform - 10, getHeight() >> 6);
        offScreen = createImage(getWidth(), getHeight());
        offScreenGraphics = offScreen.getGraphics();
    }

	public void endGame() {
		// TODO przekazanie wynikow etc
		gameOver = true;
	}

	public void startGame() {
		thread = new Thread(this);
		gameOver = false;
		thread.start();
	}
}
