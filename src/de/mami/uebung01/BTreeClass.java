package de.mami.uebung01;

import static gdi.MakeItSimple.*;

public class BTreeClass implements BTree{
	
	private int nodeSize = 0;
	private BTreeNode root = null;
	
	BTreeClass(int nodeSize){
		this.nodeSize = nodeSize*2;
	}

	@Override
	public boolean insert(Integer integer) {
		if(isEmpty()) {
			root = new BTreeNode(nodeSize);
			root.insertValue(integer);
			return true;
		}
		return insert(integer, root);
	}
	
	private boolean insert(Integer integer, BTreeNode node) {
		if(!node.hasSubtrees()) { //No subtrees, then insert integer into this leaf
			node.insertValue(integer);
		}
		else { //Otherwise look into which subtree to go now
			int index = node.size;
			boolean found = false;
			while(index >= 0 && !found) {
				while(index > 0 && node.getValue(index) == null && !found)
					index--;
				if(index == 0) {
					if(node.getValue(index) > integer) {
						insert(integer, node.getSubtree(index));
						found = true;
					}
					else {
						insert(integer, node.getSubtree(index+1));
						found = true;
					}
				}
				else {
					if(node.getValue(index) > integer)
						index--;
					else {
						insert(integer, node.getSubtree(index+1));
						found = true;
					}
				}
			}
		}
		if(node.full())
			return splitCheck(root);
		return true;
	}
	
	private boolean splitCheck(BTreeNode node) {
		if(node == root && node.full()) {
			BTreeNode left = new BTreeNode(nodeSize);
			BTreeNode right = new BTreeNode(nodeSize);
			for(int i = 0; i < nodeSize+1; i++) {
				if(i < (nodeSize)/2) {
					left.insertValue(node.getValue(i));
					left.insertSubtree(node.getSubtree(i));
				}
				if(i > (nodeSize)/2) {
					right.insertValue(node.getValue(i));
					right.insertSubtree(node.getSubtree(i));
				}
			}
			left.insertSubtree(node.getSubtree((nodeSize+1)/2));
			right.insertSubtree(node.getSubtree(nodeSize+1));
			Integer middle = node.getValue((nodeSize+1)/2);
			node.clear();
			node.insertValue(middle);
			node.setSubtree(0, left);
			node.setSubtree(1, right);
			return splitCheck(root);
		}
		for(int index = 0; index < node.size+1; index++) {
			BTreeNode subtree = node.getSubtree(index);
			if(subtree != null && subtree.full()) {
				BTreeNode add = new BTreeNode(nodeSize);
				node.shiftContents(index); //Shift to make space for pulled up value
				node.shiftTrees(index);
				Integer middle = subtree.getValue((subtree.size)/2);
				subtree.setValue((subtree.size)/2, null);
				int i = ((node.size)/2);
				while(i <= subtree.size){
					add.insertValue(subtree.getValue(i));
					add.insertSubtree(subtree.getSubtree(i+1));
					subtree.setSubtree(i+1, null);
					subtree.setValue(i, null);
					i++;
				}
				add.insertSubtree(subtree.getSubtree(i+2));
				subtree.setSubtree(i+2, null);
				node.setValue(index,middle);
				node.setSubtree(index, subtree);
				node.setSubtree(index+1, add);
				return splitCheck(root);
			}
			else if(subtree != null && subtree.hasSubtrees()) {
				
				splitCheck(subtree);
			}
		}
		return true;
	}

	@Override
	public boolean insert(String filename) {
		
		if (!isFilePresent(filename) || !isFileReadable(filename))
			return false;
		
		Object input = openInputFile(filename);
		
		while(!isEndOfInputFile(input))
			insert(readInt(input));
		
		closeInputFile(input);
		return true;
	}

	@Override
	public boolean contains(Integer integer) {
		if(isEmpty())
			return false;
		else
			return contains(integer, root);
	}
	
//	private boolean contains(Integer integer, BTreeNode node) {
//		int i = 0;
//		while(i >= node.getValue(i)) {
//			if(node.getValue(i) == integer)
//				return true;
//			i++;
//			if(node.getValue(i) == null) {
//				if(node.hasSubtrees())
//					return contains(integer, node.getSubtree(nodeSize));
//				else
//					return false;
//			}
//		}
//		return contains(integer, node.getSubtree(i));
//	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public int height() {
		if(isEmpty())
			return 0;
		else
			return 1 + height(root);
	}
	
	private int height(BTreeNode node) {
		if(node.hasSubtrees())
			return 1 + height(node.getSubtree(0));
		else
			return 1;
	}

	@Override
	public Integer getMax() {
		if(isEmpty())
			return null;
		else
			return getMax(root);
	}
	
	private Integer getMax(BTreeNode node) {
		if(node.hasSubtrees()) {
			int i = nodeSize;
			while(node.getSubtree(i) == null)
				i--;
			return getMax(node.getSubtree(i));
		}
		else {
			int i = nodeSize;
			while(node.getValue(i) == null)
				i--;
			return node.getValue(i);
		}
	}

	@Override
	public Integer getMin() {
		if(isEmpty())
			return null;
		else
			return getMin(root);
	}
	
	private Integer getMin(BTreeNode node) {
		if(node.hasSubtrees())
			return getMin(node.getSubtree(0));
		else
			return node.getValue(0);
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public BTree addAll(BTree otherTree) {
			return this;
	}

	@Override
	public BTree deepClone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void printInorder() {
		if(isEmpty())
			print("Is Empty.");
		else {
			printInorder(root);
			println();
		}
	}
	
	public void printInorder(BTreeNode node) {
		 print("(");
		 for(int i = 0; i < nodeSize+1; i++) {
			 if(node.getSubtree(i) != null)
				 printInorder(node.getSubtree(i));
			 if(node.getValue(i) != null)
				 print(node.getValue(i) + " ");
		 }
		 print(")");
	}

	@Override
	public void printPostorder() {
		if(isEmpty())
			print("Is Empty.");
		else {
			printPostorder(root);
			println();
		}
	}
	
	public void printPostorder(BTreeNode node) {
		 for(int i = 0; i < nodeSize; i++) {
			 if(node.getSubtree(i) != null)
				 printPostorder(node.getSubtree(i));
			 if(node.getValue(i) != null)
				 print(node.getValue(i) + ", ");
		 }
	}

	@Override
	public void printPreorder() {
		if(isEmpty())
			print("Is Empty.");
		else {
			printPreorder(root);
			println();
		}
	}
	
	public void printPreorder(BTreeNode node) {
		 print("(");
		 for(int i = 0; i < nodeSize+1; i++) {
			 if(node.getValue(i) != null)
				 print(node.getValue(i) + " ");
			 if(node.getSubtree(i) != null)
				 printPreorder(node.getSubtree(i));
		 }
		 print(")");
	}

	@Override
	public void printLevelorder() {
		// TODO Auto-generated method stub
		
	}

}
