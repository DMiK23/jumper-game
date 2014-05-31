package jumper.gui.panels;

import java.awt.BorderLayout;
import java.io.FileNotFoundException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import jumper.gui.FrameComponents;
import jumper.gui.canvas.BoardCanvas;
import jumper.model.controllers.GameController;
import jumper.model.controllers.GameListener;

@SuppressWarnings("serial")
public class GamePanel extends JumperPanel implements GameListener {
	
	private BoardCanvas canvas;
	private JLabel timerLabel;
	
	public GamePanel (FrameComponents fc) {
		super (fc);
		this.setLayout(new BorderLayout());
		timerLabel = new JLabel();
		add(timerLabel, BorderLayout.NORTH); 
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
	public void endBoard(int boardScore) {
		remove(canvas);
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
}
