import kolejki_zgloszen.*;

import java.util.Random;

public class Test {
	public static void main(String[] args) {
		KolejkaPriorytetowaLifoDlugoscZmienna kolejka = new KolejkaPriorytetowaLifoDlugoscZmienna(10);
		Sekwencja numery = new Sekwencja(1, 1);
		Zegar zegar = new Zegar();
		Random priorytety = new Random();
		
		char z = ' ';
		
		while (kolejka.stan() < 30) {
			kolejka.wstaw(new Zgloszenie(numery.nastepny(), zegar.czasOdStartu(),
				priorytety.nextInt(10) + 1));
		}
		
		for (int i = kolejka.stan(); i > 0; --i) {
			System.out.println(kolejka.usun().toString());
		}
		
		//System.out.println("\nAby przerwac prace programu, wcisnij q");
		//
		//try {
		//	z = (char) System.in.read();
		//}
		//catch (IOException wyj) {
		//	System.out.println("Blad wejscia - " + wyj.toString().substring(0, 1).toLowerCase() +
		//		wyj.toString().substring(1));
		//}
	}
}
