package jumper.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Okno glowne.
 * 
 * @author Maurycy
 * 
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private final FrameComponents components;

	/**
	 * Tworzy kontekst gry i panele z zawartoscia.
	 */
	public MainFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600, 400);
		setMinimumSize(new Dimension(200, 150));
		setLayout(new BorderLayout());
		JPanel cardPanel = new JPanel();
		components = new FrameComponents(cardPanel);
		this.add(cardPanel);
	}

	/**
	 * Nalezy wywolac po ustawieniu MainFrame visible(true). Pokazuje panel
	 * glownego menu.
	 */
	public void showMenu() {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				components.showMenu();
			}
		});
	}
}
