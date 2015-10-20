import java.util.Iterator;


public class lololo implements Iterable{
	
	int[] arr;
	
	lololo() {
		arr = new int[6];
	}

	@Override
	public Iterator iterator() {
		return new Iterator<Integer>(){
			
			int i = 0;
			
			@Override
			public boolean hasNext() {
				if(arr.length < i)
					return false;
				return true;
			}

			@Override
			public Integer next() {
				if(hasNext())
					return arr[i++];
				else
					return null;
			}
			
		};
	}

}
