package kolejki_zgloszen;

public final class Sekwencja {
	private final int pierwszy, roznica;
	private int nastepny;
	
	private boolean add(int a, int b) {
		long c = (long) a + (long) b;
		return c < 0x80000000 || c > 0x7FFFFFFF;
	}
	
	public Sekwencja(final int pierwszy, final int roznica) {
		if (roznica == 0) {
			throw new IllegalArgumentException("\nRoznica rowna 0");
		}
		
		this.pierwszy = pierwszy;
		this.roznica = roznica;
		nastepny = pierwszy - roznica;
	}
	
	public Sekwencja() {
		this(1, 1);
	}
	
	public Sekwencja(final int pierwszy) {
		this(pierwszy, 1);
	}
	
	public int pierwszy() {
		return pierwszy;
	}
	
	public int roznica() {
		return roznica;
	}
	
	public int nastepny() throws SekwencjaZuzytaWyj {
		if (add(nastepny, roznica)) {
			if (nastepny + roznica != pierwszy) {
				throw new SekwencjaZuzytaWyj();
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
	
	public static void main(String[] args) {
		Sekwencja numery = new Sekwencja(0, 1000);
		try {
			while (true) {
				System.out.println(numery.nastepny());
			}
		}
		catch (SekwencjaZuzytaWyj wyj) {
			System.out.println(wyj.toString());
		}
	}
}
