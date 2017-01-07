package kolejki_zgloszen;

public interface KolejkaI extends Iterable<Zgloszenie> {
	default int dlugosc() {
		throw new UnsupportedOperationException("Dlugosc bufora niedostepna");
	}
	
	default boolean kolejkaPelna() {
		throw new UnsupportedOperationException("Kolejka nie moze byc pelna");
	}
	
	boolean kolejkaPusta();
	
	int stan();
	
	void wstaw(final Zgloszenie zgloszenie) throws KolejkaPelnaWyj;
	
	Zgloszenie nastepne() throws KolejkaPustaWyj;
	
	Zgloszenie usun() throws KolejkaPustaWyj;
	
	//Zgloszenie usunWybrane(Zgloszenie zgloszenie) throws KolejkaPustaWyj;
	//void usunWybrane(int numer) throws KolejkaPustaWyj;
}
