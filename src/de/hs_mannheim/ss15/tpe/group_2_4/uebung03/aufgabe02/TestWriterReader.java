package de.hs_mannheim.ss15.tpe.group_2_4.uebung03.aufgabe02;

import static gdi.MakeItSimple.*;
import java.io.*;

public class TestWriterReader {

    public static PrintWriter w;
    public static CaesarReader r;

    public static void main(String args[]) {

        try {
            // Test Writer
            w = new PrintWriter(new CaesarWriter(new FileWriter("test.txt"), 5));
            w.println("ABC");
            w.println("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWÄÖÜäöü");
            w.write("Milch");
            w.write(" ");
            w.write('F');
            w.close();

        } catch (IOException e) {
            System.out.println("Fehler beim Erstellen der Datei");
        }

        try {
            // Test Reader
            r = new CaesarReader(new FileReader("test.txt"), 5);

            while (r.ready()) {
                char c = (char) r.read();
                System.out.print(c);
            }

            r.close();

        } catch (IOException e) {
            System.out.println("Fehler beim Lesen der Datei");
        }
    }
}
