package jumper.gui.canvas;

/**
 * Klasa abstrakcyjna po której dziedzicz¹ obiekty planszy.
 * @author Maurycy
 *
 */
public abstract class BoardObject {

	private int x;
	private int y;
	
	public BoardObject (int x1, int y1) {
		x = x1;
		y = y1;
	}
	
	public void setX (int x1) {
		x = x1;
	}
	
	public void setY (int y1) {
		y = y1;
	}
	public int getX () {
		return x;
	}
	
	public int getY () {
		return y;
	}
}
