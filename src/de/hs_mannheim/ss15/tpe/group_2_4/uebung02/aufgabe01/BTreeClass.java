package de.hs_mannheim.ss15.tpe.group_2_4.uebung02.aufgabe01;

import static gdi.MakeItSimple.*;

public class BTreeClass implements BTree {

    private int nodeSize = 0;
    private int order = 0;
    private BTreeNode root = null;

    public BTreeClass(int order) {
        if (order < 1) {
            throw new GDIException("Order must greater than 1");
        }
        this.nodeSize = order * 2;
        this.order = order;
    }

    @Override
    public boolean insert(Comparable comp) {
        if (contains(comp)) {
            return false;
        }

        if (isEmpty()) {
            root = new BTreeNode(nodeSize);
            root.insertValue(comp);
            return true;
        }

        return insert(comp, root);
    }

    private boolean insert(Comparable comp, BTreeNode node) {
        if (!node.hasSubtrees()) { //No subtrees, then insert integer into this leaf
            node.insertValue(comp);
        } else { //Otherwise look into which subtree to go now
            int index = node.size;
            boolean found = false; //Break condition for while-loop
            while (index >= 0 && !found) {
                while (index > 0 && node.getValue(index) == null && !found) { //decrease index until you find the first integers
                    index--;
                }
                if (index == 0) { //Special case at index 0
                    if (node.getValue(index).compareTo(comp) > 0) { //Decide whether to go into tree inside index 0 or 1
                        insert(comp, node.getSubtree(index));
                        found = true;
                    } else {
                        insert(comp, node.getSubtree(index + 1));
                        found = true;
                    }
                } else { //Further decrease if integer not at the right spot
                    if (node.getValue(index).compareTo(comp) > 0) {
                        index--;
                    } else {
                        insert(comp, node.getSubtree(index + 1));
                        found = true;
                    }
                }
            }
        }
        if (node.full()) { //splitCheck whole tree if node is full after insertion
            return splitCheck(root);
        }
        return true;
    }

    private boolean splitCheck(BTreeNode node) {
        if (node == root && node.full()) { //Special case: root
            BTreeNode left = new BTreeNode(nodeSize);
            BTreeNode right = new BTreeNode(nodeSize);
            for (int i = 0; i < nodeSize + 1; i++) { //Creates two new nodes which will contain everything from the former root except middle value
                if (i < (nodeSize) / 2) {
                    left.insertValue(node.getValue(i));
                    left.insertSubtree(node.getSubtree(i));
                }
                if (i > (nodeSize) / 2) {
                    right.insertValue(node.getValue(i));
                    right.insertSubtree(node.getSubtree(i));
                }
            }
            //Don't forget the extra subtrees!
            left.insertSubtree(node.getSubtree((nodeSize + 1) / 2));
            right.insertSubtree(node.getSubtree(nodeSize + 1));
            Comparable middle = node.getValue((nodeSize + 1) / 2);
            node.clear(); //root stays the same object
            node.insertValue(middle);
            node.setSubtree(0, left);
            node.setSubtree(1, right);
            return splitCheck(root); //Continue check from scratch
        }
        for (int index = 0; index < node.size + 1; index++) { //Go through every subtree from the POV of the parent, so we keep the reference to it
            BTreeNode subtree = node.getSubtree(index);
            if (subtree != null && subtree.full()) {
                BTreeNode add = new BTreeNode(nodeSize); //One additional tree, former subtree is the "left" tree, add is the "right" tree
                node.shiftContents(index); //Shift to make space for pulled up value
                node.shiftTrees(index);
                Comparable middle = subtree.getValue((subtree.size) / 2);
                subtree.setValue((subtree.size) / 2, null);
                int i = ((node.size) / 2);
                while (i <= subtree.size) { //Carry half of the values from subtree to add
                    add.insertValue(subtree.getValue(i));
                    add.insertSubtree(subtree.getSubtree(i + 1));
                    subtree.setSubtree(i + 1, null);
                    subtree.setValue(i, null);
                    i++;
                }
                //Again, think about the last subtree to be carried over
                add.insertSubtree(subtree.getSubtree(i + 2));
                subtree.setSubtree(i + 2, null);
                node.setValue(index, middle); //Pull up new middle value and set the subtrees
                node.setSubtree(index, subtree);
                node.setSubtree(index + 1, add);
                return splitCheck(root); //Again, check from scratch
            } else if (subtree != null && subtree.hasSubtrees()) {

                splitCheck(subtree); //Check deeper
            }
        }
        return true; //Finish
    }


