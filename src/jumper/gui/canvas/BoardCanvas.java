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
	//private Thread trener = null;
	private Dimension defaultSize = new Dimension(600, 200);
	
	/**
	 * Kostruktor zapisuje tablice z ustawieniami element�w.
	 * @param board
	 */
	public BoardCanvas (Board board) {
		this.board = board;
		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent ce) {
                BoardCanvas.this.updateOffscrSize();
            }
		});
	}
	
	public void addNotify() {
        super.addNotify();
        offScreen = createImage(defaultSize.width, defaultSize.height);
        offScreenGraphics = offScreen.getGraphics();
    }
	
	public void paint (Graphics g) {
		g.drawImage(offScreen, 0, 0, this);
	}
	
	void updateOffscreen() {
		offScreenGraphics.clearRect(0, 0, offScreen.getWidth(this), offScreen.getHeight(this));
		offScreenGraphics.setColor(Color.yellow);
		// szerkosc platformy
        int szerPlatf = (getWidth() >> 4)-10 ;
        // wysokosc platformy
        int wysPlatf = getHeight() >> 6;
        int skala = getWidth() >> 4;
        //rysowanie platform
        for (Point p : board.getPolozeniePlatform()) {
        	offScreenGraphics.fillRect(p.x*skala, p.y*skala, szerPlatf, wysPlatf);
        }
        //offScreenGraphics.fillOval(board.getPolozenieBonusu().x*skala, board.getPolozenieBonusu().y*skala, wysPlatf, wysPlatf);
        //offScreenGraphics.fillOval(board.getPolozenieGracza().x*skala, board.getPolozenieGracza().y*skala, wysPlatf, szerPlatf);
        offScreenGraphics.drawImage(Toolkit.getDefaultToolkit().getImage("0.gif"), board.getPolozenieGracza().x*skala, board.getPolozenieGracza().y*skala, szerPlatf, szerPlatf, this);
        offScreenGraphics.drawImage(Toolkit.getDefaultToolkit().getImage("1.gif"), board.getPolozenieBonusu().x*skala, board.getPolozenieBonusu().y*skala, wysPlatf, wysPlatf, this);
    }
	
	public void modifyLocation () {
		//TODO
	}
	
	void sleep() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException ie) {
        }
    }
	
	public void run() {
        while (true) {
        	updateOffscreen();
            repaint();
            modifyLocation();
            sleep();
        }
    }
	
	public void updateOffscrSize() {
        offScreen = createImage(getWidth(), getHeight());
        offScreenGraphics = offScreen.getGraphics();
    }

}
