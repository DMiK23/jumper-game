package jumper.gui.canvas.components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import jumper.gui.canvas.CollisionDetector;
import jumper.model.Board.BoardFactory;

/**
 * Gracz.
 * @author Maurycy
 *
 */
public class Player extends BoardObject implements KeyListener {
	
	private final CollisionDetector detector;
	private Boolean ruchWLewo = false;
	private Boolean ruchWPrawo = false;
	
	public Player (Point p, Dimension dim, CollisionDetector det) {
		super(p, dim);
		detector = det;
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
				trySetY(getY() - 130);
			}			
			break;
		}
		System.out.println("player got KeyEvent Pressed");
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
		}
		System.out.println("player got KeyEvent Released");
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("player got KeyEvent Typed");
	}

	@Override
	protected void setX(int newX) {
		super.setX(newX < 0 ? 0 : (newX > (BoardFactory.scale -1) ? (BoardFactory.scale -1) : newX));
	}
	
	@Override
	protected void setY(int newY) {
		super.setY(newY < 0 ? 0 : (newY > (BoardFactory.scale -1) ? (BoardFactory.scale -1) : newY));
	}
	
	private void trySetX(int newX) {
		int old = getX();
		setX(newX); 
		if (detector.collision(this))
			setX(old);
	}
	
	private void trySetY(int newY) {
		int old = getY();
		setY(newY); 
		if (detector.collision(this))
			setY(old);
	}
	
	private boolean isOnPlatform () {
		int old = getY();
		boolean wynik = false;
		setY(old + 1); 
		if (detector.collision(this))
			wynik = true;
		setY(old);
		return wynik;
	}
	
	public void applyMovement () {
		trySetY(getY() + 2);	//grawitacja
		if (ruchWPrawo == true) {
			trySetX(getX() + 2);
		}
		if (ruchWLewo == true) {
			trySetX(getX() - 2);
		}
	}
}
