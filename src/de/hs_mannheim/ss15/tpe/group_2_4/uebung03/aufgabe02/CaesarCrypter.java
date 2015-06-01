package de.hs_mannheim.ss15.tpe.group_2_4.uebung03.aufgabe02;

import static gdi.MakeItSimple.println;

public class CaesarCrypter implements Crypter {

    private int key;
    private char[] chars;

    public CaesarCrypter(int key) {
        this.key = key % 58; //Key could be larger, but it is not necessary to make it large.
        chars = new char[58];

        // Fill the char array with all uppercase chars
        for (int i = 0; i < 26; i++) {
            chars[i] = (char) (i + 'A');
        }

        // Fill the char array with all lowercase chars
        for (int i = 0; i < 26; i++) {
            chars[i + 26] = (char) (i + 'a');
        }

        // Fill the char array with Umlaute
        chars[52] = 'Ä';
        chars[53] = 'Ö';
        chars[54] = 'Ü';
        chars[55] = 'ä';
        chars[56] = 'ö';
        chars[57] = 'ü';
    }

    public void setKey(int key) {
        this.key = key % 26;
    }

    public int getKey() {
        return this.key;
    }

    @Override
    public String encrypt(String message) {
        String cypherText = "";

        //Encryption by shifting all letters by given key to the right.
        while (message.length() > 0) {
            char h = message.charAt(0);
            //Only a-z and A-Z
            int index = indexOf(chars, h);
            if (index > -1) {
                index = (index + key) % 58;
                h = chars[index];
            }
            cypherText += h;
            message = message.substring(1, message.length());
        }
        return cypherText;
    }

    @Override
    public String decrypt(String cypherText) {
        String message = "";

        while (cypherText.length() > 0) {
            char h = cypherText.charAt(0);
            //Only a-z and A-Z
            int index = indexOf(chars, h);
//            println(index);
            if (index > -1) {
                index = (index - key + 58) % 58;
                h = chars[index];
            }
            message += h;
            cypherText = cypherText.substring(1, cypherText.length());
        }
        return message;
    }

    /**
     * Get the array index of the key value
     * @param array char array
     * @param key key for search
     * @return index, if not found -1
     */
    public static int indexOf(char[] array, char key) {
        int returnvalue = -1;
        for (int i = 0; i < array.length; ++i) {
            if (key == array[i]) {
                returnvalue = i;
                break;
            }
        }
        return returnvalue;
    }

}
