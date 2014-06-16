package jumper;

import java.awt.EventQueue;

import jumper.gui.MainFrame;

/**
 * Uruchamia program. Tworzy okno g³ówne programu i  wyœwietla menu.
 * @author Maurycy
 *
 */
public class JumperGameApp {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainFrame frame = new MainFrame();
				frame.setVisible(true);
				frame.showMenu();
			}
		});
	}
}
