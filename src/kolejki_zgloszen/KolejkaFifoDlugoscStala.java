package kolejki_zgloszen;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public final class KolejkaFifoDlugoscStala implements KolejkaI {
	private Zgloszenie[] bufor;
	
	private int iw, iu;
	private int stan;
	
	private class KolejkaFifoDlugoscStalaIt implements Iterator<Zgloszenie> {
		private int i;
		
		private KolejkaFifoDlugoscStalaIt() {}
		
		public boolean hasNext() {
			return i < stan;
		}
		
		public Zgloszenie next() {
			if (!hasNext()) {
				throw new NoSuchElementException("Iterator wykorzystany");
			}
			
			return bufor[(iu + i++) % bufor.length];
		}
	}
	
	private int indeksZgloszenia(int nr) {
		for (int i = iu, j = 0; j < stan; i = (i + 1) % bufor.length, ++j) {
			if (bufor[i].numer() == nr) {
				return i;
			}
		}
		
		throw new NoSuchElementException("Nie ma zgloszenia numer " + nr);
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
	
	public int dlugosc() {
		return bufor.length;
	}
	
	public boolean kolejkaPelna() {
		return stan == bufor.length;
	}
	
	public boolean kolejkaPusta() {
		return stan == 0;
	}
	
	public int stan() {
		return stan;
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
	
	public Zgloszenie nastepne() throws KolejkaPustaWyj {
		if (iu == iw) {
			throw new KolejkaPustaWyj();
		}
		
		return bufor[iu];
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
	
	public void usunWybrane(int numer) {
		
	}
	
	public Iterator<Zgloszenie> iterator() {
		return new KolejkaFifoDlugoscStalaIt();
	}
	
	public static void main(String[] args) {
		Sekwencja numery = new Sekwencja(1, 1);
		Zegar zegar = new Zegar();
		java.util.Random generator = new Random();
		KolejkaFifoDlugoscStala kolejka = new KolejkaFifoDlugoscStala(5);
		
		kolejka.wstaw(new Zgloszenie(numery.nastepny(), zegar.czasOdStartu(), generator.nextInt(10) + 1));
		kolejka.wstaw(new Zgloszenie(numery.nastepny(), zegar.czasOdStartu(), generator.nextInt(10) + 1));
		kolejka.wstaw(new Zgloszenie(numery.nastepny(), zegar.czasOdStartu(), generator.nextInt(10) + 1));
		kolejka.wstaw(new Zgloszenie(numery.nastepny(), zegar.czasOdStartu(), generator.nextInt(10) + 1));
		kolejka.wstaw(new Zgloszenie(numery.nastepny(), zegar.czasOdStartu(), generator.nextInt(10) + 1));
		
		System.out.println("Zawartosc wejsciowa:");
		
		for (Zgloszenie z : kolejka) {
			System.out.println(z.toString());
		}
		
		kolejka.usun();
		kolejka.usun();
		kolejka.usun();
		
		System.out.println("\nZawartosc wyjsciowa:");
		
		for (Zgloszenie z : kolejka) {
			System.out.println(z.toString());
		}
	}
}
