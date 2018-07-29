package org.example.test.views.client;

import java.awt.BorderLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.util.ResourceBundle;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingUtilities;

import org.example.test.controllers.CommunicationService;
import org.example.test.events.ApplicationEvent;
import org.example.test.events.ConnectEvent;
import org.example.test.events.DisconnectEvent;
import org.example.test.events.OpenFileEvent;
import org.example.test.events.SaveAsFileEvent;
import org.example.test.events.SaveFileEvent;
import org.example.test.events.SendDataEvent;
import org.example.test.views.components.ApplicationObservable;
import org.example.test.views.components.ApplicationObserver;
import org.example.test.views.components.buttons.ConnectButton;
import org.example.test.views.components.buttons.DisconnectButton;
import org.example.test.views.components.buttons.SendButton;
import org.example.test.views.components.textpane.LogTextPane;

public class ClientPanel extends JPanel implements ApplicationObserver {

	private static final long serialVersionUID = 6988908113970125824L;

    private ApplicationObservable observable;

	private ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");
	
	private CommunicationService communicationService;

    private JComboBox<String> endLineComboBox;
    private JTextField hostnameTextField;
    private JTextField messageTextField;
    private JTextField portTextField;

    private LogTextPane tramaTextPane;
    private JComboBox<String> typeMessageComboBox;
    
	private ConnectButton connectButton;
	private DisconnectButton disconnectButton;
	private SendButton sendButton;
	
	public ClientPanel() {
		initComponents();
	}

	private void initComponents() {
		JPanel comunicationPanel, conectorPanel;
		JLabel hostnameLabel, portLabel;
		JScrollPane tramaScrollPane;
		GroupLayout comunicationPanelLayout, conectorPanelLayout;

        hostnameLabel = new JLabel();
        hostnameLabel.setText(resourceBundle.getString("field.hostname.label.text"));
        
        hostnameTextField = new JTextField();
//        hostnameTextField.setInputVerifier(new InputVerifier() {
//			
//			@Override
//			public boolean verify(JComponent input) {
//				return validateForm();
//				
//			}
//		});
        hostnameTextField.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				validateForm();
			}
			
			@Override
			public void focusGained(FocusEvent e) { }
		});

        portLabel = new JLabel();
        portLabel.setText(resourceBundle.getString("field.port.label.text"));
        
        portTextField = new JTextField();
