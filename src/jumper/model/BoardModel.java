package jumper.model;

import java.awt.Point;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

/**
 * Przechowuje rozklad elementow planszy i gracza dla danego poziomu.
 * Dany poziom ma swoj indywidualny numer.
 * 
 * Polozenia objektow sa wyrazone wielkosciami (0-16;0-8),
 * gdzie puntk (0,0) to lewy gorny rog, a (16,8) to prawy dolny.
 * 
 * @author Maurycy
 *
 */

public class BoardModel {

	private final int numerPoziomu;
	private final List<Point> polozeniePlatform;
	private final Point polozeniePoczatkoweGracza;
	private final BonusTypeEnumerator typBonusu;
	private final Point polozenieBonusu;
	private final long czasNaPrzejscie;
	private final int punktyPlatforma;
	private final int punktyPremia;
	
	/**
	 * Tworzy model planszy z paramtrami.
	 * @param numpoz - numer poziomu
	 * @param polplat - lista wspolzednych platform
	 * @param polgrac - plozenie poczatkowe gracza
	 * @param typbon - typ bonusa
	 * @param polbon - polozenie bonusu
	 * @param cnp - czas na przejscie planszy
	 * @param punplat - punkty za znikniecie platformy
	 * @param punprem - premia punktowa
	 */
	public BoardModel (int numpoz, List<Point> polplat, Point polgrac, BonusTypeEnumerator typbon,
			Point polbon, long cnp, int punplat, int punprem) {
		numerPoziomu = numpoz;
		polozeniePlatform = polplat;
		polozeniePoczatkoweGracza = polgrac;
		typBonusu = typbon;
		polozenieBonusu = polbon;
		czasNaPrzejscie = cnp;
		punktyPlatforma = punplat;
		punktyPremia = punprem;
	}
	
	/**
	 * Zwraca numer poziomu.
	 * @return numer poziomu.
	 */
	public int getNumerPoziomu() {
		return numerPoziomu;
	}
	
	/**
	 * Zwraca po³o¿enie platform.
	 * @return po³o¿enie platform.
	 */
	public List<Point> getPolozeniePlatform() {
		return polozeniePlatform;
	}
	
	/**
	 * Zwraca po³o¿enie poczatkowe gracza.
	 * @return po³o¿enie poczatkowe gracza.
	 */
	public Point getPolozeniePoczatkoweGracza() {
		return (Point) polozeniePoczatkoweGracza.clone();
	}
	
	/**
	 * Zwraca po³o¿enie bonusu.
	 * @return po³o¿enie bonusu.
	 */
	public Point getPolozenieBonusu() {
		return (Point) polozenieBonusu.clone();
	}
	
	/**
	 * Zwraca czas na przejœcie.
	 * @return czas na przejœcie.
	 */
	public long getCzasNaPrzejscie() {
		return czasNaPrzejscie;
	}
	
	/**
	 * Zwraca typ bonusu.
	 * @return typ bonusu.
	 */
	public BonusTypeEnumerator getTypBonusu() {
		return typBonusu;
	}
	
	/**
	 * Zwraca punkty za platformê.
	 * @return punkty za platformê.
	 */
	public int getPunktyPlatforma() {
		return punktyPlatforma;
	}
	
	/**
	 * Zwraca punkty za bonus punktowy.
	 * @return punkty za bonus punktowy.
	 */
	public int getPunktyPremia() {
		return punktyPremia;
	}
	
	/**
	 * Wczytuje dane planszy z pliku.
	 * Ustawia proporcje i rozdzielczoœæ planszy.
	 * @author Maurycy
	 *
	 */
	public static class BoardFactory {
		
		private Scanner skaner;
		private final int punktyPlatforma;
		private final int punktyPremia;
		public final static String boardLimiter = "=====";
		public static final int scaleX = 1024;	//musi byæ potêg¹ 2
		public static final double ratio = 0.5;
		public static final int scaleY = (int)(scaleX * ratio);
		
		/**
		 * Zapamietuje skaner z ktorego bedzie wczytywac level.
		 * @param scanner przechowuje wczytane z pliku konfiguracujnego dane
		 * @param punktyPlatforma - punkty za str¹cenie platformy.
		 * @param punktyPremia - premia za bonuz punktowy.
		 */
		public BoardFactory (Scanner scanner, int punktyPlatforma, int punktyPremia) {
			skaner = scanner;
			this.punktyPlatforma = punktyPlatforma;
			this.punktyPremia = punktyPremia;
		}
		
		/**
		 * Wczytuje 1plansze o okreslonym formacie ze skanera.
		 * Zajmuje sie przeskalowaniem z 16x8 do 1024x512 pol.
		 * @return plansza stworzona na podstawie odczytanych danych.
		 */
		public BoardModel getNextBoard () {
			int np = skaner.nextInt();
			long cnp = skaner.nextLong() * 1000;
			Point pg = new Point(skaner.nextInt() * (scaleX/16), skaner.nextInt() * (scaleY/((int)(16*ratio))));
			BonusTypeEnumerator tb = BonusTypeEnumerator.create(skaner.nextInt());
			Point pb = new Point(skaner.nextInt() * (scaleX/16), skaner.nextInt() * (scaleY/((int)(16*ratio))));
			List<Point> pp = new Vector<Point>();
			do {
				pp.add(new Point(skaner.nextInt() * (scaleX/16), skaner.nextInt() * (scaleY/((int)(16*ratio)))));
			} while (!skaner.hasNext(boardLimiter));
			skaner.next(boardLimiter);
			return new BoardModel (np, pp, pg, tb, pb, cnp, this.punktyPlatforma, this.punktyPremia);
		}
		
	}
}
