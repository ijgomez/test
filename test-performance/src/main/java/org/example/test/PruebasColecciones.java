package org.example.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class PruebasColecciones {

	public interface ITest {
		public Object test(long iteraciones);
	}

	public static void test(long calentamiento, long pruebas, ITest prueba) {
		Object y = prueba.test(calentamiento);
		System.gc();
		System.runFinalization();

		//long t1 = System.currentTimeMillis();
		long t1 = System.nanoTime();
		Object x = prueba.test(pruebas);
		long t2 = System.nanoTime();
		//long t2 = System.currentTimeMillis();
		double tpi = (t2 - t1) / (double) pruebas;
		//double tpi = (t2 - t1);
		System.out.println("Tiempo = " + tpi);
		System.out.println(x.hashCode() + " " + y.hashCode());

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("-----------------------------------------");
			System.out.println("1 - Recorrido con for tradiccional");
			System.out.println("2 - Recorrido con foreach");
			System.out.println("3 - Recorrido con for tradicional y tamaño constante");
			System.out.println("4 - Recorrido con un iterador.");
			System.out.println("5 - Recorrido inverso.");
			
			System.out.println("0 - Salir");
			String nextLine = sc.nextLine();
			switch (nextLine.charAt(0)) {
			case '0':
				sc.close();
				return;
			case '1':
				test(50000, 100000000, new ITest() {

					@Override
					public Object test(long iteraciones) {
						List<String> lista = new ArrayList<>();
						for (int i = 0; i < 2000000; i++) {
							lista.add(String.valueOf(i));
						}
						Object x = null;
						for (int i = 0; i < lista.size(); i++) {
							x = lista.get(i);
						}
						return x;
					}
				});
				break;
			case '2':
				test(50000, 100000000, new ITest() {

					@Override
					public Object test(long iteraciones) {
						List<String> lista = new ArrayList<>();
						for (int i = 0; i < 2000000; i++) {
							lista.add(String.valueOf(i));
						}
						Object x = null;
						for (Object z : lista) {
							x = z;
						}
						return x;
					}
				});
				break;
			case '3':
				test(50000, 100000000, new ITest() {

					@Override
					public Object test(long iteraciones) {
						List<String> lista = new ArrayList<>();
						for (int i = 0; i < 2000000; i++) {
							lista.add(String.valueOf(i));
						}
						Object x = null;
						int tamaño = lista.size();
						for (int i = 0; i < tamaño; i++) {
							x = lista.get(i);
						}
						return x;
					}
				});
				break;
			case '4':
				test(50000, 100000000, new ITest() {

					@Override
					public Object test(long iteraciones) {
						List<String> lista = new ArrayList<>();
						for (int i = 0; i < 2000000; i++) {
							lista.add(String.valueOf(i));
						}
						Object x = null;
						Iterator<String> iterator = lista.iterator();
						while (iterator.hasNext()) {
							x = iterator.next();
						}
						return x;
					}
				});
				break;
			case '5':
				test(50000, 100000000, new ITest() {

					@Override
					public Object test(long iteraciones) {
						List<String> lista = new ArrayList<>();
						for (int i = 0; i < 2000000; i++) {
							lista.add(String.valueOf(i));
						}
						Object x = null;
						int tamaño = lista.size();
						for (int i =  tamaño -1; i >= 0; i--) {
							x = lista.get(i);
						}
						return x;
					}
				});
				break;
			}
		}
	}

}