//        portTextField.setInputVerifier(new InputVerifier() {
//			
//			@Override
//			public boolean verify(JComponent input) {
////				JTextField textField = (JTextField) input;
////				try {
////					Integer.valueOf(textField.getText());
////					return true;
////				} catch (Exception e) {
////					// TODO Auto-generated method stub
////					return false;
////				}
//				return validateForm();
//				
//			}
//		});
        portTextField.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				validateForm();
			}
			
			@Override
			public void focusGained(FocusEvent e) { }
		});

        connectButton = new ConnectButton();

        disconnectButton = new DisconnectButton();

        conectorPanel = new JPanel();
        
        conectorPanelLayout = new GroupLayout(conectorPanel);
        conectorPanel.setLayout(conectorPanelLayout);
        conectorPanelLayout.setHorizontalGroup(
            conectorPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(conectorPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hostnameLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hostnameTextField, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(portLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(portTextField, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(connectButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(disconnectButton)
                .addContainerGap())
        );
        conectorPanelLayout.setVerticalGroup(
            conectorPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(conectorPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(conectorPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(disconnectButton)
                    .addComponent(connectButton)
                    .addComponent(hostnameLabel)
                    .addComponent(hostnameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(portTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(portLabel))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        tramaTextPane = new LogTextPane();
        
        tramaScrollPane = new JScrollPane();
        tramaScrollPane.setViewportView(tramaTextPane);

        typeMessageComboBox = new JComboBox<>();
        typeMessageComboBox.setModel(new DefaultComboBoxModel<>(new String[] { "Text", "Byte" }));

        messageTextField = new JTextField();

        endLineComboBox = new JComboBox<>();
        endLineComboBox.setModel(new DefaultComboBoxModel<>(new String[] { "<CR>", "<LF>", "<CR><LF>" }));

        sendButton = new SendButton();
 
        comunicationPanel = new JPanel();
        
        comunicationPanelLayout = new GroupLayout(comunicationPanel);
        comunicationPanel.setLayout(comunicationPanelLayout);
        comunicationPanelLayout.setHorizontalGroup(
            comunicationPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(comunicationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(comunicationPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(tramaScrollPane)
                    .addGroup(comunicationPanelLayout.createSequentialGroup()
                        .addComponent(typeMessageComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(messageTextField, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(endLineComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sendButton)))
                .addContainerGap())
        );
        comunicationPanelLayout.setVerticalGroup(
            comunicationPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(comunicationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tramaScrollPane, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(comunicationPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(typeMessageComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(sendButton)
                    .addComponent(endLineComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(messageTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        
        super.setLayout(new BorderLayout());
        super.add(conectorPanel, BorderLayout.NORTH);
        super.add(comunicationPanel, BorderLayout.CENTER);
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
		this.tramaTextPane.registerIn(observable);
		this.connectButton.registerIn(observable);
		this.disconnectButton.registerIn(observable);
		this.sendButton.registerIn(observable);
		updateView();
	}

	private void updateView() {
		this.communicationService = CommunicationService.INSTANCE;
		
		this.messageTextField.requestFocus();
		this.disconnectButton.setEnabled(communicationService.isConnect());
		this.sendButton.setEnabled(communicationService.isConnect());
		//this.connectButton.setEnabled(false);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listener(ApplicationEvent event) {

		if (event instanceof OpenFileEvent) {
			JFileChooser fileChooser;
			int result;
			
			fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			
			result = fileChooser.showOpenDialog(SwingUtilities.getRoot(this));
			if (result == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				// TODO Auto-generated method stub				
			}

		} else if (event instanceof SaveFileEvent) {
			// TODO Auto-generated method stub

		} else if (event instanceof SaveAsFileEvent) {
			JFileChooser fileChooser;
			int result;
			
			fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			
			result = fileChooser.showSaveDialog(SwingUtilities.getRoot(this));
			if (result == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				// TODO Auto-generated method stub				
			}
			
		} else if (event instanceof SendDataEvent) {
			this.sendDataAction();
			
		} else if (event instanceof ConnectEvent) {
			this.connectAction();
			
		} else if (event instanceof DisconnectEvent) {
			this.disconnectAction();
			
		}

	}
	
	protected boolean validateForm() {

//		if (StringUtils.isEmpty(this.hostnameTextField.getText())) {
//			return false;
//		}
//		
//		if (StringUtils.isEmpty(this.portTextField.getText()) || !StringUtils.isNumeric(this.portTextField.getText())) {
//			return false;
//		}
//		
//		if (!communicationService.isConnect()) {
//			//this.connectButton.setEnabled(result);
//		}
		
		return true;
	}

	private void disconnectAction() {
		try {
			this.communicationService.closeConnection();
			this.connectButton.setEnabled(!communicationService.isConnect());
			this.disconnectButton.setEnabled(communicationService.isConnect());
			this.sendButton.setEnabled(communicationService.isConnect());
			this.hostnameTextField.setEditable(!communicationService.isConnect());
			this.portTextField.setEditable(!communicationService.isConnect());
			this.connectButton.requestFocus();
		} catch (Exception e) {
			e.printStackTrace(System.err);
			// TODO Auto-generated method stub			
		}
		
	}

	private void connectAction() {
		String hostname;
		int port;
		
		try {
			this.tramaTextPane.clean();
			hostname = this.hostnameTextField.getText();
			port = Integer.valueOf(this.portTextField.getText());
			
			this.communicationService.openConnection(hostname, port);
			
			this.connectButton.setEnabled(!communicationService.isConnect());
			this.disconnectButton.setEnabled(communicationService.isConnect());
			this.sendButton.setEnabled(communicationService.isConnect());
			this.hostnameTextField.setEditable(!communicationService.isConnect());
			this.portTextField.setEditable(!communicationService.isConnect());
			this.messageTextField.requestFocus();
		} catch (Exception e) {
			e.printStackTrace(System.err);
			JOptionPane.showMessageDialog(SwingUtilities.getRoot(this), e, "Error", JOptionPane.ERROR_MESSAGE);
			// TODO Auto-generated method stub			
		}
		
	}

	private void sendDataAction() {
		String type = (String) this.typeMessageComboBox.getSelectedItem();
		String message = this.messageTextField.getText();
		String endLineCharacters = (String) this.endLineComboBox.getSelectedItem();
		
		this.communicationService.sendMessage(message.getBytes());
		// TODO Auto-generated method stub		
		
	}

}
