package org.example.test;

import java.awt.Color;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;

public class WeakRefenceExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		Set<WeakReference<Color>> c = new HashSet<>();
		
		
		
		c.add(new WeakReference<Color>(new Color(0, 0, 0)));
		c.add(new WeakReference<Color>(Color.RED));
		
		System.out.println("Antes GC");
		for (WeakReference<Color> weakReference : c) {
        	System.out.println(weakReference.get());	
		}
		
        System.gc();
        Thread.sleep(100);

        System.out.println("Despues GC");
        for (WeakReference<Color> weakReference : c) {
        	System.out.println(weakReference.get());	
		}
        
	}

}
