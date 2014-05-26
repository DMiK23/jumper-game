package jumper.gui.panels;

import java.awt.BorderLayout;
import java.io.FileNotFoundException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import jumper.gui.FrameComponents;
import jumper.gui.canvas.BoardCanvas;
import jumper.model.ConfigFileOpener;

@SuppressWarnings("serial")
public class GamePanel extends JumperPanel {
	
	private final ConfigFileOpener config;
	private final BoardCanvas canvas;
	private JLabel timerLabel;
	
	public GamePanel (FrameComponents fc) {
		super (fc);
		this.setLayout(new BorderLayout());
		ConfigFileOpener readCfg = null;
		BoardCanvas tmpCanvas = null;
		try {
			readCfg = new ConfigFileOpener("config.txt");
			tmpCanvas = new BoardCanvas(readCfg.getLevelsList().get(0), this);
			add(tmpCanvas, BorderLayout.CENTER);
		} catch (FileNotFoundException e) {
			readCfg = null;
			// informujemy o tym w funkcji putOnTop:
			// zakladamy ze wtedy config == null
		} finally {
			config = readCfg;
			canvas = tmpCanvas;
		}
		timerLabel = new JLabel();
		add(timerLabel, BorderLayout.NORTH);
//		// TODO guzik - do schowania (pokazywany na koniec planszy)
//		JButton gameOverButton = new JButton("Zgin");
//		gameOverButton.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				canvas.endGame();
//				getFrameComponents().showGameOver();
//			}
//		});
//		add(gameOverButton, BorderLayout.NORTH); 
	}

	@Override
	public void putOnTop() {
		if (config == null) {
			JOptionPane.showMessageDialog(getFrameComponents().getCardPanel(),
					"Nie uda³o siê wczytaæ pliku konfiguracyjnego!", "B³¹d", ERROR, null);
			getFrameComponents().showMenu();
		}
		canvas.requestFocusInWindow();
		canvas.startGame();
	}

	public void gameOver() {
		getFrameComponents().showGameOver();
	}
	
	public void setTimer(long milisecsLeft) {
		if (milisecsLeft < 0) {
			canvas.endGame();
		}
		timerLabel.setText("pozosta³o: " + milisecsLeft/1000 + "s");
	}
}
