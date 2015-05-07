package de.hs_mannheim.ss15.tpe.group_2_4.uebung02.aufgabe01;
import static gdi.MakeItSimple.*;

public class Main {

	public static void main(String[] args) {
            BTreeClass btree = new BTreeClass(1);
            
//            btree.insert("integers.txt");
            
            btree.insert(1);
            btree.insert(2);
            btree.insert(3);
            btree.insert(4);
            btree.printHierarchy();
            btree.insert(5);
            btree.insert(6);
            btree.insert(6);
            btree.insert(7);
            btree.insert(8);
            btree.insert(9);
            btree.insert(10);
            btree.insert(11);
            btree.insert(12);
            btree.insert(13);
            btree.insert(14);
            btree.insert(15);
            btree.insert(16);
            btree.insert(17);
            btree.insert(18);
            btree.insert(19);
            btree.insert(20);
            btree.insert(21);
            btree.insert(22);
            btree.insert(23);
            btree.insert(24);            
            btree.insert(25);
            btree.printHierarchy();
            btree.insert(-43);
            btree.insert(32);
            btree.insert(22);
            btree.insert(11);
            btree.insert(10);
            btree.insert(12);
            btree.insert(13);
            btree.insert(14);
            btree.insert(15);
            btree.insert(11);
            btree.insert(16);
            btree.insert(17);
            btree.insert(18);
            btree.insert(19);
            btree.insert(20);
            btree.insert(21);
//            println("Contains 11: "+btree.contains(11));
//            btree.insert(11);
            
//            btree.printHierarchy();
            
            btree.delete(2);
//            btree.printHierarchy();
//            btree.delete(3);
//            btree.printHierarchy();
            btree.delete(4);
//                        btree.delete(1);

//            btree.printHierarchy();
//            btree.delete(4);
//            btree.delete(3);
            
            btree.printHierarchy();
//            println("Ausgabe Inorder");
//            btree.printInorder();
//            println("Ausgabe Preorder");
//            btree.printPreorder();
//            println("Ausgabe Postorder");
//            btree.printPostorder();
//            println("Ausgabe Levelorder");
//            btree.printLevelorder();
//            println("Height: "+btree.height());
//            println("getMin: "+btree.getMin());
//            println("getMax: "+btree.getMax());
//            println("Size: "+btree.size());
//            println("Contains: "+btree.contains(10));
//            println("Contains: "+btree.contains(1));
//            
//            
//            println("Neuer Baum erstellen");
//            
//            BTreeClass btree2 = new BTreeClass(1);
//            btree2.insert(-43);
//            btree2.insert(32);
//            btree2.insert(22);
//            btree2.insert(11);
//            
//            btree.printInorder();
            
	}

}
