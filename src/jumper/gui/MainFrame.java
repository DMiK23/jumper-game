package jumper.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainFrame extends JFrame {

	private CardLayout layout;
	private JPanel mainPanel;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MainFrame () {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600, 400);
		setMinimumSize(new Dimension(200, 150));
		setLayout(new BorderLayout());
		mainPanel = new JPanel(layout = new CardLayout());
		add(mainPanel, BorderLayout.CENTER);
		// karty próbne dla makiety
		JPanel menuCard = new JPanel();
		menuCard.setLayout(new BoxLayout(menuCard, BoxLayout.PAGE_AXIS));
		
		JButton newGameButton = new JButton("Nowa Gra");
		JButton highscoresButton = new JButton("Najlepsze wyniki");
		newGameButton.setAlignmentX(CENTER_ALIGNMENT);
		
		newGameButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(mainPanel, "Game Over");				
			}
		});
		
		highscoresButton.setAlignmentX(CENTER_ALIGNMENT);
//		highscoresButton
		
		
		menuCard.add(highscoresButton);
		menuCard.add(newGameButton);
		
		JPanel highscoreCard = new JPanel();
		highscoreCard.setLayout(new BoxLayout(highscoreCard, BoxLayout.PAGE_AXIS));
		JLabel wyniki = new JLabel("Wyniki:/n 1/n 2");
		wyniki.setAlignmentX(CENTER_ALIGNMENT);
		highscoreCard.add(wyniki);
		
		JPanel gameOverCard = new JPanel();
		gameOverCard.setLayout(new BoxLayout(gameOverCard, BoxLayout.PAGE_AXIS));
		
		JButton backToMenuButton = new JButton("Powrót do menu");
		backToMenuButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(mainPanel, "Menu");
			}
		});
		
		backToMenuButton.setAlignmentX(CENTER_ALIGNMENT);
		JLabel napisPrzegranej = new JLabel("GAME OVER!!!");
		napisPrzegranej.setAlignmentX(CENTER_ALIGNMENT);
		gameOverCard.add(backToMenuButton);
		gameOverCard.add(napisPrzegranej );
		
		mainPanel.add(menuCard, "Menu");
		mainPanel.add(gameOverCard, "Game Over");
		mainPanel.add(highscoreCard, "Wyniki");
		layout.show(mainPanel, "Menu");
	}

}
