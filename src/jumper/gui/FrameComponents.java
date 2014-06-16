package jumper.gui;

import java.awt.CardLayout;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;

import javax.swing.JPanel;

import jumper.gui.panels.GameOver;
import jumper.gui.panels.GamePanel;
import jumper.gui.panels.Highscores;
import jumper.gui.panels.MenuPanel;
import jumper.model.HighScoreManager;

/**
 * Tworzenie i zarzadzanie kartami w podanym panelu.
 * @author Maurycy
 *
 */
@SuppressWarnings("serial")
public class FrameComponents extends JPanel{
	
	private final JPanel cardPanel;
	private final CardLayout cardLayout;
	/* Panele (i ich nazwy) dodane do layoutu */
	private final Highscores scoresPanel;
	private final static String scoresPanelName = "wyniki";
	private GamePanel gamePanel;
	private final static String gamePanelName = "gra";
	private final GameOver gameOverPanel;
	private final static String gameOverPanelName = "koniec";
	private final MenuPanel menuPanel;
	private final static String menuPanelName = "menu";
	private HighScoreManager highScoreManager;
	
	/**
	 * Konfiguruje panel gry i zarzadza nim.
	 * Ustawia CardLayout i dodaje potrzebne panele,
	 * w tym menu i sama gre.
	 * @param mainPanel panel zawierajacy CardLayout i pozostale panele gry
	 */
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
		highScoreManager = new HighScoreManager();
	
	}
	
	/**
	 * 
	 * @return Panel-rodzic CardLayout-u.
	 */
	public JPanel getCardPanel() {
		return cardPanel;
	}
	
	/**
	 * Ustawia w widoku panel z najlepszymi wynikami.
	 */
	public void showHighscores () {
		cardLayout.show(cardPanel, scoresPanelName);
		scoresPanel.putOnTop();
	}
	
	/**
	 * Ustawia w widoku panel z gra.
	 */
	public void showGamePanel () {
		cardPanel.remove(gamePanel);
		gamePanel = new GamePanel(this);
		cardPanel.add(gamePanel, gamePanelName);
		cardPanel.validate();
		cardLayout.show(cardPanel, gamePanelName);
		gamePanel.putOnTop();
	}
	
	/**
	 * Ustawia w widoku panel informujacy o zakonczeniu gry z wynikiem.
	 */
	public void showGameOver (int gameScore) {
		gameOverPanel.setScore(gameScore);
		cardLayout.show(cardPanel, gameOverPanelName);
		gameOverPanel.putOnTop();
	}
	
	/**
	 * Ustawia w widoku panel menu.
	 */
	public void showMenu () {
		cardLayout.show(cardPanel, menuPanelName);
		menuPanel.putOnTop();
	}
}
