package jumper.gui;

import java.awt.CardLayout;
import java.io.FileNotFoundException;
import java.util.InvalidPropertiesFormatException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import jumper.gui.panels.GameOverPanel;
import jumper.gui.panels.GamePanel;
import jumper.gui.panels.HighscoresPanel;
import jumper.gui.panels.MenuPanel;
import jumper.model.HighScoreManager;

/**
 * Tworzy i zarzêdza wszytkiemi kartami w podanym panelu.
 * 
 * @author Maurycy
 * 
 */
@SuppressWarnings("serial")
public class FrameComponents extends JPanel {

	private final JPanel cardPanel;
	private final CardLayout cardLayout;
	/* Panele (i ich nazwy) dodane do layoutu */
	private final HighscoresPanel scoresPanel;
	private final static String scoresPanelName = "wyniki";
	private GamePanel gamePanel;
	private final static String gamePanelName = "gra";
	private final GameOverPanel gameOverPanel;
	private final static String gameOverPanelName = "koniec";
	private final MenuPanel menuPanel;
	private final static String menuPanelName = "menu";
	private HighScoreManager highScoreManager;

	/**
	 * Konfiguruje panel gry i zarzadza nim. Ustawia CardLayout i dodaje
	 * potrzebne panele, w tym menu i sama gre.
	 * 
	 * @param mainPanel
	 *            panel zawierajacy CardLayout i pozostale panele gry.
	 */
	public FrameComponents(JPanel mainPanel) {

		cardPanel = mainPanel;
		menuPanel = new MenuPanel(this);
		scoresPanel = new HighscoresPanel(this);
		gamePanel = new GamePanel(this);
		gameOverPanel = new GameOverPanel(this);
		cardLayout = new CardLayout();
		cardPanel.setLayout(cardLayout);
		cardPanel.add(menuPanel, menuPanelName);
		cardPanel.add(scoresPanel, scoresPanelName);
		cardPanel.add(gamePanel, gamePanelName);
		cardPanel.add(gameOverPanel, gameOverPanelName);
		highScoreManager = new HighScoreManager();
	}

	/**
	 * Zwraca panel zawieraj¹cy CardLayout i pozostale panele gry.
	 * @return Panel-rodzic CardLayout-u.
	 */
	public JPanel getCardPanel() {
		return cardPanel;
	}

	/**
	 * Ustawia w widoku panel z najlepszymi wynikami.
	 * Przekazuje mu te¿ menad¿era wyników.
	 */
	public void showHighscores() {
		scoresPanel.setHSManager(highScoreManager);
		cardLayout.show(cardPanel, scoresPanelName);
		scoresPanel.putOnTop();
	}

	/**
	 * Ustawia w widoku panel z gra.
	 */
	public void showGamePanel() {
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
	public void showGameOver(int gameScore) {
		gameOverPanel.setContext(gameScore, highScoreManager);
		cardLayout.show(cardPanel, gameOverPanelName);
		gameOverPanel.putOnTop();
	}

	/**
	 * Ustawia w widoku panel menu.
	 */
	public void showMenu() {
		cardLayout.show(cardPanel, menuPanelName);
		String errorMessage = null;
		try {
			highScoreManager = HighScoreManager.readFromFile();
		} catch (FileNotFoundException e) {
			errorMessage = "Plik najlepszych wynikow nie zosta³ odnaleziony!";
		} catch (InvalidPropertiesFormatException e) {
			errorMessage = "Plik najlepszych wynikow jest ¿le sformatowany!";
		} catch (Exception e) {
			errorMessage = "Podczas odczytywania pliku najlepszych wyników wyst¹pi³ nieznany b³¹d!";
		}
		if (errorMessage != null)
		{
			JOptionPane.showMessageDialog(menuPanel,
					errorMessage,
					"B³¹d!", JOptionPane.ERROR_MESSAGE);
			try {
				highScoreManager.saveScores();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(menuPanel,
						"Zapis czystego pliku wyników nie powiód³ siê!",
						"B³¹d!", JOptionPane.ERROR_MESSAGE);
			}
		}
		menuPanel.putOnTop();
	}
}
