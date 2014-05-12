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
	 * Method called after object is displayed in parent layout.
	 */
	public void putOnTop() {
		// do nothing by default
	}
	
}
