package kolejki_zgloszen;

public final class KolejkaLifoDlugoscStala implements KolejkaZgloszen {
	private final Zgloszenie[] bufor;
	
	private int w;
	
	public KolejkaLifoDlugoscStala(final int dlugosc) {
		if (dlugosc <= 0) {
			throw new IllegalArgumentException("Dlugosc mniejsza niz 1");
		}
		
		bufor = new Zgloszenie[dlugosc];
		
		w = 0;
	}
	
	public KolejkaLifoDlugoscStala(final Zgloszenie[] tablica) {
		if (tablica == null) {
			throw new IllegalArgumentException("Tablica-null");
		}
		
		bufor = new Zgloszenie[tablica.length];
		
		for (int i = bufor.length - 1; i >= 0; --i) {
			bufor[i] = tablica[i];
		}
		
		w = tablica.length;
	}
	
	public KolejkaLifoDlugoscStala(final KolejkaLifoDlugoscStala kolejka) {
		if (kolejka == null) {
			throw new IllegalArgumentException("Kolejka-null");
		}
		
		bufor = new Zgloszenie[kolejka.bufor.length];
		
		for (int i = bufor.length - 1; i >= 0; --i) {
			bufor[i] = kolejka.bufor[i];
		}
		
		w = kolejka.bufor.length;
	}
	
	public void wstaw(final Zgloszenie zgloszenie) throws KolejkaPelnaWyj {
		if (kolejkaPelna()) {
			throw new KolejkaPelnaWyj(bufor.length, zgloszenie);
		}
		
		bufor[w++] = zgloszenie;
	}
	
	public Zgloszenie usun() throws KolejkaPustaWyj {
		if (kolejkaPusta()) {
			throw new KolejkaPustaWyj();
		}
		
		Zgloszenie z = bufor[--w];
		
		bufor[w] = null;
		
		return z;
	}
	
	public boolean kolejkaPusta() {
		return w == 0;
	}
	
	public int stan() {
		return w;
	}
	
	public Zgloszenie nastepne() throws KolejkaPustaWyj {
		if (kolejkaPusta()) {
			throw new KolejkaPustaWyj();
		}
		
		return bufor[w - 1];
	}
	
	public boolean kolejkaPelna() {
		return w == bufor.length;
	}
	
	public int dlugosc() {
		return bufor.length;
	}
}
