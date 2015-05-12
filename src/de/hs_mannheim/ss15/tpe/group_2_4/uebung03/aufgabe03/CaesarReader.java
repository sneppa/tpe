
package de.hs_mannheim.ss15.tpe.group_2_4.uebung03.aufgabe03;
import java.io.*;

/**
 *
 * @author pi
 */
public class CaesarReader extends FilterReader {

    private int key;
     
    protected CaesarReader(Reader in) {
        super(in);
        this.in = in;
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
    public int read(char[] cbuf, int off, int len) throws IOException {
        return super.read(cbuf, off, len); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int read() throws IOException {
        return super.read(); //To change body of generated methods, choose Tools | Templates.
    }
    
    private String decrypt(String cypherText) {
            String message = "";

            while(cypherText.length() > 0) {
                    char h = cypherText.charAt(0);
                    //Only a-z and A-Z
                    if((('a' <= h)  && (h <= 'z')) || (('A' <= h) && (h <= 'Z'))) {
                            if((('a' <= h)  && (h <= 'z')))
                                    h = (char)(h - 'a'); //See encrypt()
                            else
                                    h = (char)(h - 'A');
                            h = (char)((h - key + 26) % 26); //+26 so modulo does work (somehow doesn't with negative ints)
                            h = (char)(h + 'A');
                    }
                    message += h;
                    cypherText = cypherText.substring(1, cypherText.length());
            }
            return message;
    }
    
}
