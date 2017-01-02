package kolejki;

import java.util.Iterator;
import java.util.NoSuchElementException;

public final class KolejkaLifoDlugoscOgraniczona<Element> implements Kolejka<Element> {	
	private final int dlugosc;
	
	private Wezel glowa;
	
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
	
	private class KolejkaLifoDlugoscOgraniczonaIt implements Iterator<Element> {
		private Wezel w;
		
		private KolejkaLifoDlugoscOgraniczonaIt() {
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
		return new KolejkaLifoDlugoscOgraniczonaIt();
	}
	
	public KolejkaLifoDlugoscOgraniczona(final int dlugosc) {
		if (dlugosc <= 0) {
			throw new IllegalArgumentException("Dlugosc mniejsza niz 1");
		}
		
		this.dlugosc = dlugosc;
	}
	
	public void wstaw(final Element element) throws KolejkaPelnaWyj {
		if (kolejkaPelna()) {
			throw new KolejkaPelnaWyj(dlugosc);
		}
		
		Wezel w = glowa;
		
		glowa = new Wezel(element, w);
		
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
	
	public int dlugosc() {
		return dlugosc;
	}
	
	public static void main(String[] args) {
		KolejkaLifoDlugoscOgraniczona<Character> k = new KolejkaLifoDlugoscOgraniczona<>(5);
		
		k.wstaw('k');
		k.wstaw('a');
		k.wstaw('m');
		k.wstaw('i');
		k.wstaw('l');
		
		System.out.println("Do usuniecia:");
		
		for (Character c : k) {
			System.out.print(c);
		}
		
		System.out.println("\n\nZnaki usuniete:");
		
		while (!k.kolejkaPusta()) {
			System.out.print(k.usun());
		}
		
		System.out.println();
	}
}
