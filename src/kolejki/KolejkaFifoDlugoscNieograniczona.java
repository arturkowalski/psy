package kolejki;

import java.util.Iterator;
import java.util.NoSuchElementException;

public final class KolejkaFifoDlugoscNieograniczona<TypElementow> implements Kolejka<TypElementow> {	
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
	
	private class KolejkaFifoDlugoscNieograniczonaIt implements Iterator<TypElementow> {
		private Wezel w;
		
		private KolejkaFifoDlugoscNieograniczonaIt() {
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
		return new KolejkaFifoDlugoscNieograniczonaIt();
	}
	
	public KolejkaFifoDlugoscNieograniczona() {}
	
	public void wstaw(final TypElementow element) throws KolejkaPelnaWyj {
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
	
	public int stan() {
		return stan;
	}
	
	public TypElementow glowa() throws KolejkaPustaWyj {
		if (kolejkaPusta()) {
			throw new KolejkaPustaWyj();
		}
		
		return glowa.e;
	}
	
	public static void main(String[] args) {
		KolejkaFifoDlugoscNieograniczona<Character> k = new KolejkaFifoDlugoscNieograniczona<>();
		
		for (char z = 'a'; z < 'z'; ++z) {
			k.wstaw(z);
		}
		
		for (Character e : k) {
			System.out.print(e);
		}
		
		System.out.println();
		
		while (!k.kolejkaPusta()) {
			System.out.print(k.usun());
		}
	}
}
