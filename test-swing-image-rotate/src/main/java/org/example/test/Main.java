package org.example.test;

import javax.swing.JFrame;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("RotateImage");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 400);

		RotateImage panel = new RotateImage();
		frame.setContentPane(panel);
		frame.setVisible(true);
	}

}
