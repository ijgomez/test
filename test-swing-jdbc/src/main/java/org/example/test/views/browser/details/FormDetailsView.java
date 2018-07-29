package org.example.test.views.browser.details;

import java.awt.GridLayout;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import org.example.test.events.ApplicationEvent;
import org.example.test.model.ConnectionData;
import org.example.test.views.components.ApplicationObserver;
import org.example.test.views.components.panels.ApplicationPanel;

public class FormDetailsView extends ApplicationPanel implements ApplicationObserver {

	private static final long serialVersionUID = 1139088501677047263L;
	
	private JTextField driverTextField;
	
	private JTextField urlTextField;
	
	private JTextField userTextField;
	
	private JTextField passwordTextField;

	protected void initComponents() {
		JPanel formPanel, securityPanel;
		JLabel driverLabel, urlLabel, userLabel, passwordLabel;
		
		driverLabel = new JLabel();
        driverLabel.setText("Driver:");
        
        driverTextField = new JTextField();

        urlLabel = new JLabel();
        urlLabel.setText("URL:");
        
        urlTextField = new JTextField();
        
        userLabel = new JLabel();
        userLabel.setText("User:");
        
        userTextField = new JTextField();

        passwordLabel = new JLabel();
        passwordLabel.setText("Password:");
        
        passwordTextField = new JTextField();
        
        formPanel = new JPanel();
        GroupLayout formPanelLayout = new GroupLayout(formPanel);
        formPanel.setLayout(formPanelLayout);
        formPanelLayout.setHorizontalGroup(
            formPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(formPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(driverLabel)
                    .addComponent(urlLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(driverTextField, GroupLayout.DEFAULT_SIZE, 733, Short.MAX_VALUE)
                    .addComponent(urlTextField))
                .addContainerGap())
        );
        formPanelLayout.setVerticalGroup(
            formPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(formPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(driverLabel)
                    .addComponent(driverTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(urlLabel)
                    .addComponent(urlTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        securityPanel = new JPanel();
        GroupLayout securityPanelLayout = new GroupLayout(securityPanel);
        securityPanel.setLayout(securityPanelLayout);
        securityPanelLayout.setHorizontalGroup(
            securityPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(securityPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(securityPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(passwordLabel)
                    .addComponent(userLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(securityPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(userTextField, GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
                    .addComponent(passwordTextField))
                .addContainerGap())
        );
        securityPanelLayout.setVerticalGroup(
            securityPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(securityPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(securityPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(userLabel)
                    .addComponent(passwordTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(securityPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLabel)
                    .addComponent(userTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        super.setLayout(new GridLayout(1, 2));
        super.add(formPanel);
        super.add(securityPanel);
        
	}
	
	public void setConnectionData(ConnectionData connectionData) {
		this.driverTextField.setText(connectionData.getDriverName());
		this.urlTextField.setText(connectionData.getUrl());
		this.userTextField.setText(connectionData.getUsername());
		this.passwordTextField.setText(connectionData.getPassword());
	}
	
	public ConnectionData getConnectionData() {
		ConnectionData connectionData;
		
		connectionData = new ConnectionData();
		connectionData.setDriverName(this.driverTextField.getText());
		connectionData.setUrl(this.urlTextField.getText());
		connectionData.setUsername(this.userTextField.getText());
		connectionData.setPassword(this.passwordTextField.getText());
		
		return connectionData;
	}
	
	@Override
	public void listener(ApplicationEvent evento) {
		// TODO Auto-generated method stub
		
	}
}
