package jumper.gui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import jumper.gui.FrameComponents;

/**
 * Panel zawierajacy menu glowne.
 * @author Maurycy
 *
 */
public class MenuPanel extends JumperPanel {
	
	
	private JPanel menuBackground;
	//private final Image img = Toolkit.getDefaultToolkit().createImage("menu_background.jpg");

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MenuPanel (FrameComponents fc) {
		super (fc);
		setBackground(new Color(102, 102, 255));
		setLayout(new BorderLayout());

		JButton newGameButton = new JButton("Nowa Gra");
		newGameButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				getFrameComponents().showGamePanel();
				
			}
		});
		newGameButton.setSize(40, 30);
		newGameButton.setBackground(Color.BLACK);
		newGameButton.setForeground(Color.white);
		add(newGameButton, BorderLayout.WEST);
		
		JButton highscoreButton = new JButton("Wyniki");
		highscoreButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				getFrameComponents().showHighscores();
				
			}
		});
		highscoreButton.setSize(40, 30);
		highscoreButton.setBackground(Color.yellow);
		highscoreButton.setForeground(Color.black);
		add(highscoreButton, BorderLayout.EAST);
					
	}
}