    /**
     * Delete an element of the tree
     * @param comp Comparable object
     * @return true/false
     */
    @Override
    public boolean delete(Comparable comp) {
        if (!contains(comp)) {
            return false;
        }

//        println("Delete: " + comp);
        if (!isEmpty()) {
            delete(root, comp);
            return true;
        }

        return false;
    }

    private void delete(BTreeNode node, Comparable key) {
        if (!node.hasSubtrees()) { // Is a leave, delete key from Leave, if it exists.
//            println("Leaf");
            boolean found = false;
            for (int i = 0; i <= nodeSize; i++) {
                if (node.contents[i] != null && node.contents[i].compareTo(key) == 0) { // key found
                    found = true;
                } else if (found) { // shift all keys one to left to overwrite the deleted key
                    node.contents[i - 1] = node.contents[i];
                }
            }
            if (found) // reset last double key
            {
                node.contents[nodeSize] = null;
            }

            if (node.countKeys() < order) { // underflow -> Check merging
//                print("Leaf deleted: "); printHierarchy();
                mergeCheck(root);
            }
        } else if (node.hasKey(key)) { // Key is an internal node
//            println("Internal");
            int cIndex = node.getIndexOfKey(key);

            BTreeNode left = node.getSubtree(cIndex);
            BTreeNode prev = left;
            BTreeNode delete = left;

            while (prev.hasSubtrees()) // Get the next smaller number
            {
                delete = prev;
                prev = prev.subTrees[prev.countKeys()];
            }

            Comparable newKey = prev.contents[prev.countKeys() - 1]; // Set found number as new key
            node.contents[cIndex] = newKey;
            delete(delete, newKey);

            if (node.countKeys() < order) { // underflow -> Check merging
//                print("Internal deleted: "); printHierarchy();
                mergeCheck(root);
            }
        } else {// Key not found in this node
//            println("No");
            int cIndex = 0;

            // get index of Subtree which contains the key
            while (node.contents[cIndex] != null && node.contents[cIndex].compareTo(key) < 0) {
                cIndex++;
            }

            BTreeNode subtree = node.getSubtree(cIndex);
            delete(subtree, key);
        }
    }

