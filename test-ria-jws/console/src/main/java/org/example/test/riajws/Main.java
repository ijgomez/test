package org.example.test.riajws;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class Main {

	public static void main(String[] args) {
		String argumentos = "Se ha arrancado la aplicacion con los argumentos:";
		if (args == null || args.length == 0) {
			argumentos = argumentos.concat("\n 0 - sin argumentos!!");
		} else {
			argumentos = argumentos.concat("\n " + args.length + " argumentos:");
			for (String a : args) {
				argumentos = argumentos.concat("\n" + a);
			}
		}
		
		JFrame frame;
		
		frame = new JFrame("test-ria-jnlp");
		frame.setMinimumSize(new Dimension(800, 600));
		
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    JTextArea textArea = new JTextArea();
	    textArea.setText(argumentos);
	    
	    frame.getContentPane().add(textArea);
	    
	    
//	    frame.pack();
	    frame.setVisible(true);
	}

}
