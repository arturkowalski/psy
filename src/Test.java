import kolejki_zgloszen.*;

import java.util.Random;
import java.io.IOException;

public class Test {
	public static void main(String[] args) {
		Sekwencja numery = new Sekwencja(1, 1);
		Zegar zegar = new Zegar();
		Random priorytety = new Random();
		KolejkaFifoDlugoscZmienna kolejka1 = new KolejkaFifoDlugoscZmienna();
		KolejkaFifoDlugoscZmienna kolejka2;
		
		char q = ' ';
		
		while (kolejka1.stan() < 25000000) {
			kolejka1.wstaw(new Zgloszenie(numery.nastepny(), zegar.czasOdStartu(),
				priorytety.nextInt(10) + 1));
		}
		
		System.out.println("Kolejka pierwsza:");
		
		for (Zgloszenie z : kolejka1) {
			System.out.println(z.toString());
		}
		
		kolejka2 = new KolejkaFifoDlugoscZmienna(kolejka1);
		
		System.out.println("\nKolejka druga:");
		
		for (Zgloszenie z : kolejka1) {
			System.out.println(z.toString());
		}
		
		System.out.println("\nZgloszenia usuniete (kolejka druga):");
		
		while (!kolejka2.kolejkaPusta()) {
			System.out.println(kolejka2.usun().toString());
		}
		
		//System.out.println("\nAby przerwac prace programu, wcisnij q");
		//
		//try {
		//	q = (char) System.in.read();
		//}
		//catch (IOException wyj) {
		//	System.out.println("Blad wejscia - " + wyj.toString().substring(0, 1).toLowerCase() +
		//		wyj.toString().substring(1));
		//}
	}
}
