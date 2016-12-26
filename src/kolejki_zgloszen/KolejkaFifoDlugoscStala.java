package kolejki_zgloszen;

public final class KolejkaFifoDlugoscStala implements KolejkaZgloszen {
	private final Zgloszenie[] bufor;
	
	private int iw, iu;
	private int stan;
	
	public KolejkaFifoDlugoscStala(final int dlugosc) {
		if (dlugosc <= 0) {
			throw new IllegalArgumentException("Dlugosc mniejsza niz 1");
		}

		bufor = new Zgloszenie[dlugosc];
		
		iw = iu = stan = 0;
	}
	
	public KolejkaFifoDlugoscStala(final Zgloszenie[] tablica) {
		if (tablica == null) {
			throw new IllegalArgumentException("Tablica-null");
		}
		
		bufor = new Zgloszenie[tablica.length];
		
		for (int i = bufor.length - 1; i >= 0; --i) {
			bufor[i] = tablica[i];
		}
		
		iw = stan = tablica.length;
		iu = 0;
	}
	
	public KolejkaFifoDlugoscStala(final KolejkaFifoDlugoscStala kolejka) {
		if (kolejka == null) {
			throw new IllegalArgumentException("Kolejka-null");
		}
		
		bufor = new Zgloszenie[kolejka.bufor.length];
		
		for (stan = 0, iw = kolejka.iu; stan < kolejka.stan; ++stan, ++iw) {
			bufor[iw] = kolejka.bufor[iw % bufor.length];
		}
		
		iw = kolejka.iw; iu = kolejka.iu;
	}
	
	public void wstaw(final Zgloszenie zgloszenie) throws KolejkaPelnaWyj {
		if (kolejkaPelna()) {
			throw new KolejkaPelnaWyj(bufor.length, zgloszenie);
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
		
		--stan;
		
		if (iu == bufor.length) {
			iu = 0;
		}
		
		return z;
	}
	
	public boolean kolejkaPusta() {
		return stan == 0;
	}
	
	public int stan() {
		return stan;
	}
	
	public Zgloszenie nastepne() throws KolejkaPustaWyj {
		if (iu == iw) {
			throw new KolejkaPustaWyj();
		}
		
		return bufor[iu];
	}
	
	public boolean kolejkaPelna() {
		return stan == bufor.length;
	}
	
	public int dlugosc() {
		return bufor.length;
	}
}
