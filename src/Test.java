import kolejki_zgloszen.*;

import java.util.Random;

public class Test {
	public static void main(String[] args) {
		KolejkaPriorytetowaDlugoscStala kolejka = new KolejkaPriorytetowaDlugoscStala(10);
		Numery postep = new Numery(1, 1);
		Random priorytety = new Random();
		
		char z = ' ';
		
		for (int i = 1; i <= 15; ++i) {
			try {
				kolejka.wstaw(new Zgloszenie(postep, (double) i, priorytety.nextInt(10) + 1));
			}
			catch (KolejkaPelnaWyj wyj) {
				System.out.println(wyj.toString());
			}
		}
		
		for (int i = kolejka.stan(); i > 0; --i) {
			try {
				System.out.println(kolejka.usun().toString());
			}
			catch (KolejkaPustaWyj wyj) {
				System.out.println(wyj.toString());
			}
		}
	}
}
