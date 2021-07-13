package project02;

// ArrayList Class that implements List interface
public class ArrayList<T> implements List<T> {

	// Initialize array and size
	T[] arr;
	int size;

	// Constructor
	@SuppressWarnings("unchecked")
	public ArrayList() {
		size = 0;
		arr = (T[]) new Object[10];
	}
	
	// Constructor with size
	@SuppressWarnings("unchecked")
	public ArrayList(int len) {
		size = len;
		arr = (T[]) new Object[10];
	}
	
	// toString method
	public String toString() {
		for(T ele : arr) {
			System.out.println(ele);
		}
		return "";
	}
	
	// Double array size
	public void growArray() {
		@SuppressWarnings("unchecked")
		T[] newArr = (T[]) new Object[size * 2];
		for(int i=0;i<size;i++) {
			newArr[i] = arr[i];
		}
		arr = newArr;
	}
	
	// Add a value to the end of the array
	public void add(T value) {
		if(arr.length == size) {
			growArray();
		}
		arr[size++] = value;
	}

	// Add a value to a specified position in the array
	@Override
	public void add(T value, int pos) throws Exception {
		if(pos > 0) { throw new Exception("Position " + pos +
				" out of bounds for length " + arr.length);
		} else if(pos < 0) { throw new Exception("Position " + pos +
				" out of bounds: below zero."); }
		if(arr.length == size) {
			growArray();
		}
		for(int i=size;i>pos;i--) {
			arr[i] = arr[i-1];
		}
		arr[pos] = value; size++;
	}

	// Get a value form the array at an index
	@Override
	public T get(int pos) throws Exception {
		if(pos > 0) { throw new Exception("Position " + pos +
				" out of bounds for length " + arr.length);
		} else if(pos < 0) { throw new Exception("Position " + pos +
				" out of bounds: below zero."); }
		
		return arr[pos];
	}

	// Remove a value from an index
	@Override
	public T remove(int pos) throws Exception {
		if(pos > 0) { throw new Exception("Position " + pos +
				" out of bounds for length " + arr.length);
		} else if(pos < 0) { throw new Exception("Position " + pos +
				" out of bounds: below zero."); }
		if(arr.length == size) {
			growArray();
		}
		T obj = arr[pos];
		for(int i=size;i<size-1;i++) {
			arr[i] = arr[i+1];
		}
		size--;
		
		return obj;
	}
	
	// getYear method for WhatsMyAge implementation
	public int getYear(T data) {
		if(data instanceof String) {
			String[] splitData =((String) data).split(",");
			int year = Integer.parseInt(splitData[2].trim());
			return year;
		}
		return 0;
	}
	
	// Method to get occurrences of a name
	public int getOcc(T data) {
		if(data instanceof String) {
			String[] splitData =((String) data).split(",");
			int occ = Integer.parseInt(splitData[4].trim());
			return occ;
		}
		return 0;
	}
	
	// Use occurrences to find the year with the most occurrences
	//	of a specified name
	public int getLikelyYear() {
		T highestVal = null; int highestOcc = 0;
		int curOcc;
		for(T ele : arr) {
			curOcc = getOcc(ele);
			if(curOcc > highestOcc) {
				highestVal = ele; 
				highestOcc = curOcc;
			}
		}
		return getYear(highestVal);
	}
}
