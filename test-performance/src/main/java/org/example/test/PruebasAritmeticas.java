package org.example.test;

import java.util.Scanner;

public class PruebasAritmeticas {

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
			System.out.println("-----------------------------------------");
			System.out.println("1 - Asignacion normal z = z*3/4");
			System.out.println("2 - Asignacion compuesta z*=3/4");
			System.out.println("3 - Division con /");
			System.out.println("4 - Division con >>");
			System.out.println("5 - Multiplicacion con *");
			System.out.println("6 - Multiplicacion con <<");
			
			System.out.println("0 - Salir");
			
			String s = sc.nextLine();
			switch (s.charAt(0)) {
			case '0':
				sc.close();
				return;
			case '1':
				test(100000, 100000000, new ITest(){

					@Override
					public Object test(long iteraciones) {
						int z = 1;
						for (int i = 0; i < iteraciones; i++) {
							z = z*3/4;
						}
						return z;
					}
					
				});
				break;
			case '2':
				test(100000, 100000000, new ITest(){

					@Override
					public Object test(long iteraciones) {
						int z = 1;
						for (int i = 0; i < iteraciones; i++) {
							z*=3/4;
						}
						return z;
					}
					
				});
				break;
			case '3':
				test(100000, 100000000, new ITest(){

					@Override
					public Object test(long iteraciones) {
						int z = 43434343;
						for (int i = 0; i < iteraciones; i++) {
							z = z / 8;
						}
						return z;
					}
					
				});
				break;
			case '4':
				test(100000, 100000000, new ITest(){

					@Override
					public Object test(long iteraciones) {
						int z = 43434343;
						for (int i = 0; i < iteraciones; i++) {
							z = z >> 3;
						}
						return z;
					}
					
				});
				break;
			case '5':
				test(100000, 100000000, new ITest(){

					@Override
					public Object test(long iteraciones) {
						int z = 32;
						for (int i = 0; i < iteraciones; i++) {
							z = z * 12;
						}
						return z;
					}
					
				});
				break;
			case '6':
				test(100000, 100000000, new ITest(){

					@Override
					public Object test(long iteraciones) {
						int z = 32;
						for (int i = 0; i < iteraciones; i++) {
							z = z << 3 + z << 2;
						}
						return z;
					}
					
				});
				break;
			}
			
		}
	}

}
