package org.example.test;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public class Cache<K,V> {
	private Map<K,SoftReference<V>> map = new HashMap<>();
	
	public void put(K clave, V valor) {
		map.put(clave, new SoftReference<V>(valor));
	}
	public V get(K clave) {
		SoftReference<V> valor = map.get(clave);
		if (valor != null) {
			V dato = valor.get();
			if (dato == null) {
				map.remove(clave);
			} 
			return dato;
		}
		return null;
	}
	
	// Ejecutar, por ejemplo, con java -Xmx250m com.ahristov.Cache, variando los tama�os de memoria para ver el efecto
	public static void main(String[] args) {
		Cache<String,byte[]> cache = new Cache<>();
		
	 
		for (int i = 0; i < 10240; i++) {
			byte[] b = new byte[102400];
			cache.put(String.valueOf(i),b);
		}


		int faltan = 0;
		for (int i = 0; i < 10240; i++) {
			byte[] b = cache.get(String.valueOf(i));
			
			if (b == null) {
				System.out.println("La entrada para "+i+" falta");
				faltan++;
			}
		}
		System.out.println("Total de fallos = "+faltan);
	}

}
