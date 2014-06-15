package jumper.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import jumper.model.Board.BoardFactory;

/**
 * Klasa wczytuje plik konfiguracyjny.
 * Metoda read.
 * @author Maurycy
 *
 */
public class ConfigFileOpener {
	
	private final int liczbaZyc;
	private final int punktyPlatforma;
	private final int punktyPremia;
	private final List<Board> listaPoziomow;
	
	public ConfigFileOpener (String configFileName) throws FileNotFoundException {
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
		BoardFactory factory = new BoardFactory(skaner, punktyPlatforma, punktyPremia);		
		while (skaner.hasNext()) {
			listaPoziomow.add(factory.getNextBoard());
		}
		skaner.close();
	}

	public int getLiczbaZyc() {
		return liczbaZyc;
	}

	
	public List<Board> getLevelsList () {
		return listaPoziomow;
	}
}
