import java.util.Random;
import java.util.Arrays;

public class Quick{

    /*return the value that is the kth smallest value of the array.
    */
    public static int quickselect(int[] data, int k){
        return quickHelp(data, k, 0, data.length-1);
    }

    private static int quickHelp(int[] data, int k, int start, int end){
        int pivotIndex = partitionDutch(data, start, end);
        if (k < pivotIndex){
            return quickHelp(data,k,start,pivotIndex-1);
        }
        if (k > pivotIndex){
            return quickHelp(data,k,pivotIndex+1,end);
        } else {
            return data[pivotIndex];
        }
    }

    /*Modify the array such that:
    *1. Only the indices from start to end inclusive are considered in range
    *2. A random index from start to end inclusive is chosen, the corresponding
    *   element is designated the pivot element.
    *3. all elements in range that are smaller than the pivot element are placed before the pivot element.
    *4. all elements in range that are larger than the pivot element are placed after the pivot element.
    *@return the index of the final position of the pivot element.
    */
    private static int partitionDutch(int[] data,int start, int end){
      int inputtedEnd = end;
      int inputtedStart = start;
      int pivot = 0; //to initialize
      int pivotIndex = start;
      Random r = new Random();
      for (int i = 0; i < inputtedEnd - inputtedStart; i++){
          if (i == 0){
              //finding the median value of data[start], data[start], and data[randomly chosen index in between]
              int startVal = data[start];
              int endVal = data[end];
              int randIndex = Math.abs(r.nextInt(end - start)) + start;
              int randVal = data[randIndex];
              if (startVal <= randVal && randVal <= endVal || startVal >= randVal && randVal >= endVal){
                pivotIndex = randIndex;
              } else if (randVal <= endVal && endVal <= startVal || randVal >= endVal && endVal >= startVal){
                pivotIndex = end;
              } else if (endVal <= startVal && startVal <= randVal || endVal >= startVal && startVal >= randVal){
                pivotIndex = start;
              }
              pivot = data[pivotIndex];
              int a = data[start];
              data[start] = data[pivotIndex];
              data[pivotIndex] = a;
              pivotIndex = start;
              start++;
          }
          if (start == end){
              if (data[start] >= pivot){
                  int c = data[start-1];
                  data[start-1] = data[pivotIndex];
                  data[pivotIndex] = c;
                  pivotIndex = start - 1;
              } else {
                  int c = data[start];
                  data[start] = data[pivotIndex];
                  data[pivotIndex] = c;
                  pivotIndex = start;
              }
          }
          if (data[start] < pivot){
              start++;
          } else {
              int b = data[end];
              data[end] = data[start];
              data[start] = b;
              end--;
          }
      }
      return pivotIndex;
    }
}
