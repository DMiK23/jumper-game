package jumper.gui.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;

import jumper.gui.FrameComponents;

/**
 * Panel konca gry. Bedzie tu wynik gracza.
 * @author Maurycy
 *
 */
public class GameOver extends JumperPanel {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GameOver (FrameComponents fc) {
		super (fc);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		JTextField wyniki = new JTextField("Punkty: 1234");
		wyniki.setAlignmentX(CENTER_ALIGNMENT);
		this.add(wyniki);
		JButton powrot = new JButton("Przegrales!!!!!!!");
		powrot.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				getFrameComponents().showMenu();
				
			}
		});
		add(powrot);
	}
	
}
