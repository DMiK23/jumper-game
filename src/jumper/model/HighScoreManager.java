package jumper.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;

/**
 * Klasa wczytuje plik konfiguracyjny.
 * Metoda read.
 * @author Maurycy
 *
 */
public class HighScoreManager {
	
	private final List<Score> listaWynikow;
	private static final int liczbaWynikow = 10;
	private static final String highScoresFileName = new String("highscores.xml");
	
	public HighScoreManager () {
		listaWynikow = new ArrayList<>(liczbaWynikow);
		try {
			readFromFile();
		} catch (InvalidPropertiesFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			for (int i = 0; i < liczbaWynikow; i++) {
				listaWynikow.add(new Score(0, "---"));
			}
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("blad");
		}
		
	}
	
	public static /*HighScoreManager*/ void readFromFile() throws InvalidPropertiesFormatException, IOException, FileNotFoundException {		
		Properties properties = new Properties();
		properties.loadFromXML(new FileInputStream(highScoresFileName));
		List<Score> listaWynikow = new ArrayList<>(liczbaWynikow);
		for (int i = 0; i < liczbaWynikow; i++) {
			String name = properties.getProperty(String.format("%d%s", i, "username"));
			int score = Integer.parseInt(properties.getProperty(String.format("%d%s", i, "score")));
			listaWynikow.add(new Score(score, name));
		}
		//return new HighScoreManager(listaWynikow);
	}
	
	public HighScoreManager(List<Score> listaWynikow) {
		this.listaWynikow = listaWynikow;
	}
	
	public void saveScores (String highScoresFileName) throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		for (int i = 0; i < liczbaWynikow; i++) {
			Score wynik = listaWynikow.get(i);
			properties.put(String.format("%d%s", i, "username"), wynik.getName());
			properties.put(String.format("%d%s", i, "score"), wynik.getScorePoints());
		}
		properties.storeToXML(new FileOutputStream(highScoresFileName), null);
	}
		
	public void addNewScore (Score newScore) {
		if (newScore.getScorePoints() > listaWynikow.get(liczbaWynikow).getScorePoints()) {
			int i = liczbaWynikow - 1;
			while (newScore.getScorePoints() > listaWynikow.get(i).getScorePoints()) {
				i++;
			}
			listaWynikow.add(i, newScore);
		}
	}
	

}

