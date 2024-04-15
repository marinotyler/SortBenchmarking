/**UMGC CMSC 451
 * Description: Abstract sort class for siomple implementation of benchmarking
 * Java 11
 */
 abstract class AbstractSort {
    
    int counter;
    long time;

    public AbstractSort(int counter, long time){
        this.counter = counter;
        this.time = time;
    }

    abstract void sort(int[] array);
    
    //initialize the benchmark variables: counter and time (ns)
    protected void startSort(){
        counter = 0;
        time = System.nanoTime();

    };
    //calculate time for benchmarking using system nanoseconds
    protected void endSort(){
        time = System.nanoTime() - time;
    };
    
    //increase the count for counting critical operations
    protected void incrementCount(){
        counter++;
    }

    //get current count value
    public int getCount(){
        return counter;
    }

    //get current time value om nanseconds
    public long getTime(){
        return time;
    }
}
