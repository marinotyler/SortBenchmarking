/**UMGC CMSC 451
 * Description: Exception class if array is not sorted after sort algo is run.
 * Date: August 13th, 2024
 * Java 11
 */
public class UnsortedArrayException extends Exception{
    
    public UnsortedArrayException(){
        super();
    }
    public UnsortedArrayException(String message, Throwable cause){
        super(message, cause);
    }
}