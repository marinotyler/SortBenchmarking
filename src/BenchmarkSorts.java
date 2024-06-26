/**UMGC CMSC 451
 * Description: Heapsort vs Bubblesort Benchmarking- Creates 12 different random datasets and runs benchmark tests for two sorting algorithms, heap and bubble sort. Each algorithm is averaged over 40 runs and the data is output to a .txt file. 
 * @author Ty Marino
 * Date: August 13th, 2024
 * Java 11
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class BenchmarkSorts {

    public static void main(String[] args) throws Exception {
        File selection = null;
        long time = 0;
        int counter = 0;
        int[] targetArray;
        ArrayList<int[]> datasets;

        datasets =initializeDataSets();
        
        //run benchmarks
        BubbleSort bubble = new BubbleSort(counter, time);
        HeapSort heap = new HeapSort(counter, time);

        //bubble sort benchmark
        System.out.println("BUBBLE SORT BENCHMARK");
        File bubbleFile = createDataFile("bubbleSortBenchmark");
        
        for(int i = 0; i < datasets.size(); i++){
            appendData(bubbleFile, System.lineSeparator()+ Integer.toString(datasets.get(i).length)+ " ");
            
            for(int j = 0; j<40; j++){
                targetArray = Arrays.copyOf(datasets.get(i), datasets.get(i).length);
                bubble.sort(targetArray);

                //throws error if array is unsorted
                if(!isSorted(targetArray)){
                    throw new UnsortedArrayException("Array did not properly sorted", new RuntimeException());
                }
                System.out.printf("Operations: %d Time Taken %d ns", bubble.getCount(), bubble.getTime());
                appendData(bubbleFile, "(" + Integer.toString(bubble.getCount())+ ","+Long.toString(bubble.getTime())+") ");
            }
        }
        
        //heap sort benchmark
        System.out.println("HEAP SORT BENCHMARK");
        File heapFile = createDataFile("heapSortBenchmark");
        for(int i = 0; i < datasets.size(); i++){
            appendData(heapFile, System.lineSeparator()+ Integer.toString(datasets.get(i).length)+ " ");            
            for(int j = 0; j<40; j++){
                targetArray = Arrays.copyOf(datasets.get(i), datasets.get(i).length);
                heap.sort(targetArray);
                
                //throws error if array is unsorted
                if(!isSorted(targetArray)){
                    throw new UnsortedArrayException("Array did not properly sorted", new RuntimeException());
                }
                System.out.printf("Operations: %d Time Taken %d ns", heap.getCount(), heap.getTime());
                appendData(heapFile, "(" + Integer.toString(bubble.getCount())+ ","+Long.toString(bubble.getTime())+") ");
            }
        }

       }   
       private static boolean isSorted(int[] targetArray) {
        for(int i : targetArray){
            if(i>i+1){
                return false;
            }
        }
        return true;
    }
    static ArrayList<int[]> initializeDataSets() throws IOException{
            //initialize datasets
            ArrayList<int[]> datasets = new ArrayList<int[]>();
            
            int dataSize = 100;
            for (int i=0; i<12; i++){
                int[] data = new int[dataSize];
                for( int j = 0; j < dataSize; j++ ){
                    data[j] = (int)(Math.random() * 100);
                }
                datasets.add(data);
                dataSize+=1000;
            }
            return datasets;
    }

    static File createDataFile(String path){
        try {
            File file = new File(path+ ".txt");
            if (file.createNewFile()) {
                System.out.println("File created: " +file.getName());
            } else{
                System.out.println("File " +file.getName()+"already exists: ");
            }
            return file;
        } catch (IOException e) {
            System.out.println("Error creating File");
            e.printStackTrace();
        }
        System.out.println("File not created");
        return null;   
        
    }

    static void appendData(File file, String value) throws IOException{
        FileWriter writer = new FileWriter(file, true);
        BufferedWriter buff = new BufferedWriter(writer);
        buff.write(value);
        buff.close();
    }
}

    
