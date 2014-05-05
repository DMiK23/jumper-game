package jumper.gui;

import java.awt.CardLayout;

import javax.swing.JPanel;

import jumper.gui.panels.GameOver;
import jumper.gui.panels.GamePanel;
import jumper.gui.panels.Highscores;
import jumper.gui.panels.MenuPanel;

public class FrameComponents extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final JPanel cardPanel;
	private final CardLayout cardLayout;
	private final Highscores scores;
	private final GamePanel game;
	private final GameOver over;
	private final MenuPanel menu;
	
	public FrameComponents(JPanel mainPanel) {
		
		cardPanel = mainPanel;
		menu = new MenuPanel(this);
		scores = new Highscores(this);
		game = new GamePanel(this);
		over = new GameOver(this);
		cardLayout = new CardLayout();
		cardPanel.setLayout(cardLayout);
		cardPanel.add(menu, "menu");
		menu.getClass().getName();
		cardPanel.add(scores, "wyniki");
		cardPanel.add(game, "gra");
		cardPanel.add(over, "koniec");
	}
	
	
	public JPanel getCardPanel() {
		return cardPanel;
	}
	
	public void showHighscores () {
		cardLayout.show(cardPanel, "wyniki");
	}
	
	public void showGamePanel () {
		cardLayout.show(cardPanel, "gra");
	}
	
	public void showGameOver () {
		cardLayout.show(cardPanel, "koniec");
	}
	
	public void showMenu () {
		cardLayout.show(cardPanel, "menu");
	}
	
}
