package de.hs_mannheim.ss15.tpe.group_2_4.uebung04.aufgabe03;

public class QuickSortThread extends Thread{
	
	Comparable[] array;
	int bottom, top;
	QuickSortThread partner;
	
	QuickSortThread(Comparable[] array, int bottom, int top, QuickSortThread partner) {
		this.array = array;
		this.bottom = bottom;
		this.top = top;
		this.partner = partner;
		start();
	}
	
	public void run() {
//		try {
//			System.out.println("sleep");
//			sleep((int)Math.random()*10000);
//		} catch(InterruptedException e) {
//			throw new RuntimeException(e);
//		}
		
		if(bottom < top) { //Only do the algorithm if part of array is not only one element
			int i = split(array, bottom, top);

			if(partner != null) {
				try {
					partner.join(); //Join partner
				} catch(InterruptedException e) {
					throw new RuntimeException(e);
				}
				
				if(true) { //Protocol
					String output = getName() + " has joined " + partner.getName() + ": ";
					for(int k = 0; k < array.length; k++) {
						if(bottom <= k && k <= top)
							output = output + "[" + array[k] + "] ";
						else
							output = output + array[k] + " ";
					}
					System.out.println(output);
				}
			}
			//Creates two new threads, each with their own part of the array
			QuickSortThread child = new QuickSortThread(array, bottom, i-1, new QuickSortThread(array, i+1, top, null));
			try {
				child.join(); //Join child
				//System.out.println("Child joined: " + getName());
			} catch(InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	/**
	 * Takes a pivot element and puts it into the right place, thus dividing the array into the left side of the pivot element and the right side of the element.
	 * Every element <= pivot is on the left side, every element > pivot is on the right side.
	 * @param array The array which has to be accessed.
	 * @param bottom Starting index of the run.
	 * @param top Last index of the run.
	 * @return Index of the pivot element.
	 */
	public static int split(Comparable[] array, int bottom, int top) {
		
		int index = bottom;
		int pivot = top;
		
		for(int m = bottom; m < top; m++) {
			if(array[m].compareTo(array[pivot]) <= 0) {
				swap(array, m, index);	//swap the current element smaller than pivot with index and increment index afterwards
				index++;
			}
		}
		
		
		swap(array, index, pivot);
		
		return index;
	}
	
	/**
	 * Swaps two elements in an array.
	 * @param array The array which has to be accessed. 
	 * @param a Index of the first element.
	 * @param b Index of the second element.
	 */
	public static void swap(Comparable[] array, int a, int b) {
		
		Comparable m = array[a];
		array[a] = array[b];
		array[b] = m;
	}

}
