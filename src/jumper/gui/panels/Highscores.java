package jumper.gui.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;

import jumper.gui.FrameComponents;

public class Highscores extends JumperPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Highscores(FrameComponents fc) {
		super (fc);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		JTextField wyniki = new JTextField("Wyniki:\n 1\n 2");
		wyniki.setAlignmentX(CENTER_ALIGNMENT);
		this.add(wyniki);
		JButton powrot = new JButton("Powrot");
		powrot.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				getComponenta().showMenu();
				
			}
		});
		add(powrot);
		
	}
	
}
