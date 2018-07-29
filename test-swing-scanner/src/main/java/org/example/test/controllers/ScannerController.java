package org.example.test.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.example.test.model.Operation;

public class ScannerController {

	
	public void scanner(String absolutePathDirectory, Operation operation) throws IOException {
		System.out.println("Scan Path....");

		Path path = Paths.get(absolutePathDirectory);

		System.out.format("toString: %s%n", path.toString());
		System.out.format("getFileName: %s%n", path.getFileName());
		System.out.format("getName(0): %s%n", path.getName(0));
		System.out.format("getNameCount: %d%n", path.getNameCount());
		// System.out.format("subpath(0,2): %s%n", path.subpath(0,2));
		System.out.format("getParent: %s%n", path.getParent());
		System.out.format("getRoot: %s%n", path.getRoot());

		FileSystem fileSystem = path.getFileSystem();

		System.out.println("fileSystem: " + fileSystem);

		Iterable<FileStore> fileStores = fileSystem.getFileStores();
		for (FileStore fileStore : fileStores) {

			System.out.println(fileStore);

		}
		
		Path walkFileTree = Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
				
			@Override
			public FileVisitResult visitFile(Path p, BasicFileAttributes attrs) throws IOException {
					
						
						String md5Hex = DigestUtils.md5Hex(new FileInputStream(p.toFile()));
						
						System.out.println(p + " - " + p.getName(p.getNameCount()  -1) + " - " + attrs.creationTime() + " - " + FileUtils.byteCountToDisplaySize(attrs.size()) + " " + md5Hex);
						if (operation != null) {
							operation.process(p.toFile());
						}
//						HashCode hash = com.google.common.io.Files.hash(p.toFile(), Hashing.md5());
//						
//						System.out.println(p + " - " + p.getName(p.getNameCount()  -1) + " - " + attrs.creationTime() + " - " + attrs.size() + " " + hash.toString());
						
						return FileVisitResult.CONTINUE;
			}
			
		});
		
		
		
		System.out.println("walkFileTree: " + walkFileTree + " ");
	}
	
	public void copy(File directory1, File directory2) throws IOException {
		long t = System.currentTimeMillis();
		final long count = 0;
		
		doWork(directory1, new Operation() {
			
			public void process(File f) {
					System.out.println("File: " + f.getAbsolutePath() + " - " + f.lastModified());	
					try {
						FileUtils.copyFileToDirectory(f, directory2);
					} catch (IOException e) {
						e.printStackTrace();
					}
				//count++;
				
			}
			
		});
		
		
		System.out.println("... end " + count + " files in " + (double)((System.currentTimeMillis() - t)/1000) + " seconds.");
	}
	
	private void doWork(File file, Operation operation) throws IOException {
		
		if (file.exists() && file.isDirectory() && file.canRead()) {
			File[] files = file.listFiles();	
			for (int i = 0; i < files.length; i++) {
				File f = files[i];
				
				if (f.exists() && f.isDirectory() && f.canRead()) {
					doWork(f, operation);
				} else {
					operation.process(f);
				}
			}
		} else {
			operation.process(file);
		}
	}
	
}
