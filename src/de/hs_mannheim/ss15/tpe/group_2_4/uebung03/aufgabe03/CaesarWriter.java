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
    protected CaesarWriter(Writer out) {
        super(out);
        this.out = out;
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

    private String encrypt(String message) {
            String cypherText = "";

            //Encryption by shifting all letters by given key to the right.
            while(message.length() > 0) {
                    char h = message.charAt(0);
                    //Only a-z and A-Z
                    if((('a' <= h)  && (h <= 'z')) || (('A' <= h) && (h <= 'Z'))) {
                            if((('a' <= h)  && (h <= 'z')))
                                    h = (char)(h - 'a'); //Number ranges from 0 - 25...
                            else
                                    h = (char)(h - 'A');
                            h = (char)((h + key) % 26); //...so it can be operated with modulo (rollover detection)
                            h = (char)(h + 'A'); //Also make everything uppercase
                    }
                    cypherText += h;
                    message = message.substring(1, message.length());
            }
            return cypherText;
    }
}
