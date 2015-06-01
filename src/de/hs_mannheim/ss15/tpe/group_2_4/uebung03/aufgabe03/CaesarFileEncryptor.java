package de.hs_mannheim.ss15.tpe.group_2_4.uebung03.aufgabe03;

import static gdi.MakeItSimple.*;
import java.io.File;

/**
 * Encrypts/Decrypts txt files in folders with Caesar Crypt
 */
public class CaesarFileEncryptor implements IFileEncryptor {
    private File sourceDirectory;
    private File destinationDirectory;
    
    @Override
    public File encrypt(File sourceDirectory) {
        errorIsNotDirectory(sourceDirectory);
        
        this.sourceDirectory = sourceDirectory;
        // Encrypt recursive
        encryptR(sourceDirectory);
        
        return sourceDirectory;
    }
    
    private void encryptR(File file)
    {
        if (file.isDirectory()) // File is directory, create new directory
        {
            if (this.sourceDirectory == file) // Special case: the root folder
            {
                String path = this.sourceDirectory.getAbsolutePath()+"_encrypted";
                boolean found = false;
                int i = 0;
                while (!found)
                {
                    File newFolder = new File(path+(i==0?"":"("+i+")"));
                    if (!newFolder.exists())
                    {
                        println(newFolder.getName());
                        found = true;
                        file.mkdir();
                        destinationDirectory = file;
                    }
                }
            }
            else // only create a new folder with the same same
            {
                
            }
            
            println(file.getName());
            File[] fl = file.listFiles();
            for (File fl1 : fl) {
                encryptR(fl1);
            }
        }
        else
        {
            if (file.getName().substring(file.getName().lastIndexOf('.')+1).equals("txt"))
            {
                println("- "+file.getName());
            }
        }
    }
    

    @Override
    public File decrypt(File sourceDirectory) {
        errorIsNotDirectory(sourceDirectory);
        
        return sourceDirectory;
    }
    
    private void errorIsNotDirectory(File file)
    {
        if (!file.canRead() || !file.isDirectory())
        {
            throw new GDIException("Angegebener Pfad nicht lesbar oder kein Ordner!");
        }
    }
    
}
