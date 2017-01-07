package kolejki_zgloszen;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public final class KolejkaFifoNogrPr implements KolejkaI {
	private Zgloszenie[] bufor;
	private final Comparator<Zgloszenie> komparator;
	private int stan;
	
	private class KolejkaFifoNogrPrIt implements Iterator<Zgloszenie> {
		private KolejkaFifoNogrPr kolejka;
		
		private KolejkaFifoNogrPrIt() {
			kolejka = new KolejkaFifoNogrPr(KolejkaFifoNogrPr.this);
		}
		
		public boolean hasNext() {
			return !kolejka.kolejkaPusta();
		}
		
		public Zgloszenie next() {
			if (!hasNext()){
				throw new NoSuchElementException("Iterator zuzyty");
			}
			
			return kolejka.usun();
		}
	}
	
	public Iterator<Zgloszenie> iterator() {
		return new KolejkaFifoNogrPrIt();
	}
	
	private boolean buforPelny() {
		return stan == bufor.length - 1;
	}
	
	private void zmienDlugosc(int dl) {
		//assert dl != bufor.length && dl > stan;
		Zgloszenie[] tab = new Zgloszenie[dl];
		System.arraycopy(bufor, 1, tab, 1, stan);
		bufor = tab;
	}
	
	private boolean porownanie(int i, int j) {
		return komparator.compare(bufor[i], bufor[j]) < 0;
	}
	
	private void zamien(int i, int j) {
		Zgloszenie z = bufor[i];
		bufor[i] = bufor[j];
		bufor[j] = z;
	}
	
	private void przywrocStruktureOdDolu(int i) {
		while (i > 1 && porownanie(i / 2, i)) {
			zamien(i, i / 2);
			i /= 2;
		}
	}
	
	private void przywrocStruktureOdGory(int i) {
		while (2 * i <= stan) {
			int j = 2 * i;
			
			if (j < stan && porownanie(j, j + 1)) {
				++j;
			}
			
			if (!porownanie(i, j)) {
				break;
			}
			
			zamien(i, j);
			i = j;
		}
	}
	
	private boolean strukturaPoddrzewaPoprawna(int k) {
		if (k > stan) {
			return true;
		}
		
		int liscLewy = 2 * k;
		int liscPrawy = 2 * k + 1;
		
		if (liscLewy  <= stan && porownanie(k, liscLewy))  {
			return false;
		}
		
		if (liscPrawy <= stan && porownanie(k, liscPrawy)) {
			return false;
		}
		
		return strukturaPoddrzewaPoprawna(liscLewy) && strukturaPoddrzewaPoprawna(liscPrawy);
	}
	
	private boolean strukturaDrzewaPoprawna() {
		return strukturaPoddrzewaPoprawna(1);
	}
	
	public KolejkaFifoNogrPr(final int dlugosc) {
		if (dlugosc <= 0) {
			throw new IllegalArgumentException("Dlugosc mniejsza niz 1");
		}
		
		bufor = new Zgloszenie[dlugosc + 1];
		komparator = Zgloszenie.komparatorFifo();
	}
	
	public KolejkaFifoNogrPr() {
		this(30);
	}
	
	public KolejkaFifoNogrPr(final Zgloszenie[] tablica) {
		if (tablica == null) {
			throw new IllegalArgumentException("Tablica-parametr rowna null");
		}
		
		bufor = new Zgloszenie[(stan = tablica.length) + 1];
		System.arraycopy(tablica, 0, bufor, 1, tablica.length);
		komparator = Zgloszenie.komparatorFifo();
		
		for (int k = stan / 2; k >= 1; --k) {
			przywrocStruktureOdGory(k);
		}
		
		//assert strukturaDrzewaPoprawna();
	}
	
	public KolejkaFifoNogrPr(final KolejkaFifoNogrPr kolejka) {
		if (kolejka == null) {
			throw new IllegalArgumentException("Kolejka-parametr rowna null");
		}
		
		bufor = new Zgloszenie[kolejka.bufor.length];
		System.arraycopy(kolejka.bufor, 1, bufor, 1, kolejka.stan);
		komparator = Zgloszenie.komparatorFifo();
		stan = kolejka.stan;
		//assert strukturaDrzewaPoprawna();
	}
	
	public boolean kolejkaPusta() {
		return stan == 0;
	}
	
	public int stan() {
		return stan;
	}
	
	public void wstaw(Zgloszenie zgloszenie) throws KolejkaPelnaWyj {
		if (buforPelny()) {
			zmienDlugosc(2 * bufor.length);
		}
		
		bufor[++stan] = zgloszenie;
		przywrocStruktureOdDolu(stan);
		//assert strukturaDrzewaPoprawna();
	}
	
	public Zgloszenie nastepne() throws KolejkaPustaWyj {
		if (kolejkaPusta()) {
			throw new KolejkaPustaWyj();
		}
		
		return bufor[1];
	}
	
	public Zgloszenie usun() throws KolejkaPustaWyj {
		if (kolejkaPusta()) {
			throw new KolejkaPustaWyj();
		}
		
		Zgloszenie z = bufor[1];
		zamien(1, stan--);
		przywrocStruktureOdGory(1);
		bufor[stan + 1] = null;
		
		if ((stan > 0) && (stan == (bufor.length - 1) / 4)) {
			zmienDlugosc(bufor.length / 2);
		}
		
		//assert strukturaDrzewaPoprawna();
		return z;
	}
}
