package org.example.test;


import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

public class ArbolDinamico extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2160596605736480358L;
	/*ATRIBUTOS*/
	protected DefaultMutableTreeNode nodoRaiz;
    	protected DefaultTreeModel modeloDeArbol;
    	protected JTree arbol;
    	private Toolkit toolkit = Toolkit.getDefaultToolkit();
	
	/*CONSTRUCTOR*/
    	public ArbolDinamico() {
		JScrollPane barraDePanel;
		
		nodoRaiz = new DefaultMutableTreeNode("Categorias");
        	modeloDeArbol = new DefaultTreeModel(nodoRaiz);
        	arbol = new JTree(modeloDeArbol);
        	
        	arbol.setEditable(true);
        	arbol.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        	arbol.setShowsRootHandles(true);

        	barraDePanel = new JScrollPane(arbol);
        	setLayout(new GridLayout(1,0));
        	add(barraDePanel);
    	}

	/*METODOS*/
    	/** Elimina todos los nodos menos el raiz. */
    	public void borrarArbol() {
        	nodoRaiz.removeAllChildren();
        	modeloDeArbol.reload();
    	}

    	/** Elimina el nodo actual */
    	public void borrarNodoActual() {
	        TreePath seleccionActual;
	        DefaultMutableTreeNode nodoActual;
        	MutableTreeNode padre;
        	
        	seleccionActual = arbol.getSelectionPath();
        	if (seleccionActual != null) {
            		nodoActual = (DefaultMutableTreeNode)(seleccionActual.getLastPathComponent());
            		padre = (MutableTreeNode)(nodoActual.getParent());
            		
            		if (padre != null) {
                		modeloDeArbol.removeNodeFromParent(nodoActual);
                		return;
            		}
        	}	 
		// Cualquiera que no fuera selecionado o el raiz.
        	toolkit.beep();
    	}

    	/** Añade un hijo al nodo seleccionado */
    	public DefaultMutableTreeNode anadirElemento(Object hijo) {
        	DefaultMutableTreeNode nodoPadre = null;
        	TreePath rutaPadre = arbol.getSelectionPath();

        	if (rutaPadre == null) {
            		nodoPadre = nodoRaiz;
        	} else {
            		nodoPadre = (DefaultMutableTreeNode)(rutaPadre.getLastPathComponent());
        	}
        	return anadirElemento(nodoPadre, hijo, true);
    	}

    	public DefaultMutableTreeNode anadirElemento(DefaultMutableTreeNode padre, Object hijo) {
        	return anadirElemento(padre, hijo, false);
    	}

    
    	public DefaultMutableTreeNode anadirElemento(DefaultMutableTreeNode padre, Object hijo, boolean esVisible) {
        	DefaultMutableTreeNode nodoHijo = new DefaultMutableTreeNode(hijo);
		
        	if (padre == null) {
            		padre = nodoRaiz;
        	}

        	modeloDeArbol.insertNodeInto(nodoHijo, padre, padre.getChildCount());

        	// Aseguras que el usuario solo pueda ver el nodo nuevo
        	if (esVisible) {
            		arbol.expandPath(new TreePath(padre.getPath()));
            		arbol.scrollPathToVisible(new TreePath(nodoHijo.getPath()));
        	}
        	return nodoHijo;
    	}
}
