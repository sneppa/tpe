package de.mami.uebung01;

public class BTreeNode {
	int size;
	Integer[] contents;
	BTreeNode[] subTrees;
	
	public BTreeNode(int size) {
		this.size = size;
		this.contents = new Integer[size+1]; //+1 because of overflow object (for split)
		this.subTrees = new BTreeNode[size+2]; //+2 because of 2m+1 +1 overflow tree
		for(int i = 0; i < contents.length; i++)
			contents[i] = null;
	}
	
	public Integer getValue(int index) {
		if(index > this.size)
			return 0;
		else
			return this.contents[index];
	}

	public void insertValue(Integer integer) {
		if(full() || integer == null)
			return;
		int index = size;
		while(index > 0) {
			while(index > 0 && contents[index-1] == null)
				index--;
			if(index == 0)
				break;
			if(contents[index-1] > integer)
				contents[index] = contents[index-1];
			else {
				contents[index] = integer;
				return;
			}
			index--;
		}
		contents[index] = integer;
	}
	
	public void setValue(int index, Integer integer) {
		if(index > this.size)
			return;
		contents[index] = integer;
	}

	public BTreeNode getSubtree(int index) {
		if(index > this.size+1)
			return null;
		else
			return this.subTrees[index];
	}
	
	public void insertSubtree(BTreeNode node) {
		if(full())
			return;
		int index = 0;
		while(index < size+2 && subTrees[index] != null)
			index++;
		subTrees[index] = node;
	}
	public void setSubtree(int index, BTreeNode node) {
		if(index > this.size+1)
			return;
		else
			this.subTrees[index] = node;
	}
	
	public boolean full() {
		for(Integer integer : contents)
			if(integer == null)
				return false;
		return true;
	}
	
	public boolean hasSubtrees() {
		for(BTreeNode node : subTrees)
			if(node != null)
				return true;
		return false;
	}
	
	public void shiftContents(int index) {
		for(int i = size-1; i >= index; i--) {
			contents[i+1] = contents[i];
		}
		contents[index] = null;
	}
	
	public void shiftTrees(int index) {
		for(int i = size; i >= index; i--) {
			subTrees[i+1] = subTrees[i];
		}
		subTrees[index] = null;
	}
	
	public void clear() {
		contents = new Integer[size+1];
		subTrees = new BTreeNode[size+2];
	}

}
