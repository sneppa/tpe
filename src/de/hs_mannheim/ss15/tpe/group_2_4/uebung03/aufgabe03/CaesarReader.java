
package de.hs_mannheim.ss15.tpe.group_2_4.uebung03.aufgabe03;
import java.io.*;

/**
 *
 * @author pi
 */
public class CaesarReader extends FilterReader {

    private int key;
        
    @Override
    public void close() throws IOException {
        super.close(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void reset() throws IOException {
        super.reset(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mark(int readAheadLimit) throws IOException {
        super.mark(readAheadLimit); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean markSupported() {
        return super.markSupported(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean ready() throws IOException {
        return super.ready(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long skip(long n) throws IOException {
        return super.skip(n); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        return super.read(cbuf, off, len); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int read() throws IOException {
        return super.read(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
