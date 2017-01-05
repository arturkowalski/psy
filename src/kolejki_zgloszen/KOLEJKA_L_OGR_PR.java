package kolejki_zgloszen;

import java.util.Iterator;
import java.util.NoSuchElementException;

public final class KOLEJKA_L_OGR_PR implements KOLEJKA_I {
	private final Zgloszenie[] bufor;
	
	private int stan;
	
	private class KOLEJKA_L_OGR_PR_IT implements Iterator<Zgloszenie> {
		private KOLEJKA_L_OGR_PR kolejka;
		
		private KOLEJKA_L_OGR_PR_IT() {
			kolejka = new KOLEJKA_L_OGR_PR(KOLEJKA_L_OGR_PR.this);
		}
		
		public boolean hasNext() {
			return !kolejka.kolejkaPusta();
		}
		
		public Zgloszenie next() {
			if (!hasNext()){
				throw new NoSuchElementException("Iterator wykorzystany");
			}
			
			return kolejka.usun();
		}
	}
	
	public Iterator<Zgloszenie> iterator() {
		return new KOLEJKA_L_OGR_PR_IT();
	}
	
	private boolean porownanie(int i, int j) {
		return bufor[i].priorytet() < bufor[j].priorytet() || bufor[i].priorytet()
			==  bufor[j].priorytet() && bufor[i].numer() < bufor[j].numer();
	}
	
	private void zamien(int i, int j) {
		Zgloszenie z = bufor[i];
		
		bufor[i] = bufor[j];
		bufor[j] = z;
	}
	
	private void przywrocStruktureOdDolu(int i) {
		while (i > 1 && porownanie(i / 2, i)) {
			zamien(i, i / 2);
			i /= 2;
		}
	}
	
	private void przywrocStruktureOdGory(int i) {
		while (2 * i <= stan) {
			int j = 2 * i;
			
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
		
		int liscLewy = 2 * k;
		int liscPrawy = 2 * k + 1;
		
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
	
	public KOLEJKA_L_OGR_PR(final int dlugosc) {
		if (dlugosc <= 0) {
			throw new IllegalArgumentException("Dlugosc mniejsza niz 1");
		}
		
		bufor = new Zgloszenie[dlugosc + 1];
	}
	
	public KOLEJKA_L_OGR_PR(final Zgloszenie[] tablica) {
		if (tablica == null) {
			throw new IllegalArgumentException("Tablica-parametr rowna null");
		}
		
		bufor = new Zgloszenie[(stan = tablica.length) + 1];
		
		System.arraycopy(tablica, 0, bufor, 1, tablica.length);
		
		for (int k = stan / 2; k >= 1; --k) {
			przywrocStruktureOdGory(k);
		}
		
		//assert strukturaDrzewaPoprawna();
	}
	
	public KOLEJKA_L_OGR_PR(final KOLEJKA_L_OGR_PR kolejka) {
		if (kolejka == null) {
			throw new IllegalArgumentException("Kolejka-parametr rowna null");
		}
		
		bufor = new Zgloszenie[kolejka.bufor.length];
		
		System.arraycopy(kolejka.bufor, 1, bufor, 1, kolejka.stan);
		
		stan = kolejka.stan;
		
		//assert strukturaDrzewaPoprawna();
	}
	
	public int dlugosc() {
		return bufor.length;
	}
	
	public boolean kolejkaPelna() {
		return stan == bufor.length - 1;
	}
	
	public boolean kolejkaPusta() {
		return stan == 0;
	}
	
	public int stan() {
		return stan;
	}
	
	public void wstaw(Zgloszenie zgloszenie) throws KolejkaPelnaWyj {
		if (kolejkaPelna()) {
			throw new KolejkaPelnaWyj(bufor.length - 1, zgloszenie);
		}
		
		bufor[++stan] = zgloszenie;
		przywrocStruktureOdDolu(stan);
		
		//assert strukturaDrzewaPoprawna();
	}
	
	public Zgloszenie nastepne() throws KolejkaPustaWyj {
		if (kolejkaPusta()) {
			throw new KolejkaPustaWyj();
		}
		
		return bufor[1];
	}
	
	public Zgloszenie usun() throws KolejkaPustaWyj {
		if (kolejkaPusta()) {
			throw new KolejkaPustaWyj();
		}
		
		Zgloszenie z = bufor[1];
		
		zamien(1, stan--);
		przywrocStruktureOdGory(1);
		
		bufor[stan + 1] = null;
		
		//assert strukturaDrzewaPoprawna();
		
		return z;
	}
}
