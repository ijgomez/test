package org.example.test;

import java.util.Random;

public class Conversor {
	public static int convert(byte input[], int byteStart, int byteEnd, char output[], int charStart, int charEnd) throws Exception {
		int charOff = charStart;
		for (int byteOff = byteStart; byteOff < byteEnd;) {
			if (charOff >= charEnd)
				throw new Exception();
			
			int i1 = input[byteOff++];
			if (i1 >= 0)
				output[charOff++] = (char) i1;
			else
				output[charOff++] = (char) (256 + i1);
		}
		return charOff - charStart;
	}

	public static void main(String[] datos) throws Exception {
		byte[] entrada = new byte[10240];
		char[] salida = new char[entrada.length];
		Random r = new Random();
		r.nextBytes(entrada);
		convert(entrada, 0, entrada.length, salida, 0, salida.length);
		for (int i = 0; i < salida.length; i++)
			//System.out.print(salida[i]);
			System.out.print(salida.length);

	}
}
