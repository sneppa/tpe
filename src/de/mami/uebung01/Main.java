package de.mami.uebung01;

import static gdi.MakeItSimple.*;

public class Main {

	public static void main(String[] args) {
            BTreeClass btree = new BTreeClass(2);
            for(int i = 10; i >= 0; i--) {
            	btree.insert(i);
            }
            btree.printInorder();
            btree.printPostorder();
            for(int i = 0; i <= 10; i++) {
            	btree.insert(i);
            }
            btree.printInorder();
            btree.insert(-5);
            btree.insert(0);
            btree.insert(0);
            btree.insert(1);
            btree.insert(1);
            btree.insert(1);
            btree.insert(1);
            btree.insert(1);
            btree.printInorder();
        	println("Height: " + btree.height());
        	println("Minimum: " + btree.getMin());
        	println("Maximum: " + btree.getMax());
//        	println(btree.contains(6));
//            
            
            
//            btree.insert(5);
//            btree.insert(2);
//            btree.insert(4);
//            btree.insert(7);
//            btree.insert(1);
//            btree.insert(6);
//            btree.insert(8);
//            btree.printInorder();
//            btree.insert(9);
//            btree.printInorder();
//            btree.insert(10);
//            btree.printInorder();
//            btree.insert(11);
//            btree.printInorder();
//            btree.insert(12);
//            btree.printInorder();
//            btree.insert(13);
//            btree.printInorder();
//            btree.insert(0);
//            btree.printInorder();
//            btree.insert(-1);
//            btree.printInorder();
//            btree.insert(-2);
//            btree.printInorder();
//            btree.insert(-3);
//            btree.printInorder();
//            btree.insert(-4);
//            btree.printInorder();
//            btree.insert(-5);
//            btree.printInorder();
//            btree.insert(14);
//            btree.printInorder();
//            btree.insert(15);
//            btree.printInorder();

            //btree.printInorder();
	}

}
