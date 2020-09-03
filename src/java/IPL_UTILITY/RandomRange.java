/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IPL_UTILITY;

/**
 *
 * @author Abhinav Kumar
 */
import java.util.Random;

/** Generate random integers in a certain range. */
public final class RandomRange {
  
  public int showRandomInteger(int aStart, int aEnd){
      Random aRandom = new Random();
    if (aStart > aEnd) {
      throw new IllegalArgumentException("Start cannot exceed End.");
    }
    //get the range, casting to long to avoid overflow problems
    long range = (long)aEnd - (long)aStart + 1;
    // compute a fraction of the range, 0 <= frac < range
    long fraction = (long)(range * aRandom.nextDouble());
    int randomNumber =  (int)(fraction + aStart);    
    return randomNumber;
  }
  
  private static void log(String aMessage){
    System.out.println(aMessage);
  }
  
} 
