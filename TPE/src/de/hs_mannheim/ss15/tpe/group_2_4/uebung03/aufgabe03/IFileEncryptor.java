package de.hs_mannheim.ss15.tpe.group_2_4.uebung03.aufgabe03;
import java.io.File;

/**
 * Interface to encrypt/decrypt Folders
 */
public interface IFileEncryptor {
    
    /**
     * Method to encrypt a folder
     * @param sourceDirectory
     * @return destinyDirectory
     */
    public File encrypt(File sourceDirectory);
    
    /**
     * Method to decrypt a folder
     * @param sourceDirectory
     * @return destinyDirectory
     */
    public File decrypt(File sourceDirectory);
}
