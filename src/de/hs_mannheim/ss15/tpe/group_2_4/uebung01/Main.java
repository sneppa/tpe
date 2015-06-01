package de.hs_mannheim.ss15.tpe.group_2_4.uebung01;
import static gdi.MakeItSimple.*;

public class Main {

	public static void main(String[] args) {
            BTreeClass btree = new BTreeClass(1);
            
//            btree.insert("integers.txt");
            
            btree.insert(9);
            btree.insert(8);
            btree.insert(7);
            btree.insert(6);
            btree.insert(5);
            btree.insert(4);
            btree.insert(3);
//            btree.insert(-43);
//            btree.insert(32);
//            btree.insert(22);
//            btree.insert(11);
//            btree.insert(10);
//            btree.insert(12);
//            btree.insert(13);
//            btree.insert(14);
//            btree.insert(15);
//            btree.insert(11);
//            btree.insert(16);
//            btree.insert(17);
//            btree.insert(18);
//            btree.insert(19);
//            btree.insert(20);
//            btree.insert(21);
//            println("Contains 11: "+btree.contains(11));
//            btree.insert(11);
            
            println("Ausgabe mit Hierarchie");
            btree.printHierarchy();
            println("Ausgabe Inorder");
            btree.printInorder();
            println("Ausgabe Preorder");
            btree.printPreorder();
            println("Ausgabe Postorder");
            btree.printPostorder();
            println("Ausgabe Levelorder");
            btree.printLevelorder();
            println("Height: "+btree.height());
            println("getMin: "+btree.getMin());
            println("getMax: "+btree.getMax());
            println("Size: "+btree.size());
            println("Contains: "+btree.contains(10));
            println("Contains: "+btree.contains(1));
            
            BTree cloned = btree.deepClone();
            println("Geklont");
            cloned.printInorder();
//            btree.printPostorder();
//            btree.printLevelorder();
            
            
            println("Neuer Baum erstellen");
            
            BTreeClass btree2 = new BTreeClass(1);
            btree2.insert(-43);
            btree2.insert(32);
            btree2.insert(22);
            btree2.insert(11);
            
            btree.addAll(btree2);
            btree.printInorder();
            
	}

}
