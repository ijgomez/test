package org.example.test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

public class ProgressBarTest extends JFrame {

	private static final long serialVersionUID = 936698744351228790L;
	private JPanel jContentPane = null;
	private JButton btnStartWorker = null;
	private JProgressBar bar = null;
	private JLabel jLabel = null;
	private JLabel lblCompletado = null;
	private JLabel lblFinish = null;
	private JButton btnCancelarWorker = null;
	
	private MiWorker trabajador;
	
	/**
	 * This method initializes btnStartWorker	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnStartWorker() {
		if (btnStartWorker == null) {
			btnStartWorker = new JButton();
			btnStartWorker.setBounds(new java.awt.Rectangle(11,11,117,29));
			btnStartWorker.setText("Start Worker");
			btnStartWorker.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(trabajador==null){
						trabajador = new MiWorker();
						
						//Agrego un Listener para el cambio de la propiedad "progress"
						trabajador.addPropertyChangeListener(new PropertyChangeListener(){
							public void propertyChange(PropertyChangeEvent evt) {
								if ("progress".equals(evt.getPropertyName())) {
					                 lblCompletado.setText(evt.getNewValue() + " %");
					            }
							}
						});
						}
						trabajador.execute();
					}
			});
		}
		return btnStartWorker;
	}

	/**
	 * This method initializes bar	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	private JProgressBar getBar() {
		if (bar == null) {
			bar = new JProgressBar();
			bar.setBounds(new java.awt.Rectangle(10,58,276,31));
		}
		return bar;
	}

	/**
	 * This method initializes btnCancelarWorker	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnCancelarWorker() {
		if (btnCancelarWorker == null) {
			btnCancelarWorker = new JButton();
			btnCancelarWorker.setBounds(new java.awt.Rectangle(137,11,136,29));
			btnCancelarWorker.setText("Cancel Worker");
			btnCancelarWorker.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(trabajador!=null){
						trabajador.cancel(false);
						trabajador = null;
					}
				}
			});
		}
		return btnCancelarWorker;
	}



	/**
	 * This is the default constructor
	 */
	public ProgressBarTest() {
		super();
		initialize();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			lblFinish = new JLabel();
			lblFinish.setBounds(new java.awt.Rectangle(93,134,153,28));
			lblFinish.setText("");
			lblCompletado = new JLabel();
			lblCompletado.setBounds(new java.awt.Rectangle(94,108,39,20));
			lblCompletado.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
			lblCompletado.setText("0%");
			jLabel = new JLabel();
			jLabel.setBounds(new java.awt.Rectangle(17,108,74,19));
			jLabel.setText("Completado:");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getBtnStartWorker(), null);
			jContentPane.add(getBar(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(lblCompletado, null);
			jContentPane.add(lblFinish, null);
			jContentPane.add(getBtnCancelarWorker(), null);
		}
		return jContentPane;
	}

	/*
	 * Esta es mi inner class "MiWorker", si se fijan, estoy
	 * instrumentando Generics para decirle a esta clase, que deber�
	 * retornar del "doInBackGround" un String, y que los dem�s m�todos
	 * no deben retornar nada (void)
	 */
	class MiWorker extends SwingWorker<String, Void>{

		@Override
		protected String doInBackground() throws Exception {
			int i=0;
			
			while(i<=100 && !isCancelled()){
				bar.setValue(i);
				setProgress(i);
				i++;
				Thread.sleep(100);
			}
			
			return "Operacion finalizada";
		}
		
		@Override
		public void done(){
			try {
				lblFinish.setText(get());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
