package de.hs_mannheim.ss15.tpe.group_2_4.uebung02;

import de.hs_mannheim.ss15.tpe.group_2_4.uebung02.aufgabe01.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Random;

/**
 *
 * @author pi
 */
public class BTreeClassTest {
    BTreeClass btree;
    int order = 1;
    Integer size = 10;
    
    @Before
    public void setUp() {
    
        btree = new BTreeClass(order);
        
        Integer[] ints = new Integer[size];
        
        for (int i = 0; i < size; i++)
        {
            ints[i] = i+1;
        }
        
        shuffleArray(ints);
        for (int i = 0; i < ints.length; i++)
        {
            //println(ints[i]);
            btree.insert(ints[i]);
        }
    }

    @Test
    public void isNotEmpty() {
        assertFalse(btree.isEmpty());
    }

    @Test
    public void isEmpty() {
        btree = new BTreeClass(1);
        assertTrue(btree.isEmpty());
    }
    
    @Test
    public void containsNonExistingNumber() {
        assertFalse(btree.contains(size+1));
    }
    
    @Test
    public void containsExistingNumber() {
        assertTrue(btree.contains(size-1));
    }
    
    @Test
    public void getMax() {
        assertSame(size, btree.getMax());
    }
    
    @Test
    public void getMin() {
        assertSame(1, btree.getMin());
    }
    
    @Test
    public void insertNewNumber() {
        assertTrue(btree.insert(size+1));
    }
    
    @Test
    public void insertExistingNumber() {
        btree.insert(size+1);
        assertFalse(btree.insert(size+1));
    }
    
    
    static void shuffleArray(Integer[] ar)
    {
      Random rnd = new Random();
      for (int i = ar.length - 1; i > 0; i--)
      {
        int index = rnd.nextInt(i + 1);
        int a = ar[index];
        ar[index] = ar[i];
        ar[i] = a;
      }
    }
}

