import kolejki_zgloszen.*;

import java.util.Random;
import java.io.IOException;

public class Test {
	public static void main(String[] args) {
		Sekwencja numery = new Sekwencja(1, 1);
		Zegar zegar = new Zegar();
		Random priorytety = new Random();
		KolejkaFifoDlugoscStala kolejka = new KolejkaFifoDlugoscStala(10);
		
		char q = ' ';
		
		while (!kolejka.kolejkaPelna()) {
			kolejka.wstaw(new Zgloszenie(numery.nastepny(), zegar.czasOdStartu(),
				priorytety.nextInt(10) + 1));
		}
		
		System.out.println("Kolejnosc zgloszen:");
		
		for (Zgloszenie z : kolejka) {
			System.out.println(z.toString());
		}
		
		System.out.println("\nZgloszenia usuniete:");
		
		while (!kolejka.kolejkaPusta()) {
			System.out.println(kolejka.usun().toString());
		}
		
		System.out.println("\nAby przerwac prace programu, wcisnij q");
		
		try {
			q = (char) System.in.read();
		}
		catch (IOException wyj) {
			System.out.println("Blad wejscia - " + wyj.toString().substring(0, 1).toLowerCase() +
				wyj.toString().substring(1));
		}
	}
}
