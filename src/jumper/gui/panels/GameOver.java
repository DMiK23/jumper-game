package jumper.gui.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import jumper.gui.FrameComponents;

public class GameOver extends JumperPanel {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GameOver (FrameComponents fc) {
		super (fc);
		JButton powrot = new JButton("Przegrales!!!!!!!");
		powrot.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				getComponenta().showMenu();
				
			}
		});
		add(powrot);
	}
	
}
