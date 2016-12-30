package kolejki_zgloszen;

import java.util.Iterator;
import java.util.NoSuchElementException;

public final class KolejkaFifoDlugoscStala implements Kolejka {
	private final Zgloszenie[] bufor;
	
	private int iw, iu;
	private int stan;
	
	private class KolejkaFifoDlugoscStalaIt implements Iterator<Zgloszenie> {
		private int i;
		
		public boolean hasNext() {
			return i < stan;
		}
		
		public Zgloszenie next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			
			return bufor[i++];
		}
	}
	
	public Iterator<Zgloszenie> iterator() {
		return new KolejkaFifoDlugoscStalaIt();
	}
	
	public KolejkaFifoDlugoscStala(final int dlugosc) {
		if (dlugosc <= 0) {
			throw new IllegalArgumentException("Dlugosc mniejsza niz 1");
		}

		bufor = new Zgloszenie[dlugosc];
	}
	
	public KolejkaFifoDlugoscStala(final Zgloszenie[] tablica) {
		if (tablica == null) {
			throw new IllegalArgumentException("Tablica-parametr rowna null");
		}
		
		bufor = new Zgloszenie[tablica.length];
		
		System.arraycopy(tablica, 0, bufor, 0, tablica.length);
		
		iw = stan = tablica.length;
	}
	
	public KolejkaFifoDlugoscStala(final KolejkaFifoDlugoscStala kolejka) {
		if (kolejka == null) {
			throw new IllegalArgumentException("Kolejka-parametr rowna null");
		}
		
		bufor = new Zgloszenie[kolejka.bufor.length];
		
		System.arraycopy(kolejka.bufor, 0, bufor, 0, kolejka.bufor.length);
		
		iw = kolejka.iw;
		iu = kolejka.iu;
		stan = kolejka.stan;
	}
	
	public void wstaw(final Zgloszenie zgloszenie) throws KolejkaPelnaWyj {
		if (kolejkaPelna()) {
			throw new KolejkaPelnaWyj(bufor.length, zgloszenie);
		}
		
		bufor[iw++] = zgloszenie;
		
		if (iw == bufor.length) {
			iw = 0;
		}
		
		++stan;
	}
	
	public Zgloszenie usun() throws KolejkaPustaWyj {
		if (kolejkaPusta()) {
			throw new KolejkaPustaWyj();
		}
		
		Zgloszenie z = bufor[iu];
		
		bufor[iu++] = null;
		
		if (iu == bufor.length) {
			iu = 0;
		}
		
		--stan;
		
		return z;
	}
	
	public boolean kolejkaPusta() {
		return stan == 0;
	}
	
	public boolean kolejkaPelna() {
		return stan == bufor.length;
	}
	
	public int stan() {
		return stan;
	}
	
	public Zgloszenie nastepne() throws KolejkaPustaWyj {
		if (iu == iw) {
			throw new KolejkaPustaWyj();
		}
		
		return bufor[iu];
	}
	
	public int dlugosc() {
		return bufor.length;
	}
}
