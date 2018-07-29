package org.example.test.convert;

import java.util.Random;


public class Conversor {
	public static final int WARM_UP = 20000;
	public static final int TEST = 100000;
	
	public static void test(byte[] entrada, char[] salida, IConvert c) throws Exception {
		for (int i = 0; i < WARM_UP; i++)
			c.convert(entrada, 0, entrada.length, salida, 0, salida.length);
		System.gc();
		System.runFinalization();
		long t1 = System.nanoTime();
		for (int i = 0; i < TEST; i++)
			c.convert(entrada, 0, entrada.length, salida, 0, salida.length);
		long t2 = System.nanoTime();
		double t = (t2-t1)/(double)TEST;
		System.out.println("tpi = "+t);
		System.out.println(salida.length);
	}
	
	public static void main(String[] datos) throws Exception {
		byte[] entrada = new byte[10240];
		char[] salida = new char[entrada.length];
		Random r = new Random();
		r.nextBytes(entrada);
		Class c = Class.forName(datos[0]);
		IConvert c1 = (IConvert)c.newInstance();
		test(entrada,salida,c1);
	}
}
