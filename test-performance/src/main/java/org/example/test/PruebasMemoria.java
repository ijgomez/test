package org.example.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

public class PruebasMemoria {

	public static List<Object> objetos = new ArrayList<>();
	
	public static List<Class<?>> clases = new ArrayList<>();
	
	public static String cadena = "";
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("1 - Crear Objetos");
			System.out.println("2 - Crear Objetos Peque�os referenciados");
			System.out.println("3 - Crear Objetos Peque�os no referenciados");
			System.out.println("4 - Destruir Objetos");
			System.out.println("5 - Crear Clases");
			System.out.println("6 - Destruir Clases");
			System.out.println("7 - Add cadena");
			System.out.println("9 - GC");
			System.out.println("0 - Salir");

			String opction = sc.nextLine();
			switch (opction) {
			case "1":
				for (int i = 0; i < 10; i++) {
					objetos.add(new byte[1024*1024*10]);
				}
				break;
			case "2":
				for (int i = 0; i < 10; i++) {
					objetos.add(new byte[1024*1024]);
				}
				break;
			case "3":
				for (int i = 0; i < 10; i++) {
					byte[] b = new byte[1024*1024];
				}
				break;
			case "4":
				for (int i = 0; i < 10; i++) {
					if (objetos.size() > 0) {
					objetos.remove(0);
					}
				}
				break;
			case "5":
				for (int i = 0; i < 1000; i++) {
					String uuid = UUID.randomUUID().toString();
					String className = "Class" + uuid.replaceAll("-", "");
					
					ClassPool pool = ClassPool.getDefault();
					CtClass clase = pool.makeClass(className);
					CtMethod metodo = CtMethod.make("public void hello(){ System.out.println(123);}", clase);
					clase.addMethod(metodo);
					clases.add(clase.toClass());
				}
				break;
			case "6":
				for (int i = 0; i < 1000; i++) {
					if (clases.size() > 0) {
						clases.remove(0);
					}
				}
				break;
			case "7":
				
				cadena  += "Hola!";
				
				break;
			case "9":
				System.gc();
				break;
			case "0":
				sc.close();
				System.exit(0);
				break;

			}

			System.out.println("Objetos: " + objetos.size());
			
		}
	}

}
