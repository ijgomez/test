package org.example.test;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		String fileURL = "http://datos.madrid.es/egob/catalogo/212531-7916318-calidad-aire-tiempo-real.txt";
        String saveDir = "target";
        try {
            HttpDownloadUtility.downloadFile(fileURL, saveDir);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
	}

}
