package zgloszenia;

public final class Zegar {
	private final long t0;
	
	public Zegar() {
		t0 = System.currentTimeMillis();
	}
	
	public double czasOdStartu() {
		return (System.currentTimeMillis() - t0) / 1000.0;
	}
}
