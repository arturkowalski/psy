package kolejki;

import java.util.Iterator;
import java.util.NoSuchElementException;

public final class KolejkaFifoDlugoscNieograniczona<Element> implements Kolejka<Element> {	
	private Wezel glowa, ogon;
	
	private int stan;
	
	private class Wezel {
		private Element e;
		private Wezel n;
		
		private Wezel() {}
		
		private Wezel(Element e, Wezel n) {
			this.e = e;
			this.n = n;
		}
	}
	
	private class KolejkaFifoDlugoscNieograniczonaIt implements Iterator<Element> {
		private Wezel w;
		
		private KolejkaFifoDlugoscNieograniczonaIt() {
			w = glowa;
		}
		
		public boolean hasNext() {
			return w != null;
		}
		
		public Element next() {
			if (!hasNext()) {
				throw new NoSuchElementException("Iterator wykorzystany");
			}
			
			Element e = w.e;
			w = w.n;
			
			return e;
		}
	}
	
	public Iterator<Element> iterator() {
		return new KolejkaFifoDlugoscNieograniczonaIt();
	}
	
	public KolejkaFifoDlugoscNieograniczona() {}
	
	public void wstaw(final Element element) throws KolejkaPelnaWyj {
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
	
	public Element nastepny() throws KolejkaPustaWyj {
		if (kolejkaPusta()) {
			throw new KolejkaPustaWyj();
		}
		
		return glowa.e;
	}
	
	public Element usun() throws KolejkaPustaWyj {
		if (kolejkaPusta()) {
			throw new KolejkaPustaWyj();
		}
		
		Element e = glowa.e;
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
	
	public static void main(String[] args) {
		KolejkaFifoDlugoscNieograniczona<Integer> k = new KolejkaFifoDlugoscNieograniczona<>();
		char z = ' ';
		
		for (int i = 0; i < 5; ++i) {
			k.wstaw(i);
		}
		
		System.out.println("Do usuniecia:");
		
		for (Integer i : k) {
			System.out.println(i);
		}
		
		System.out.println("\nElementy usuniete:");
		
		while (!k.kolejkaPusta()) {
			System.out.println(k.usun());
		}
	}
}
