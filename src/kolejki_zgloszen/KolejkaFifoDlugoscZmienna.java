package kolejki_zgloszen;

public final class KolejkaFifoDlugoscZmienna implements Kolejka {
	private Zgloszenie[] bufor;
	
	private int iw, iu;
	private int stan;
	
	private boolean kolejkaPelna() {
		return stan == bufor.length;
	}
	
	private void zmienDlugosc(final int dl) {
		assert dl >= stan;
		
		Zgloszenie tab[] = new Zgloszenie[dl];
		
		for (int i = stan - 1; i >= 0; --i) {
			tab[i] = bufor[(iu + i) % bufor.length];
		}
		
		iw = stan;
		iu = 0;
		bufor = tab;
	}
	
	public KolejkaFifoDlugoscZmienna(final int dlugosc) {
		if (dlugosc <= 0) {
			throw new IllegalArgumentException("Dlugosc mniejsza niz 1");
		}
		
		bufor = new Zgloszenie[dlugosc];
		
		iw = iu = stan = 0;
	}
	
	public KolejkaFifoDlugoscZmienna() {
		this(30);
	}
	
	public KolejkaFifoDlugoscZmienna(final Zgloszenie[] tablica) {
		if (tablica == null) {
			throw new IllegalArgumentException("Tablica-parametr rowna null");
		}
		
		bufor = new Zgloszenie[tablica.length];
		
		for (int i = bufor.length - 1; i >= 0; --i) {
			bufor[i] = tablica[i];
		}
		
		iw = stan = tablica.length;
		iu = 0;
	}
	
	public KolejkaFifoDlugoscZmienna(final KolejkaFifoDlugoscZmienna kolejka) {
		if (kolejka == null) {
			throw new IllegalArgumentException("Kolejka-parametr rowna null");
		}
		
		bufor = new Zgloszenie[kolejka.bufor.length];
		
		for (stan = 0, iw = kolejka.iu; stan < kolejka.stan; ++stan, ++iw) {			
			bufor[iw] = kolejka.bufor[iw %= bufor.length];
		}
		
		iw = kolejka.iw;
		iu = kolejka.iu;
	}
	
	public void wstaw(final Zgloszenie zgloszenie) {
		if (kolejkaPelna()) {
			zmienDlugosc(bufor.length << 1);
		}
		
		bufor[iw++] = zgloszenie;
		++stan;
		
		if (iw == bufor.length) {
			iw = 0;
		}
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
		
		if (stan > 0 && stan == bufor.length >> 2) {
			zmienDlugosc(bufor.length >> 1);
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
		if (kolejkaPusta()) {
			throw new KolejkaPustaWyj();
		}
		
		return bufor[iu];
	}
}
