package kolejki;

public interface Kolejka<TypElementow> extends Iterable<TypElementow> {
	void wstaw(final TypElementow element) throws KolejkaPelnaWyj;
	
	TypElementow usun() throws KolejkaPustaWyj;
	
	boolean kolejkaPusta();
	
	default boolean kolejkaPelna() {
		throw new UnsupportedOperationException("Kolejka nie moze byc pelna");
	}
	
	int stan();
}
