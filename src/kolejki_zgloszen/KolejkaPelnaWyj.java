package kolejki_zgloszen;

public final class KolejkaPelnaWyj extends RuntimeException {
	private final int dlugosc;
	
	private final Zgloszenie zgloszenie;
	
	public KolejkaPelnaWyj(final int dlugosc, final Zgloszenie zgloszenie) {
		this.dlugosc = dlugosc;
		this.zgloszenie = zgloszenie;
	}
	
	public String toString() {
		return "Dlugosc kolejki rowna " + dlugosc + " - " + zgloszenie.toString().substring(0, 1).toLowerCase() +
			zgloszenie.toString().substring(1) + " odrzucone";
	}
}
