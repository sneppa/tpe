package de.mami.uebung01;

public interface BTree {

	/**
	 * Inserts an object into the tree.
	 * @param integer Given object (integer). 
	 * @return true when successful.
	 */
	boolean insert(Integer integer);
	
	/**
	 * Inserts all objects extracted from file.
	 * @param filename Directory with \workspace as root. 
	 * @return true when successful.
	 */
	boolean insert(String filename);
	
	/**
	 * Returns whether the tree contains given object.
	 * @param o Given object (integer).
	 * @return true if contained by tree.
	 */
	boolean contains(Integer integer);
	
	/**
	 * @return Returns the amount of objects contained by the tree.
	 */
	int size();
	
	/**
	 * @return Returns the height of the tree (root included).
	 */
	int height();
	
	/**
	 * @return Returns object with biggest comparable value.
	 */
	Integer getMax();
	
	/**
	 * @return Returns object with lowest comparable value.
	 */
	Integer getMin();
	
	/**
	 * @return true when size == 0
	 */
	boolean isEmpty();
	
	/**
	 * Fuses another BTree into this tree.
	 * @param otherTree Given other tree.
	 * @return this tree (for chaining purposes)
	 */
	BTree addAll(BTree otherTree);
	
	/**
	 * Creates a deep clone of this tree.
	 * @return new other tree with contents of same value but diffrent reference.
	 */
	BTree deepClone();
	
	/**
	 * Prints contents of tree in regular order (smallest to largest).
	 */
	void printInorder();
	
	/**
	 * Prints contents in postorder.
	 */
	void printPostorder();
	
	/**
	 * Prints contents in preorder.
	 */
	void printPreorder();
	
	/**
	 * Prints contents horizontally, one level after another.
	 */
	void printLevelorder();
}
