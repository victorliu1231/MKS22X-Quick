import java.util.Random;
import java.util.Arrays;

public class Quick{
    public static void quicksort(int[] data){
        quickSortHelp(data, 0, data.length-1);
    }

    private static void quickSortHelp(int[] data, int lo, int hi){
        if (hi - lo <= 370){
            insertionSort(data, lo, hi);
        } else {
            int[] mid = partitionDutch(data, lo, hi);
            //if (!(Math.abs((mid[0] - lo) - (hi - mid[1])) <= 1)){
            //    System.out.println("im about to end myself");
            //}
            quickSortHelp(data, lo, mid[0] - 1);
            quickSortHelp(data, mid[1] + 1, hi);
        }
    }

    public static int[] partitionDutch(int[] data,int lo, int hi){
        //finding the median value of data[lo], data[lo], and data[index in between]
        //start here is just being used as a randomIndex, will be changed later
        int start = (hi - lo) / 2 + lo;
        int loVal = data[lo];
        int hiVal = data[hi];
        int[] threeVals = new int[]{loVal, data[start], hiVal};
        Arrays.sort(threeVals);
        int median = threeVals[1];
        if (median == loVal){
            start = lo;
        } else if (median == hiVal){
            start = hi;
        }
        //else if median == data[start], then nothing needs to be reassigned
        int pivot = data[start];
        start = lo;
        //lo is pivotIndex

        while (start <= hi){
            int startNum = data[start];
            int hiNum = data[hi];
            //swapping part of the code
            if (pivot < startNum){
                if (!(pivot < hiNum)){
                    data[start] = hiNum;
                    data[hi] = startNum;
                }
                hi--;
            } else if (pivot > startNum){
                if (start != lo){
                    data[start] = data[lo];
                    data[lo] = startNum;
                }
                lo++;
                start++;
            } else {
                start++;
            }
        }
        start--;
        return new int[]{lo, start};
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

    public static void swap(int[] data, int x, int y){
        int a = data[x];
        data[x] = data[y];
        data[y] = a;
    }
}