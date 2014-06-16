package jumper.gui.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import jumper.gui.FrameComponents;
import jumper.model.Score;

/**
 * Panel konca gry. Pokazuje wynik gracza.
 * @author Maurycy
 *
 */
@SuppressWarnings("serial")
public class GameOver extends JumperPanel {
	
	private JLabel wyniki = new JLabel("Punkty: ----");
	private int wynikGracza;

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
	
	@Override
	public void putOnTop() {
		String nazwa =  JOptionPane.showInputDialog("Wpisz swój nick");
		Score score = new Score(wynikGracza, nazwa == null ? "anonim" : nazwa);
		// TODO addNewSco
	}
	
	public void setScore (int gameScore) {
		wyniki.setText("Punkty: " + gameScore);
		this.wynikGracza = gameScore;
	}
	
}
