/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hs_mannheim.ss15.tpe.group_2_4.uebung03;

import de.hs_mannheim.ss15.tpe.group_2_4.uebung03.aufgabe02.CaesarWriter;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pi
 */
public class CaesarWriterTest {
    
    public CaesarWriterTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of write method, of class CaesarWriter.
     */
    @Test
    public void testWrite_3args_1() throws Exception {
        System.out.println("write");
        String str = "";
        int off = 0;
        int len = 0;
        CaesarWriter instance = null;
        instance.write(str, off, len);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of write method, of class CaesarWriter.
     */
    @Test
    public void testWrite_3args_2() throws Exception {
        System.out.println("write");
        char[] cbuf = null;
        int off = 0;
        int len = 0;
        CaesarWriter instance = null;
        instance.write(cbuf, off, len);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of write method, of class CaesarWriter.
     */
    @Test
    public void testWrite_int() throws Exception {
        System.out.println("write");
        int c = 0;
        CaesarWriter instance = null;
        instance.write(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
