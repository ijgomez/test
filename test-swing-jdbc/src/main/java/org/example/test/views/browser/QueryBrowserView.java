package org.example.test.views.browser;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.io.File;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.JAXBException;

import org.example.test.controller.DatabaseController;
import org.example.test.events.ApplicationEvent;
import org.example.test.events.CleanLogEvent;
import org.example.test.events.ExecuteEvent;
import org.example.test.events.OpenFileEvent;
import org.example.test.events.SaveFileEvent;
import org.example.test.model.ConnectionData;
import org.example.test.views.browser.details.FormDetailsView;
import org.example.test.views.browser.details.QueryDetailsView;
import org.example.test.views.browser.details.ResultDetailsView;
import org.example.test.views.components.ApplicationObservable;
import org.example.test.views.components.ApplicationObserver;
import org.example.test.views.components.panels.ApplicationPanel;

public class QueryBrowserView extends ApplicationPanel implements ApplicationObserver {

	private static final long serialVersionUID = 7276707399661052950L;

	private FormDetailsView formDetailsView;

	private QueryDetailsView queryDetailsView;

	private ResultDetailsView resultDetailsView;
	
	private DatabaseController controller = new DatabaseController();

	@Override
	protected void initComponents() {
		JPanel panel;
		
		this.formDetailsView = new FormDetailsView();
		
		this.queryDetailsView = new QueryDetailsView();
		
		this.resultDetailsView = new ResultDetailsView();
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1));
		panel.add(queryDetailsView);
		panel.add(resultDetailsView);

		super.setLayout(new BorderLayout());		
		super.add(formDetailsView, BorderLayout.NORTH);
		super.add(panel, BorderLayout.CENTER);
	}
	
	@Override
	public void registerIn(ApplicationObservable observable) {
		super.registerIn(observable);
		this.formDetailsView.registerIn(observable);
		this.queryDetailsView.registerIn(observable);
		this.resultDetailsView.registerIn(observable);
	}
	
	@Override
	public void listener(ApplicationEvent event) {
		if (event instanceof ExecuteEvent) {
			ConnectionData connectionData = null;
			List<String> queries = null;
			
			try {
				connectionData = formDetailsView.getConnectionData();
				queries = queryDetailsView.listOfQueries();
				
				controller.executeQuery(connectionData, queries, this.resultDetailsView);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (event instanceof OpenFileEvent) {
			JFileChooser fileChooser;
			ConnectionData connectionData = null;
			File file = null;
			
			try {
				fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
				fileChooser.setMultiSelectionEnabled(false);
				fileChooser.setFileFilter(new FileNameExtensionFilter("Database configuration files (.xml)", "xml"));
				if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
					file = fileChooser.getSelectedFile();

					connectionData = controller.openConnectionDataFile(file);
					if (connectionData != null) {
						formDetailsView.setConnectionData(connectionData);
					}
				}
			} catch (HeadlessException | JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else if (event instanceof SaveFileEvent) {
			JFileChooser fileChooser = null;
			ConnectionData connectionData = null;
			File file = null;
			
			try {
				fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
				fileChooser.setMultiSelectionEnabled(false);
				fileChooser.setFileFilter(new FileNameExtensionFilter("Database configuration files (.xml)", "xml"));
				if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
					file = fileChooser.getSelectedFile();
					
					connectionData = formDetailsView.getConnectionData();
					controller.saveConnectionDataFile(file, connectionData);
				}
			} catch (HeadlessException | JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (event instanceof CleanLogEvent) {
			resultDetailsView.clean();
		}
	}

}
