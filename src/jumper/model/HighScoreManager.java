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
 * {@link #LICZBA_WYNIKOW}.
 * 
 * @author Maurycy
 * 
 */
public class HighScoreManager {

	private final List<Score> listaWynikow;
	public static final int LICZBA_WYNIKOW = 10;
	public static final String highScoresFileName = "highscores.xml";

	/**
	 * Tworzy pusta liste 10 najlepszych wynikow.
	 */
	public HighScoreManager() {
		listaWynikow = new ArrayList<>(LICZBA_WYNIKOW);
		for (int i = 0; i < LICZBA_WYNIKOW; i++) {
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
	 *             jesli plik jest zle sformatowany.
	 * @throws IOException
	 *             jesli nastapil blad odczytu pliku.
	 * @throws FileNotFoundException
	 *             jesli plik nie zostal odnaleziony.
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
		List<Score> listaWynikow = new ArrayList<>(LICZBA_WYNIKOW);
		for (int i = 0; i < LICZBA_WYNIKOW; i++) {
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
		for (int i = 0; i < LICZBA_WYNIKOW; i++) {
			Score wynik = listaWynikow.get(i);
			properties.put(String.format("%d%s", i, "username"),
					wynik.getNick());
			properties.put(String.format("%d%s", i, "score"),
					Integer.toString(wynik.getPoints()));
		}
		properties.storeToXML(new FileOutputStream(highScoresFileName), null);
	}

	/**
	 * Dodaje wynik do listy, jesli miesci sie w pierwszych
	 * {@link HighScoreManager#LICZBA_WYNIKOW}.
	 * 
	 * @param newScore
	 *            - wynik do dodania do listy.
	 */
	public void addNewScore(Score newScore) {
		int newScorePoints = newScore.getPoints(); 
		// jesli ostatni wynik jest lepszy, na pewno nie dodajemy
		if (!isHighScore(newScorePoints)) {
			return;
		}
		// wynik na pewno wyzszy niz ostatni
		int i = 0;
		// znajdywanie pierwszego mniejszego wyniku
		while (newScorePoints <= listaWynikow.get(i).getPoints()) {
			++i; // indeksu nie pilnujemy bo na pewno miescimy sie w tabeli
		}
		listaWynikow.add(i, newScore);
	}

	/**
	 * Sprawdza czy podany wynik zostanie dolaczony do najlepszych wynikow.
	 * 
	 * @param newScorePoints
	 *            - wynik do sprawdzenia.
	 * @return true jesli wynik miesci sie wsrod najlpszych, false w p.p.
	 */
	public boolean isHighScore(int newScorePoints) {
		// jesli ostatni wynik jest lepszy lub rowny, na pewno nie dodajemy
		return newScorePoints > listaWynikow.get(LICZBA_WYNIKOW - 1)
				.getPoints();
	}

	/**
	 * Tworzy i zwraca liste {@link #LICZBA_WYNIKOW} najlepszych wynikow.
	 * 
	 * @return Utworzona liste najlepszych wynikow.
	 */
	public List<Score> getHighScores() {
		List<Score> resultList = new ArrayList<>(LICZBA_WYNIKOW);
		resultList.addAll(listaWynikow.subList(0, LICZBA_WYNIKOW));
		return resultList;
	}
}
