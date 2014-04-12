package jumper;

import javax.swing.SwingUtilities;

import jumper.gui.MainFrame;

public class Bootstrapper {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new MainFrame().setVisible(true);
				
			}
		});

	}

}
