package org.example.test;

import org.example.test.viewer.AxisViewerFrame;

public class Main {

	public static void main(String[] args) {

		AxisViewerFrame frame = new AxisViewerFrame("192.168.0.1", "user", "passord");
		frame.setVisible(true);
		frame.run();

	}

}
