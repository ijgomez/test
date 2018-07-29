package org.example.test;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;

public class PantallaArbol extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -476985998979587434L;
	private int subFijoNodoNuevo = 1;
	
    	public PantallaArbol(JFrame marco) {
	        //ARBOL
	        final ArbolDinamico arbol = new ArbolDinamico();
	        
	        cargarArbol(arbol);
		
		//BOTON 1
	        JButton botonAnadir = new JButton("Añadir");
	        botonAnadir.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	                	arbol.anadirElemento("Nuevo Nodo " + subFijoNodoNuevo++);
	            	}
	        });
		//BOTON 2
	        JButton botonBorrar = new JButton("Eliminar");
	        botonBorrar.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	                	arbol.borrarNodoActual();
	            	}
	        });
		//BOTON 3
	        JButton botonLimpiar = new JButton("Limpiar");
	        botonLimpiar.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	                	arbol.borrarArbol();
	            	}
	        });
	
	        //JUNTAMOS COMPONENTES
	        setLayout(new BorderLayout());
	        arbol.setPreferredSize(new Dimension(300, 150));
	        add(arbol, BorderLayout.CENTER);
	
	        JPanel panel = new JPanel();
	        panel.setLayout(new GridLayout(0,1));
	        panel.add(botonAnadir);
	        panel.add(botonBorrar);
	        panel.add(botonLimpiar);
	        add(panel, BorderLayout.EAST);
    	}
	
    	public void cargarArbol(ArbolDinamico arbol) {
	        String p1Name = new String("Padre 1");
	        String p2Name = new String("Padre 2");
	        String c1Name = new String("Hijo 1");
	        String c2Name = new String("Hijo 2");
	
	        DefaultMutableTreeNode p1, p2;
	
	        p1 = arbol.anadirElemento(null, p1Name);
	        p2 = arbol.anadirElemento(null, p2Name);
	
	        arbol.anadirElemento(p1, c1Name);
	        arbol.anadirElemento(p1, c2Name);
		arbol.anadirElemento(p2, c1Name);
	        arbol.anadirElemento(p2, c2Name);
	}

}
