package jumper.gui.canvas.components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import jumper.model.Board.BoardFactory;
import jumper.model.controllers.CollisionDetector;
import jumper.model.controllers.PlayerListener;

/**
 * Gracz.
 * @author Maurycy
 *
 */
public class Player extends BoardObject implements KeyListener {

	private static int maxWysSkoku = (int)(0.75*(BoardFactory.scaleY >> 1));
	private boolean betterJump = false;
	private static final int dGrawitacji = maxWysSkoku >> 5;
	private static final int dSkoku = dGrawitacji << 1;
	private static final int dRuchuPoziomego = BoardFactory.scaleX >> 8;
	private Boolean ruchWLewo = false;
	private Boolean ruchWPrawo = false;
	private Boolean ruchWGore = false;
	private int wysSkoku = 0;
	private final Rectangle mock;
	private final CollisionDetector detector;
	private PlayerListener listener;
	
	public Player (Point p, Dimension dim, CollisionDetector det) {
		super(p, dim);
		detector = det;
		mock = new Rectangle(p, dim);
	}
	
	public void setListener(PlayerListener listener) {
		this.listener = listener;
	}
	
	public void paintPlayer(Graphics g)
	{
		g.drawImage(Toolkit.getDefaultToolkit().getImage("0.gif"),
				onScreenPoint.x, onScreenPoint.y,
				onScreenDim.width, onScreenDim.height, null);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_RIGHT :
			ruchWPrawo = true;
			ruchWLewo = false;
			break;
		case KeyEvent.VK_LEFT  :
			ruchWLewo = true;
			ruchWPrawo = false;
			break;
		case KeyEvent.VK_UP    :
			if(isOnPlatform()) {
				ruchWGore = true;
				wysSkoku = 0;
			}			
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_RIGHT :
			ruchWPrawo = false;
			break;
		case KeyEvent.VK_LEFT  :
			ruchWLewo = false;
			break;
		case KeyEvent.VK_UP  :
			ruchWGore = false;
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar() == 'p') {
			listener.playerWantsToPause();
		}
	}

	@Override
	protected void setX(int newX) {
		int xInBounds = newX < 0 ? 0 : (newX > (BoardFactory.scaleX -1) ? (BoardFactory.scaleX -1) : newX); 
		super.setX(xInBounds);
		mock.x = xInBounds;
	}
	
	@Override
	protected void setY(int newY) {
		int yInBounds = newY < 0 ? 0 : (newY > (BoardFactory.scaleY -1) ? (BoardFactory.scaleY -1) : newY);
		super.setY(yInBounds);
		mock.y = yInBounds;
	}
	
	public void betterJump () {
		betterJump = true;
	}
	
	private void tryAddToX(int dx) {
		int i = 0;
		int dxSign = dx < 0 ? -1 : (dx > 0 ? 1 : 0);
		dx = dx < 0 ? -dx : dx; // abs
		for (; i < dx; ++i) {
			mock.x += dxSign;
			if (detector.collision(mock, false)) { //false -> ruch w osi x -> nie patrzymy czy zeszlismy z platformy
				mock.x -= dxSign;
				break;
			}
		}
		setX(mock.x);
	}
	
	private void tryAddToY(int dy) {
		int i = 0;
		int dySign = dy < 0 ? -1 : (dy > 0 ? 1 : 0);
		dy = dy < 0 ? -dy : dy; // abs
		for (; i < dy; ++i) {
			mock.y += dySign;
			if (detector.collision(mock, true)) { //true -> ruch w osi y -> patrzymy czy zeszlismy z platformy
				mock.y -= dySign;
				break;
			}
		}
		setY(mock.y);
	}
	
	private boolean isOnPlatform () {
		mock.y += 1;
		if (detector.collision(mock, false)) { //false -> spr. war. skoku -> nie patrzymy czy zeszlismy z platformy
			mock.y -= 1;
			return true;
		}
		mock.y -= 1;
		return false;
	}
	
	public void applyMovement () {
		int dx = 0;
		int dy = 0;
		dy += dGrawitacji; // grawitacja
		dx += ruchWPrawo ? dRuchuPoziomego : 0;
		dx -= ruchWLewo ? dRuchuPoziomego : 0;
		if (ruchWGore) {
			if (wysSkoku < maxWysSkoku + (betterJump ? maxWysSkoku >> 1 : 0)) {
				dy -= dSkoku;
				wysSkoku += dSkoku;
			} else { // koniec zasiegu skoku
				ruchWGore = false;
			}
		}
		if (getY() + dy >= BoardFactory.scaleY && listener != null) {
			listener.onPlayerOutOfBoard();
		}
		tryAddToX(dx);
		tryAddToY(dy);
	}
}
