package org.example.test;

import java.awt.Color;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class ReferenciasFantasma {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Color c = new Color(0, 0, 0);
		ReferenceQueue<Color> cola = new ReferenceQueue<>();
		
		WeakReference<Color> wr = new WeakReference<Color>(c, cola);
		c = null;
		System.out.println(cola.poll());
		
		System.gc();
		Thread.sleep(2000);
		System.out.println(wr.get());
		System.out.println(cola.poll());
		
		PhantomReference<Color> rf = new PhantomReference<>(c, cola);
		
		
	}

}
