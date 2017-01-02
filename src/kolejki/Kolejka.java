package kolejki;

public interface Kolejka<Element> extends Iterable<Element> {
	void wstaw(final Element element) throws KolejkaPelnaWyj;
	
	Element nastepny() throws KolejkaPustaWyj;
	
	Element usun() throws KolejkaPustaWyj;
	
	boolean kolejkaPusta();
	
	default boolean kolejkaPelna() {
		throw new UnsupportedOperationException("Kolejka nie moze byc pelna");
	}
	
	int stan();
}
