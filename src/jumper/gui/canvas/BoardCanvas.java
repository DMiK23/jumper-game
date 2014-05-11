package jumper.gui.canvas;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import jumper.model.Board;

/**
 * Plansza z gra. Rysuje elementy planszy przy pomocy podwojnego bufora.
 * @author Maurycy
 *
 */
public class BoardCanvas extends Canvas implements Runnable {
	
	Image offScreen = null;
	Graphics offScreenGraphics = null;	
	Board board;
	Thread trener = null;
	Dimension defaultSize = new Dimension(600, 200);
	/**
	 * Kostruktor zapisuje tablice z ustawieniami elementów.
	 * @param board
	 */
	public BoardCanvas (Board board) {
		this.board = board;
	}
	
	public void addNotify() {
        super.addNotify();
        offScreen = createImage(defaultSize.width, defaultSize.height);
        offScreenGraphics = offScreen.getGraphics();
        System.out.println("addNotify");
    }
	
	public void paint (Graphics g) {
		g.drawImage(offScreen, 0, 0, this);
	}
	
	void updateOffscreen() {
		offScreenGraphics.clearRect(0, 0, offScreen.getWidth(this), offScreen.getHeight(this));
		offScreenGraphics.setColor(Color.yellow);
        int szer = getWidth() >> 3;
        int wys = getWidth() >> 5;
        int skala = getWidth() >> 4;
        for (int i = 0; i < board.getPolozeniePlatform().size(); i++ ) {
        	offScreenGraphics.fillRect(board.getPolozeniePlatform().get(i).x*skala , board.getPolozeniePlatform().get(i).y*skala, szer, wys);
        }
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
        	System.out.println("run");
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
