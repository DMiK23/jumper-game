package jumper.model;

import java.awt.Point;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

/**
 * Przechowuje rozklad elementow planszy i gracza dla danego poziomu.
 * Dany poziom ma swoj indywidualny numer.
 * 
 * Polozenia objektow sa wyrazone wielkosciami (0-16;0-16),
 * gdzie puntk (0,0) to lewy gorny rog, a (16,16) to prawy dolny.
 * 
 * @author Maurycy
 *
 */

public class Board {

	private final int numerPoziomu;
	private final List<Point> polozeniePlatform;
	private final Point polozeniePoczatkoweGracza;
	private final BonusTypeEnumerator typBonusu;
	private final Point polozenieBonusu;
	private final long czasNaPrzejscie;
	
	/**
	 * 
	 * @param np - numer poziomu
	 * @param pp - lista wspolzednych platform
	 * @param pg - plozenie poczatkowe gracza
	 * @param tb - typ bonusa
	 * @param pb - polozenie bonusu
	 * @param cnp - czas na przejscie planszy
	 */
	public Board (int np, List<Point> pp, Point pg, BonusTypeEnumerator tb, Point pb, long cnp) {
		numerPoziomu = np;
		polozeniePlatform = pp;
		polozeniePoczatkoweGracza = pg;
		typBonusu = tb;
		polozenieBonusu = pb;
		czasNaPrzejscie = cnp;
	}
	
	public int getNumerPoziomu() {
		return numerPoziomu;
	}
	
	public List<Point> getPolozeniePlatform() {
		return polozeniePlatform;
	}
	
	public Point getPolozeniePoczatkoweGracza() {
		return polozeniePoczatkoweGracza;
	}
	
	public Point getPolozenieBonusu() {
		return polozenieBonusu;
	}
	
	public long getCzasNaPrzejscie() {
		return czasNaPrzejscie;
	}
	
	public BonusTypeEnumerator getTypBonusu() {
		return typBonusu;
	}
	
	/**
	 * Wczytuje dane planszy z pliku.
	 * @author Maurycy
	 *
	 */
	public static class BoardFactory {
		
		private Scanner skaner;
		public final static String boardLimiter = "=====";
		public static final int scale = 1024; 
		
		/**
		 * Zapamietuje skaner z ktorego bedzie wczytywac level.
		 * @param scanner przechowuje wczytane z pliku konfiguracujnego dane
		 */
		public BoardFactory (Scanner scanner) {
			skaner = scanner;
		}
		
		/**
		 * Wczytuje 1plansze o okreslonym formacie ze skanera.
		 * Zajmuje sie przeskalowaniem z 16x16 do 1024x1024 pol.
		 * @return plansza stworzona na podstawie odczytanych danych.
		 */
		public Board getNextBoard () {
			int np = skaner.nextInt();
			long cnp = skaner.nextLong() * 1000;
			Point pg = new Point(skaner.nextInt() * (scale/16), skaner.nextInt() * (scale/16));
			BonusTypeEnumerator tb = BonusTypeEnumerator.create(skaner.nextInt());
			Point pb = new Point(skaner.nextInt() * (scale/16), skaner.nextInt() * (scale/16));
			List<Point> pp = new Vector<Point>();
			do {
				pp.add(new Point(skaner.nextInt() * (scale/16), skaner.nextInt() * (scale/16)));
			} while (!skaner.hasNext(boardLimiter));
			skaner.next(boardLimiter);
			return new Board (np, pp, pg, tb, pb, cnp );
		}
		
	}
}
