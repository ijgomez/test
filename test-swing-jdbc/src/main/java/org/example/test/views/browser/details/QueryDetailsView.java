package org.example.test.views.browser.details;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.example.test.events.ApplicationEvent;
import org.example.test.helpers.SQLHelper;
import org.example.test.views.components.ApplicationObservable;
import org.example.test.views.components.ApplicationObserver;
import org.example.test.views.components.buttons.ExecuteButton;
import org.example.test.views.components.panels.ApplicationPanel;

public class QueryDetailsView extends ApplicationPanel implements ApplicationObserver {

	private static final long serialVersionUID = 1139088501677047263L;
	
	private JTextArea queryTextArea;

	private ExecuteButton executeButton;

	protected void initComponents() {
		JScrollPane queryScrollPane;
		JPanel queryButtonPanel;

        queryTextArea = new JTextArea();
        queryTextArea.setColumns(20);
        queryTextArea.setFont(new Font("Courier New", 0, 13)); // NOI18N
        queryTextArea.setRows(5);
        
        queryScrollPane = new JScrollPane();
        queryScrollPane.setViewportView(queryTextArea);

        executeButton = new ExecuteButton();
               
        queryButtonPanel = new JPanel();
        queryButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        queryButtonPanel.add(executeButton);
        
        super.setLayout(new BorderLayout());
        super.add(queryScrollPane, BorderLayout.CENTER);
        super.add(queryButtonPanel, BorderLayout.SOUTH);
		
	}
	
	public List<String> listOfQueries() {
		return SQLHelper.parser(queryTextArea.getText());
	}
	
	@Override
	public void registerIn(ApplicationObservable observable) {
		super.registerIn(observable);
		this.executeButton.registerIn(observable);
	}

	@Override
	public void listener(ApplicationEvent evento) { }
}
