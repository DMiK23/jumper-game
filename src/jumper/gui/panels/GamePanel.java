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
	
	public GamePanel (FrameComponents fc) throws FileNotFoundException {
		super (fc);
		setLayout(new BorderLayout());
		
		JButton gameOverButton = new JButton("Umrzyj");
		gameOverButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				getComponenta().showGameOver();
				
			}
		});
		add(gameOverButton, BorderLayout.NORTH); 
		config = new ConfigFileOpener("config.txt");
		
		add(new BoardCanvas(config.getLevelsList().get(0)));
		
		

	}

}
