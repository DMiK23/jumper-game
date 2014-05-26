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
	
	private int lastScale;
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
			setX(getX() + 1); 
			if (detector.collision(this))
				setX(getX() - 1);
			break;
		case KeyEvent.VK_LEFT  :
			setX(getX() - 1); 
			if (detector.collision(this))
				setX(getX() + 1);
			break;
		case KeyEvent.VK_UP    :
			setY(getY() - 1); 
			if (detector.collision(this))
				setY(getY() + 1);
			break;
		case KeyEvent.VK_DOWN  :
			setY(getY() + 1); 
			if (detector.collision(this))
				setY(getY() - 1);
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
	public void updateScaling(Dimension newOnScreenDimension, int newScale) {
		super.updateScaling(newOnScreenDimension, newScale);
		lastScale = newScale;
	}
	
	@Override
	public void updateScaling(int newScale) {
		super.updateScaling(newScale);
		lastScale = newScale;
	}

	private void setX(int newX) {
		p.x = newX < 0 ? 0 : (newX > 127 ? 127 : newX);
		onScreenPoint.x = p.x * lastScale;
	}
	
	private void setY(int newY) {
		p.y = newY < 0 ? 0 : (newY > 127 ? 127 : newY);
		onScreenPoint.y = p.y * lastScale;
	}
}
