package de.hs_mannheim.ss15.tpe.group_2_4.uebung03.aufgabe02;

import java.io.*;

/**
 *
 * @author pi
 */
public class CaesarWriter extends FilterWriter {

    private int key;
    private CaesarCrypter crypter;

    /**
     * Initialize the Writer
     * @param out
     * @param key Caesar Key
     */
    public CaesarWriter(Writer out, int key) {
        super(out);
        crypter = new CaesarCrypter(key);
        this.out = out;
        this.key = key;
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        write(str.toCharArray(), off, len); // String -> CharArray -> write(chararray)
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        for (int i = 0; i < len; ++i) {
            write(cbuf[off + i]); // Pass every char from the char array to the write(int)
        }
    }

    @Override
    public void write(int c) throws IOException {
        super.write(crypter.encrypt(((char) c)+"").toCharArray()[0]); // Encrypt the char and write it to the file
    }
}
