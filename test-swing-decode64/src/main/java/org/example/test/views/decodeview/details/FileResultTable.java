package org.example.test.views.decodeview.details;

import java.io.File;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.lang.StringUtils;
import org.example.test.controllers.Decode64Worker;
import org.example.test.events.ApplicationEvent;
import org.example.test.events.CleanLogEvent;
import org.example.test.views.components.ApplicationObservable;
import org.example.test.views.components.ApplicationObserver;

public class FileResultTable extends JTable implements ApplicationObserver {

	private static final long serialVersionUID = -4410869855086319718L;

	private String filter;

	private DefaultTableModel tableModel;

//	private ScannerController scannerController = new ScannerController();

	private Decode64Worker worker;
	
	public FileResultTable() {
		this.initComponents();
	}

	private void initComponents() {
		
		tableModel = new DefaultTableModel(
	            new Object [][] {},
	                new String [] {
	                    "Absolute Path"
	                }
	            ) {

	    			private static final long serialVersionUID = -5994040214670844292L;
	    			
	    			Class<?>[] types = new Class [] {
	                    String.class, String.class, Long.class, Object.class
	                };
	                boolean[] canEdit = new boolean [] {
	                    false, false, false, false
	                };

	                public Class<?> getColumnClass(int columnIndex) {
	                    return types [columnIndex];
	                }

	                public boolean isCellEditable(int rowIndex, int columnIndex) {
	                    return canEdit [columnIndex];
	                }
	            };
		
		
		super.setModel(this.tableModel);
		
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
	}

	@Override
	public void listener(ApplicationEvent event) {
		if (event instanceof CleanLogEvent) {
			this.clean();
			
		} 
//		else if (event instanceof ExecuteEvent) {
//			updateView();
//		}
	}

	public void decode(String decodeString) {
		try {
			//this.clean();
			
				
			
			if (worker != null && !worker.isDone()) { 
				worker.cancel(true); 
			} else if (worker != null && this.worker.isDone()) {
				System.out.println("scannerWorker is done!!!");
			} 
			this.worker = new Decode64Worker(decodeString){
				@Override
				protected void process(List<File> chunks) {
					for (File file : chunks) {
						if (filter == null || StringUtils.isEmpty(filter) || StringUtils.contains(file.getAbsolutePath(), filter)) {
							tableModel.addRow(new Object[]{file});
						}
					}
					
				}
			};
			this.worker.execute();
			// TODO Auto-generated method stub

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void clean() {
		
		while (this.tableModel.getRowCount() > 0) {
			this.tableModel.removeRow(0);
		}
		tableModel.fireTableDataChanged();
		
		// TODO Auto-generated catch block
	}



	public void setSearchText(String text) {
		this.filter = text;
		
	}
}
