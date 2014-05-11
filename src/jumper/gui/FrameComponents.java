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
	/* Panele dodane do layoutu */
	private final Highscores scoresPanel;
	private final static String scoresPanelName = "wyniki";
	private final GamePanel gamePanel;
	private final static String gamePanelName = "gra";
	private final GameOver gameOverPanel;
	private final static String gameOverPanelName = "koniec";
	private final MenuPanel menuPanel;
	private final static String menuPanelName = "menu";
	
	public FrameComponents(JPanel mainPanel) {
		
		cardPanel = mainPanel;
		menuPanel = new MenuPanel(this);
		scoresPanel = new Highscores(this);
		gamePanel = new GamePanel(this);
		gameOverPanel = new GameOver(this);
		cardLayout = new CardLayout();
		cardPanel.setLayout(cardLayout);
		cardPanel.add(menuPanel, menuPanelName);
		cardPanel.add(scoresPanel, scoresPanelName);
		cardPanel.add(gamePanel, gamePanelName);
		cardPanel.add(gameOverPanel, gameOverPanelName);
	}
	
	
	public JPanel getCardPanel() {
		return cardPanel;
	}
	
	public void showHighscores () {
		cardLayout.show(cardPanel, scoresPanelName);
		scoresPanel.putOnTop();
	}
	
	public void showGamePanel () {
		cardLayout.show(cardPanel, gamePanelName);
		gamePanel.putOnTop();
	}
	
	public void showGameOver () {
		cardLayout.show(cardPanel, gameOverPanelName);
		gameOverPanel.putOnTop();
	}
	
	public void showMenu () {
		cardLayout.show(cardPanel, menuPanelName);
		menuPanel.putOnTop();
	}
	
}