    /**
     * Check node if there is an underflow and merge it
     * @param node to check
     */
    private void mergeCheck(BTreeNode node) {
//        println("check");
        for (int i = 0; i < node.subTrees.length; i++) {
            if (node.subTrees[i] != null) {
                BTreeNode left = node.subTrees[i] != null ? node.subTrees[i] : null;
                BTreeNode right = node.subTrees[i + 1] != null ? node.subTrees[i + 1] : null;

                if (left != null && left.countKeys() < order) {// Compare left with right (Possible if item deleted in 0-order*2-1)
//                    println("Left node to small");
                    if (right.countKeys() > order) {// right node has more nodes than order size -> compensation
//                        println("compensation");
                        left.contents[left.countKeys()] = node.contents[i];
                        node.contents[i] = right.contents[0];

                        for (int x = 0; x < right.countKeys(); x++) {
                            right.contents[x] = right.contents[x + 1];
                            right.subTrees[x] = right.subTrees[x + 1];
                        }
//                        print("compensated: "); printHierarchy();
                    } else { // right node has not enough nodes -> merge
//                        println("merge");
                        left.contents[left.countKeys()] = node.contents[i];
                        mergeNodes(left, right);
//                        print("not fully merged: "); printHierarchy();

                        for (int x = i + 1; x < node.contents.length; x++) {
//                            println(x);
                            node.contents[x - 1] = node.contents[x];
                            node.subTrees[x] = node.subTrees[x + 1];
                        }
//                        print("merged: "); printHierarchy();
                    }
                    mergeCheck(root);
                    return;
                } else if (right != null && right.countKeys() < order) { // Compare right with left (Possible if item deleted in the last node)

//                    println("Right node to small");
                    if (left.countKeys() > order) {// left node has more nodes than order size -> compensation
//                        println("compensation");
                        for (int x = 0; x < right.countKeys(); x++) {
                            right.contents[x] = right.contents[x + 1];
                            right.subTrees[x] = right.subTrees[x + 1];
                        }
                        right.contents[0] = node.contents[i];
                        node.contents[i] = left.contents[left.countKeys() - 1];
                        left.contents[left.countKeys() - 1] = null;
//                        print("compensated: "); printHierarchy();

                    } else { // left node has not enough nodes -> merge
//                        println("merge");
                        left.contents[left.countKeys()] = node.contents[i];
                        mergeNodes(left, right);
                        for (int x = i + 1; x < node.contents.length; x++) {
//                            println(x);
                            node.contents[x - 1] = node.contents[x];
                            node.subTrees[x] = node.subTrees[x + 1];
                        }
//                        print("merged: "); printHierarchy();
                    }
                    mergeCheck(root);
                    return;
                } else if (i == 0 && left != null && right == null) { // Only first node full -> left node up
                    println("up");
                    node.contents = left.contents;
                    node.subTrees = left.subTrees;
                    mergeCheck(root);
                    return;
                } else if (left != null) {
                    mergeCheck(left);
                }
            }
        }
    }

    /**
     * Merge two nodes into the left node
     *
     * @param left Node
     * @param right Node
     * @return center/middle ID
     */
    public int mergeNodes(BTreeNode left, BTreeNode right) {
        int plus = left.countKeys();
        int middle = plus;

        // Shift the left content + size of right, if right values are smaller than left
        if (right.contents[0] != null && right.contents[0].compareTo(left.contents[left.countKeys() - 1]) < 0) {
            int rplus = right.countKeys();
            middle = rplus;
            plus = 0;

            for (int i = 0; i < rplus; i++) {
                left.contents[i + rplus] = left.contents[i];
            }
        }

        // Insert right contents to left node
        for (int i = 0; i < right.countKeys(); i++) {
            left.contents[i + plus] = right.contents[i];
            if (right.hasSubtrees()) {
                left.subTrees[i + plus] = right.subTrees[i];
            }
        }
        if (right.hasSubtrees()) { // Don't forget the last subtree on the right
            left.subTrees[right.countKeys() + plus] = right.subTrees[right.countKeys()];
        }

        return middle;
    }

    @Override
    public boolean contains(Comparable integer) {
        if (!isEmpty()) {
            return contains(integer, root);
        }
        return false;
    }

    private boolean contains(Comparable integer, BTreeNode node) {
        int i = 0;
        //Go through all values in node, iterate if integer bigger than value
        while (integer.compareTo(node.getValue(i)) >= 0) {
            if (node.getValue(i) == integer) {
                return true;
            }
            i++;
            if (node.getValue(i) == null) { //If at end of contents and no subtree available -> failure
                if (node.getSubtree(i) != null) {
                    return contains(integer, node.getSubtree(i)); //Else check inside subtree
                } else {
                    return false;
                }
            }
        }
        if (node.getSubtree(i) == null) //Same procedure as right above but in the case of a smaller integer
        {
            return false;
        } else {
            return contains(integer, node.getSubtree(i));
        }
    }

    @Override
    public int size() {
        if (!isEmpty()) {
            return size(root);
        } else {
            return 0;
        }
    }

    private int size(BTreeNode node) {
        Integer count = 0, i = 0;

        while (i < node.contents.length && node.contents[i] != null) {
            i++;
        }
        count += i;

        for (i = 0; i < node.subTrees.length; i++) {
            if (node.subTrees[i] != null) {
                count += size(node.subTrees[i]);
            }
        }

        return count;
    }

    @Override
    public int height() {
        if (isEmpty()) {
            return 0;
        } else {
            return height(root);
        }
    }

