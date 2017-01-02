package kolejki;

import java.util.Iterator;
import java.util.NoSuchElementException;

public final class KolejkaFifoDlugoscStala<TypElementow> implements Kolejka<TypElementow> {
	private final int dlugosc;
	
	private Wezel glowa, ogon;
	
	private int stan;
	
	private class Wezel {
		private TypElementow e;
		private Wezel n;
		
		private Wezel() {}
		
		private Wezel(TypElementow e, Wezel n) {
			this.e = e;
			this.n = n;
		}
	}
	
	private class KolejkaFifoDlugoscStalaIt implements Iterator<TypElementow> {
		private Wezel w;
		
		private KolejkaFifoDlugoscStalaIt() {
			w = glowa;
		}
		
		public boolean hasNext() {
			return w != null;
		}
		
		public TypElementow next() {
			if (!hasNext()) {
				throw new NoSuchElementException("Iterator wykorzystany");
			}
			
			TypElementow e = w.e;
			w = w.n;
			
			return e;
		}
	}
	
	public Iterator<TypElementow> iterator() {
		return new KolejkaFifoDlugoscStalaIt();
	}
	
	public KolejkaFifoDlugoscStala(final int dlugosc) {
		if (dlugosc <= 0) {
			throw new IllegalArgumentException("Dlugosc mniejsza niz 1");
		}
		
		this.dlugosc = dlugosc;
	}
	
	public void wstaw(final TypElementow element) throws KolejkaPelnaWyj {
		if (kolejkaPelna()) {
			throw new KolejkaPelnaWyj(dlugosc);
		}
		
		Wezel w = new Wezel(element, null);
		
		if (kolejkaPusta()) {
			glowa = w;
		}
		else {
			ogon.n = w;
		}
		
		ogon = w;
		++stan;
	}
	
	public TypElementow usun() throws KolejkaPustaWyj {
		if (kolejkaPusta()) {
			throw new KolejkaPustaWyj();
		}
		
		TypElementow e = glowa.e;
		glowa = glowa.n;
		--stan;
		
		if (kolejkaPusta()) {
			ogon = null;
		}
		
		return e;
	}
	
	public boolean kolejkaPusta() {
		return stan == 0;
	}
	
	public boolean kolejkaPelna() {
		return stan == dlugosc;
	}
	
	public int stan() {
		return stan;
	}
	
	public TypElementow glowa() throws KolejkaPustaWyj {
		if (kolejkaPusta()) {
			throw new KolejkaPustaWyj();
		}
		
		return glowa.e;
	}
	
	public int dlugosc() {
		return dlugosc;
	}
	
	public static void main(String[] args) {
		KolejkaFifoDlugoscStala<Character> k = new KolejkaFifoDlugoscStala<>(5);
		
		k.wstaw('k');
		k.wstaw('a');
		k.wstaw('m');
		k.wstaw('i');
		k.wstaw('l');
		
		for (Character e : k) {
			System.out.println(e);
		}
		
		while (!k.kolejkaPusta()) {
			System.out.println(k.usun());
		}
	}
}
