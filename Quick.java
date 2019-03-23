import java.util.Random;
import java.util.Arrays;

public class Quick{
    public static Random r = new Random();

    public static void quicksort(int[] data){
        quickSortHelp(data, 0, data.length-1);
    }

    private static void quickSortHelp(int[] data, int lo, int hi){
        if (hi - lo <= 47){
            insertionSort(data, lo, hi);
        } else {
            int[] mid = partitionDutch(data, lo, hi);
            quickSortHelp(data, lo, mid[0] - 1);
            quickSortHelp(data, mid[1]+1, hi);
        }
    }

      public static int[] partitionDutch(int[] data,int lo, int hi){
        //finding the median value of data[lo], data[lo], and data[randomly chosen index in between]
        int mid = Math.abs(r.nextInt(hi - lo)) + lo;
        
        int a = data[lo];
        data[lo] = data[mid];
        data[mid] = a;

        mid = lo + 1;
        int pivot = data[lo];
        //lo is pivotIndex and mid is start

        while (mid <= hi){
            int midNum = data[mid];
            //swapping part of the code
            if (pivot < midNum){
                data[mid] = data[hi];
                data[hi] = midNum;
                hi--;
            } else if (pivot > midNum){
                data[mid] = data[lo];
                data[lo] = midNum;
                lo++;
                mid++;
            } else {
                mid++;
            }
        }
        mid--;
        int[] ans = new int[]{lo, mid};
        return ans;
      }

      /*return the value that is the kth smallest value of the array.
    */
    public static int quickselect(int[] data, int k){
        return quickSelectHelp(data, k, 0, data.length-1);
    }

    private static int quickSelectHelp(int[] data, int k, int lo, int hi){
        if (lo == hi){
            return data[lo];
        }
        int[] midIndex = partitionDutch(data, lo, hi);
        if (k < midIndex[0]){
            return quickSelectHelp(data,k,lo,midIndex[0]-1);
        }
        if (k > midIndex[1]){
            return quickSelectHelp(data,k,midIndex[1]+1,hi);
        } else {
            return data[midIndex[0]];
        }
    }

      private static void insertionSort(int[] ary, int lo, int hi){
          if (lo >= hi){
              return;
          }
        int storer = ary[lo];
        boolean madeSwaps = false;
        for (int n = lo+1; n < hi+1; n++){ //loops through whole thing, loing with the unsorted part
          storer = ary[n]; //the value that wants to be sorted
          int i = n;
          while (i > lo && storer < ary[i-1]){ //looping through sorted part and finding out where to place it
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