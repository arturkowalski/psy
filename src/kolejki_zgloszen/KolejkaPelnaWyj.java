package kolejki_zgloszen;

public final class KolejkaPelnaWyj extends Exception {
	private final int dlugosc;
	
	public KolejkaPelnaWyj(final int dlugosc) {
		this.dlugosc = dlugosc;
	}
	
	public String toString() {
		return "Kolejka pelna - maksymalna dlugosc rowna " + dlugosc;
	}
}
