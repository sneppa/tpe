package de.hs_mannheim.ss15.tpe.group_2_4.uebung04.aufgabe03;

import static org.junit.Assert.*;

import org.junit.Test;

public class QuickSort3Test {

	@Test
	public void testArray() {
		Comparable[] array = {51,23,1,0,23,7,8,5,14,17,8,3,7,7,2,1,76,27,457,74,82,534,1,4,92,671,235,14,6134,613,635,845,734};
		new QuickSort3().sort(array);
		String output = "";
		for(int i = 0; i < array.length; i++) {
			output = output + array[i] + " ";
		}
		assertEquals("0 1 1 1 2 3 4 5 7 7 7 8 8 14 14 17 23 23 27 51 74 76 82 92 235 457 534 613 635 671 734 845 6134 ", output);
	}

}
