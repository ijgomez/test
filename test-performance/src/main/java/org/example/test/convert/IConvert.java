package org.example.test.convert;

public interface IConvert {
	public int convert(
			byte input[], int byteStart, int byteEnd, char output[],
			int charStart, int charEnd) throws Exception;
}
