package kolejki_zgloszen;

import java.util.Iterator;
import java.util.NoSuchElementException;

public final class KolejkaLifoDlugoscStala implements KolejkaI {
	private final Zgloszenie[] bufor;
	
	private int w;
	
	private class KolejkaLifoDlugoscStalaIt implements Iterator<Zgloszenie> {
		private int i;
		
		private KolejkaLifoDlugoscStalaIt() {
			i = w - 1;
		}
		
		public boolean hasNext() {
			return i >= 0;
		}
		
		public Zgloszenie next() {
			if (!hasNext()) {
				throw new NoSuchElementException("Iterator wykorzystany");
			}
			
			return bufor[i--];
		}
	}
	
	private int indeks(int nr) {
		for (int i = 0; i < w; ++i) {
			if (bufor[i].numer() == nr) {
				return i;
			}
		}
		
		throw new NoSuchElementException("Nie ma zgloszenia numer " + nr);
	}
	
	public KolejkaLifoDlugoscStala(final int dlugosc) {
		if (dlugosc <= 0) {
			throw new IllegalArgumentException("Dlugosc mniejsza niz 1");
		}
		
		bufor = new Zgloszenie[dlugosc];
	}
	
	public KolejkaLifoDlugoscStala(final Zgloszenie[] tablica) {
		if (tablica == null) {
			throw new IllegalArgumentException("Tablica-parametr rowna null");
		}
		
		bufor = new Zgloszenie[tablica.length];
		
		System.arraycopy(tablica, 0, bufor, 0, tablica.length);
		
		w = tablica.length;
	}
	
	public KolejkaLifoDlugoscStala(final KolejkaLifoDlugoscStala kolejka) {
		if (kolejka == null) {
			throw new IllegalArgumentException("Kolejka-parametr rowna null");
		}
		
		bufor = new Zgloszenie[kolejka.bufor.length];
		
		System.arraycopy(kolejka.bufor, 0, bufor, 0, kolejka.w);
		
		w = kolejka.w;
	}
	
	public int dlugosc() {
		return bufor.length;
	}
	
	public boolean kolejkaPelna() {
		return w == bufor.length;
	}
	
	public boolean kolejkaPusta() {
		return w == 0;
	}
	
	public int stan() {
		return w;
	}
	
	public void wstaw(final Zgloszenie zgloszenie) throws KolejkaPelnaWyj {
		if (kolejkaPelna()) {
			throw new KolejkaPelnaWyj(bufor.length, zgloszenie);
		}
		
		bufor[w++] = zgloszenie;
	}
	
	public Zgloszenie nastepne() throws KolejkaPustaWyj {
		if (kolejkaPusta()) {
			throw new KolejkaPustaWyj();
		}
		
		return bufor[w - 1];
	}
	
	public Zgloszenie usun() throws KolejkaPustaWyj {
		if (kolejkaPusta()) {
			throw new KolejkaPustaWyj();
		}
		
		Zgloszenie z = bufor[--w];
		
		bufor[w] = null;
		
		return z;
	}
	
	public void usunWybrane(int numer) throws KolejkaPustaWyj {
		if (kolejkaPusta()) {
			throw new KolejkaPustaWyj();
		}
		
		int i = indeks(numer);
		int t = w - i - 1;
		
		if (t > 0) {
			System.arraycopy(bufor, i + 1, bufor, i, t);
		}
		
		bufor[--w] = null;
	}
	
	public Iterator<Zgloszenie> iterator() {
		return new KolejkaLifoDlugoscStalaIt();
	}
	
	public static void main(String[] args) {
		Sekwencja numery = new Sekwencja(1, 1);
		Zegar zegar = new Zegar();
		java.util.Random generator = new java.util.Random();
		KolejkaLifoDlugoscStala kolejka = new KolejkaLifoDlugoscStala(10);
		
		kolejka.wstaw(new Zgloszenie(numery.nastepny(), zegar.czasOdStartu(), generator.nextInt(10) + 1));
		kolejka.wstaw(new Zgloszenie(numery.nastepny(), zegar.czasOdStartu(), generator.nextInt(10) + 1));
		kolejka.wstaw(new Zgloszenie(numery.nastepny(), zegar.czasOdStartu(), generator.nextInt(10) + 1));
		kolejka.wstaw(new Zgloszenie(numery.nastepny(), zegar.czasOdStartu(), generator.nextInt(10) + 1));
		kolejka.wstaw(new Zgloszenie(numery.nastepny(), zegar.czasOdStartu(), generator.nextInt(10) + 1));
		
		System.out.println("Zawartosc wyjsciowa:");
		
		for (Zgloszenie z : kolejka) {
			System.out.println(z.toString());
		}
		
		kolejka.usunWybrane(3);
		
		System.out.println("\nTrzecie zgloszenie usuniete:");
		
		for (Zgloszenie z : kolejka) {
			System.out.println(z.toString());
		}
		
		kolejka.wstaw(new Zgloszenie(numery.nastepny(), zegar.czasOdStartu(), generator.nextInt(10) + 1));
		kolejka.wstaw(new Zgloszenie(numery.nastepny(), zegar.czasOdStartu(), generator.nextInt(10) + 1));
		kolejka.wstaw(new Zgloszenie(numery.nastepny(), zegar.czasOdStartu(), generator.nextInt(10) + 1));
		kolejka.wstaw(new Zgloszenie(numery.nastepny(), zegar.czasOdStartu(), generator.nextInt(10) + 1));
		kolejka.wstaw(new Zgloszenie(numery.nastepny(), zegar.czasOdStartu(), generator.nextInt(10) + 1));
		kolejka.wstaw(new Zgloszenie(numery.nastepny(), zegar.czasOdStartu(), generator.nextInt(10) + 1));
		
		System.out.println("\nKolejka pelna:");
		
		for (Zgloszenie z : kolejka) {
			System.out.println(z.toString());
		}
	}
}
