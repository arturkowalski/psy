package kolejki_zgloszen;

public final class Zegar {
	private final long t0;
	
	public Zegar() {
		t0 = System.currentTimeMillis();
	}
	
	public double czasOdStartu() {
		long t = System.currentTimeMillis();
		
		return (t - t0) / 1000.0;
	}
}
