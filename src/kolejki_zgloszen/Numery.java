package kolejki_zgloszen;

public final class Numery {
	private final int pierwszy, roznica;
	
	private int nastepny;
	
	public Numery(final int pierwszy, final int roznica) {
		this.pierwszy = pierwszy;
		this.roznica = roznica;
		
		nastepny = pierwszy - roznica;
	}
	
	public int nastepny() {
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
