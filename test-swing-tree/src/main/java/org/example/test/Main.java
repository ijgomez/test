package org.example.test;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class Main {
	public static void main (String []arg){
		JFrame marco = new JFrame("Main");

        	Container contenedor = marco.getContentPane();
        	contenedor.setLayout(new GridLayout(1,1));
        	contenedor.add(new PantallaArbol(marco));

        	marco.addWindowListener(new WindowAdapter() {
            		public void windowClosing(WindowEvent e) {
                		System.exit(0);
            		}
        	});

        	marco.pack();
        	marco.setVisible(true);
	}
}