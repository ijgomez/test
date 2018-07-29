package org.example.test.views.decodeview;

import java.awt.Cursor;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import org.example.test.events.ApplicationEvent;
import org.example.test.events.ExecuteEvent;
import org.example.test.views.components.ApplicationObservable;
import org.example.test.views.components.ApplicationObserver;
import org.example.test.views.decodeview.details.DecodeFormPanel;
import org.example.test.views.decodeview.details.FileResultTable;
import org.jdesktop.swingx.JXMultiSplitPane;
import org.jdesktop.swingx.MultiSplitLayout;

public class DecodePanel extends JXMultiSplitPane implements ApplicationObserver {

	private static final long serialVersionUID = -8250449846099598424L;

    private ApplicationObservable observable;
    
    private DecodeFormPanel decodeFormPanel;
    
    private FileResultTable fileResultTable;
	
	public DecodePanel() {
		this.initComponents();
	}

	private void initComponents() {
		JScrollPane resultScrollPane;
		decodeFormPanel = new DecodeFormPanel();

		fileResultTable = new FileResultTable();
		fileResultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		 resultScrollPane = new JScrollPane();
	        resultScrollPane.setViewportView(fileResultTable);
	        resultScrollPane.getViewport().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		MultiSplitLayout.Node modelRoot = MultiSplitLayout.parseModel("(COLUMN weight=1.0 (LEAF name=panel.top weight=0.8) (LEAF name=panel.botton weight=0.2))"); 
	        
		super.getMultiSplitLayout().setModel(modelRoot);
		super.setBorder( BorderFactory.createEmptyBorder( 4, 4, 4, 4 ) ); 
		super.add(decodeFormPanel, "panel.top");
		super.add(resultScrollPane, "panel.botton");
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
		decodeFormPanel.registerIn(observable);
		fileResultTable.registerIn(observable);
	}

	@Override
	public void listener(ApplicationEvent evento) {
		if (evento instanceof ExecuteEvent) {
			fileResultTable.decode(decodeFormPanel.getDecodeString());
			// TODO Auto-generated method stub			
		}
	}
}
