package jumper.gui.canvas;

/**
 * Klasa abstrakcyjna po ktorej dziedzicza obiekty planszy.
 * @author Maurycy
 *
 */
public abstract class BoardObject {

	private int x;
	private int y;
	
	public BoardObject (int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setX (int x) {
		this.x = x;
	}
	
	public void setY (int y) {
		this.y = y;
	}
	public int getX () {
		return x;
	}
	
	public int getY () {
		return y;
	}
}
