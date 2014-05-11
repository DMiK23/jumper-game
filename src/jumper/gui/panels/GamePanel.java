package jumper.gui.panels;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;

import jumper.gui.FrameComponents;
import jumper.gui.canvas.BoardCanvas;
import jumper.model.ConfigFileOpener;

public class GamePanel extends JumperPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final ConfigFileOpener config;
	private BoardCanvas canvas;
	
	public GamePanel (FrameComponents fc) throws FileNotFoundException {
		super (fc);
		setLayout(new BorderLayout());
		
		JButton gameOverButton = new JButton("Umrzyj");
		gameOverButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				getFrameComponents().showGameOver();
				
			}
		});
		add(gameOverButton, BorderLayout.NORTH); 
		config = new ConfigFileOpener("config.txt");
		canvas = new BoardCanvas(config.getLevelsList().get(0));
		add(canvas, BorderLayout.CENTER);
		setVisible(true);
		System.out.println("thread");
		new Thread(canvas).start();
		//pack();
		

	}

}
