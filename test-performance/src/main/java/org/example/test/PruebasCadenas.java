package org.example.test;

import java.util.Scanner;

public class PruebasCadenas {

	public interface ITest {
		public Object test(long iteraciones);
	}

	public static void test(long calentamiento, long pruebas, ITest prueba) {
		Object y = prueba.test(calentamiento);
		System.gc();
		System.runFinalization();

		long t1 = System.currentTimeMillis();
		Object x = prueba.test(pruebas);
		long t2 = System.currentTimeMillis();
		double tpi = (t2 - t1) / (double) pruebas;
		System.out.println("Tiempo = " + tpi);
		System.out.println(x.hashCode() + " " + y.hashCode());

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("1 - Prueba +");
			System.out.println("2 - Prueba StringBuffer");
			System.out.println("3 - Prueba StringBuilder");
			System.out.println("0 - Salir");
			String nextLine = sc.nextLine();
			switch (nextLine.charAt(0)) {
			case '0':
				sc.close();
				return;
			case '1':
				test(5000, 100000, new ITest() {

					@Override
					public Object test(long iteraciones) {
						String s = "";
						for (int i = 0; i < iteraciones; i++) {
							s = s + i;
						}
						return s;
					}
				});
				break;
			case '2':
				test(5000, 100000, new ITest() {

					@Override
					public Object test(long iteraciones) {
						StringBuffer s = new StringBuffer();
						for (int i = 0; i < iteraciones; i++) {
							s.append(i);
						}
						return s;
					}
				});
				break;
			case '3':
				test(5000, 100000, new ITest() {

					@Override
					public Object test(long iteraciones) {
						StringBuilder s = new StringBuilder();
						for (int i = 0; i < iteraciones; i++) {
							s.append(i);
						}
						return s;
					}
				});
				break;
			}
		}
	}

}
