import kolejki_zgloszen.*;

import java.util.Random;
import java.io.IOException;
import java.util.zip.ZipEntry;

public class Test {
	public static void main(String[] args) {
		final int r = 10;
		
		Zgloszenie[] zgloszenia = new Zgloszenie[r];
		
		Sekwencja numery = new Sekwencja(1, 1);
		Zegar zegar = new Zegar();
		Random priorytety = new Random();
		
		KolejkaFifoDlugoscStala kolejka1;
		KolejkaFifoDlugoscZmienna kolejka2;
		KolejkaLifoDlugoscStala kolejka3;
		KolejkaLifoDlugoscZmienna kolejka4;
		KolejkaPriorytetowaFifoDlugoscStala kolejka5;
		KolejkaPriorytetowaFifoDlugoscZmienna kolejka6;
		KolejkaPriorytetowaLifoDlugoscStala kolejka7;
		KolejkaPriorytetowaLifoDlugoscZmienna kolejka8;
		
		int i = 0;
		char z = ' ';
		
		for (i = 0 ; i < r; ++i) {
			zgloszenia[i] = new Zgloszenie(numery.nastepny(), zegar.czasOdStartu(),
				priorytety.nextInt(10) + 1);
		}
		
		//kolejka1 = new KolejkaFifoDlugoscStala(zgloszenia);
		//kolejka2 = new KolejkaFifoDlugoscZmienna(zgloszenia);
		//kolejka3 = new KolejkaLifoDlugoscStala(zgloszenia);
		//kolejka4 = new KolejkaLifoDlugoscZmienna(zgloszenia);
		//kolejka5 = new KolejkaPriorytetowaFifoDlugoscStala(zgloszenia);
		//kolejka6 = new KolejkaPriorytetowaFifoDlugoscZmienna(zgloszenia);
		//kolejka7 = new KolejkaPriorytetowaLifoDlugoscStala(zgloszenia);
		kolejka8 = new KolejkaPriorytetowaLifoDlugoscZmienna(zgloszenia);
		
		for (i = kolejka8.stan(); i <= 1000000; ++i) {
			kolejka8.wstaw(new Zgloszenie(numery.nastepny(), zegar.czasOdStartu(),
				priorytety.nextInt(10) + 1));
		}
		
		while (!kolejka8.kolejkaPusta()) {
			System.out.println(kolejka8.usun().toString());
		}
		
		System.out.println("\nAby przerwac prace programu, wcisnij q");
		
		try {
			z = (char) System.in.read();
		}
		catch (IOException wyj) {
			System.out.println("Blad wejscia - " + wyj.toString().substring(0, 1).toLowerCase() +
				wyj.toString().substring(1));
		}
	}
}
