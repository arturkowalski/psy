package kolejki_zgloszen;

public final class KolejkaLifoDlugoscZmienna implements KolejkaZgloszen {
	private int dlugosc;
	
	private Zgloszenie[] bufor;
	
	private int indeks;
	private int stan;
	
	public KolejkaLifoDlugoscZmienna(final int dlugosc) {
		if (dlugosc <= 0) {
			throw new IllegalArgumentException("Dlugosc mniejsza niz 1");
		}
		
		bufor = new Zgloszenie[this.dlugosc = dlugosc];
		
		indeks = stan = 0;
	}
	
	public KolejkaLifoDlugoscZmienna(final Zgloszenie[] tablica) {
		if (tablica == null) {
			throw new IllegalArgumentException("Tablica-null");
		}
		
		bufor = new Zgloszenie[dlugosc = tablica.length];
		
		for (int i = dlugosc - 1; i >= 0; --i) {
			bufor[i] = tablica[i];
		}
		
		indeks = stan = tablica.length;
	}
	
	public KolejkaLifoDlugoscZmienna(final KolejkaLifoDlugoscZmienna kolejka) {
		if (kolejka == null) {
			throw new IllegalArgumentException("Kolejka-null");
		}
		
		bufor = new Zgloszenie[dlugosc = kolejka.bufor.length];
		
		for (int i = dlugosc - 1; i >= 0; --i) {
			bufor[i] = kolejka.bufor[i];
		}
		
		indeks = stan = kolejka.bufor.length;
	}
	
	public void wstaw(final Zgloszenie zgloszenie) {
		if (indeks == dlugosc) {
			Zgloszenie t[] = new Zgloszenie[2 * dlugosc];
			
			for (int i = dlugosc - 1; i >= 0; --i) {
				t[i] = bufor[i];
			}
			
			dlugosc <<= 1;
			bufor = t;
		}
		
		bufor[indeks++] = zgloszenie;
		++stan;
	}
	
	public Zgloszenie doUsuniecia() throws KolejkaPustaWyj {
		if (indeks == 0) {
			throw new KolejkaPustaWyj();
		}
		
		return bufor[indeks - 1];
	}
	
	public Zgloszenie usun() throws KolejkaPustaWyj {
		if (indeks == 0) {
			throw new KolejkaPustaWyj();
		}
		
		Zgloszenie z = bufor[--indeks];
		
		bufor[indeks] = null;
		
		--stan;
		
		return z;
	}
	
	public boolean kolejkaPusta() {
		// return indeks == 0;
		return stan == 0;
	}
	
	public int stan() {
		return stan;
	}
}
