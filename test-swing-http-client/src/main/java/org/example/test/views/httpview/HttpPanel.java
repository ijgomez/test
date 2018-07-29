package org.example.test.views.httpview;

import java.awt.Cursor;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.apache.commons.lang.StringUtils;
import org.example.test.controllers.HttpClientController;
import org.example.test.events.ApplicationEvent;
import org.example.test.events.ExecuteEvent;
import org.example.test.views.components.ApplicationObservable;
import org.example.test.views.components.ApplicationObserver;
import org.example.test.views.components.buttons.ExecuteButton;

public class HttpPanel extends JPanel implements ApplicationObserver {

	private static final long serialVersionUID = -6796375263443730996L;

	private JTextArea resultTextArea;

	private JTextField urlTextField;

	private ExecuteButton executeButton;

	public HttpPanel() {
		this.initComponents();
	}
	
	private void initComponents() {
    	JLabel selectLabel, searchLabel;
    	
        JScrollPane resultScrollPane;

        selectLabel = new JLabel();
        selectLabel.setText("URL:");
        
        urlTextField = new JTextField();
        urlTextField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				validateForm();
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				validateForm();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				validateForm();
			}
		});

        executeButton = new ExecuteButton();
        executeButton.setEnabled(false);
        
        searchLabel = new JLabel();
        searchLabel.setText("Search:");
                
        resultTextArea = new JTextArea();
                
        resultScrollPane = new JScrollPane();
        resultScrollPane.setViewportView(resultTextArea);
        resultScrollPane.getViewport().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        GroupLayout layout = new GroupLayout(this);
        super.setLayout(layout);
        layout.setHorizontalGroup(
        		layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(resultScrollPane, GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(selectLabel)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(urlTextField)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(executeButton)))
                    .addContainerGap())
        );
       
        layout.setVerticalGroup(
        		layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(selectLabel)
                        .addComponent(executeButton)
                        .addComponent(urlTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(resultScrollPane, GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                    .addContainerGap())
            );

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
		this.executeButton.registerIn(observable);
	}
	
	@Override
	public void listener(ApplicationEvent event) {

		if (event instanceof ExecuteEvent) {

			
			HttpClientController controller = new HttpClientController();
			
			try {
				controller.get(this.urlTextField.getText(), (String s) -> {
					resultTextArea.setText(s);
				});
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

	private void validateForm() {
		if (StringUtils.isEmpty(urlTextField.getText())) {
			executeButton.setEnabled(false);
		} else {
			executeButton.setEnabled(true);
			
		}
	}
	
	
}
