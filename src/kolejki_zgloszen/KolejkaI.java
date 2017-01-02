package kolejki_zgloszen;

public interface KolejkaI extends Iterable<Zgloszenie> {
	default int dlugosc() {
		throw new UnsupportedOperationException("Dlugosc bufora niedostepna");
	}
	
	boolean kolejkaPusta();
	
	default boolean kolejkaPelna() {
		throw new UnsupportedOperationException("KolejkaI nie moze byc pelna");
	}
	
	int stan();
	
	void wstaw(final Zgloszenie zgloszenie) throws KolejkaPelnaWyj;
	
	Zgloszenie nastepne() throws KolejkaPustaWyj;
	
	Zgloszenie usun() throws KolejkaPustaWyj;
	
	//Zgloszenie usunWybrane(int numer) throws NoSuchElementException;
}
