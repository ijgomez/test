package org.example.test.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import org.example.test.events.ApplicationEvent;
import org.example.test.events.CloseApplicationEvent;
import org.example.test.views.browser.QueryBrowserView;
import org.example.test.views.components.ApplicationObservable;
import org.example.test.views.components.ApplicationObserver;
import org.example.test.views.menu.ApplicationMenuBar;

public class ApplicationFrame extends JFrame implements ApplicationObserver {

	private static final long serialVersionUID = 7799346828640704885L;

	private ApplicationMenuBar applicationMenuBar;

	private QueryBrowserView queryDetailsView;

	public ApplicationFrame() {
		this.initComponents();
	}
	
	private void initComponents() {
				
		this.applicationMenuBar = new ApplicationMenuBar();
		
		this.queryDetailsView = new QueryBrowserView();
		
		super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        super.setTitle("test-swing-jdbc");
        super.setLocationByPlatform(true);
        super.setPreferredSize(new Dimension(800, 600));
		super.setJMenuBar(applicationMenuBar);
		super.add(queryDetailsView, BorderLayout.CENTER);
		super.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				confirmExitAction();
			}
		});
		super.pack();
	}
	
	private ApplicationObservable observable;
	
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
		this.queryDetailsView.registerIn(observable);
	}
	
	@Override
	public void listener(ApplicationEvent event) {
		
		if (event instanceof CloseApplicationEvent) {
			this.confirmExitAction();
		}
	}
	
	protected void confirmExitAction() {
		if (JOptionPane.showConfirmDialog(this, "Salir de la aplicacion?", "Exit Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			dispose();			
		} else {
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}
	}
}
