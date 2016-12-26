import kolejki_zgloszen.*;

import java.util.Random;

public class Test {
	public static void main(String[] args) {
		KolejkaFifoDlugoscStala kolejka = new KolejkaFifoDlugoscStala(10);
		Numery postep = new Numery(1, 1);
		Random priorytety = new Random();
		
		int i = 1;
		char z = ' ';
		
		for ( ; i <= 10; ++i) {
			try {
				kolejka.wstaw(new Zgloszenie(postep, (double) i, priorytety.nextInt(10) + 1));
			}
			catch (KolejkaPelnaWyj wyj) {
				System.out.println(wyj.toString());
			}
		}
		
		try {
			kolejka.usun();
			kolejka.wstaw(new Zgloszenie(postep, (double) i, priorytety.nextInt(10) + 1));
		}
		catch (final KolejkaPustaWyj | KolejkaPelnaWyj wyj) {
			System.out.println(wyj.toString());
		}
		
		for (i = kolejka.stan(); i > 0; --i) {
			try {
				System.out.println(kolejka.usun().toString());
			}
			catch (KolejkaPustaWyj wyj) {
				System.out.println(wyj.toString());
			}
		}
	}
}
