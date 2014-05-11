package jumper.gui.canvas;

public class Bonus extends BoardObject{
	
	private int name;
	
	public Bonus (int x, int y) {
		super(x,y);
	}
	
	public void setName (int n) {
		name = n;
	}
	public int getName () {
		return name;
	}

}
