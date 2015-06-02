package de.hs_mannheim.ss15.tpe.group_2_4.uebung03.aufgabe03;

import static gdi.MakeItSimple.*;
import de.hs_mannheim.ss15.tpe.group_2_4.uebung03.aufgabe02.CaesarReader;
import de.hs_mannheim.ss15.tpe.group_2_4.uebung03.aufgabe02.CaesarWriter;
import java.io.*;

/**
 * Encrypts/Decrypts txt files in folders with Caesar Crypt
 */
public class CaesarFileEncryptor implements IFileEncryptor {
    private File sourceDirectory;
    private File destinationDirectory;
    private CaesarReader cr;
    private CaesarWriter cw;
    private int key;
    
    CaesarFileEncryptor(int key) {
    	this.key = key;
    }
    
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
                        println(newFolder.getAbsolutePath());
                        found = true;
                        println("Directory success: " + newFolder.mkdir());
                        destinationDirectory = file;
                    }
                    i++;
                }
            }
            else // only create a new folder with the same name
            {
            	String path = file.getAbsolutePath().replace(this.sourceDirectory.getAbsolutePath(), this.destinationDirectory.getAbsolutePath());
                boolean found = false;
                int i = 0;
                while (!found)
                {
                    File newFolder = new File(path+(i==0?"":"("+i+")"));
                    if (!newFolder.exists())
                    {
                        println(newFolder.getAbsolutePath());
                        found = true;
                        println("Directory success: " + newFolder.mkdir());
                    }
                    i++;
                }
            }
            
            println(file.getName());
            File[] fl = file.listFiles();
            for (File fl1 : fl) {
                encryptR(fl1);
            }
        }
        else //If it is a .txt file...
        {
            if (file.getName().substring(file.getName().lastIndexOf('.')+1).equals("txt"))
            {
                try {
                	String path = file.getAbsolutePath().replace(this.sourceDirectory.getAbsolutePath(), this.destinationDirectory.getAbsolutePath());
                	cw = new CaesarWriter( new BufferedWriter(new FileWriter(new File(path))), key);
                	BufferedReader fr = new BufferedReader(new FileReader(file));
                	while(fr.ready()) {
                		cw.write(fr.readLine());
                		cw.write("\n");
                		cw.flush();
                	}
                	cw.close();
                	fr.close();
                }
                catch(IOException e) {
                	System.err.println("Zugriff verweigert!");
                }
                println("- "+file.getAbsolutePath().replace(this.sourceDirectory.getAbsolutePath(), this.destinationDirectory.getAbsolutePath()));
            }
        }
    }
    

    @Override
    public File decrypt(File sourceDirectory) {
        errorIsNotDirectory(sourceDirectory);
        
        this.sourceDirectory = sourceDirectory;
        // Encrypt recursive
        decryptR(sourceDirectory);
        
        return sourceDirectory;
    }
    
    private void decryptR(File file)
    {
        if (file.isDirectory()) // File is directory, create new directory
        {
            if (this.sourceDirectory == file) // Special case: the root folder
            {
                String path = this.sourceDirectory.getAbsolutePath()+"_decrypted";
                boolean found = false;
                int i = 0;
                while (!found)
                {
                    File newFolder = new File(path+(i==0?"":"("+i+")"));
                    if (!newFolder.exists())
                    {
                        println(newFolder.getAbsolutePath());
                        found = true;
                        println("Directory success: " + newFolder.mkdir());
                        destinationDirectory = file;
                    }
                    i++;
                }
            }
            else // only create a new folder with the same name
            {
            	String path = file.getAbsolutePath().replace(this.sourceDirectory.getAbsolutePath(), this.destinationDirectory.getAbsolutePath());
                boolean found = false;
                int i = 0;
                while (!found)
                {
                    File newFolder = new File(path+(i==0?"":"("+i+")"));
                    if (!newFolder.exists())
                    {
                        println(newFolder.getAbsolutePath());
                        found = true;
                        println("Directory success: " + newFolder.mkdir());
                    }
                    i++;
                }
            }
            
            println(file.getName());
            File[] fl = file.listFiles();
            for (File fl1 : fl) {
                encryptR(fl1);
            }
        }
        else //If it is a .txt file...
        {
            if (file.getName().substring(file.getName().lastIndexOf('.')+1).equals("txt"))
            {
                try {
                	String path = file.getAbsolutePath().replace(this.sourceDirectory.getAbsolutePath(), this.destinationDirectory.getAbsolutePath());
                	cr = new CaesarReader( new BufferedReader(new FileReader(new File(path))), key);
                	BufferedWriter fw = new BufferedWriter(new FileWriter(file));
                	while(cr.ready()) {
                		fw.write(cr.read());
                		fw.flush();
                	}
                	cr.close();
                	fw.close();
                }
                catch(IOException e) {
                	System.err.println("Zugriff verweigert!");
                }
                println("- "+file.getAbsolutePath().replace(this.sourceDirectory.getAbsolutePath(), this.destinationDirectory.getAbsolutePath()));
            }
        }
    }
    
    
    private void errorIsNotDirectory(File file)
    {
        if (!file.canRead() || !file.isDirectory())
        {
            throw new GDIException("Angegebener Pfad nicht lesbar oder kein Ordner!");
        }
    }
    
}
