package jumper.gui.panels;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import jumper.gui.FrameComponents;
import jumper.gui.canvas.BoardCanvas;
import jumper.model.ConfigFileOpener;

@SuppressWarnings("serial")
public class GamePanel extends JumperPanel {
	
	private final ConfigFileOpener config;
	private BoardCanvas canvas;
	
	public GamePanel (FrameComponents fc) {
		super (fc);
		setLayout(new BorderLayout());
		
		// trzeba ustalic jak sie wczytuje plik, chwilowo tutaj wrzuce blad ladowania
		ConfigFileOpener readCfg = null;
		try {
			readCfg = new ConfigFileOpener("config.txt");
			canvas = new BoardCanvas(readCfg.getLevelsList().get(0));
			add(canvas, BorderLayout.CENTER);
		} catch (FileNotFoundException e) {
			readCfg = null;
			// informujemy o tym w funkcji putOnTop:
			// zakladamy ze wtedy config == null
		} finally {
			config = readCfg;
		}
		// guzik - do schowania (pokazywany na koniec planszy)
		JButton gameOverButton = new JButton("Umrzyj");
		gameOverButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				getFrameComponents().showGameOver();
				
			}
		});
		add(gameOverButton, BorderLayout.NORTH); 
	}

	@Override
	public void putOnTop() {
		if (config == null) {
			JOptionPane.showMessageDialog(getFrameComponents().getCardPanel(),
					"Nie uda³o siê wczytaæ pliku konfiguracyjnego!", "B³¹d", ERROR, null);
			getFrameComponents().showMenu();
		}
		new Thread(canvas).start();
	}
}
