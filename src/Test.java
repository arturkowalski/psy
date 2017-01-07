import kolejki_zgloszen.*;
import java.util.Random;

import static java.lang.System.out;
import static java.lang.System.in;
import java.io.IOException;

public class Test {
	private static void stop(String lan) {
		char t = ' ';
		
		out.println(lan);
		
		try {
			while ((t = (char) in.read()) != '\n') {
				continue;
			}
		}
		catch (IOException wyj) {
			out.println(wyj.toString());
		}
	}
	
	public static void main(String[] args) {
		Sekwencja numery = new Sekwencja(1, 1);
		Stoper stoper = new Stoper();
		Random priorytety = new Random();
		KolejkaFifoOgrNpr k11;
		KolejkaFifoOgrNpr k12;
		KolejkaFifoNogrNpr k21;
		KolejkaFifoNogrNpr k22;
		KolejkaLifoOgrNpr k31;
		KolejkaLifoOgrNpr k32;
		KolejkaLifoNogrNpr k41;
		KolejkaLifoNogrNpr k42;
		KolejkaFifoOgrPr k51;
		KolejkaFifoOgrPr k52;
		KolejkaFifoNogrPr k61;
		KolejkaFifoNogrPr k62;
		KolejkaLifoOgrPr k71;
		KolejkaLifoOgrPr k72;
		KolejkaLifoNogrPr k81;
		KolejkaLifoNogrPr k82;
		char p = ' ';
		
		k11 = new KolejkaFifoOgrNpr(25000000);
		
		stop("Kolejka k11 stworzona - aby kontynuowac, wcisnij p");
		
		while (k11.stan() < 25000000) {
			k11.wstaw(new Zgloszenie(numery.nastepny(), stoper.czas(),
				priorytety.nextInt(10) + 1));
		}
		
		stop("Kolejka k11 wypelniona - aby kontynuowac, wcisnij p");
		k12 = new KolejkaFifoOgrNpr(k11);
		stop("Kolejka k11 skopiowana - aby kontynuowac, wcisnij p");
		k11 = null;
		stop("Kolejka k11 usunieta - aby kontynuowac, wcisnij p");
		
		out.println("\nKolejka k12:");
		for (Zgloszenie z : k12) {
			out.println(z.toString());
		}
		
		out.println("\nZgloszenia usuniete (k12):");
		while (!k12.kolejkaPusta()) {
			out.println(k12.usun().toString());
		}
		
		stop("\nKolejka k12 wyczyszczona - aby kontynuowac, wcisnij p");
		k12 = null;
		k21 = new KolejkaFifoNogrNpr();
		stop("\nKolejka k21 stworzona - aby kontynuowac, wcisnij p");
		
		while (k21.stan() < 25000000) {
			k21.wstaw(new Zgloszenie(numery.nastepny(), stoper.czas(),
				priorytety.nextInt(10) + 1));
		}
		
		stop("Kolejka k21 wypelniona - aby kontynuowac, wcisnij p");
		k22 = new KolejkaFifoNogrNpr(k21);
		stop("Kolejka k21 skopiowana - aby kontynuowac, wcisnij p");
		k21 = null;
		stop("Kolejka k21 usunieta - aby kontynuowac, wcisnij p");
		
		out.println("\nKolejka k22:");
		for (Zgloszenie z : k22) {
			out.println(z.toString());
		}
		
		out.println("\nZgloszenia usuniete (k22):");
		while (!k22.kolejkaPusta()) {
			out.println(k22.usun().toString());
		}
		
		stop("\nKolejka k22 wyczyszczona - aby kontynuowac, wcisnij p");
		k22 = null;
		k31 = new KolejkaLifoOgrNpr(25000000);
		stop("\nKolejka k31 stworzona - aby kontynuowac, wcisnij p");
		
		while (k31.stan() < 25000000) {
			k31.wstaw(new Zgloszenie(numery.nastepny(), stoper.czas(),
				priorytety.nextInt(10) + 1));
		}
		
		stop("Kolejka k31 wypelniona - aby kontynuowac, wcisnij p");
		k32 = new KolejkaLifoOgrNpr(k31);
		stop("Kolejka k31 skopiowana - aby kontynuowac, wcisnij p");
		k31 = null;
		stop("Kolejka k31 usunieta - aby kontynuowac, wcisnij p");
		
		out.println("\nKolejka k32:");
		for (Zgloszenie z : k32) {
			out.println(z.toString());
		}
		
		out.println("\nZgloszenia usuniete (k32):");
		while (!k32.kolejkaPusta()) {
			out.println(k32.usun().toString());
		}
		
		stop("\nKolejka k32 wyczyszczona - aby kontynuowac, wcisnij p");
		k32 = null;
		k41 = new KolejkaLifoNogrNpr();
		stop("\nKolejka k41 stworzona - aby kontynuowac, wcisnij p");
		
		while (k41.stan() < 25000000) {
			k41.wstaw(new Zgloszenie(numery.nastepny(), stoper.czas(),
				priorytety.nextInt(10) + 1));
		}
		
		stop("Kolejka k41 wypelniona - aby kontynuowac, wcisnij p");
		k42 = new KolejkaLifoNogrNpr(k41);
		stop("Kolejka k41 skopiowana - aby kontynuowac, wcisnij p");
		k41 = null;
		stop("Kolejka k41 usunieta - aby kontynuowac, wcisnij p");
		
		out.println("\nKolejka k42:");
		for (Zgloszenie z : k42) {
			out.println(z.toString());
		}
		
		out.println("\nZgloszenia usuniete (k42):");
		while (!k42.kolejkaPusta()) {
			out.println(k42.usun().toString());
		}
		
		stop("\nKolejka k42 wyczyszczona - aby kontynuowac, wcisnij p");
		k42 = null;
		k51 = new KolejkaFifoOgrPr(25000000);
		stop("\nKolejka k51 stworzona - aby kontynuowac, wcisnij p");
		
		while (k51.stan() < 25000000) {
			k51.wstaw(new Zgloszenie(numery.nastepny(), stoper.czas(),
				priorytety.nextInt(10) + 1));
		}
		
		stop("Kolejka k51 wypelniona - aby kontynuowac, wcisnij p");
		k52 = new KolejkaFifoOgrPr(k51);
		stop("Kolejka k51 skopiowana - aby kontynuowac, wcisnij p");
		k51 = null;
		stop("Kolejka k51 usunieta - aby kontynuowac, wcisnij p");
		
		out.println("\nKolejka k52:");
		for (Zgloszenie z : k52) {
			out.println(z.toString());
		}
		
		out.println("\nZgloszenia usuniete (k52):");
		while (!k52.kolejkaPusta()) {
			out.println(k52.usun().toString());
		}
		
		stop("\nKolejka k52 wyczyszczona - aby kontynuowac, wcisnij p");
		k52 = null;
		k61 = new KolejkaFifoNogrPr();
		stop("\nKolejka k61 stworzona - aby kontynuowac, wcisnij p");
		
		while (k61.stan() < 25000000) {
			k61.wstaw(new Zgloszenie(numery.nastepny(), stoper.czas(),
				priorytety.nextInt(10) + 1));
		}
		
		stop("Kolejka k61 wypelniona - aby kontynuowac, wcisnij p");
		k62 = new KolejkaFifoNogrPr(k61);
		stop("Kolejka k61 skopiowana - aby kontynuowac, wcisnij p");
		k61 = null;
		stop("Kolejka k61 usunieta - aby kontynuowac, wcisnij p");
		
		out.println("\nKolejka k62:");
		for (Zgloszenie z : k62) {
			out.println(z.toString());
		}
		
		out.println("\nZgloszenia usuniete (k62):");
		while (!k62.kolejkaPusta()) {
			out.println(k62.usun().toString());
		}
		
		stop("\nKolejka k62 wyczyszczona - aby kontynuowac, wcisnij p");
		k62 = null;
		k71 = new KolejkaLifoOgrPr(25000000);
		stop("\nKolejka k71 stworzona - aby kontynuowac, wcisnij p");
		
		while (k71.stan() < 25000000) {
			k71.wstaw(new Zgloszenie(numery.nastepny(), stoper.czas(),
				priorytety.nextInt(10) + 1));
		}
		
		stop("Kolejka k71 wypelniona - aby kontynuowac, wcisnij p");
		k72 = new KolejkaLifoOgrPr(k71);
		stop("Kolejka k71 skopiowana - aby kontynuowac, wcisnij p");
		k71 = null;
		stop("Kolejka k71 usunieta - aby kontynuowac, wcisnij p");
		
		out.println("\nKolejka k72:");
		for (Zgloszenie z : k72) {
			out.println(z.toString());
		}
		
		out.println("\nZgloszenia usuniete (k72):");
		while (!k72.kolejkaPusta()) {
			out.println(k72.usun().toString());
		}
		
		stop("\nKolejka k72 wyczyszczona - aby kontynuowac, wcisnij p");
		k72 = null;
		k81 = new KolejkaLifoNogrPr();
		stop("\nKolejka k81 stworzona - aby kontynuowac, wcisnij p");
		
		while (k81.stan() < 25000000) {
			k81.wstaw(new Zgloszenie(numery.nastepny(), stoper.czas(),
				priorytety.nextInt(10) + 1));
		}
		
		stop("Kolejka k81 wypelniona - aby kontynuowac, wcisnij p");
		k82 = new KolejkaLifoNogrPr(k81);
		stop("Kolejka k81 skopiowana - aby kontynuowac, wcisnij p");
		k81 = null;
		stop("Kolejka k81 usunieta - aby kontynuowac, wcisnij p");
		
		out.println("\nKolejka k82:");
		for (Zgloszenie z : k82) {
			out.println(z.toString());
		}
		
		out.println("\nZgloszenia usuniete (k82):");
		while (!k82.kolejkaPusta()) {
			out.println(k82.usun().toString());
		}
		
		stop("\nKolejka k82 wyczyszczona - aby kontynuowac, wcisnij p");
	}
}
