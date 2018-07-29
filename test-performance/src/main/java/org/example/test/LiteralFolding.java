package org.example.test;


public class LiteralFolding {
	
	public static final int VECES_WARM_UP = 10000;
	public static final int VECES_PRUEBA = 10000000;
	
	public static int fibonacci(int n) {
		int resultado = -1;
		int anterior = -1;
		int suma = 0;
		
		for (int i = 0; i <= n; i++) {
			suma = anterior + resultado;
			anterior = resultado;
			resultado = suma;
		}
		
		return resultado;
	}
	
	public static void test(int veces) {
//		int y = 2;
		int y = 0;
		for (int i = 0; i < veces; i++) {
//			int z = 2;
//			y = z*4;
			y = fibonacci(40);
		} 
		System.out.println(y);
//		System.out.println(y);
	}
	
	public static void main(String[] args) {
		test(VECES_WARM_UP);
		System.gc();
		System.runFinalization();
		long t1 = System.currentTimeMillis();
		
		test(VECES_PRUEBA);
		long t2 = System.currentTimeMillis();
		
		System.out.println("tiempo: " + + (t2-t1)/(double)VECES_PRUEBA);
		
		
		
		// -XX:+UseSerialGC
		// -Xms= -Xmx=
		
		//t1
		//prueba
		//t2
		//Tiempo=t2-t1
		
		
		
		
		
		
	}
}
