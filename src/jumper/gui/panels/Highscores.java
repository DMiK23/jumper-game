package jumper.gui.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTextPane;

import jumper.gui.FrameComponents;
import jumper.model.HighScoreManager;
import jumper.model.Score;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.FlowLayout;

/**
 * Pokazywanie najlepszych wynikow.
 * 
 * @author Maurycy
 * 
 */
public class Highscores extends JumperPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JTextPane wynikiTextPane;
	private HighScoreManager hsManager = new HighScoreManager();

	public Highscores(FrameComponents fc) {
		super(fc);
		setBackground(Color.BLACK);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0};
		gridBagLayout.rowHeights = new int[] {0};
		gridBagLayout.columnWeights = new double[] { 1.0 };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0 };
		setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
				panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
				wynikiTextPane = new JTextPane();
				panel.add(wynikiTextPane);
				wynikiTextPane.setForeground(Color.BLACK);
				wynikiTextPane.setBackground(Color.BLACK);
				wynikiTextPane.setFont(new Font("Tahoma", Font.BOLD, 15));
				wynikiTextPane.setEnabled(false);
				wynikiTextPane.setEditable(false);
		JButton powrotButton = new JButton("Powrot");
		powrotButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				getFrameComponents().showMenu();

			}
		});
		GridBagConstraints gbc_powrotButton = new GridBagConstraints();
		gbc_powrotButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_powrotButton.anchor = GridBagConstraints.EAST;
		gbc_powrotButton.gridx = 0;
		gbc_powrotButton.gridy = 1;
		add(powrotButton, gbc_powrotButton);
	}

	@Override
	public void putOnTop() {
		StringBuilder scoreTextBuilder = new StringBuilder(String.format(
				"TOP %d:\n", HighScoreManager.LICZBA_WYNIKOW));
		List<Score> lista = hsManager.getHighScores();
		for (int i = 0; i < lista.size();) {
			Score score = lista.get(i);
			scoreTextBuilder.append(String.format("%d. %d\t- %s\n", ++i,
					score.getScorePoints(), score.getName()));
		}
		wynikiTextPane.setText(scoreTextBuilder.toString());
	}

	public void setHSManager(HighScoreManager hsManager) {
		this.hsManager = hsManager;
	}

}
