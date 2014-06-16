package jumper.gui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import jumper.gui.FrameComponents;
import jumper.model.HighScoreManager;
import jumper.model.Score;

/**
 * Panel konca gry. Pokazuje wynik gracza.
 * Pozwala na zapisanie wyniku wraz z nickiem gracza.
 * @author Maurycy
 * 
 */
@SuppressWarnings("serial")
public class GameOverPanel extends AbstractJumperPanel {

	private JLabel wyniki = new JLabel("Punkty: ----");
	private JLabel sukcesLabel = new JLabel();
	private JPanel scoreInfoPanel = new JPanel();
	private final JButton powrotButton;
	// kontekst
	private int wynikGracza = 0;
	private HighScoreManager hsManager = new HighScoreManager();

	/**
	 * Ustawia grafik� panelu.
	 * Zapami�tuje panel zarz�dzaj�cy kartami.
	 * @param fc - panel zarz�dzaj�cy kartami.
	 */
	public GameOverPanel(FrameComponents fc) {
		super(fc);
		setLayout(new BorderLayout());
		scoreInfoPanel.add(wyniki);
		scoreInfoPanel.setBackground(new Color(50));
		add(scoreInfoPanel, BorderLayout.CENTER);
		add(sukcesLabel, BorderLayout.NORTH);
		powrotButton = new JButton("Powrot do menu");
		powrotButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				getFrameComponents().showMenu();

			}
		});

		add(powrotButton, BorderLayout.SOUTH);
	}

	/**
	 * W przypadku gdy wynik zalicza si� do najlepszych, prosi gracza o nick.
	 * Wy�witla r�wnie� wtedy inormacj� o zakwalifikowaniu sie do najlepszych wynik�w.
	 */
	@Override
	public void putOnTop() {
		wyniki.setText(String.format("Punkty: %d", wynikGracza));
		wyniki.setForeground(Color.orange);
		wyniki.setFont(new Font("Tahoma", Font.BOLD, 35));
		if (hsManager.isHighScore(wynikGracza)) {
			String nazwa = JOptionPane.showInputDialog(this, "Wpisz sw�j nick");
			Score score = new Score(wynikGracza, nazwa == null
					|| nazwa.isEmpty() ? "anonim" : nazwa);
			hsManager.addNewScore(score);
			sukcesLabel.setText(String.format("Jestes w TOP %d !!!",
					HighScoreManager.LICZBA_WYNIKOW));
			try {
				hsManager.saveScores();
			} catch (IOException e) {
				JOptionPane.showConfirmDialog(this,
						"Wyst�pi� b��d zapisu najlepszych wynikow!", "B��d!",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			sukcesLabel.setText("");
		}
		powrotButton.requestFocusInWindow();
	}

	/**
	 * Przekazuje panelowi wynik i menad�era wynik�w.
	 * @param wynikGracza - punktu zdobyte w trakcie gry.
	 * @param hsManager - menad�er wynik�w.
	 */
	public void setContext(int wynikGracza, HighScoreManager hsManager) {
		this.wynikGracza = wynikGracza;
		this.hsManager = hsManager;
	}

}
