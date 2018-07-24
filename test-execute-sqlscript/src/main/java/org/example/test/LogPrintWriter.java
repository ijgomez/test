package org.example.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import org.apache.log4j.Logger;

public class LogPrintWriter extends PrintWriter {

    /**
     * Internal buffer.
     */
    private StringBuffer buffer = new StringBuffer();

    /**
     * Logger for message output.
     */
    private Logger log;

    /**
     * Creates a new <code>LogPrintWriter</code> which is based on a
     * <code>Writer</code>.
     *
     * @param out the base <code>Writer</code>.
     */
    public LogPrintWriter(Writer out) {
        super(out);
    }

    /**
     * Creates a new <code>LogPrintWriter</code> which is based on a
     * <code>Logger</code>.
     *
     * @param log the base <code>Logger</code>.
     */
    public LogPrintWriter(Logger log) {
        super(new NullWriter());
        this.log = log;
    }
    
    /**
     * Sets a new output <code>Writer</code>. Calling this method will flush
     * this <code>LogPrintWriter</code> before the new <code>Writer</code>
     * <code>out</code> is set.
     *
     * @param out the <code>Writer</code> to use for output.
     */
    public void setWriter(Writer out) {
        flushBuffer();
        this.out = out;
        this.log = null;
    }

    /**
     * Sets a new <code>Logger</code>. Calling this method will flush this
     * <code>LogPrintWriter</code> before the new <code>Logger</code> is set.
     *
     * @param log the new <code>Logger</code> to use for output.
     */
    public void setLogger(Logger log) {
        flushBuffer();
        out = new NullWriter();
        this.log = log;
    }
    
  //------------------< overrides from PrintWriter >-------------------------

    public void close() {
        flushBuffer();
        super.close();
    }

    public void flush() {
        flushBuffer();
        super.flush();
    }

    public void write(int c) {
        buffer.append(c);
    }

    public void write(char cbuf[], int off, int len) {
        buffer.append(cbuf, off, len);
    }

    public void write(String str, int off, int len) {
        buffer.append(str.substring(off, off + len));
    }

    public void println() {
        if (log == null) {
            // only add newline when operating on a writer
            buffer.append('\n');
        }
        flushBuffer();
    }

  //-----------------------< private methods >--------------------------------

    private void flushBuffer() {
        if (buffer.length() == 0) {
            return;
        }
        if (log != null) {
            log.debug(buffer.toString());
        } else {
            try {
                out.write(buffer.toString());
            } catch (IOException e) {
                this.setError();
            }
        }
        // reset buffer
        buffer.setLength(0);
    }
    
    
}
