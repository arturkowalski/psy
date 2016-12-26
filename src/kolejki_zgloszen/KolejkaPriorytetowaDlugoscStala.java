package kolejki_zgloszen;

public final class KolejkaPriorytetowaDlugoscStala implements KolejkaZgloszen {
	private final Zgloszenie[] bufor;
	
	private int stan;
	
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
	
	public KolejkaPriorytetowaDlugoscStala(final int dlugosc) {
		if (dlugosc <= 0) {
			throw new IllegalArgumentException("Dlugosc mniejsza niz 1");
		}
		
		bufor = new Zgloszenie[dlugosc + 1];
		
		bufor[0] = null;
		stan = 0;
	}
	
	public void wstaw(Zgloszenie zgloszenie) throws KolejkaPelnaWyj {
		if (kolejkaPelna()) {
			throw new KolejkaPelnaWyj(bufor.length, zgloszenie);
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
	
	public boolean kolejkaPelna() {
		return stan == bufor.length - 1;
	}
	
	public int stan() {
		return stan;
	}
	
	public int dlugosc() {
		return bufor.length;
	}
}
