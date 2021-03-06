package de.hs_mannheim.ss15.tpe.group_2_4.uebung01;

import static gdi.MakeItSimple.*;

public class BTreeClass implements BTree {

    private int nodeSize = 0;
    private int order = 0;
    private BTreeNode root = null;

    public BTreeClass(int order) {
        if (order < 1)
            throw new GDIException("Order must greater than 1");
        this.nodeSize = order * 2;
        this.order = order;
    }

    @Override
    public boolean insert(Integer integer) {
        if (contains(integer)) {
            return false;
        }
        
        if (isEmpty()) {
            root = new BTreeNode(nodeSize);
            root.insertValue(integer);
            return true;
        }

        return insert(integer, root);
    }

    private boolean insert(Integer integer, BTreeNode node) {
        if (!node.hasSubtrees()) { //No subtrees, then insert integer into this leaf
            node.insertValue(integer);
        } else { //Otherwise look into which subtree to go now
            int index = node.size;
            boolean found = false; //Break condition for while-loop
            while (index >= 0 && !found) {
                while (index > 0 && node.getValue(index) == null && !found) { //decrease index until you find the first integers
                    index--;
                }
                if (index == 0) { //Special case at index 0
                    if (node.getValue(index) > integer) { //Decide whether to go into tree inside index 0 or 1
                        insert(integer, node.getSubtree(index));
                        found = true;
                    } else {
                        insert(integer, node.getSubtree(index + 1));
                        found = true;
                    }
                } else { //Further decrease if integer not at the right spot
                    if (node.getValue(index) > integer) {
                        index--;
                    } else {
                        insert(integer, node.getSubtree(index + 1));
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
            Integer middle = node.getValue((nodeSize + 1) / 2);
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
                Integer middle = subtree.getValue((subtree.size) / 2);
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

    @Override
    public boolean insert(String filename) {

        if (!isFilePresent(filename) || !isFileReadable(filename)) {
            return false;
        }

        Object input = openInputFile(filename);

        while (!isEndOfInputFile(input)) {
            insert(readInt(input));
        }

        closeInputFile(input);
        return true;
    }

    @Override
    public boolean contains(Integer integer) {
        if (!isEmpty()) {
            return contains(integer, root);
        }
        return false;
    }

    private boolean contains(Integer integer, BTreeNode node) {
        int i = 0;
        //Go through all values in node, iterate if integer bigger than value
        while (integer >= node.getValue(i)) {
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
            throw new GDIException("Tree is emtpy");
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
    public Integer getMax() {
        if (isEmpty()) {
            return null;
        } else {
            return getMax(root);
        }
    }

    private Integer getMax(BTreeNode node) {
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
    public Integer getMin() {
        if (isEmpty()) {
            return null;
        } else {
            return getMin(root);
        }
    }

    private Integer getMin(BTreeNode node) {
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

    @Override
    public BTree addAll(BTree otherTree) {
        addAll(otherTree.getRootNode());
        return this;
    }

    private void addAll(BTreeNode node) {
        for (int i = 0; i < node.subTrees.length; i++) {
            if (node.subTrees[i] != null) {
                addAll(node.subTrees[i]);
            }

            if (i < node.contents.length && node.contents[i] != null) {
                this.insert(node.contents[i]);
            }
        }
    }

    @Override
    public BTree deepClone() {
        BTreeClass returnTree = new BTreeClass(nodeSize);

        if (!isEmpty()) {
            deepClone(returnTree, root);
        }

        return returnTree;
    }

    private void deepClone(BTree cloneTree, BTreeNode node) {
        Integer content;
        for (int i = 0; i < node.subTrees.length; i++) {
            if (node.subTrees[i] != null) {
                deepClone(cloneTree, node.subTrees[i]);
            }

            if (i < node.contents.length && node.contents[i] != null) {
                content = new Integer(node.getValue(i));
                cloneTree.insert(node.contents[i]);
            }
        }
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
        for (int i = 0; i < node.subTrees.length-1; i++)
        {
            if (node.getSubtree(i) != null && i == 0) {
                printPostorder(node.getSubtree(i));
            }
            
            if (node.getSubtree(i+1) != null) {
                printPostorder(node.getSubtree(i+1));
            }
        }

        if (node.getSubtree(0) == null) {
            print(node.getValue(0)+", ");

        } else {
            for (int i = 0; i < node.contents.length; i++)
                if (node.getValue(i) != null)
                    print(node.getValue(i)+", ");
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
