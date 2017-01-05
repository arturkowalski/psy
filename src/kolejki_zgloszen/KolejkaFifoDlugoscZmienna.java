package kolejki_zgloszen;

import java.util.Iterator;
import java.util.NoSuchElementException;

public final class KolejkaFifoDlugoscZmienna implements Kolejka {
	private Zgloszenie[] bufor;
	
	private int iw, iu;
	private int stan;
	
	private class KolejkaFifoDlugoscZmiennaIt implements Iterator<Zgloszenie> {
		private int i;
		
		public boolean hasNext() {
			return i < stan;
		}
		
		public Zgloszenie next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			
			return bufor[(iu + i++) % bufor.length];
		}
	}
	
	public Iterator<Zgloszenie> iterator() {
		return new KolejkaFifoDlugoscZmiennaIt();
	}
	
	private boolean buforPelny() {
		return stan == bufor.length;
	}
	
	private void zmienDlugosc(final int dl) {
		assert dl != bufor.length && dl >= stan;
		
		Zgloszenie tab[] = new Zgloszenie[dl];
		
		if (dl > bufor.length) {
			if (iu > 0) {
				System.arraycopy(bufor, 0, tab, stan - iu, iu);
			}
			
			System.arraycopy(bufor, iu, tab, 0, stan - iu);
		}
		else {
			if (iu + stan <= bufor.length) {
				System.arraycopy(bufor, iu, tab, 0, stan);
			}
			else {
				System.arraycopy(bufor, iu, tab, 0, bufor.length - iu);
				System.arraycopy(bufor, 0, tab, bufor.length - iu, stan - bufor.length + iu);
			}
		}
		
		iw = stan;
		iu = 0;
		bufor = tab;
	}
	
	public KolejkaFifoDlugoscZmienna(final int dlugosc) {
		if (dlugosc <= 0) {
			throw new IllegalArgumentException("Dlugosc mniejsza niz 1");
		}
		
		bufor = new Zgloszenie[dlugosc];
	}
	
	public KolejkaFifoDlugoscZmienna() {
		this(30);
	}
	
	public KolejkaFifoDlugoscZmienna(final Zgloszenie[] tablica) {
		if (tablica == null) {
			throw new IllegalArgumentException("Tablica-parametr rowna null");
		}
		
		bufor = new Zgloszenie[tablica.length];
		
		System.arraycopy(tablica, 0, bufor, 0, tablica.length);
		
		iw = stan = tablica.length;
	}
	
	public KolejkaFifoDlugoscZmienna(final KolejkaFifoDlugoscZmienna kolejka) {
		if (kolejka == null) {
			throw new IllegalArgumentException("Kolejka-parametr rowna null");
		}
		
		bufor = new Zgloszenie[kolejka.bufor.length];
		
		System.arraycopy(kolejka.bufor, 0, bufor, 0, kolejka.bufor.length);
		
		iw = kolejka.iw;
		iu = kolejka.iu;
		stan = kolejka.stan;
	}
	
	public void wstaw(final Zgloszenie zgloszenie) {
		if (buforPelny()) {
			zmienDlugosc(2 * bufor.length);
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
		
		if (stan > 0 && stan == bufor.length / 4) {
			zmienDlugosc(bufor.length / 2);
		}
		
		--stan;
		
		return z;
	}
	
	public boolean kolejkaPusta() {
		return stan == 0;
	}
	
	public int stan() {
		return stan;
	}
	
	public Zgloszenie nastepne() throws KolejkaPustaWyj {
		if (kolejkaPusta()) {
			throw new KolejkaPustaWyj();
		}
		
		return bufor[iu];
	}
}
