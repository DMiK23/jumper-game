package jumper.gui;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Okno glowne.
 * @author Maurycy
 *
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	public MainFrame () {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600, 400);
		setMinimumSize(new Dimension(200, 150));
		setLayout(new BorderLayout());		
		JPanel cardPanel = new JPanel();
		/*FrameComponents zarzadca = */new FrameComponents(cardPanel);
		add(cardPanel);
	}
}
