package kolejki_zgloszen;

public class KolejkaPriorytetowaLifoDlugoscStala {
	private final Zgloszenie[] bufor;
	
	private int stan;
	
	private boolean porownanie(int i, int j) {
		//return bufor[i].priorytet() < bufor[j].priorytet();
		return bufor[i].priorytet() < bufor[j].priorytet() || bufor[i].priorytet()
			==  bufor[j].priorytet() && bufor[i].numer() < bufor[j].numer();
	}
	
	private void zamien(int i, int j) {
		Zgloszenie z = bufor[i];
		
		bufor[i] = bufor[j];
		bufor[j] = z;
	}
	
	private void przywrocStruktureOdDolu(int i) {
		while (i > 1 && porownanie(i >> 1, i)) {
			zamien(i, i >> 1);
			
			i >>= 1;
		}
	}
	
	private void przywrocStruktureOdGory(int i) {
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
	
	private boolean strukturaPoddrzewaPoprawna(int k) {
		if (k > stan) {
			return true;
		}
		
		int liscLewy = k << 1;
		int liscPrawy = (k << 1) + 1;
		
		if (liscLewy  <= stan && porownanie(k, liscLewy))  {
			return false;
		}
		
		if (liscPrawy <= stan && porownanie(k, liscPrawy)) {
			return false;
		}
		
		return strukturaPoddrzewaPoprawna(liscLewy) && strukturaPoddrzewaPoprawna(liscPrawy);
	}
	
	private boolean strukturaDrzewaPoprawna() {
		return strukturaPoddrzewaPoprawna(1);
	}
	
	public KolejkaPriorytetowaLifoDlugoscStala(final int dlugosc) {
		if (dlugosc <= 0) {
			throw new IllegalArgumentException("Dlugosc mniejsza niz 1");
		}
		
		bufor = new Zgloszenie[dlugosc + 1];
		
		bufor[0] = null;
		stan = 0;
	}
	
	public KolejkaPriorytetowaLifoDlugoscStala(final Zgloszenie[] tablica) {
		if (tablica == null) {
			throw new IllegalArgumentException("Tablica-parametr rowna null");
		}
		
		bufor = new Zgloszenie[(stan = tablica.length) + 1];
		
		for (int i = stan - 1; i >= 0; --i) {
			bufor[i + 1] = tablica[i];
		}
		
		for (int k = stan >> 1; k >= 1; --k) {
			przywrocStruktureOdGory(k);
		}
		
		assert strukturaDrzewaPoprawna();
	}
	
	public KolejkaPriorytetowaLifoDlugoscStala(final KolejkaPriorytetowaLifoDlugoscStala kolejka) {
		if (kolejka == null) {
			throw new IllegalArgumentException("Kolejka-parametr rowna null");
		}
		
		bufor = new Zgloszenie[(stan = kolejka.bufor.length) + 1];
		
		for (int i = stan - 1; i >= 0; --i) {
			bufor[i + 1] = kolejka.bufor[i];
		}
		
		for (int k = stan >> 1; k >= 1; --k) {
			przywrocStruktureOdGory(k);
		}
		
		assert strukturaDrzewaPoprawna();
	}
	
	public void wstaw(Zgloszenie zgloszenie) throws KolejkaPelnaWyj {
		if (kolejkaPelna()) {
			throw new KolejkaPelnaWyj(bufor.length - 1, zgloszenie);
		}
		
		bufor[++stan] = zgloszenie;
		
		przywrocStruktureOdDolu(stan);
		
		assert strukturaDrzewaPoprawna();
	}
	
	public Zgloszenie usun() throws KolejkaPustaWyj {
		if (kolejkaPusta()) {
			throw new KolejkaPustaWyj();
		}
		
		Zgloszenie z = bufor[1];
		
		zamien(1, stan--);
		
		przywrocStruktureOdGory(1);
		
		bufor[stan + 1] = null;
		
		assert strukturaDrzewaPoprawna();
		
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
		
		return bufor[1];
	}
	
	public boolean kolejkaPelna() {
		return stan == bufor.length - 1;
	}
	
	public int dlugosc() {
		return bufor.length;
	}
}
