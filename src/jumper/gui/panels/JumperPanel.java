package jumper.gui.panels;

import javax.swing.JPanel;

import jumper.gui.FrameComponents;

/**
 * Klasa abstrakcyjna po ktorej dziedzicza wszystkie panele.
 * @author Maurycy
 *
 */
public abstract class JumperPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final FrameComponents fc;
	
	public JumperPanel ( FrameComponents fc) {
		this.fc = fc;
	}
	
	protected FrameComponents getFrameComponents() {
		return fc;
	}
	
	/**
	 * Metoda jest wo³ana przy prze³¹czaniu sie na dany panel,
	 * powiadamia panel, ¿e jest widoczny.
	 */
	public void putOnTop() {
		// do nothing by default
	}
	
}
