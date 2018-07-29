package org.example.test;

import java.awt.EventQueue;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Application application;

				application = new Application();
				application.run();
			}
		});
	}

}
