package org.example.test;

import javax.swing.JFrame;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpeedometerPanel speedometer = new SpeedometerPanel();
        SpeedometerAction action = new SpeedometerAction(speedometer);
        JFrame f = new JFrame("Speedometer");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(speedometer);
        f.getContentPane().add(action.getUIpanel(), "South");
        f.setSize(400,340);
        f.setLocation(200,200);
        f.setVisible(true);
	}

}
