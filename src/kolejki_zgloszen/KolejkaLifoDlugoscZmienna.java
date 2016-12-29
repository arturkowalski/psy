package kolejki_zgloszen;

import java.util.Iterator;
import java.util.NoSuchElementException;

public final class KolejkaLifoDlugoscZmienna implements Kolejka {
	private Zgloszenie[] bufor;
	
	private int w;
	
	private class KolejkaLifoDlugoscZmiennaIt implements Iterator<Zgloszenie> {
		private int i;
		
		private KolejkaLifoDlugoscZmiennaIt() {
			i = w - 1;
		}
		
		public boolean hasNext() {
			return i >= 0;
		}
		
		public Zgloszenie next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			
			return bufor[i--];
		}
	}
	
	public Iterator<Zgloszenie> iterator() {
		return new KolejkaLifoDlugoscZmiennaIt();
	}
	
	private boolean buforPelny() {
		return w == bufor.length;
	}
	
	private void zmienDlugosc(final int dl) {
		assert dl >= w;
		
		Zgloszenie tab[] = new Zgloszenie[dl];
		
		System.arraycopy(bufor, 0, tab, 0, w);
		
		bufor = tab;
	}
	
	public KolejkaLifoDlugoscZmienna(final int dlugosc) {
		if (dlugosc <= 0) {
			throw new IllegalArgumentException("Dlugosc mniejsza niz 1");
		}
		
		bufor = new Zgloszenie[dlugosc];
	}
	
	public KolejkaLifoDlugoscZmienna() {
		this(30);
	}
	
	public KolejkaLifoDlugoscZmienna(final Zgloszenie[] tablica) {
		if (tablica == null) {
			throw new IllegalArgumentException("Tablica-parametr rowna null");
		}
		
		bufor = new Zgloszenie[tablica.length];
		
		System.arraycopy(tablica, 0, bufor, 0, tablica.length);
		
		w = tablica.length;
	}
	
	public KolejkaLifoDlugoscZmienna(final KolejkaLifoDlugoscZmienna kolejka) {
		if (kolejka == null) {
			throw new IllegalArgumentException("Kolejka-parametr rowna null");
		}
		
		bufor = new Zgloszenie[kolejka.bufor.length];
		
		System.arraycopy(kolejka.bufor, 0, bufor, 0, kolejka.bufor.length);
		
		w = kolejka.bufor.length;
	}
	
	public void wstaw(final Zgloszenie zgloszenie) {
		if (buforPelny()) {
			zmienDlugosc(2 * bufor.length);
		}
		
		bufor[w++] = zgloszenie;
	}
	
	public Zgloszenie usun() throws KolejkaPustaWyj {
		if (w == 0) {
			throw new KolejkaPustaWyj();
		}
		
		Zgloszenie z = bufor[--w];
		
		bufor[w] = null;
		
		if (w > 0 && w == bufor.length / 4) {
			zmienDlugosc(bufor.length / 2);
		}
		
		return z;
	}
	
	public boolean kolejkaPusta() {
		return w == 0;
	}
	
	public int stan() {
		return w;
	}
	
	public Zgloszenie nastepne() throws KolejkaPustaWyj {
		if (w == 0) {
			throw new KolejkaPustaWyj();
		}
		
		return bufor[w - 1];
	}
}
