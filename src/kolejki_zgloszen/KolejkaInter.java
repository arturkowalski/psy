package kolejki_zgloszen;

public interface KolejkaInter {
	void wstaw(final Zgloszenie zgloszenie) throws KolejkaPelnaWyj;
	
	Zgloszenie doUsuniecia() throws KolejkaPustaWyj;
	
	Zgloszenie usun() throws KolejkaPustaWyj;
	
	boolean kolejkaPusta();
	
	int stan();
}
