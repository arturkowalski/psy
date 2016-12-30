import kolejki_zgloszen.*;

import java.util.Random;
import java.io.IOException;

public class Test {
	public static void main(String[] args) {
		Sekwencja numery = new Sekwencja(1, 1);
		Zegar zegar = new Zegar();
		Random priorytety = new Random();
		
		KolejkaFifoDlugoscStala kolejka11;
		KolejkaFifoDlugoscStala kolejka12;
		KolejkaFifoDlugoscZmienna kolejka21;
		KolejkaFifoDlugoscZmienna kolejka22;
		KolejkaLifoDlugoscStala kolejka31;
		KolejkaLifoDlugoscStala kolejka32;
		KolejkaLifoDlugoscZmienna kolejka41;
		KolejkaLifoDlugoscZmienna kolejka42;
		KolejkaPriorytetowaFifoDlugoscStala kolejka51;
		KolejkaPriorytetowaFifoDlugoscStala kolejka52;
		KolejkaPriorytetowaFifoDlugoscZmienna kolejka61;
		KolejkaPriorytetowaFifoDlugoscZmienna kolejka62;
		KolejkaPriorytetowaLifoDlugoscStala kolejka71;
		KolejkaPriorytetowaLifoDlugoscStala kolejka72;
		KolejkaPriorytetowaLifoDlugoscZmienna kolejka81;
		KolejkaPriorytetowaLifoDlugoscZmienna kolejka82;
		
		//kolejka11 = new KolejkaFifoDlugoscStala(25000000);
		//
		//while (kolejka11.stan() < 25000000) {
		//	kolejka11.wstaw(new Zgloszenie(numery.nastepny(), zegar.czasOdStartu(),
		//		priorytety.nextInt(10) + 1));
		//}
		//
		//kolejka12 = new KolejkaFifoDlugoscStala(kolejka11);
		//
		//kolejka11 = null;
		//
		//System.out.println("\nKolejka kolejka12:");
		//
		//for (Zgloszenie z : kolejka12) {
		//	System.out.println(z.toString());
		//}
		//
		//System.out.println("\nZgloszenia usuniete (kolejka12):");
		//
		//while (!kolejka12.kolejkaPusta()) {
		//	System.out.println(kolejka12.usun().toString());
		//}
		//
		//try {
		//	Thread.sleep(5000);
		//}
		//catch (InterruptedException wyj) {
		//	System.out.println(wyj.toString());
		//}
		//
		//kolejka12 = null;
		//
		//kolejka21 = new KolejkaFifoDlugoscZmienna();
		//
		//while (kolejka21.stan() < 25000000) {
		//	kolejka21.wstaw(new Zgloszenie(numery.nastepny(), zegar.czasOdStartu(),
		//		priorytety.nextInt(10) + 1));
		//}
		//
		//kolejka22 = new KolejkaFifoDlugoscZmienna(kolejka21);
		//
		//kolejka21 = null;
		//
		//System.out.println("\nKolejka kolejka22:");
		//
		//for (Zgloszenie z : kolejka22) {
		//	System.out.println(z.toString());
		//}
		//
		//System.out.println("\nZgloszenia usuniete (kolejka22):");
		//
		//while (!kolejka22.kolejkaPusta()) {
		//	System.out.println(kolejka22.usun().toString());
		//}
		//
		//try {
		//	Thread.sleep(5000);
		//}
		//catch (InterruptedException wyj) {
		//	System.out.println(wyj.toString());
		//}
		//
		//kolejka22 = null;
		//
		//kolejka31 = new KolejkaLifoDlugoscStala(25000000);
		//
		//while (kolejka31.stan() < 25000000) {
		//	kolejka31.wstaw(new Zgloszenie(numery.nastepny(), zegar.czasOdStartu(),
		//		priorytety.nextInt(10) + 1));
		//}
		//
		//kolejka32 = new KolejkaLifoDlugoscStala(kolejka31);
		//
		//kolejka31 = null;
		//
		//System.out.println("\nKolejka kolejka32:");
		//
		//for (Zgloszenie z : kolejka32) {
		//	System.out.println(z.toString());
		//}
		//
		//System.out.println("\nZgloszenia usuniete (kolejka32):");
		//
		//while (!kolejka32.kolejkaPusta()) {
		//	System.out.println(kolejka32.usun().toString());
		//}
		//
		//try {
		//	Thread.sleep(5000);
		//}
		//catch (InterruptedException wyj) {
		//	System.out.println(wyj.toString());
		//}
		//
		//kolejka32 = null;
		//
		//kolejka41 = new KolejkaLifoDlugoscZmienna();
		//
		//while (kolejka41.stan() < 25000000) {
		//	kolejka41.wstaw(new Zgloszenie(numery.nastepny(), zegar.czasOdStartu(),
		//		priorytety.nextInt(10) + 1));
		//}
		//
		//kolejka42 = new KolejkaLifoDlugoscZmienna(kolejka41);
		//
		//kolejka41 = null;
		//
		//System.out.println("\nKolejka kolejka42:");
		//
		//for (Zgloszenie z : kolejka42) {
		//	System.out.println(z.toString());
		//}
		//
		//System.out.println("\nZgloszenia usuniete (kolejka42):");
		//
		//while (!kolejka42.kolejkaPusta()) {
		//	System.out.println(kolejka42.usun().toString());
		//}
		//
		//try {
		//	Thread.sleep(5000);
		//}
		//catch (InterruptedException wyj) {
		//	System.out.println(wyj.toString());
		//}
		//
		//kolejka42 = null;
		//
		//kolejka51 = new KolejkaPriorytetowaFifoDlugoscStala(25000000);
		//
		//while (kolejka51.stan() < 25000000) {
		//	kolejka51.wstaw(new Zgloszenie(numery.nastepny(), zegar.czasOdStartu(),
		//		priorytety.nextInt(10) + 1));
		//}
		//
		//kolejka52 = new KolejkaPriorytetowaFifoDlugoscStala(kolejka51);
		//
		//kolejka51 = null;
		//
		//System.out.println("\nKolejka kolejka52:");
		//
		//for (Zgloszenie z : kolejka52) {
		//	System.out.println(z.toString());
		//}
		//
		//System.out.println("\nZgloszenia usuniete (kolejka52):");
		//
		//while (!kolejka52.kolejkaPusta()) {
		//	System.out.println(kolejka52.usun().toString());
		//}
		//
		//try {
		//	Thread.sleep(5000);
		//}
		//catch (InterruptedException wyj) {
		//	System.out.println(wyj.toString());
		//}
		//
		//kolejka52 = null;
		//
		//kolejka61 = new KolejkaPriorytetowaFifoDlugoscZmienna();
		//
		//while (kolejka61.stan() < 25000000) {
		//	kolejka61.wstaw(new Zgloszenie(numery.nastepny(), zegar.czasOdStartu(),
		//		priorytety.nextInt(10) + 1));
		//}
		//
		//kolejka62 = new KolejkaPriorytetowaFifoDlugoscZmienna(kolejka61);
		//
		//kolejka61 = null;
		//
		//System.out.println("\nKolejka kolejka62:");
		//
		//for (Zgloszenie z : kolejka62) {
		//	System.out.println(z.toString());
		//}
		//
		//System.out.println("\nZgloszenia usuniete (kolejka62):");
		//
		//while (!kolejka62.kolejkaPusta()) {
		//	System.out.println(kolejka62.usun().toString());
		//}
		//
		//try {
		//	Thread.sleep(5000);
		//}
		//catch (InterruptedException wyj) {
		//	System.out.println(wyj.toString());
		//}
		//
		//kolejka62 = null;
		//
		//kolejka71 = new KolejkaPriorytetowaLifoDlugoscStala(25000000);
		//
		//while (kolejka71.stan() < 25000000) {
		//	kolejka71.wstaw(new Zgloszenie(numery.nastepny(), zegar.czasOdStartu(),
		//		priorytety.nextInt(10) + 1));
		//}
		//
		//kolejka72 = new KolejkaPriorytetowaLifoDlugoscStala(kolejka71);
		//
		//kolejka71 = null;
		//
		//System.out.println("\nKolejka kolejka72:");
		//
		//for (Zgloszenie z : kolejka72) {
		//	System.out.println(z.toString());
		//}
		//
		//System.out.println("\nZgloszenia usuniete (kolejka72):");
		//
		//while (!kolejka72.kolejkaPusta()) {
		//	System.out.println(kolejka72.usun().toString());
		//}
		//
		//try {
		//	Thread.sleep(5000);
		//}
		//catch (InterruptedException wyj) {
		//	System.out.println(wyj.toString());
		//}
		//
		//kolejka72 = null;
		
		kolejka81 = new KolejkaPriorytetowaLifoDlugoscZmienna();
		
		while (kolejka81.stan() < 25000000) {
			kolejka81.wstaw(new Zgloszenie(numery.nastepny(), zegar.czasOdStartu(),
				priorytety.nextInt(10) + 1));
		}
		
		kolejka82 = new KolejkaPriorytetowaLifoDlugoscZmienna(kolejka81);
		
		kolejka81 = null;
		
		System.out.println("\nKolejka kolejka82:");
		
		for (Zgloszenie z : kolejka82) {
			System.out.println(z.toString());
		}
		
		System.out.println("\nZgloszenia usuniete (kolejka82):");
		
		while (!kolejka82.kolejkaPusta()) {
			System.out.println(kolejka82.usun().toString());
		}
		
	}
}
