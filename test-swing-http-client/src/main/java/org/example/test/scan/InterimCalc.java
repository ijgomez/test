package org.example.test.scan;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

public class InterimCalc {

	private JPanel mainPanel = new JPanel();
	   private JTextField resultField = new JTextField(10);
	   private JButton doItBtn = new JButton("Do It!");
	   private DecimalFormat dblFormat = new DecimalFormat("0.0000000000");
	   private SwingWorker<Void, Double> mySwingWorker = null;

	   public InterimCalc() {
	      mainPanel.add(doItBtn);
	      mainPanel.add(resultField);
	      displayResult(0.0);

	      doItBtn.addActionListener(new DoItListener());
	   }

	   public void displayResult(double result) {
	      resultField.setText(dblFormat.format(result));
	   }

	   public JPanel getMainPanel() {
	      return mainPanel;
	   }

	   private class DoItListener implements ActionListener {

	      public void actionPerformed(ActionEvent e) {
	         if (mySwingWorker != null && !mySwingWorker.isDone()) {
	            mySwingWorker.cancel(true);
	         }
	         displayResult(0.0);
	         mySwingWorker = new MySwingWorker();
	         mySwingWorker.execute();
	      }
	   }

	   private class MySwingWorker extends SwingWorker<Void, Double> {

	      private static final int INTERIM_LENGTH = 10000; // how many loops to do before displaying

	      @Override
	      protected Void doInBackground() throws Exception {
	         boolean keepGoing = true;
	         long index = 1L;
	         double value = 0.0;
	         while (keepGoing) {
	            for (int i = 0; i < INTERIM_LENGTH; i++) {
	               int multiplier = (index % 2 == 0) ? -1 : 1;
	               value += (double)multiplier / (index);
	               index++;
	            }
	            publish(value);
	         }
	         return null;
	      }

	      @Override
	      protected void process(List<Double> chunks) {
	         for (Double dbl : chunks) {
	            displayResult(dbl);
	         }
	      }

	   }

	   private static void createAndShowUI() {
	      JFrame frame = new JFrame("Decay Const");
	      frame.getContentPane().add(new InterimCalc().getMainPanel());
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      frame.pack();
	      frame.setLocationRelativeTo(null);
	      frame.setVisible(true);
	   }

	   public static void main(String[] args) {
	      java.awt.EventQueue.invokeLater(new Runnable() {
	         public void run() {
	            createAndShowUI();
	         }
	      });
	   }
}
