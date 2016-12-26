package kolejki_zgloszen;

public final class KolejkaLifoDlugoscStala implements KolejkaZgloszenInt {
	private final int dlugosc;
	
	private final Zgloszenie[] bufor;
	
	private int indeks;
	private int stan;
	
	public KolejkaLifoDlugoscStala(final int dlugosc) {
		if (dlugosc <= 0) {
			throw new IllegalArgumentException("Dlugosc mniejsza niz 1");
		}
		
		bufor = new Zgloszenie[this.dlugosc = dlugosc];
		
		indeks = stan = 0;
	}
	
	public KolejkaLifoDlugoscStala(final Zgloszenie[] tablica) {
		if (tablica == null) {
			throw new IllegalArgumentException("Tablica-null");
		}
		
		bufor = new Zgloszenie[dlugosc = tablica.length];
		
		for (int i = dlugosc - 1; i >= 0; --i) {
			bufor[i] = tablica[i];
		}
		
		indeks = stan = tablica.length;
	}
	
	public KolejkaLifoDlugoscStala(final KolejkaLifoDlugoscStala kolejka) {
		if (kolejka == null) {
			throw new IllegalArgumentException("Kolejka-null");
		}
		
		bufor = new Zgloszenie[dlugosc = kolejka.bufor.length];
		
		for (int i = dlugosc - 1; i >= 0; --i) {
			bufor[i] = kolejka.bufor[i];
		}
		
		indeks = stan = kolejka.bufor.length;
	}
	
	public void wstaw(final Zgloszenie zgloszenie) throws KolejkaPelnaWyj {
		if (kolejkaPelna()) {
			throw new KolejkaPelnaWyj(dlugosc);
		}
		
		bufor[indeks++] = zgloszenie;
		++stan;
	}
	
	public Zgloszenie doUsuniecia() throws KolejkaPustaWyj {
		if (kolejkaPusta()) {
			throw new KolejkaPustaWyj();
		}
		
		return bufor[indeks - 1];
	}
	
	public Zgloszenie usun() throws KolejkaPustaWyj {
		if (kolejkaPusta()) {
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
	
	public boolean kolejkaPelna() {
		// return indeks == dlugosc;
		return stan == dlugosc;
	}
	
	public int stan() {
		return stan;
	}
	
	public int dlugosc() {
		return dlugosc;
	}
}
