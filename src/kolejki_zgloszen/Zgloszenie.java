package kolejki_zgloszen;

import java.util.Comparator;

public final class Zgloszenie {
	private final int numer;
	private final double czasNadejscia;
	private final int priorytet;
	
	public Zgloszenie(final int numer, final double czasNadejscia, final int priorytet) {
		if (czasNadejscia < 0.0) {
			throw new IllegalArgumentException("\nCzas nadejscia mniejszy niz 0");
		}
		
		if (priorytet < 1 || priorytet > 10) {
			throw new IllegalArgumentException("\nPriorytet spoza zakresu");
		}
		
		this.numer = numer;
		this.czasNadejscia = czasNadejscia;
		this.priorytet = priorytet;
	}
	
	public int numer() {
		return numer;
	}
	
	public double czasNadejscia() {
		return czasNadejscia;
	}
	
	public int priorytet() {
		return priorytet;
	}
	
	public static Comparator<Zgloszenie> komparatorFifo() {
		return new Comparator<Zgloszenie>() {
			public int compare(Zgloszenie z1, Zgloszenie z2) {
				if (z1.priorytet < z2.priorytet || z1.priorytet ==  z2.priorytet && z1.numer > z2.numer) {
					return -1;
				}
				else if (z1.priorytet == z2.priorytet && z1.numer == z2.numer) {
					return 0;
				}
				else {
					return 1;
				}
			}
		};
	}
	
	public static Comparator<Zgloszenie> komparatorLifo() {
		return new Comparator<Zgloszenie>() {
			public int compare(Zgloszenie z1, Zgloszenie z2) {
				if (z1.priorytet < z2.priorytet || z1.priorytet ==  z2.priorytet && z1.numer < z2.numer) {
					return -1;
				}
				else if (z1.priorytet == z2.priorytet && z1.numer == z2.numer) {
					return 0;
				}
				else {
					return 1;
				}
			}
		};
	}
	
	public String toString() {
		return "Zgloszenie " + numer + "/" + czasNadejscia + "/" + priorytet;
	}
	
	public static void main(String[] args) {
		Sekwencja numery = new Sekwencja();
		Stoper stoper = new Stoper();
		java.util.Random generator = new java.util.Random();
		Zgloszenie[] zgloszenia = new Zgloszenie[3];
		Comparator<Zgloszenie> komparatorF = Zgloszenie.komparatorFifo();
		Comparator<Zgloszenie> komparatorL = Zgloszenie.komparatorLifo();
		
		System.out.println("Tablica zgloszen:");
		for (int i = 0; i < zgloszenia.length; ++i) {
			zgloszenia[i] = new Zgloszenie(numery.nastepny(), stoper.czas(), generator.nextInt(10) + 1);
			System.out.println(zgloszenia[i]);
		}
		
		System.out.println("\nKomparator pierwszy:");
		for (Zgloszenie z1 : zgloszenia) {
			for (Zgloszenie z2 : zgloszenia) {
				System.out.print(z1 + " ");
				if (komparatorF.compare(z1, z2) < 0) {
					System.out.println("< " + z2);
				}
				else if (komparatorF.compare(z1, z2) == 0) {
					System.out.println("= " + z2);
				}
				else {
					System.out.println("> " + z2);
				}
			}
		}
		
		System.out.println("\nKomparator drugi:");
		for (Zgloszenie z1 : zgloszenia) {
			for (Zgloszenie z2 : zgloszenia) {
				System.out.print(z1 + " ");
				if (komparatorL.compare(z1, z2) < 0) {
					System.out.println("< " + z2);
				}
				else if (komparatorL.compare(z1, z2) == 0) {
					System.out.println("= " + z2);
				}
				else {
					System.out.println("> " + z2);
				}
			}
		}
	}
}
