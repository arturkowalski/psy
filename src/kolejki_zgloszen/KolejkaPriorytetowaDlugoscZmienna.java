package kolejki_zgloszen;

public final class KolejkaPriorytetowaDlugoscZmienna implements KolejkaZgloszen {
	private Zgloszenie[] bufor;
	
	private int stan;
	
	private boolean kolejkaPelna() {
		return stan == bufor.length - 1;
	}
	
	private void zmienDlugosc(int dl) {
		assert dl > stan;
		
		Zgloszenie[] tab = new Zgloszenie[dl];
		
		for (int i = 1; i <= stan; ++i) {
			tab[i] = bufor[i];
		}
		
		bufor = tab;
	}
	
	private boolean porownanie(int i, int j) {
		return bufor[i].priorytet() < bufor[j].priorytet();
	}
	
	private void zamien(int i, int j) {
		Zgloszenie z = bufor[i];
		
		bufor[i] = bufor[j];
		bufor[j] = z;
	}
	
	private void przywrocUkladOdDolu(int i) {
		while (i > 1 && porownanie(i >> 1, i)) {
			zamien(i, i >> 1);
			
			i >>= 1;
		}
	}
	
	private void przywrocUkladOdGory(int i) {
		while (i << 1 <= stan) {
			int j = i << 1;
			
			if (j < stan && porownanie(j, j + 1)) {
				++j;
			}
			
			if (!porownanie(i, j)) {
				break;
			}
			
			zamien(i, j);
			
			i = j;
		}
	}
	
	public KolejkaPriorytetowaDlugoscZmienna(final int dlugosc) {
		if (dlugosc <= 0) {
			throw new IllegalArgumentException("Dlugosc mniejsza niz 1");
		}
		
		bufor = new Zgloszenie[dlugosc + 1];
		
		bufor[0] = null;
		stan = 0;
	}
	
	public void wstaw(Zgloszenie zgloszenie) throws KolejkaPelnaWyj {
		if (kolejkaPelna()) {
			zmienDlugosc(bufor.length << 1);
		}
		
		bufor[++stan] = zgloszenie;
		
		przywrocUkladOdDolu(stan);
	}
	
	public Zgloszenie usun() throws KolejkaPustaWyj {
		if (kolejkaPusta()) {
			throw new KolejkaPustaWyj();
		}
		
		Zgloszenie z = bufor[1];
		
		zamien(1, stan--);
		
		przywrocUkladOdGory(1);
		
		bufor[stan + 1] = null;
		
		if ((stan > 0) && (stan == (bufor.length - 1) >> 2)) {
			zmienDlugosc(bufor.length >> 1);
		}
		
		return z;
	}
	
	public Zgloszenie nastepne() throws KolejkaPustaWyj {
		if (kolejkaPusta()) {
			throw new KolejkaPustaWyj();
		}
		
		return bufor[1];
	}
	
	public boolean kolejkaPusta() {
		return stan == 0;
	}
	
	public int stan() {
		return stan;
	}
	
	public int dlugosc() {
		return bufor.length;
	}
}
