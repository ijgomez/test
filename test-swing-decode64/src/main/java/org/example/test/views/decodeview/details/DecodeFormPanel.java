package org.example.test.views.decodeview.details;

import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;

import org.example.test.events.ApplicationEvent;
import org.example.test.views.components.ApplicationObservable;
import org.example.test.views.components.ApplicationObserver;
import org.example.test.views.components.buttons.ExecuteButton;

public class DecodeFormPanel extends JPanel implements ApplicationObserver {

	private static final long serialVersionUID = 6244170668242196044L;

    private ApplicationObservable observable;
	
	private JTextArea decodeTextArea;
	
	private ExecuteButton executeButton;
	
	public DecodeFormPanel() {
		initComponents();
	}

	private void initComponents() {
		
	    JScrollPane decodeScrollPane;

        decodeTextArea = new JTextArea();
//        decodeTextArea.setColumns(20);
//        decodeTextArea.setRows(5);
        
        decodeScrollPane = new JScrollPane();
        decodeScrollPane.setViewportView(decodeTextArea);

        executeButton = new ExecuteButton();

        GroupLayout layout = new GroupLayout(this);
        super.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(executeButton))
                    .addComponent(decodeScrollPane, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(decodeScrollPane, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(executeButton)
                .addContainerGap())
        );
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
		this.executeButton.registerIn(observable);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listener(ApplicationEvent evento) {
		// TODO Auto-generated method stub
		
	}

	public String getDecodeString() {
		return this.decodeTextArea.getText();
	}

}
