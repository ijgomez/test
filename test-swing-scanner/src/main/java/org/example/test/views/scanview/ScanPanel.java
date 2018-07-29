package org.example.test.views.scanview;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.apache.commons.lang.StringUtils;
import org.example.test.events.ApplicationEvent;
import org.example.test.events.OpenFileEvent;
import org.example.test.events.SaveFileEvent;
import org.example.test.events.SelectFileEvent;
import org.example.test.views.components.ApplicationObservable;
import org.example.test.views.components.ApplicationObserver;
import org.example.test.views.components.buttons.ExecuteButton;
import org.example.test.views.components.buttons.SelectButton;
import org.example.test.views.scanview.details.FileResultTable;

public class ScanPanel extends JPanel implements ApplicationObserver {

	private static final long serialVersionUID = -6796375263443730996L;

	private FileResultTable fileResultTable;

	private JTextField selectTextField;
	
	private JTextField searchTextField;
	
	private SelectButton selectButton;
	
	private ExecuteButton executeButton;
	
	public ScanPanel() {
		this.initComponents();
	}
	
	private void initComponents() {
    	JLabel selectLabel, searchLabel;
    	
        JScrollPane resultScrollPane;

        selectLabel = new JLabel();
        selectLabel.setText("Directorio:");
        
        selectTextField = new JTextField();
        selectTextField.getDocument().addDocumentListener(new DocumentListener() {
			
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

        selectButton = new SelectButton();
        
        searchLabel = new JLabel();
        searchLabel.setText("Search:");
       
        searchTextField = new JTextField();
        searchTextField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				fileResultTable.setSearchText(searchTextField.getText());
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				fileResultTable.setSearchText(searchTextField.getText());
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				fileResultTable.setSearchText(searchTextField.getText());
			}
		});
        searchTextField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fileResultTable.updateView();
				
			}
		});
        
        executeButton = new ExecuteButton();
        executeButton.setEnabled(false);
        
        fileResultTable = new FileResultTable();
        fileResultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                
        resultScrollPane = new JScrollPane();
        resultScrollPane.setViewportView(fileResultTable);
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
                        .addComponent(selectTextField)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(selectButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(searchLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchTextField)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(executeButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(selectLabel)
                    .addComponent(selectButton)
                    .addComponent(selectTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resultScrollPane, GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(searchLabel)
                    .addComponent(executeButton)
                    .addComponent(searchTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
		this.selectButton.registerIn(observable);
		this.executeButton.registerIn(observable);
		this.fileResultTable.registerIn(observable);
	}
	
	@Override
	public void listener(ApplicationEvent event) {
		
		 if (event instanceof SelectFileEvent) {
			JFileChooser fileChooser;
			File file = null;
			
			try {
				fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
				fileChooser.setMultiSelectionEnabled(false);
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
					file = fileChooser.getSelectedFile();
					if (file.exists() && file.isDirectory()) {
						this.selectTextField.setText(file.getAbsolutePath());
						this.fileResultTable.setDirectory(file);
					} else {
						// TODO Auto-generated catch block
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		} else if (event instanceof OpenFileEvent) {
			// TODO Auto-generated catch block
			
		} else if (event instanceof SaveFileEvent) {
			// TODO Auto-generated catch block
			
		
		}
		
	}

	private void validateForm() {
		if (StringUtils.isEmpty(selectTextField.getText())) {
			executeButton.setEnabled(false);
		} else {
			File d = new File(selectTextField.getText());
			if (d.exists() && d.isDirectory()) {
				executeButton.setEnabled(true);
			} else {
				executeButton.setEnabled(false);
			}
			
		}
	}
	
	
}
