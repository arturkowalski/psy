package kolejki;

public final class KolejkaPelnaWyj extends RuntimeException {
	private final int dlugosc;
	
	public KolejkaPelnaWyj(final int dlugosc) {
		this.dlugosc = dlugosc;
	}
	
	public String toString() {
		return "Kolejka pelna - maksymalna dlugosc rowna " + dlugosc;
	}
}
