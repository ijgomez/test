package org.example.test.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import org.example.test.events.ApplicationEvent;
import org.example.test.events.CloseApplicationEvent;
import org.example.test.views.components.ApplicationObservable;
import org.example.test.views.components.ApplicationObserver;
import org.example.test.views.decodeview.DecodePanel;
import org.example.test.views.menu.ApplicationMenuBar;

public class ApplicationFrame extends JFrame implements ApplicationObserver {

	private static final long serialVersionUID = 3607618315867510690L;
	
	private ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");
	
    private ApplicationObservable observable;
		
	private ApplicationMenuBar applicationMenuBar;

	private DecodePanel mainPanel;
	
	public ApplicationFrame() {
		initComponents();
	}
                          
    private void initComponents() {

    	this.applicationMenuBar = new ApplicationMenuBar();
        
        this.mainPanel = new DecodePanel();
       
        super.setTitle(resourceBundle.getString("application.title"));
        super.setLocationByPlatform(true);
        super.setPreferredSize(new Dimension(800, 600));
        super.setMinimumSize(new Dimension(800, 600));
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        super.setJMenuBar(this.applicationMenuBar);
        super.setLayout(new BorderLayout());
        super.add(mainPanel, BorderLayout.CENTER);
        super.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				confirmExitAction();
			}
		});
		super.pack();
    }  

    @Override
	public void registerIn(ApplicationObservable observable) {
		if (observable != null) {
			this.observable = observable;
			this.observable.add(this);
		} else {
			this.observable.remove(this);
			this.observable = observable;
		}
		this.applicationMenuBar.registerIn(observable);
		this.mainPanel.registerIn(observable);
	}
	
	@Override
	public void listener(ApplicationEvent event) {
		if (event instanceof CloseApplicationEvent) {
			this.confirmExitAction();
		}
	}
	
	protected void confirmExitAction() {
		if (JOptionPane.showConfirmDialog(this, resourceBundle.getString("dialog.exit.message"), resourceBundle.getString("dialog.exit.title"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			dispose();			
		} else {
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}
	}

}
