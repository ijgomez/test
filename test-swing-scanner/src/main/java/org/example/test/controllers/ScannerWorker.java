package org.example.test.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

import javax.swing.SwingWorker;

public class ScannerWorker extends SwingWorker<Void, File> {
	
	private File directory;
	
	public ScannerWorker(File directory) {
		this.directory = directory;
	}

	@Override
	protected Void doInBackground() throws Exception {
		Files.walkFileTree(Paths.get(directory.getAbsolutePath()), new SimpleFileVisitor<Path>(){
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
//				
//				String md5Hex = DigestUtils.md5Hex(new FileInputStream(file.toFile()));
//				
//				String msg = file + " - " + file.getName(file.getNameCount() - 1) + " - " + attrs.creationTime() + " - " + FileUtils.byteCountToDisplaySize(attrs.size()) + " " + md5Hex;

				publish(file.toFile());

				return FileVisitResult.CONTINUE;
			}
		});
		return null;
	}
	
	@Override
	protected void process(List<File> chunks) {
//		for (String msg : chunks) {
//			// TODO Auto-generated method stub
////			 displayResult(msg);
//		}
	}

}
