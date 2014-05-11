package jumper;

import javax.swing.SwingUtilities;

import jumper.gui.MainFrame;

public class Bootstrapper {

	public static void main(String[] args) {
		final MainFrame frame = new MainFrame();
        
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				frame.setVisible(true);
			}
		});
	}
}
