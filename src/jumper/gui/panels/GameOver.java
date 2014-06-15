package jumper.gui.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

import jumper.gui.FrameComponents;

/**
 * Panel konca gry. Pokazuje wynik gracza.
 * @author Maurycy
 *
 */
@SuppressWarnings("serial")
public class GameOver extends JumperPanel {
	
	private JLabel wyniki = new JLabel("Punkty: ----");

	public GameOver (FrameComponents fc) {
		super (fc);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		wyniki.setAlignmentX(CENTER_ALIGNMENT);
		this.add(wyniki);
		JButton powrot = new JButton("Powrot do menu");
		powrot.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				getFrameComponents().showMenu();
				
			}
		});
		add(powrot);
	}
	
	public void setScore (int gameScore) {
		wyniki.setText("Punkty: " + gameScore);
	}
	
}
