
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BenchmarkSorts {
    public static void main(String[] args) throws Exception {
        Scanner scanner;
        File selection = null;
        int[] targetArray;
        long time = 0;
        int counter = 0;
        ArrayList<int[]> datasets;

        datasets =initializeDataSets();
        
        //run benchmarks
        BubbleSort bubble = new BubbleSort(counter, time);
        HeapSort heap = new HeapSort(counter, time);

        //bubble sort benchmark
        System.out.println("BUBBLE SORT BENCHMARK");
        for(int i = 0; i < datasets.size(); i++){
            targetArray = datasets.get(i);
            bubble.sort(targetArray);
            
            //throws error if array is unsorted
            if(!isSorted(targetArray)){
                throw new UnsortedArrayException("Array did not properly sorted", new RuntimeException());
            }
            System.out.printf("Operations: %d Time Taken %d", bubble.getCount(), bubble.getTime());
            System.out.println(time);
        }
        
        //heap sort benchmark
        System.out.println("HEAP SORT BENCHMARK");
        for(int i = 0; i < datasets.size(); i++){
            targetArray = datasets.get(i);
            heap.sort(targetArray);
            
            //throws error if array is unsorted
            if(!isSorted(targetArray)){
                throw new UnsortedArrayException("Array did not properly sorted", new RuntimeException());
            }
            System.out.printf("Operations: %d Time Taken %d", heap.getCount(), heap.getTime());
            System.out.println(time);
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
                dataSize+=100;
            }
            return datasets;
        }
    }

    
