package kolejki_zgloszen;

public final class Stoper {
	private final long start;
	
	public Stoper() {
		start = System.currentTimeMillis();
	}
	
	public double czas() {
		return (System.currentTimeMillis() - start) / 1000.0;
	}
}
