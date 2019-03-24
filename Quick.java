import java.util.Random;
import java.util.Arrays;

public class Quick{
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

    /*public static int[] partitionDutchOld(int[] data,int lo, int hi){
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
        boolean hasReachedOneOfDuplicates = false;

        swap(data, lo, start);
        start = lo+1;
        int pivot = data[lo];
        //lo is pivotIndex

        while (start <= hi){
            //System.out.println("pivot: "+lo+", start: "+start+", hi: "+hi);
            //System.out.println(Arrays.toString(data));
            //System.out.println();
            //swapping part of the code
            if (pivot < data[start]){
                if (!(pivot < data[hi])){
                    swap(data, hi, start);
                }
                hi--;
            } else if (pivot > data[start]){
                if (hasReachedOneOfDuplicates){
                    swap(data, lo, start);
                    lo++;
                }
                if (start <= hi){
                    start++;
                }
            } else {
                if (hasReachedOneOfDuplicates){
                    if (start <= hi){
                        start++;
                    }
                } else {
                    swap(data, start-1, lo);
                    lo = start-1;
                    if (start <= hi){
                        start++;
                    }
                    hasReachedOneOfDuplicates = true;
                }
            }
        }
        start--;
        if (!hasReachedOneOfDuplicates){
            if (pivot < data[start]){
                swap(data, lo, start-1);
            } else {
                //System.out.println("data[lo]: "+data[lo]+", data[start]: "+data[start]);
                //System.out.println(Arrays.toString(data));
                //System.out.println();
                swap(data, lo, start);
                //System.out.println(Arrays.toString(data));
            }
            lo = start;
            hi = start;
        }
        //System.out.println("pivot: "+lo+", start: "+mid+", hi: "+hi+", array: "+Arrays.toString(data));
        int[] ans = new int[]{lo, start};
        return ans;
      }*/

      public static int[] partitionDutch(int[] data,int lo, int hi){
        //finding the median value of data[lo], data[lo], and data[index in between]
        //start here is just being used as a randomIndex, will be changed later
        int end = hi;
        int startStart = lo;

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
        //System.out.println(Arrays.toString(threeVals)+", median: "+median+", start: "+data[start]);


        //else if median == data[start], then nothing needs to be reassigned
        int pivot = data[start];
        //swap(data, lo, start);
        start = lo;
        //lo is pivotIndex
        //System.out.println(Arrays.toString(data)); 
        //System.out.println("lo: "+lo+", hi: "+hi+", pivot: "+pivot);
        //System.out.println();

        while (start <= hi){
            //System.out.println(Arrays.toString(data)); System.out.println("lo: "+lo+", start: "+start+", data[start]: "+data[start]+", pivot: "+pivot+", hi: "+hi+", end: "+end);
            //System.out.println();
            int startNum = data[start];
            //swapping part of the code
            if (pivot < startNum){
                if (!(pivot < data[hi])){
                    data[start] = data[hi];
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
        //System.out.println(Arrays.toString(data)); System.out.println("lo: "+lo+", start: "+start+", end: "+end);
        //System.out.println("startStart: "+startStart+", pivotIndex: "+lo+", end: "+end);
        int[] ans = new int[]{lo, start};
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

      public static void swap(int[] data, int x, int y){
          int a = data[x];
          data[x] = data[y];
          data[y] = a;
      }
}