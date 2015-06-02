package de.hs_mannheim.ss15.tpe.group_2_4.uebung04.aufgabe03;

/**
 * QuickSort3 implements the third variant of the QuickSort algorithm presented in the lecture and counts the amount of comparisons between elements of the array and the amount of swap operations. Now with threads!
 * @author Mike Hukiewitz & Martin Weber
 *
 */
public class QuickSort3 implements SortAlgorithm{
    
	public static void main(String[] args) {
		
		Comparable[] array = {51,23,1,0,23,7,8,5,14,17,8,3,7,7,2,1,76,27,457,74,82,534,1,4,92,671,235,14,6134,613,635,845,734};
		printProtocol(array);
	}
	
	
	/**
	 * Prints the sorting protocol for the given array.
	 * @param array Array which has to be protocolled.
	 */
	public static void printProtocol(Comparable[] array) {
		
		SortAlgorithm quick = new QuickSort3();
		String output = "";
		for(int i = 0; i < array.length; i++) {
			output = output + array[i] + " ";
		}
		System.out.println(output);

		quick.sort(array);
		
		output = "";
		for(int i = 0; i < array.length; i++) {
			output = output + array[i] + " ";
		}
		System.out.println(output);
	}
	
	
	
	/**
	 * Implementation of the QuickSort algorithm, can sort parts of an array or the entirety of it.
	 * @param array The array which has to be sorted.
	 * @param bottom Starting index of the run.
	 * @param top Last index of the run.
	 */
	public void sort(Comparable[] array, int bottom, int top) {
		
		QuickSortThread child = new QuickSortThread(array, bottom, top, null);
		try {
			child.join(); //Join child
			System.out.println("Finish");
		} catch(InterruptedException e) {
			throw new RuntimeException(e);
		}
	}


	@Override
	public void sort(Comparable[] array) {
		
		sort(array, 0,array.length - 1);
	}

}
