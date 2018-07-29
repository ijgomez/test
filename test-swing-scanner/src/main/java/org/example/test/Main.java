package org.example.test;

import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				Application application;
				
				application = new Application();
				application.run();
				
			}
		});
	}

}
