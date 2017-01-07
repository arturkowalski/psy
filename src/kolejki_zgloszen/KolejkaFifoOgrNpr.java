package kolejki_zgloszen;

import java.util.Iterator;
import java.util.NoSuchElementException;

public final class KolejkaFifoOgrNpr implements KolejkaI {
	private Zgloszenie[] bufor;
	private int iw, iu;
	private int stan;
	
	private class KolejkaFifoOgrNprIt implements Iterator<Zgloszenie> {
		private int i;
		
		private KolejkaFifoOgrNprIt() {}
		
		public boolean hasNext() {
			return i < stan;
		}
		
		public Zgloszenie next() {
			if (!hasNext()) {
				throw new NoSuchElementException("Iterator zuzyty");
			}
			
			return bufor[(iu + i++) % bufor.length];
		}
	}
	
	private int indeks(int nr) {
		for (int i = iu, j = 0; j < stan; i = (i + 1) % bufor.length, ++j) {
			if (bufor[i].numer() == nr) {
				return i;
			}
		}
		
		throw new NoSuchElementException("Nie ma zgloszenia numer " + nr);
	}
	
	public KolejkaFifoOgrNpr(final int dlugosc) {
		if (dlugosc <= 0) {
			throw new IllegalArgumentException("Dlugosc mniejsza niz 1");
		}
		
		bufor = new Zgloszenie[dlugosc];
	}
	
	public KolejkaFifoOgrNpr(final Zgloszenie[] tablica) {
		if (tablica == null) {
			throw new IllegalArgumentException("Tablica-parametr rowna null");
		}
		
		bufor = new Zgloszenie[tablica.length];
		System.arraycopy(tablica, 0, bufor, 0, tablica.length);
		iw = stan = tablica.length;
	}
	
	public KolejkaFifoOgrNpr(final KolejkaFifoOgrNpr kolejka) {
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
		if (kolejkaPusta()) {
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
		if (kolejkaPusta()) {
			throw new KolejkaPustaWyj();
		}
		
		Zgloszenie[] tab = new Zgloszenie[bufor.length];
		int ix = indeks(numer);
		bufor[ix] = null;
		
		if (iu < iw || bufor.length - iu == stan) {
			System.arraycopy(bufor, iu, tab, 0, ix - iu);
			System.arraycopy(bufor, ix + 1, tab, ix - iu, stan - ix - 1);
		}
		else {
			if (ix >= iu && ix < bufor.length - 1) {
				System.arraycopy(bufor, iu, tab, 0, ix - iu);
				System.arraycopy(bufor, ix + 1, tab, ix - iu, bufor.length - ix - 1);
				System.arraycopy(bufor, 0, tab, -iu + bufor.length -  1, stan - bufor.length + iu);
			}
			else if (ix >= iu && ix == bufor.length - 1) {
				System.arraycopy(bufor, iu, tab, 0, ix - iu);
				System.arraycopy(bufor, 0, tab, ix - iu, stan - ix + iu - 1);
			}
			else if (ix < iu && ix < iw - 1) {
				System.arraycopy(bufor, iu, tab, 0, bufor.length - iu);
				System.arraycopy(bufor, 0, tab, bufor.length - iu, ix);
				System.arraycopy(bufor, ix + 1, tab, bufor.length - iu + ix, iw - ix - 1);
			}
			else {
				System.arraycopy(bufor, iu, tab, 0, bufor.length - iu);
				System.arraycopy(bufor, 0, tab, bufor.length - iu, ix);
			}
		}
		
		bufor = tab;
		iw = --stan;
		iu = 0;
	}
	
	public Iterator<Zgloszenie> iterator() {
		return new KolejkaFifoOgrNprIt();
	}
	
	public static void main(String[] args) {
		Sekwencja numery = new Sekwencja(1, 1);
		Stoper stoper = new Stoper();
		java.util.Random generator = new java.util.Random();
		KolejkaFifoOgrNpr kolejka = new KolejkaFifoOgrNpr(7);
		kolejka.wstaw(new Zgloszenie(numery.nastepny(), stoper.czas(), generator.nextInt(10) + 1));
		kolejka.wstaw(new Zgloszenie(numery.nastepny(), stoper.czas(), generator.nextInt(10) + 1));
		kolejka.wstaw(new Zgloszenie(numery.nastepny(), stoper.czas(), generator.nextInt(10) + 1));
		kolejka.wstaw(new Zgloszenie(numery.nastepny(), stoper.czas(), generator.nextInt(10) + 1));
		kolejka.wstaw(new Zgloszenie(numery.nastepny(), stoper.czas(), generator.nextInt(10) + 1));
		kolejka.wstaw(new Zgloszenie(numery.nastepny(), stoper.czas(), generator.nextInt(10) + 1));
		kolejka.wstaw(new Zgloszenie(numery.nastepny(), stoper.czas(), generator.nextInt(10) + 1));
		kolejka.usun();
		kolejka.usun();
		kolejka.usun();
		kolejka.usun();
		kolejka.wstaw(new Zgloszenie(numery.nastepny(), stoper.czas(), generator.nextInt(10) + 1));
		kolejka.wstaw(new Zgloszenie(numery.nastepny(), stoper.czas(), generator.nextInt(10) + 1));
		kolejka.wstaw(new Zgloszenie(numery.nastepny(), stoper.czas(), generator.nextInt(10) + 1));
		kolejka.wstaw(new Zgloszenie(numery.nastepny(), stoper.czas(), generator.nextInt(10) + 1));
		System.out.println("Zawartosc poczatkowa:");
		
		for (Zgloszenie z : kolejka) {
			System.out.println(z.toString());
		}
		
		kolejka.usunWybrane(11);
		System.out.println("\nZawartosc wyjsciowa:");
		
		for (Zgloszenie z : kolejka) {
			System.out.println(z.toString());
		}
	}
}
