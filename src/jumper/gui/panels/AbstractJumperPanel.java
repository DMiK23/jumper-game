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
	 * Konstruktor pozwala zapamiętać panel zarządzający kartami.
	 * @param fc -panel zarządzający kartami.
	 */
	public AbstractJumperPanel(FrameComponents fc) {
		this.fc = fc;
	}

	/**
	 * Zwraca panel zarządzający kartami.
	 * @return panel zarządzający kartami.
	 */
	protected FrameComponents getFrameComponents() {
		return fc;
	}

	/**
	 * Metoda jest wołana przy przełączaniu sie na dany panel, powiadamia panel, że jest widoczny.
	 */
	public void putOnTop() {
		// do nothing by default
	}

}
