package org.example.test.views.scanview.details;

import java.io.File;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.lang.StringUtils;
import org.example.test.controllers.ScannerWorker;
import org.example.test.events.ApplicationEvent;
import org.example.test.events.CleanLogEvent;
import org.example.test.events.ExecuteEvent;
import org.example.test.views.components.ApplicationObservable;
import org.example.test.views.components.ApplicationObserver;

public class FileResultTable extends JTable implements ApplicationObserver {

	private static final long serialVersionUID = -4410869855086319718L;
	
	private File directory;
	
	private String filter;

	private DefaultTableModel tableModel;

//	private ScannerController scannerController = new ScannerController();

	private ScannerWorker scannerWorker;
	
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
			
		} else if (event instanceof ExecuteEvent) {
			updateView();
		}
	}

	public void updateView() {
		try {
			this.clean();
			
				
			
			if (scannerWorker != null && !scannerWorker.isDone()) { 
				scannerWorker.cancel(true); 
			} else if (scannerWorker != null && this.scannerWorker.isDone()) {
				System.out.println("scannerWorker is done!!!");
			} 
			this.scannerWorker = new ScannerWorker(directory){
				@Override
				protected void process(List<File> chunks) {
					for (File file : chunks) {
						if (filter == null || StringUtils.isEmpty(filter) || StringUtils.contains(file.getAbsolutePath(), filter)) {
						tableModel.addRow(new Object[]{file});
						}
					}
					
				}
			};
			this.scannerWorker.execute();
			
			
//			this.scannerController.scanner(this.directory.getAbsolutePath(), new Operation() {
//				
//				@Override
//				public void process(File f) {
//					
//					if (filter == null || StringUtils.isEmpty(filter) || StringUtils.contains(f.getAbsolutePath(), filter)) {
//						// TODO Auto-generated method stub
//						tableModel.addRow(new Object[]{f});
//						tableModel.fireTableDataChanged();
//					}
//				}
//			});
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

	public void setDirectory(File d) {
		this.directory = d;
		
	}

	public void setSearchText(String text) {
		this.filter = text;
		
	}
}
