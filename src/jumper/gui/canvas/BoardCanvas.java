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
	private final List<Platform> platforms;
	//private Thread trener = null;
	private final Dimension defaultSize = new Dimension(600, 200);
	private Dimension platformSize;
	private int skala;
	
	/**
	 * Kostruktor zapisuje tablice z ustawieniami elementów.
	 * @param board - obiekt przechowujacy parametry poziomu.
	 */
	public BoardCanvas (Board board) {
		this.board = board;
		platforms = new ArrayList<Platform>(board.getPolozeniePlatform().size());
		for (Point p : board.getPolozeniePlatform()) {
			platforms.add(new Platform(p.x, p.y));
		}
		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent ce) {
                BoardCanvas.this.updateSize();
            }
		});
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
        // rysowanie platform
        for (Point p : board.getPolozeniePlatform()) {
        	offScreenGraphics.fillRect(p.x*skala, p.y*skala, platformSize.width, platformSize.height);
        }
        offScreenGraphics.drawImage(Toolkit.getDefaultToolkit().getImage("0.gif"),
        		board.getPolozenieGracza().x*skala, board.getPolozenieGracza().y*skala,
        		platformSize.height, platformSize.width, this);
        offScreenGraphics.drawImage(Toolkit.getDefaultToolkit().getImage("1.gif"),
        		board.getPolozenieBonusu().x*skala, board.getPolozenieBonusu().y*skala,
        		platformSize.height, platformSize.width, this);
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
        	updateOffscreen();
            repaint();
            modifyLocation();
            sleep();
        }
    }
	
	/**
	 * Przelicza wielkosci zalezne od rozmiarow okna,
	 * oraz tworzy obraz uzywany jako bufor (o dobrych wymiarach).
	 */
	public void updateSize() {
        skala = getWidth() >> 4;
        platformSize = new Dimension(skala - 10, getHeight() >> 6);
        offScreen = createImage(getWidth(), getHeight());
        offScreenGraphics = offScreen.getGraphics();
    }
}
