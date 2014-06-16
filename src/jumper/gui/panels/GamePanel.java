package jumper.gui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.FileNotFoundException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import jumper.controller.GameController;
import jumper.controller.GameListener;
import jumper.gui.FrameComponents;
import jumper.gui.canvas.BoardCanvas;

/**
 * Panel zawieraj¹cy grê.
 * Tworzy kontrolera gry. {@link #GameController(String, GameListener)}
 * Pokazuje aktualne informacje o grze.
 * Po skoñczeniu gry pokazuje panel koñca gry. {@link #endGame(int)}
 * @author Maurycy
 *
 */
@SuppressWarnings("serial")
public class GamePanel extends AbstractJumperPanel implements GameListener {
	
	private BoardCanvas canvas;
	private JLabel timerLabel = new JLabel();
	private JLabel scoreLabel = new JLabel();
	private JLabel livesLabel = new JLabel();
	private JPanel gameInfoPanel = new JPanel();
	
	/**
	 * Ustawia grafikê panelu.
	 * Zapamiêtuje panel zarz¹dzaj¹cy kartami.
	 * @param fc - panel zarz¹dzaj¹cy kartami.
	 */
	public GamePanel (FrameComponents fc) {
		super (fc);
		this.setLayout(new BorderLayout());
		gameInfoPanel.setLayout(new GridLayout(1, 3, 2 ,0));
		gameInfoPanel.setBackground(new Color(50));
		gameInfoPanel.add(timerLabel);
		gameInfoPanel.add(scoreLabel);
		gameInfoPanel.add(livesLabel);
		add(gameInfoPanel, BorderLayout.NORTH);
		
	}

	/**
	 * Przy pokazaniu siê panel tworzy kontrolera gry.
	 */
	@Override
	public void putOnTop() {
		try {
			GameController controller = new GameController("config.txt", this);
			controller.startGame();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(getFrameComponents().getCardPanel(),
					"Nie uda³o siê wczytaæ pliku konfiguracyjnego!", "B³¹d", JOptionPane.ERROR_MESSAGE);
			getFrameComponents().showMenu();
		}
	}

	/**
	 * Po skoñczeniu gry pokazuje panel koñca gry.
	 */
	@Override
	public void endGame(int gameScore) {
		getFrameComponents().showGameOver(gameScore);
	}

	/**
	 * Po planszy usuwa j¹.
	 */
	@Override
	public void endBoard(int boardScore, boolean passed) {
		remove(canvas);
		validate();
	}

	/**
	 * Tworzy now¹ planszê.
	 */
	@Override
	public void startNewBoard(BoardCanvas canvas) {
		this.canvas = canvas;
		add(canvas, BorderLayout.CENTER);
		validate();
		canvas.requestFocusInWindow();
	}

	/**
	 * Przekazuje ile czasu zosta³o graczowi.
	 */
	@Override
	public void setPozostalyCzas(long czas) {
		timerLabel.setText("Pozosta³o: " + czas/1000 + "s");
		timerLabel.setForeground(Color.orange);
	}

	/**
	 * Przekazuje ile punktów ma gracz na poziomie.
	 */
	@Override
	public void setScore(int score) {
		scoreLabel.setText("Punkty w tym poziomie: " + score);
		scoreLabel.setForeground(Color.orange);
	}

	/**
	 * Przekazuje ile ¿yæ zosta³o graczowi oraz ile ma punktów w sumie.
	 */
	@Override
	public void setLivesAndTotal(int lives, int totalScore) {
		livesLabel.setText("Punkty w sumie: " + totalScore + "   ¯ycia: " + lives);
		livesLabel.setForeground(Color.orange);
	}

	@Override
	public void oneUp() {
		// ignorujemy, nie spodziewamy sie takiego zdarzenia - my to zdarzenie wysylamy
	} 
	
}
