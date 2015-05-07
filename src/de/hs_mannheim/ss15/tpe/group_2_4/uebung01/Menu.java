package de.hs_mannheim.ss15.tpe.group_2_4.uebung01;
import static gdi.MakeItSimple.*;

public class Menu {

    static BTree btree = null;

    public static void main(String[] args) {

        print("Bitte Ordnung des Baumes eingeben: ");
        int order = readInt();
        readLine();
        btree = new BTreeClass(order);

        while (true) {
            makeMenu();
        }
    }

    public static void makeMenu() {
        int number;
        String file;
        
        println("------------------- Menü -------------------");
        println("1: Zahl hinzufügen");
        println("2: Zahlendatei einlesen");
        println("3: Zahl vorhanden?");
        println("4: Größe des Baums");
        println("5: Höhe des Baums");
        println("6: Größter Eintrag des Baums");
        println("7: Kleinster Eintrag des Baums");
        println("----------------- Ausgaben -----------------");
        println("8: inorder");
        println("9: postorder");
        println("10: preorder");
        println("11: levelorder");

        int selection = readInt();

        switch (selection) {
            case 1:
                    print("Zahl eingeben: ");
                    number = readInt();
                    btree.insert(number);
                break;
            case 2:
                    print("Dateiname: ");
                    file = readLine();
                    btree.insert(file);
                break;
            case 3:
                    print("Zahl eingeben: ");
                    number = readInt();
                    println(btree.contains(number));
                break;
            case 4:
                    println(btree.size());
                break;
            case 5:
                    println(btree.height());
                break;
            case 6:
                    println(btree.getMax());
                break;
            case 7:
                    println(btree.getMin());
                break;
            case 8:
                    btree.printInorder();
                break;
            case 9:
                    btree.printPostorder();
                break;
            case 10:
                    btree.printPreorder();
                break;
            case 11:
                    btree.printLevelorder();
                break;
            default:
                break;
        }

        readLine();

    }
}
