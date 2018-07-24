package org.example.test;

import java.io.IOException;
import java.io.Writer;

public class NullWriter extends Writer {

	public void close() throws IOException {
        // ignore
    }

    public void flush() throws IOException {
        // ignore
    }

    public void write(char cbuf[], int off, int len) throws IOException {
        // ignore
    }
}
