package jumper.gui.panels;

import javax.swing.JPanel;

import jumper.gui.FrameComponents;

/**
 * Klasa abstrakcyjna po ktorej dziedzicza wszystkie panele.
 * 
 * @author Maurycy
 * 
 */
public abstract class AbstractJumperPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private final FrameComponents fc;

	/**
	 * Konstruktor pozwala zapami�ta� panel zarz�dzaj�cy kartami.
	 * @param fc -panel zarz�dzaj�cy kartami.
	 */
	public AbstractJumperPanel(FrameComponents fc) {
		this.fc = fc;
	}

	/**
	 * Zwraca panel zarz�dzaj�cy kartami.
	 * @return panel zarz�dzaj�cy kartami.
	 */
	protected FrameComponents getFrameComponents() {
		return fc;
	}

	/**
	 * Metoda jest wo�ana przy prze��czaniu sie na dany panel, powiadamia panel, �e jest widoczny.
	 */
	public void putOnTop() {
		// do nothing by default
	}

}
