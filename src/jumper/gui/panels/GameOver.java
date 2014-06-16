package jumper.gui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
	private JPanel scoreInfoPanel = new JPanel();

	public GameOver (FrameComponents fc) {
		super (fc);
		setLayout(new BorderLayout());
		scoreInfoPanel.add(wyniki);
		scoreInfoPanel.setBackground(new Color(50));
		add(scoreInfoPanel, BorderLayout.CENTER);
		JButton powrot = new JButton("Powrot do menu");
		powrot.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				getFrameComponents().showMenu();
				
			}
		});
		
		add(powrot, BorderLayout.SOUTH);
	}
	
	@Override
	public void putOnTop() {
		String nazwa =  JOptionPane.showInputDialog("Wpisz swój nick");
		Score score = new Score(wynikGracza, nazwa == null ? "anonim" : nazwa);
		// TODO addNewSco
	}
	
	public void setScore (int gameScore) {
		wyniki.setText("Punkty: " + gameScore);
		wyniki.setForeground(Color.orange);
		this.wynikGracza = gameScore;
	}
	
}
