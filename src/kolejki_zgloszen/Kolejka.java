package kolejki_zgloszen;

public interface Kolejka {
	void wstaw(final Zgloszenie zgloszenie) throws KolejkaPelnaWyj;
	
	Zgloszenie usun() throws KolejkaPustaWyj;
	
	boolean kolejkaPusta();
	
	int stan();
}
