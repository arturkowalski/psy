package zgloszenia;

public final class Sekwencja {
	private final int pierwszy, roznica;
	
	private int nastepny;
	
	private boolean add(int a, int b) {
		long c = (long) a + (long) b;
		
		return c < 0x80000000 || c > 0x7FFFFFFF;
	}
	
	public Sekwencja(final int pierwszy, final int roznica) {
		if (roznica == 0) {
			throw new IllegalArgumentException("Roznica rowna 0");
		}
		
		this.pierwszy = pierwszy;
		this.roznica = roznica;
		
		nastepny = pierwszy - roznica;
	}
	
	public int nastepny() throws SekwencjaWykorzystanaWyj {
		if (add(nastepny, roznica)) {
			if (nastepny + roznica != pierwszy) {
				throw new SekwencjaWykorzystanaWyj();
			}
		}
		
		nastepny += roznica;
		
		return nastepny;
	}
	
	public int[] nNastepnych(final int n) {
		int[] wyn = new int[n];
		
		for (int i = 0; i < n; ++i) {
			wyn[i] = nastepny();
		}
		
		return wyn;
	}
	
	public int pierwszy() {
		return pierwszy;
	}
	
	public int roznica() {
		return roznica;
	}
}
