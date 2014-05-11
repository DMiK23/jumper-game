package jumper.model;

import java.awt.Point;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

/**
 * Przechowuje rozklad elementow planszy i gracza.
 * Dany poziom swoj indywidualny numer.
 * 
 * @author Maurycy
 *
 */

public class Board {

	private final int numerPoziomu;
	private final List<Point> polozeniePlatform;
	private final Point polozenieGracza;
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
		polozenieGracza = pg;
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
	
	public Point getPolozenieGracza() {
		return polozenieGracza;
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
		
		/**
		 * Zapamietuje skaner z ktorego bedzie wczytywac level.
		 * @param scanner
		 */
		public BoardFactory (Scanner scanner) {
			skaner = scanner;
		}
		
		/**
		 * Wczytuje 1plansze o okreslonym formacie ze skanera.
		 * @return plansza stworzona na podstawie odczytanch danych.
		 */
		public Board getNextBoard () {
			int np = skaner.nextInt();
			long cnp = skaner.nextLong();
			Point pg = new Point(skaner.nextInt(), skaner.nextInt());
			BonusTypeEnumerator tb = BonusTypeEnumerator.create(skaner.nextInt());
			Point pb = new Point(skaner.nextInt(), skaner.nextInt());
			List<Point> pp = new Vector<Point>();
			do {
				pp.add(new Point(skaner.nextInt(), skaner.nextInt()));
			} while (!skaner.hasNext(boardLimiter));
			skaner.next(boardLimiter);
			return new Board (np, pp, pg, tb, pb, cnp );
		}
		
	}
}