    private int height(BTreeNode node) {
        if (node.hasSubtrees()) {
            return 1 + height(node.getSubtree(0));
        } else {
            return 1;
        }
    }

    @Override
    public Comparable getMax() {
        if (isEmpty()) {
            return null;
        } else {
            return getMax(root);
        }
    }

    private Comparable getMax(BTreeNode node) {
        if (node.hasSubtrees()) {
            int i = nodeSize;
            while (node.getSubtree(i) == null) {
                i--;
            }
            return getMax(node.getSubtree(i));
        } else {
            int i = nodeSize;
            while (node.getValue(i) == null) {
                i--;
            }
            return node.getValue(i);
        }
    }

    @Override
    public Comparable getMin() {
        if (isEmpty()) {
            return null;
        } else {
            return getMin(root);
        }
    }

    private Comparable getMin(BTreeNode node) {
        if (node.hasSubtrees()) {
            return getMin(node.getSubtree(0));
        } else {
            return node.getValue(0);
        }
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Print Tree inorder with hierarchy
     */
    public void printHierarchy() {
        if (isEmpty()) {
            print("Is Empty.");
        } else {
            printHierarchy(root);
            println();
        }
    }

    private void printHierarchy(BTreeNode node) {
        for (int i = 0; i < node.subTrees.length; i++) {
            if (node.subTrees[i] != null) {
                print("[");
                printHierarchy(node.subTrees[i]);
                print("]");
            }

            if (i < this.order * 2 && node.contents[i] != null) {
                print(node.contents[i] + ", ");
            }
        }
    }

    @Override
    public void printInorder() {
        if (isEmpty()) {
            print("Is Empty.");
        } else {
            printInorder(root);
            println();
        }
    }

    private void printInorder(BTreeNode node) {
        for (int i = 0; i < nodeSize + 1; i++) {
            if (node.getSubtree(i) != null) {
                printInorder(node.getSubtree(i));
            }
            if (node.getValue(i) != null) {
                print(node.getValue(i) + ", ");
            }
        }
    }

    @Override
    public void printPostorder() {
        if (isEmpty()) {
            print("Is Empty.");
        } else {
            printPostorder(root);
            println();
        }
    }

    private void printPostorder(BTreeNode node) {
        for (int i = 0; i < node.subTrees.length - 1; i++) {
            if (node.getSubtree(i) != null && i == 0) {
                printPostorder(node.getSubtree(i));
            }

            if (node.getSubtree(i + 1) != null) {
                printPostorder(node.getSubtree(i + 1));
            }
        }

        if (node.getSubtree(0) == null) {
            print(node.getValue(0) + ", ");

        } else {
            for (int i = 0; i < node.contents.length; i++) {
                if (node.getValue(i) != null) {
                    print(node.getValue(i) + ", ");
                }
            }
        }
    }

    @Override
    public void printPreorder() {
        if (isEmpty()) {
            print("Is Empty.");
        } else {
            printPreorder(root);
            println();
        }
    }

    private void printPreorder(BTreeNode node) {
        for (int i = 0; i < nodeSize + 1; i++) {
            if (node.getValue(i) != null) {
                print(node.getValue(i) + ", ");
            }
            if (node.getSubtree(i) != null) {
                printPreorder(node.getSubtree(i));
            }
        }
    }

    @Override
    public void printLevelorder() {
        if (!isEmpty()) {
            String[] levels = new String[height()];
            levels = printLevelorder(root, levels, 0);
            for (String level : levels) {
                print(level);
            }
        }
        println();
    }

    private String[] printLevelorder(BTreeNode node, String[] levels, Integer level) {
        if (levels[level] == null) {
            levels[level] = "";
        }

        for (int i = 0; i < order * 2 + 1; i++) {
            if (node.subTrees[i] != null) {
                levels = printLevelorder(node.subTrees[i], levels, level + 1);
            }
        }

        for (int i = 0; i < order * 2; i++) {
            if (node.contents[i] != null) {
                levels[level] += node.contents[i] + ", ";
            }
        }

        return levels;
    }

    @Override
    public BTreeNode getRootNode() {
        return root;
    }

}
