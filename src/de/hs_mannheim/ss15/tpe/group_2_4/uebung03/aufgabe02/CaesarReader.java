package de.hs_mannheim.ss15.tpe.group_2_4.uebung03.aufgabe02;

import static gdi.MakeItSimple.*;
import java.io.*;

/**
 *
 * @author pi
 */
public class CaesarReader extends FilterReader {

    private int key;
    private CaesarCrypter crypter;

    /**
     * Initialize the FilterReader
     * @param in
     * @param key CaesarKey
     */
    public CaesarReader(Reader in, int key) {
        super(in);
        crypter = new CaesarCrypter(key);
        this.in = in;
        this.key = key;
    }

    /**
     * This method overrides the read() from the superclass and realizes the caesar-decoding
     */
    public int read() throws IOException {
        char h = (char) super.read();
//        println(h);
        return crypter.decrypt(h+"").toCharArray()[0];
    }
}
