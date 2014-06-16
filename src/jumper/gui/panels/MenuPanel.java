package jumper.gui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import jumper.gui.FrameComponents;

/**
 * Panel zawierajacy menu glowne.
 * Pozwala zacz¹æ naw¹ grê lub zobaczyæ listê najlepszych wyników. 
 * @author Maurycy
 * 
 */
public class MenuPanel extends AbstractJumperPanel {

	private static final long serialVersionUID = 1L;
	JButton newGameButton;
	JButton highscoreButton;

	/**
	 * Ustawia grafikê panelu.
	 * Zapamiêtuje panel zarz¹dzaj¹cy kartami.
	 * @param fc - panel zarz¹dzaj¹cy kartami.
	 */
	public MenuPanel(FrameComponents fc) {
		super(fc);
		setBackground(new Color(102, 102, 255));
		setLayout(new BorderLayout());

		newGameButton = new JButton("Nowa Gra");
		newGameButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				getFrameComponents().showGamePanel();

			}
		});
		newGameButton.setSize(40, 30);
		newGameButton.setBackground(Color.BLACK);
		newGameButton.setForeground(Color.white);
		add(newGameButton, BorderLayout.WEST);

		JButton highscoreButton = new JButton("Wyniki");
		highscoreButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				getFrameComponents().showHighscores();

			}
		});
		highscoreButton.setSize(40, 30);
		highscoreButton.setBackground(Color.yellow);
		highscoreButton.setForeground(Color.black);
		add(highscoreButton, BorderLayout.EAST);
		
		ImagePanel menuBackground = new ImagePanel();
		add(menuBackground, BorderLayout.CENTER);
	}
	
	/**
	 * Ustawia fokus na guzik nowej gry.
	 */
	@Override
	public void putOnTop() {
		newGameButton.requestFocusInWindow();
	}
	
	/**
	 * Tworzy panel z grafik¹ w menu.
	 * @author Maurycy
	 *
	 */
	public class ImagePanel extends JPanel{

		private static final long serialVersionUID = 1L;
		private BufferedImage image;

	    public ImagePanel() {
	       try {                
	          image = ImageIO.read(new File("menu_background.jpg"));
	       } catch (IOException ex) {
	    	   System.out.println("Plik najlepszych wynikow nie zosta³ odnaleziony!");
	       }
	    }

	    /**
	     * Dodaje obraz jpg do panelu.
	     */
	    @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);   
	        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
	    }

	}
}
