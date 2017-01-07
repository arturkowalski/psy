package kolejki_zgloszen;

import java.util.Iterator;
import java.util.NoSuchElementException;

public final class KolejkaLifoOgrNpr implements KolejkaI {
	private final Zgloszenie[] bufor;
	private int w;
	
	private class KolejkaLifoOgrNprIt implements Iterator<Zgloszenie> {
		private int i;
		
		private KolejkaLifoOgrNprIt() {
			i = w - 1;
		}
		
		public boolean hasNext() {
			return i >= 0;
		}
		
		public Zgloszenie next() {
			if (!hasNext()) {
				throw new NoSuchElementException("\nIterator zuzyty");
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
		
		throw new NoSuchElementException("\nNie ma zgloszenia numer " + nr);
	}
	
	public KolejkaLifoOgrNpr(final int dlugosc) {
		if (dlugosc <= 0) {
			throw new IllegalArgumentException("\nDlugosc mniejsza niz 1");
		}
		
		bufor = new Zgloszenie[dlugosc];
	}
	
	public KolejkaLifoOgrNpr(final Zgloszenie[] tablica) {
		if (tablica == null) {
			throw new IllegalArgumentException("\nTablica-parametr rowna null");
		}
		
		bufor = new Zgloszenie[tablica.length];
		System.arraycopy(tablica, 0, bufor, 0, tablica.length);
		w = tablica.length;
	}
	
	public KolejkaLifoOgrNpr(final kolejki_zgloszen.KolejkaLifoOgrNpr kolejka) {
		if (kolejka == null) {
			throw new IllegalArgumentException("\nKolejka-parametr rowna null");
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
		return new KolejkaLifoOgrNprIt();
	}
	
	public static void main(String[] args) {
		Sekwencja numery = new Sekwencja(1, 1);
		Stoper stoper = new Stoper();
		java.util.Random generator = new java.util.Random();
		kolejki_zgloszen.KolejkaLifoOgrNpr kolejka = new kolejki_zgloszen.KolejkaLifoOgrNpr(10);
		kolejka.wstaw(new Zgloszenie(numery.nastepny(), stoper.czas(), generator.nextInt(10) + 1));
		kolejka.wstaw(new Zgloszenie(numery.nastepny(), stoper.czas(), generator.nextInt(10) + 1));
		kolejka.wstaw(new Zgloszenie(numery.nastepny(), stoper.czas(), generator.nextInt(10) + 1));
		kolejka.wstaw(new Zgloszenie(numery.nastepny(), stoper.czas(), generator.nextInt(10) + 1));
		kolejka.wstaw(new Zgloszenie(numery.nastepny(), stoper.czas(), generator.nextInt(10) + 1));
		
		System.out.println("Zawartosc wyjsciowa:");
		for (Zgloszenie z : kolejka) {
			System.out.println(z.toString());
		}
		
		kolejka.usunWybrane(3);
		
		System.out.println("\nTrzecie zgloszenie usuniete:");
		for (Zgloszenie z : kolejka) {
			System.out.println(z.toString());
		}
		
		kolejka.wstaw(new Zgloszenie(numery.nastepny(), stoper.czas(), generator.nextInt(10) + 1));
		kolejka.wstaw(new Zgloszenie(numery.nastepny(), stoper.czas(), generator.nextInt(10) + 1));
		kolejka.wstaw(new Zgloszenie(numery.nastepny(), stoper.czas(), generator.nextInt(10) + 1));
		kolejka.wstaw(new Zgloszenie(numery.nastepny(), stoper.czas(), generator.nextInt(10) + 1));
		kolejka.wstaw(new Zgloszenie(numery.nastepny(), stoper.czas(), generator.nextInt(10) + 1));
		kolejka.wstaw(new Zgloszenie(numery.nastepny(), stoper.czas(), generator.nextInt(10) + 1));
		
		System.out.println("\nKolejka pelna:");
		for (Zgloszenie z : kolejka) {
			System.out.println(z.toString());
		}
	}
}
