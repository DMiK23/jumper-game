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
 * Zarzadza lista wynikow. Zajmuje sie zapisem ({@link #readFromFile(String)}) i
 * odczytem ({@link #saveScores(String)}) do pliku. Dodawanie nowych wynikow
 * nastepuje tylko jesli miesci sie w TOP X, gdzie X jest ustalone jako
 * {@link #liczbaWynikow}.
 * 
 * @author Maurycy
 * 
 */
public class HighScoreManager {

	private final List<Score> listaWynikow;
	private static final int liczbaWynikow = 10;
	private static final String highScoresFileName = "highscores.xml";

	/**
	 * Tworzy pusta liste 10 najlepszych wynikow.
	 */
	public HighScoreManager() {
		listaWynikow = new ArrayList<>(liczbaWynikow);
		for (int i = 0; i < liczbaWynikow; i++) {
			listaWynikow.add(new Score(0, "---"));
		}
	}

	/**
	 * Tworzy managera podanej listy wynikow.
	 * 
	 * @param listaWynikow
	 *            - lista wyniow zarzadzana od teraz przez managera.
	 */
	public HighScoreManager(List<Score> listaWynikow) {
		this.listaWynikow = listaWynikow;
	}

	/**
	 * Metoda wygody, dziala jak wywolanie {@link #readFromFile(String)} z
	 * argumentem {@link #highScoresFileName}.
	 * 
	 * @return Manager z wynikami odczytanymi z pliku.
	 * @throws InvalidPropertiesFormatException
	 *             - jesli plik jest zle sformatowany.
	 * @throws IOException
	 *             - jesli nastapil blad odczytu pliku.
	 * @throws FileNotFoundException
	 *             - jesli plik nie zostal odnaleziony.
	 */
	public static HighScoreManager readFromFile()
			throws InvalidPropertiesFormatException, IOException,
			FileNotFoundException {
		return readFromFile(highScoresFileName);
	}

	/**
	 * Laduje wyniki z pliku o podanej nazwie i tworzy managera zarzadzajacego
	 * tymi wynikami.
	 * 
	 * @param highScoresFileName
	 *            - nazwa pliku z ktorego wyniki maja byc odczytane.
	 * @return Manager z wynikami odczytanymi z pliku.
	 * @throws InvalidPropertiesFormatException
	 *             jesli plik jest zle sformatowany.
	 * @throws IOException
	 *             jesli nastapil blad odczytu pliku.
	 * @throws FileNotFoundException
	 *             jesli plik nie zostal odnaleziony.
	 */
	public static HighScoreManager readFromFile(String highScoresFileName)
			throws InvalidPropertiesFormatException, IOException,
			FileNotFoundException {
		Properties properties = new Properties();
		properties.loadFromXML(new FileInputStream(highScoresFileName));
		List<Score> listaWynikow = new ArrayList<>(liczbaWynikow);
		for (int i = 0; i < liczbaWynikow; i++) {
			String name = properties.getProperty(String.format("%d%s", i,
					"username"));
			int score = Integer.parseInt(properties.getProperty(String.format(
					"%d%s", i, "score")));
			listaWynikow.add(new Score(score, name));
		}
		return new HighScoreManager(listaWynikow);
	}

	/**
	 * Zapisuje wyniki z tego managera do pliku. Metoda wygody, rownowazne
	 * wywolaniu {@link #saveScores(String)} z argumentem
	 * {@link #highScoresFileName}.
	 * 
	 * @param highScoresFileName
	 *            nazwa pliku do ktorego wyniki zostana zapisane.
	 * @throws FileNotFoundException
	 *             jesli plik o podanej nazwie nie mogl zostac utworzony.
	 * @throws IOException
	 *             jesli wystapil blad operacji IO.
	 */
	public void saveScores() throws FileNotFoundException, IOException {
		saveScores(highScoresFileName);
	}

	/**
	 * Zapisuje wyniki z tego managera do pliku.
	 * 
	 * @param highScoresFileName
	 *            nazwa pliku do ktorego wyniki zostana zapisane.
	 * @throws FileNotFoundException
	 *             jesli plik o podanej nazwie nie mogl zostac utworzony.
	 * @throws IOException
	 *             jesli wystapil blad operacji IO.
	 */
	public void saveScores(String highScoresFileName)
			throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		for (int i = 0; i < liczbaWynikow; i++) {
			Score wynik = listaWynikow.get(i);
			properties.put(String.format("%d%s", i, "username"),
					wynik.getName());
			properties.put(String.format("%d%s", i, "score"),
					wynik.getScorePoints());
		}
		properties.storeToXML(new FileOutputStream(highScoresFileName), null);
	}

	/**
	 * Dodaje wynik do listy, jesli miesci sie w pierwszych
	 * {@link HighScoreManager#liczbaWynikow}.
	 * 
	 * @param newScore
	 *            - wynik do dodania do listy.
	 */
	public void addNewScore(Score newScore) {
		if (newScore.getScorePoints() <= listaWynikow.get(liczbaWynikow)
				.getScorePoints()) {
			return;
		}
		int i = liczbaWynikow - 1;
		while (newScore.getScorePoints() > listaWynikow.get(i).getScorePoints()) {
			i++;
		}
		listaWynikow.add(i, newScore);

	}

}
