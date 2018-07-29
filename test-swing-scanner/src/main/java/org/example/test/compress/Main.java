package org.example.test.compress;

import java.io.IOException;

import org.apache.commons.compress.compressors.CompressorException;
import org.example.test.controllers.CompressorController;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			System.out.println("Descomprimiendo...");
			CompressorController.INSTANCE.uncompress("IOLAN_SCS-SDS-STS_UG_v3.3.pdf.bz2");
			System.out.println("..OK.");
		} catch (IOException | CompressorException e) {
			e.printStackTrace();
		}
		
//		try {
//			System.out.println("Comprimiendo...");
//			CompressorService.INSTANCE.compress("UOC_HP_0845_20121214.1");
//			System.out.println("..OK.");
//		} catch (IOException | CompressorException e) {
//			e.printStackTrace();
//		}
	}

	
	
}
