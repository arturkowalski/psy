package kolejki_zgloszen;

public final class KolejkaFifoDlugoscZmienna implements KolejkaZgloszenInt {
	private int dlugosc;
	
	private Zgloszenie[] bufor;
	
	private int iWstaw, iUsun;
	private int stan;
	
	// Wyjsciowa dugosc moze sie tylko zwiekszyc
	public KolejkaFifoDlugoscZmienna(final int dlugosc) {
		if (dlugosc <= 0) {
			throw new IllegalArgumentException("Dlugosc mniejsza niz 1");
		}
		
		bufor = new Zgloszenie[(this.dlugosc = dlugosc) + 1];
		
		iWstaw = iUsun = stan = 0;
	}
	
	public KolejkaFifoDlugoscZmienna(final Zgloszenie[] tablica) {
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
	
	public KolejkaFifoDlugoscZmienna(final KolejkaFifoDlugoscZmienna kolejka) {
		if (kolejka == null) {
			throw new IllegalArgumentException("Kolejka-null");
		}
		
		bufor = new Zgloszenie[(dlugosc = kolejka.dlugosc) + 1];
		
		for (stan = 0, iWstaw = kolejka.iUsun; stan < kolejka.stan; ++stan, ++iWstaw) {
			if (iWstaw == bufor.length) {
				iWstaw %= bufor.length;
			}
			
			bufor[iWstaw] = kolejka.bufor[iWstaw];
		}
		
		iWstaw = kolejka.iWstaw;
		iUsun = kolejka.iUsun;
	}
	
	public void wstaw(final Zgloszenie zgloszenie) {
		// if (stan == dlugosc && iUsun == 0) {
		if (iWstaw == dlugosc && iUsun == 0) {
			Zgloszenie t[] = new Zgloszenie[2 * dlugosc + 1];
			
			for (int i = dlugosc - 1; i >= 0; --i) {
				t[i] = bufor[i];
			}
			
			dlugosc <<= 1;
			bufor = t;
		}
		
		if (iWstaw + 1 == iUsun) {
			Zgloszenie t[] = new Zgloszenie[2 * dlugosc + 1];
			
			for (int i = stan - 1; i >= 0; ++i) {
				if (iUsun + i >= bufor.length) {
					t[i] = bufor[(iUsun + i) % bufor.length];
				}
				else {
					t[i] = bufor[iUsun + i];
				}
			}
			
			dlugosc <<= 1;
			iWstaw = stan;
			iUsun = 0;
			bufor = t;
		}
		
		bufor[iWstaw++] = zgloszenie;
		++stan;
		
		if (iWstaw == bufor.length) {
			iWstaw = 0;
		}
	}
	
	public Zgloszenie doUsuniecia() throws KolejkaPustaWyj {
		if (kolejkaPusta()) {
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
	
	public int stan() {
		return stan;
	}
}
