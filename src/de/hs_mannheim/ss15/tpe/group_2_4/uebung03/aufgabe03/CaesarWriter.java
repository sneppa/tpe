package de.hs_mannheim.ss15.tpe.group_2_4.uebung03.aufgabe03;
import java.io.*;
/**
 *
 * @author pi
 */
public class CaesarWriter extends FilterWriter {

    private int key;
    
    /**
     *
     */
    public CaesarWriter() {
        
    }
        
    @Override
    public void close() throws IOException {
        super.close(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void flush() throws IOException {
        super.flush(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        super.write(str, off, len); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        super.write(cbuf, off, len); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void write(int c) throws IOException {
        super.write(c); //To change body of generated methods, choose Tools | Templates.
    }
    
}
