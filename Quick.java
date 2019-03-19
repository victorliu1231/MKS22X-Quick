import java.util.Random;
import java.util.Arrays;

public class Quick{

    /*Modify the array to be in increasing order. 
    */   
    public static void quicksort(int[] data){
        quickSortHelp(data, 0, data.length-1);
    }

    private static void quickSortHelp(int[] data, int start, int end){
        if (end - start <= 10){
            insertionSort(data, start, end);
        } else {
            int pivot = partitionDutch(data, start, end);
            quickSortHelp(data, start, pivot - 1);
            quickSortHelp(data, pivot+1, end);
        }
    }

    /*return the value that is the kth smallest value of the array.
    */
    //for practice
    public static int quickselect(int[] data, int k){
        return quickSelectHelp(data, k, 0, data.length-1);
    }

    private static int quickSelectHelp(int[] data, int k, int start, int end){
        int pivotIndex = partitionDutch(data, start, end);
        if (k < pivotIndex){
            return quickSelectHelp(data,k,start,pivotIndex-1);
        }
        if (k > pivotIndex){
            return quickSelectHelp(data,k,pivotIndex+1,end);
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
            //this section of code terminates the rest of the loop in effect
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
            //swapping part of the code
            if (data[start] < pivot){
                start++;
            } else if (data[start] > pivot){
                int b = data[end];
                data[end] = data[start];
                data[start] = b;
                end--;
            } else {
                //50-50 shot of throwing it left or right of pivot
                int chance = Math.abs(r.nextInt(2));
                if (chance == 0){
                    start++;
                } else {
                  int b = data[end];
                  data[end] = data[start];
                  data[start] = b;
                  end--;
                }
            }
        }
        return pivotIndex;
      }

      private static void insertionSort(int[] ary, int start, int end){
        int storer = ary[start];
        boolean madeSwaps = false;
        for (int n = start+1; n < end+1; n++){ //loops through whole thing, starting with the unsorted part
          storer = ary[n]; //the value that wants to be sorted
          int i = n;
          while (i > start && storer < ary[i-1]){ //looping through sorted part and finding out where to place it
            ary[i] = ary[i-1]; //while looping, shifting over the elements to make room for the storer
            i--;
            madeSwaps = true;
          }
          if (madeSwaps){ //only if the while loop runs will you actually edit the sorted part
            ary[i] = storer;
          }
          madeSwaps = false; //resets the boolean so next pass has a clean slate
        }
      }

}
