package kolejki_zgloszen;

import java.util.Comparator;

public final class Zgloszenie {
	private final int numer;
	private final double czasNadejscia;
	private final int priorytet;
	
	public Zgloszenie(final int numer, final double czasNadejscia, final int priorytet) {
		if (czasNadejscia < 0.0) {
			throw new IllegalArgumentException("Czas nadejscia mniejszy niz 0");
		}
		
		if (priorytet < 1 || priorytet > 10) {
			throw new IllegalArgumentException("Priorytet spoza zakresu");
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
}
