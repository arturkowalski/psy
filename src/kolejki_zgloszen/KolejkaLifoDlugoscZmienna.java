package kolejki_zgloszen;

public final class KolejkaLifoDlugoscZmienna implements KolejkaZgloszen {
	private Zgloszenie[] bufor;
	
	private int w;
	
	private boolean kolejkaPelna() {
		return w == bufor.length;
	}
	
	private void zmienDlugosc(final int dl) {
		assert dl >= w;
		
		Zgloszenie tab[] = new Zgloszenie[dl];
		
		for (int i = w - 1; i >= 0; --i) {
			tab[i] = bufor[i];
		}
		
		bufor = tab;
	}
	
	public KolejkaLifoDlugoscZmienna(final int dlugosc) {
		if (dlugosc <= 0) {
			throw new IllegalArgumentException("Dlugosc mniejsza niz 1");
		}
		
		bufor = new Zgloszenie[dlugosc];
		
		w = 0;
	}
	
	public KolejkaLifoDlugoscZmienna(final Zgloszenie[] tablica) {
		if (tablica == null) {
			throw new IllegalArgumentException("Tablica-null");
		}
		
		bufor = new Zgloszenie[tablica.length];
		
		for (int i = bufor.length - 1; i >= 0; --i) {
			bufor[i] = tablica[i];
		}
		
		w = tablica.length;
	}
	
	public KolejkaLifoDlugoscZmienna(final KolejkaLifoDlugoscZmienna kolejka) {
		if (kolejka == null) {
			throw new IllegalArgumentException("Kolejka-null");
		}
		
		bufor = new Zgloszenie[kolejka.bufor.length];
		
		for (int i = bufor.length - 1; i >= 0; --i) {
			bufor[i] = kolejka.bufor[i];
		}
		
		w = kolejka.bufor.length;
	}
	
	public void wstaw(final Zgloszenie zgloszenie) {
		if (kolejkaPelna()) {
			zmienDlugosc(bufor.length << 1);
		}
		
		bufor[w++] = zgloszenie;
	}
	
	public Zgloszenie usun() throws KolejkaPustaWyj {
		if (w == 0) {
			throw new KolejkaPustaWyj();
		}
		
		Zgloszenie z = bufor[--w];
		
		bufor[w] = null;
		
		if (w > 0 && w == bufor.length >> 2) {
			zmienDlugosc(bufor.length >> 1);
		}
		
		return z;
	}
	
	public Zgloszenie doUsuniecia() throws KolejkaPustaWyj {
		if (w == 0) {
			throw new KolejkaPustaWyj();
		}
		
		return bufor[w - 1];
	}
	
	public boolean kolejkaPusta() {
		return w == 0;
	}
	
	public int stan() {
		return w;
	}
}
