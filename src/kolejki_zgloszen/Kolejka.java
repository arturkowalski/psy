package kolejki_zgloszen;

public interface Kolejka extends Iterable<Zgloszenie> {
	void wstaw(final Zgloszenie zgloszenie) throws KolejkaPelnaWyj;
	
	Zgloszenie usun() throws KolejkaPustaWyj;
	
	boolean kolejkaPusta();
	
	int stan();
}
