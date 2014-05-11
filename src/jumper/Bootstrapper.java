package jumper;

import java.io.FileNotFoundException;

import javax.swing.SwingUtilities;

import jumper.gui.MainFrame;

public class Bootstrapper {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				try {
					new MainFrame().setVisible(true);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});

	}

}
