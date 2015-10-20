package de.hs_mannheim.ss15.tpe.group_2_4.uebung04.aufgabe01;
import static gdi.MakeItSimple.*;
import java.io.*;

public class BTreeNode implements Serializable {

    int size;
    Comparable[] contents;
    BTreeNode[] subTrees;

    public BTreeNode(int size) {
        this.size = size;
        this.contents = new Comparable[size + 1]; //+1 because of overflow object (for split)
        this.subTrees = new BTreeNode[size + 2]; //+2 because of 2m+1 +1 overflow tree
        for (int i = 0; i < contents.length; i++) {
            contents[i] = null;
        }
    }

    /**
     * Getter-method.
     *
     * @param index Index at which to look at.
     * @return Returns the value at given index.
     */
    public Comparable getValue(int index) {
        if (index > this.size) {
            return null;
        } else {
            return this.contents[index];
        }
    }

    /**
     * Inserts an integer into this node at the right (sorted) position.
     *
     * @param comp Comparable to be added
     */
    public void insertValue(Comparable comp) {
        if (full() || comp == null) //Failsafes
        {
            return;
        }
        int index = size;
        while (index > 0) { //Find the first values in node
            while (index > 0 && contents[index - 1] == null) {
                index--;
            }
            if (index == 0) //We're at the last index, integer must go into this spot (because it's the smallest number)
            {
                break;
            }
            if (contents[index - 1].compareTo(comp) > 0) //Upcomming integer is bigger?
            {
                contents[index] = contents[index - 1]; //Shift it to the right
            } else {
                contents[index] = comp;
                return;
            }
            index--; //Next position
        }
        contents[index] = comp;
    }

    /**
     * Inserts an integer into this node at the right (sorted) position.
     *
     * @param comp Comparable to be added
     */
    public void deleteValue(Comparable comp) {
        int index = size;
        while (index > 0) { //Find the first values in node
            while (index > 0 && contents[index - 1] == null) {
                index--;
            }
            if (index == 0) //We're at the last index, integer must go into this spot (because it's the smallest number)
            {
                contents[index] = null;
            }
            if (contents[index - 1].compareTo(comp) > 0) //Upcomming integer is bigger?
            {
                contents[index] = contents[index - 1]; //Shift it to the right
            } else {
                contents[index] = comp;
                return;
            }
            index--; //Next position
        }
        contents[index] = comp;
    }

    /**
     * Setter-method
     *
     * @param index Index which has to be set.
     * @param comp Comparable which has to be inserted.
     */
    public void setValue(int index, Comparable comp) {
        if (index > this.size) {
            return;
        }
        contents[index] = comp;
    }

    /**
     * Getter-method
     *
     * @param index Index of the subtrees.
     * @return Subtree at given index.
     */
    public BTreeNode getSubtree(int index) {
        if (index > this.size + 1) {
            return null;
        } else {
            return this.subTrees[index];
        }
    }

    /**
     * Inserts a node into the list of subtrees, right at the end of it
     *
     * @param node Node to be inserted.
     */
    public void insertSubtree(BTreeNode node) {
        if (full()) {
            return;
        }
        int index = 0; //Will insert subtree as soon as it finds the first in the array
        while (index < size + 2 && subTrees[index] != null) {
            index++;
        }
        subTrees[index] = node;
    }

    /**
     * Setter-method.
     *
     * @param index Index to be set.
     * @param node Node to be inserted.
     */
    public void setSubtree(int index, BTreeNode node) {
        if (index > this.size + 1) {
            return;
        } else {
            this.subTrees[index] = node;
        }
    }

    /**
     * @return True, if the list of values is full.
     */
    public boolean full() {
        for (Comparable comp : contents) {
            if (comp == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return True, if the list of values is empty.
     */
    public boolean empty() {
        return (contents[0] == null);
    }

    /**
     * @return True, if there are any subtrees inside.
     */
    public boolean hasSubtrees() {
        for (BTreeNode node : subTrees) {
            if (node != null) {
                return true;
            }
        }
        return false;
    }

    /**
     * Shifts values by one index +1
     *
     * @param index Index which is affected by the shift (and it's follow-ups)
     */
    public void shiftContents(int index) {
        for (int i = size - 1; i >= index; i--) {
            contents[i + 1] = contents[i];
        }
        contents[index] = null;
    }

    /**
     * Shifts trees by one index +1
     *
     * @param index Index which is affectd by the shift (and it's follow-ups)
     */
    public void shiftTrees(int index) {
        for (int i = size; i >= index; i--) {
            subTrees[i + 1] = subTrees[i];
        }
        subTrees[index] = null;
    }

    /**
     * Clears all attributes of the node.
     */
    public void clear() {
        contents = new Comparable[size + 1];
        subTrees = new BTreeNode[size + 2];
    }
    
    /**
     * Searchs for key
     * @param key Comparable Object to find
     * @return boolean true if found
     */
    public boolean hasKey(Comparable key) {
        for (int i = 0; i < size; i++)
            if (contents[i] != null && contents[i].compareTo(key) == 0)
                    return true;
        return false;
    }
    
    /**
     * Searchs for key
     * @param key Comparable Object to find
     * @return integer Index of Key, -1 not found
     */
    public int getIndexOfKey(Comparable key) {
        for (int i = 0; i < size; i++)
            if (contents[i] != null && contents[i].compareTo(key) == 0)
                    return i;
        return -1;
    }
    
    /**
     * Get the amount of keys in this node
     * @return 
     */
    public int countKeys() {
        int i = 0;
        for (Comparable comp : contents) {
            if (comp != null) {
                i++;
            }
        }
        return i;
    }
    
    /**
     * Returns last Key and delets it
     * @return Comparable key
     */
    public Comparable popLastKey()
    {
        int i = 0;
        while (contents[i] != null)
        {
            i++;
        }
        
        i--;
        
        Comparable key = contents[i];
        contents[i] = null;
        
        return key;
    }

}
