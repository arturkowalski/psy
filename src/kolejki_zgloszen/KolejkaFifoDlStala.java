package kolejki_zgloszen;

public final class KolejkaFifoDlStala implements KolejkaInter {
	private final int dlugosc;
	
	private final Zgloszenie[] bufor;
	
	private int iWstaw, iUsun;
	private int stan;
	
	public KolejkaFifoDlStala(final int dlugosc) {
		if (dlugosc <= 0) {
			throw new IllegalArgumentException("Dlugosc mniejsza niz 1");
		}
		
		// Wywolanie metody wstaw(), gdy iWstaw = iUsun - 1
		bufor = new Zgloszenie[(this.dlugosc = dlugosc) + 1];
		
		iWstaw = iUsun = stan = 0;
	}
	
	public KolejkaFifoDlStala(final Zgloszenie[] tablica) {
		if (tablica == null) {
			throw new IllegalArgumentException("Tablica-null");
		}
		
		bufor = new Zgloszenie[(dlugosc = tablica.length) + 1];
		
		for (int i = dlugosc - 1; i >= 0; --i) {
			bufor[i] = tablica[i];
		}
		
		iWstaw = stan = tablica.length;
		iUsun = 0;
	}
	
	public KolejkaFifoDlStala(final KolejkaFifoDlStala kolejka) {
		if (kolejka == null) {
			throw new IllegalArgumentException("Kolejka-null");
		}
		
		bufor = new Zgloszenie[(dlugosc = kolejka.dlugosc) + 1];
		
		// stan < kolejka.stan
		for (stan = 0, iWstaw = kolejka.iUsun; stan < kolejka.stan; ++stan, ++iWstaw) {
			if (iWstaw == bufor.length) {
				iWstaw %= bufor.length;
			}
			
			bufor[iWstaw] = kolejka.bufor[iWstaw];
		}
		
		iWstaw = kolejka.iWstaw; iUsun = kolejka.iUsun;
	}
	
	public void wstaw(final Zgloszenie zgloszenie) throws KolejkaPelnaWyj {
		if (kolejkaPelna()) {
			throw new KolejkaPelnaWyj(dlugosc);
		}
		
		bufor[iWstaw++] = zgloszenie;
		
		if (iWstaw == bufor.length) {
			iWstaw = 0;
		}
		
		++stan;
	}
	
	public Zgloszenie doUsuniecia() throws KolejkaPustaWyj {
		if (iUsun == iWstaw) {
			throw new KolejkaPustaWyj();
		}
		
		return bufor[iUsun];
	}
	
	public Zgloszenie usun() throws KolejkaPustaWyj {
		if (kolejkaPusta()) {
			throw new KolejkaPustaWyj();
		}
		
		Zgloszenie z = bufor[iUsun];
		
		bufor[iUsun++] = null;
		
		--stan;
		
		if (iUsun == bufor.length) {
			iUsun = 0;
		}
		
		return z;
	}
	
	public boolean kolejkaPusta() {
		// return iUsun == iWstaw;
		return stan == 0;
	}
	
	public boolean kolejkaPelna() {
		// return iWstaw == dlugosc && iUsun == 0 || iWstaw + 1 == iUsun;
		// return stan == dlugosc && iUsun == 0 || iWstaw + 1 == iUsun;
		return stan == dlugosc;
	}
	
	public int stan() {
		return stan;
	}
	
	public int dlugosc() {
		return dlugosc;
	}
}
