package org.example.test;

import java.awt.Color;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class MapaCanonicoColores {
	private static Map<Integer,WeakReference<Color>> 
		colores = new HashMap<Integer,WeakReference<Color>>();
	
	public static Color getColor(int r, int g, int b) {
		int codigoColor = r << 16 | g << 8 | b;
		WeakReference<Color> c = colores.get(codigoColor);
		if (c != null) {
			Color c1 = c.get();
			if (c1 != null) return c1;
		}
		Color color = new Color(r,g,b);
		colores.put(codigoColor,new WeakReference<Color>(color));
		return color;
	}
	
	public static void main(String[] args) {
		Color c = getColor(255, 0, 255);
		Color c1 = getColor(255, 0, 255);
		System.out.println(c+" "+c1+" "+(c == c1));
		c = null;
		c1 = null;
		System.gc();
		c = getColor(255, 0, 255);
		System.out.println(c);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
