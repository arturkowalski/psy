package kolejki_zgloszen;

public interface Kolejka extends Iterable<Zgloszenie> {
	void wstaw(final Zgloszenie zgloszenie) throws KolejkaPelnaWyj;
	
	Zgloszenie usun() throws KolejkaPustaWyj;
	
	boolean kolejkaPusta();
	
	default boolean kolejkaPelna() {
		throw new UnsupportedOperationException("Kolejka nie moze byc pelna");
	}
	
	int stan();
}
