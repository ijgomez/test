package org.example.test;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class WatcherFrame extends JFrame {

	private static final long serialVersionUID = 487377711017972949L;

	private Document document;
	
	public WatcherFrame() {
		super();
		this.initializateGUI();
	}

	private void initializateGUI() {
		JPanel folderPanel, logPanel;
		JScrollPane scrollPane;
		JTextPane textPane;
		JButton watchButton;
		

		watchButton = new JButton();
		watchButton.setText("Directory");
		watchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				selectFolderAction(e);
			}
		});
		
		folderPanel = new JPanel();
		folderPanel.add(watchButton);
		
		textPane = new JTextPane();
		textPane.setEditable(false);
	
		this.document = textPane.getDocument();
		
		
		
		logPanel = new JPanel();
		logPanel.setLayout(new BorderLayout());
		logPanel.add(textPane, BorderLayout.CENTER);
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(logPanel);


		super.setLayout(new BorderLayout());
		super.add(folderPanel, BorderLayout.NORTH);
		super.add(scrollPane, BorderLayout.CENTER);
		
	}

	protected void selectFolderAction(ActionEvent e) {

		JFileChooser fc;
				
				fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				
				if (fc.showOpenDialog(WatcherFrame.this) == JFileChooser.APPROVE_OPTION)  {
					
					File selectedFile = fc.getSelectedFile();
					
					
					if (selectedFile.isDirectory()) {
						
						printlnLog("Observando directorio: " + selectedFile.getAbsolutePath());
						
//						configWatcher(selectedFile.getAbsolutePath());
						
						new WatchThread(selectedFile.getAbsolutePath()).start();
					}
								
				}

//				for (int i = 0; i < 20; i++) {
//					final int id = i;
//					Timer t = new Timer(300, new ActionListener() {
//						
//						@Override
//						public void actionPerformed(ActionEvent e) {
//							System.out.println("Timer " + id);
//							printlnLog("Timer " + id);
//							
//						}
//					});	
//					t.start();
//				}

				
				
	}

//	private void configWatcher(String absolutePath) {
//		
//
//		
//		//define a folder root
//        Path path = Paths.get(absolutePath);       
//
//        try {
//           WatchService watchService = path.getFileSystem().newWatchService();
//           path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
//
//           WatchKey watchKey;
//           
//           do {
//           watchKey = watchService.take();
//
//           List<WatchEvent<?>> events = watchKey.pollEvents();
//           for (WatchEvent<?> event : events) {
//                if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
//                	this.printlnLog("Created: " + event.context().toString());
//                	System.out.println("Created: " + event.context().toString());
//                    
//                }
//                if (event.kind() == StandardWatchEventKinds.ENTRY_DELETE) {
//                	this.printlnLog("Delete: " + event.context().toString());
//                	System.out.println("Delete: " + event.context().toString());
//                }
//                if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
//                	this.printlnLog("Modify: " + event.context().toString());
//                	System.out.println("Modify: " + event.context().toString());
//                }
//            }
//           } while (watchKey.reset());
//           
//        } catch (Exception e) {
//            System.out.println("Error: " + e.toString());
//        }
//		
//	}

	private void printlnLog(String msg) {
		this.printLog(msg + "\n");
	}
	
	private void printLog(final String msg) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				try {
					
					document.insertString(document.getLength(), msg, null);
				} catch (BadLocationException e1) {

					e1.printStackTrace();
				}				
				
			}
		});

	}
}
