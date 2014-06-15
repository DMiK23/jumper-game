package jumper.gui.panels;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.FileNotFoundException;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import jumper.gui.FrameComponents;
import jumper.gui.canvas.BoardCanvas;
import jumper.model.controllers.GameController;
import jumper.model.controllers.GameListener;

@SuppressWarnings("serial")
public class GamePanel extends JumperPanel implements GameListener {
	
	private BoardCanvas canvas;
	private JLabel timerLabel = new JLabel();
	private JLabel scoreLabel = new JLabel();
	private JLabel livesLabel = new JLabel();
	private JPanel gameInfoPanel = new JPanel();
	
	public GamePanel (FrameComponents fc) {
		super (fc);
		this.setLayout(new BorderLayout());
		gameInfoPanel.setLayout(new GridLayout(2, 3, 2 ,0));
		//gameInfoPanel.add(timerLabel);
		gameInfoPanel.add(scoreLabel);
		gameInfoPanel.add(timerLabel);
		gameInfoPanel.add(livesLabel);
		add(gameInfoPanel, BorderLayout.NORTH);
		
	}

	@Override
	public void putOnTop() {
		try {
			GameController controller = new GameController("config.txt", this);
			controller.startGame();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(getFrameComponents().getCardPanel(),
					"Nie uda³o siê wczytaæ pliku konfiguracyjnego!", "B³¹d", ERROR, null);
			getFrameComponents().showMenu();
		}
	}

	@Override
	public void endGame(int gameScore) {
		// TODO przekazanie wynikow
		getFrameComponents().showGameOver();
	}

	@Override
	public void endBoard(int boardScore, boolean passed) {
		remove(canvas);
		validate();
	}

	@Override
	public void startNewBoard(BoardCanvas canvas) {
		this.canvas = canvas;
		add(canvas, BorderLayout.CENTER);
		validate();
		canvas.requestFocusInWindow();
	}

	@Override
	public void setPozostalyCzas(long czas) {
		timerLabel.setText("pozosta³o: " + czas/1000 + "s");
	}

	@Override
	public void setScore(int score) {
		scoreLabel.setText("Punkty: " + score);		
	}

	@Override
	public void setLives(int lives) {
		livesLabel.setText("¯ycia: " + lives);
		
	}

	@Override
	public void oneUp() {
	} 
	
}
