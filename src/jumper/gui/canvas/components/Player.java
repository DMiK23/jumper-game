package jumper.gui.canvas.components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import jumper.gui.canvas.CollisionDetector;

/**
 * Gracz.
 * @author Maurycy
 *
 */
public class Player extends BoardObject implements KeyListener {
	
	private final CollisionDetector detector;
	
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
			trySetX(getX() + 1);
			break;
		case KeyEvent.VK_LEFT  :
			trySetX(getX() - 1);
			break;
		case KeyEvent.VK_UP    :
			trySetY(getY() - 1);
			break;
		case KeyEvent.VK_DOWN  :
			trySetY(getY() + 1);
			break;
		}
		System.out.println("player got KeyEvent Pressed");
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("player got KeyEvent Released");
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("player got KeyEvent Typed");
	}

	@Override
	protected void setX(int newX) {
		super.setX(newX < 0 ? 0 : (newX > 127 ? 127 : newX));
	}
	
	@Override
	protected void setY(int newY) {
		super.setY(newY < 0 ? 0 : (newY > 127 ? 127 : newY));
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
	
	
	public void applyGravity () {
		trySetY(getY() + 1);
	}
}
