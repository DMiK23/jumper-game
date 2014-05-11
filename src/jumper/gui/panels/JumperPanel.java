package jumper.gui.panels;

import javax.swing.JPanel;

import jumper.gui.FrameComponents;

public abstract class JumperPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final FrameComponents fc;
	
	public JumperPanel ( FrameComponents fc) {
		this.fc = fc;
	}
	
	protected FrameComponents getKomponent() {
		return fc;
	}
	
}
