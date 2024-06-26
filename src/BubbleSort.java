/**UMGC CMSC 451
 * Description: Bubble sort algorithm implementation. 
 * @author Ty Marino
 * Date: August 13th, 2024
 * Java 11
 */
public class BubbleSort extends AbstractSort {
    
    
    public BubbleSort(int counter, long time) {
        super(counter, time);
    }

    @Override
    void sort(int[] arr) {
        // Java program for implementation 
        // of Bubble Sort 
        startSort();
		int n = arr.length; 
		for (int i = 0; i < n - 1; i++) 
			for (int j = 0; j < n - i - 1; j++) 
				if (arr[j] > arr[j + 1]) { 
					// swap temp and arr[i] 
					int temp = arr[j]; 
					arr[j] = arr[j + 1]; 
					arr[j + 1] = temp; 
                    incrementCount();
				} 
        endSort();

    }

}
    
