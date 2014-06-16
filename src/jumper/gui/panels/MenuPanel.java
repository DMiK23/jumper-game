package jumper.gui.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;

import javax.swing.JButton;

import jumper.gui.FrameComponents;
import jumper.model.HighScoreManager;

/**
 * Panel zawierajacy menu glowne.
 * @author Maurycy
 *
 */
public class MenuPanel extends JumperPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MenuPanel (FrameComponents fc) {
		super (fc);
		JButton highscoreButton = new JButton("Wyniki");
		highscoreButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				getFrameComponents().showHighscores();
				
			}
		});
		add(highscoreButton);
		
		JButton newGameButton = new JButton("Nowa Gra");
		newGameButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				getFrameComponents().showGamePanel();
				
			}
		});
		add(newGameButton);
				
	}
}
