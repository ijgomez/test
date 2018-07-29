package org.example.test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

public class WatchThread extends Thread {

	private String absolutePath;
	
	private boolean isRunning = true;
	
	public WatchThread(String absolutePath) {
		super();
		this.absolutePath = absolutePath;
	}
	
	@Override
	public void run() {
		while (isRunning) {
			//define a folder root
	        Path path = Paths.get(absolutePath);       

	        try {
	           WatchService watchService = path.getFileSystem().newWatchService();
	           path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);

	           WatchKey watchKey;
	           
	           do {
	           watchKey = watchService.take();

	           List<WatchEvent<?>> events = watchKey.pollEvents();
	           for (WatchEvent<?> event : events) {
	                process(event);
	            }
	           } while (watchKey.reset());
	           
	        } catch (Exception e) {
	            System.out.println("Error: " + e.toString());
	        }
		}
		
	}

	private void process(WatchEvent<?> event) {
		if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
//	                	this.printlnLog("Created: " + event.context().toString());
			System.out.println("Created: " + event.context().toString());
		    
		}
		if (event.kind() == StandardWatchEventKinds.ENTRY_DELETE) {
//	                	this.printlnLog("Delete: " + event.context().toString());
			System.out.println("Delete: " + event.context().toString());
		}
		if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
//	                	this.printlnLog("Modify: " + event.context().toString());
			System.out.println("Modify: " + event.context().toString());
		}
	}
	
	public void stopThread() {
		this.isRunning = false;
	}
}
