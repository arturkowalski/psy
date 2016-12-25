package kolejki_zgloszen;

public final class Zgloszenie {
	private final int numer;
	private final double czasNadejscia;
	private final int priorytet;
	
	public Zgloszenie(final Numery numery, final double czasNadejscia, final int priorytet) {
		if (czasNadejscia < .0) {
			throw new IllegalArgumentException("Czas nadejscia mniejszy niz 0");
		}
		
		if (priorytet < 1 || priorytet > 10) {
			throw new IllegalArgumentException("Priorytet spoza zakresu");
		}
		
		this.numer = numery.nastepny();
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
	
	public String toString() {
		return "Zgloszenie numer " + numer + ": czas nadejscia rowny " + czasNadejscia +
			", priorytet - " + priorytet;
	}
}
