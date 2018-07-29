package org.example.test.controllers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.apache.commons.compress.utils.IOUtils;

public class CompressorController {

	public static final CompressorController INSTANCE = new CompressorController();

	public void compress(String fileName) throws IOException, CompressorException {
		final FileOutputStream out = new FileOutputStream(fileName + ".bz2");

		CompressorOutputStream cos = new CompressorStreamFactory().createCompressorOutputStream(CompressorStreamFactory.BZIP2, out);
		IOUtils.copy(new FileInputStream(fileName), cos);
		cos.close();
	}

	public void uncompress(String fileName) throws IOException, CompressorException {
		final InputStream is = new FileInputStream(fileName);

		CompressorInputStream in = new CompressorStreamFactory().createCompressorInputStream(CompressorStreamFactory.BZIP2, is);
		IOUtils.copy(in, new FileOutputStream(fileName.replaceAll(".bz2", "")));
		in.close();
	}
}
