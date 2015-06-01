package de.hs_mannheim.ss15.tpe.group_2_4.uebung03.aufgabe03;

import static gdi.MakeItSimple.*;
import java.io.File;

/**
 * Userinterface for encrypt/decrypt a folder.
 */
public class Menu {

    public static void main(String[] args) {
        print("Bitte Ordner angeben: ");
        String folder = readLine();
        CaesarFileEncryptor caesar = new CaesarFileEncryptor();
        File file = new File(folder);
            
        

        while (true) {
            println("------------------- Menü -------------------");
            println("1: kodieren");
            println("2: dekodieren");
            println("--------------------------------------------");

            int selection = readInt();

            switch (selection) {
                case 1:
                    caesar.encrypt(file);
                    break;
                case 2:
                    caesar.decrypt(file);
                    break;
                default:
                    return;
            }

            readLine();
        }
    }
}
