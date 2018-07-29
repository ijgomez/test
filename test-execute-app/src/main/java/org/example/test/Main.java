package org.example.test;

import java.awt.BorderLayout;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.commons.lang.time.DateFormatUtils;

public class Main {

	public static void main(String[] args) {
		JFrame frame;
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(new JLabel("Fecha: " + DateFormatUtils.format(Calendar.getInstance(), "dd/MM/yyyy hh24:mm:ss,ZZZ")), BorderLayout.CENTER);
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setLayout(new BorderLayout());
		frame.add(panel, BorderLayout.CENTER);
		
		frame.setVisible(true);
	}

}
