import java.util.Random;
import java.util.Arrays;

public class Quick{

    /*Modify the array to be in increasing order. 
    */   
    public static void quicksort(int[] data){
        quickSortHelp(data, 0, data.length-1);
    }

    private static void quickSortHelp(int[] data, int lo, int hi){
        if (lo >= hi){
            return;
        }
        else if (hi - lo <= 47){
            insertionSort(data, lo, hi);
        } else {
            int[] mid = partitionDutch(data, lo, hi);
            quickSortHelp(data, lo, mid[0] - 1);
            quickSortHelp(data, mid[1]+1, hi);
        }
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

      /*Modify the array such that:
    *1. Only the indices from lo to hi inclusive are considered in range
    *2. A random index from lo to hi inclusive is chosen, the corresponding
    *   element is designated the mid element.
    *3. all elements in range that are smaller than the mid element are placed before the mid element.
    *4. all elements in range that are larger than the mid element are placed after the mid element.
    *@return the index of the final position of the mid element.
    */
    public static int[] partitionDutch(int[] data,int lo, int hi){
        Random r = new Random();
        boolean hasReachedOneOfDuplicates = false;

        //finding the median value of data[lo], data[lo], and data[randomly chosen index in between]
        int mid = Math.abs(r.nextInt() % (hi - lo)) + lo;
        /*
        int loVal = data[lo];
        int hiVal = data[hi];
        int randVal = data[mid];
        //test if we need all this finding median shit is faster
        if (loVal <= randVal && randVal <= hiVal || loVal >= randVal && randVal >= hiVal){
          //nothing, mid stays same
        } else if (randVal <= hiVal && hiVal <= loVal || randVal >= hiVal && hiVal >= loVal){
          mid = hi;
        } else if (hiVal <= loVal && loVal <= randVal || hiVal >= loVal && loVal >= randVal){
          mid = lo;
        }
        */
        swap(data, lo, mid);
        mid = lo + 1;
        //System.out.println("median: "+mid+", start: "+lo+", randVal: "+randVal+", end: "+hi);
        //lo is pivotIndex and mid is start

        while (mid <= hi){
            //System.out.println("pivot: "+lo+", start: "+mid+", hi: "+hi+", array: ");//+Arrays.toString(data));
            //swapping part of the code
            if (data[lo] < data[mid]){
                swap(data, hi, mid);
                hi--;
            } else if (data[lo] > data[mid]){
                if (hasReachedOneOfDuplicates){
                    swap(data, lo, mid);
                    lo++;
                }
                if (mid <= hi){
                    mid++;
                }
            } else {
                if (hasReachedOneOfDuplicates){
                    if (mid <= hi){
                        mid++;
                    }
                } else {
                    swap(data, mid-1, lo);
                    lo = mid-1;
                    if (mid <= hi){
                        mid++;
                    }
                    hasReachedOneOfDuplicates = true;
                }
            }
        }
        mid--;
        if (!hasReachedOneOfDuplicates){
            if (data[lo] < data[mid]){
                swap(data, lo, mid-1);
            } else {
                swap(data, lo, mid);
            }
            lo = mid;
            hi = mid;
        }
        //System.out.println("pivot: "+lo+", start: "+mid+", hi: "+hi+", array: "+Arrays.toString(data));
        int[] ans = new int[]{lo, mid};
        return ans;
      }

      private static void insertionSort(int[] ary, int lo, int hi){
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

      private static void swap(int[] data, int x, int y){
          int a = data[x];
          data[x] = data[y];
          data[y] = a;
      }

      public static void quicksortNonOptimized(int[] data){
        quickSortHelpNonOptimized(data, 0, data.length-1);
    }

    private static void quickSortHelpNonOptimized(int[] data, int start, int end){
        if (end - start <= 43){
            insertionSort(data, start, end);
        } else {
            int pivot = partition(data, start, end);
            quickSortHelpNonOptimized(data, start, pivot - 1);
            quickSortHelpNonOptimized(data, pivot+1, end);
        }
    }

    private static int partition(int[] data,int start, int end){
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

}
