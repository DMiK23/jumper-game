package jumper.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import jumper.model.BoardModel.BoardFactory;

/**
 * Konfiguracja gry. Konstruktor wczytuje z pliku.
 * 
 * @author Maurycy
 * 
 */
public class GameConfiguration {

	private final int liczbaZyc;
	private final int punktyPlatforma;
	private final int punktyPremia;
	private final List<BoardModel> listaPoziomow;

	/**
	 * Wczytuje dane z pliku konfiguracyjnego i zleca ztworzenie modeli wszytkich plansz.
	 * @param configFileName - pliku konfiguracyjny.
	 * @throws FileNotFoundException  - jeœli nie ma pliku.
	 */
	public GameConfiguration(String configFileName)
			throws FileNotFoundException {
		Scanner skaner = new Scanner(new File(configFileName));
		if (!skaner.hasNext()) {
			skaner.close();
			throw new FileNotFoundException("nie znaleziono pliku");
		}
		liczbaZyc = skaner.nextInt();
		punktyPlatforma = skaner.nextInt();
		punktyPremia = skaner.nextInt();
		listaPoziomow = new Vector<>();
		skaner.next(BoardFactory.boardLimiter);
		BoardFactory factory = new BoardFactory(skaner, punktyPlatforma,
				punktyPremia);
		while (skaner.hasNext()) {
			listaPoziomow.add(factory.getNextBoard());
		}
		skaner.close();
	}

	/**
	 * Zwraca liczbê ¿yæ.
	 * @return - liczba ¿yæ.
	 */
	public int getLiczbaZyc() {
		return liczbaZyc;
	}

	/**
	 *  Zwraca listê modeli plansz.
	 * @return - lista modeli plansz.
	 */
	public List<BoardModel> getLevelsList() {
		return listaPoziomow;
	}
}
