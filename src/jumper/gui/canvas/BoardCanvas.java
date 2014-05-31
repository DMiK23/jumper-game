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
 * Zajmuje sie rowniez przeskalowywaniem przy zmianie obszaru rysowanego.
 * @author Maurycy
 *
 */
@SuppressWarnings("serial")
public class BoardCanvas extends Canvas {
	
	public static final int scaleX = BoardFactory.scaleX; 
	public static final int scaleY = BoardFactory.scaleY; 
	public static final double ratio = BoardFactory.ratio;
	
	private static final Dimension platformDim = new Dimension(scaleX/16, scaleY/((int)(64*ratio)));
	private static final Dimension playerDim = new Dimension(scaleX/64, scaleY/((int)(64*ratio)));
	private static final Dimension bonusDim = new Dimension(scaleX/128, scaleY/((int)(128*ratio)));
	
	private Image offScreen = null;
	private Graphics offScreenGraphics = null;	
	private final Dimension defaultSize = new Dimension(600, 200);
	private final Image img = Toolkit.getDefaultToolkit().createImage("background.jpg");
	
	private final Player player;
	private final Bonus bonus;
	private final List<Platform> platforms;
	
	/**
	 * Tworzy elementy graficzne.
	 * @param board - obiekt przechowujacy parametry poziomu.
	 * @param playerCollDetector - obiekt kontrolujacy kolizje gracza
	 */
	public BoardCanvas (final Board board, final CollisionDetector playerCollDetector) {
		this.bonus = new Bonus(board.getPolozenieBonusu(), bonusDim, board.getTypBonusu());
		this.platforms = new ArrayList<Platform>(board.getPolozeniePlatform().size());
		for (Point p : board.getPolozeniePlatform()) {
			platforms.add(new Platform(p, platformDim));
		}
		this.player = new Player(board.getPolozeniePoczatkoweGracza(), playerDim, playerCollDetector);
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
	
	public void updateOffscreen() {
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
	
	public Bonus getBonus() {
		return bonus;
	}
	
	public List<Platform> getPlatforms() {
		return platforms;
	}
}
