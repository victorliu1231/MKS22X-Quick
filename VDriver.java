import java.util.Random;
import java.util.Arrays;

public class VDriver{
    public static void main(String[]args){
        //test(500000,4000000);
        //test(1000000,1000000);
        //test(10000000,10000000);
        int[] test = new int[]{-1,2,3,-1,2};
        System.out.println(Quick.quickselect(test, 0));
        System.out.println(Quick.quickselect(test, 1));
        System.out.println(Quick.quickselect(test, 2));
        System.out.println(Quick.quickselect(test, 3));
        System.out.println(Quick.quickselect(test, 4));
        
        for (int i = 1; i <= 1000; i++){
            int[] ary = new int[i];
            Random r = new Random();
            for (int in = 0; in < ary.length; in++){
                ary[in] = Math.abs(r.nextInt() % 100);
            }
            int k = Math.abs(r.nextInt()) % i;
            int ok = Quick.quickselect(ary, k);
            try {
                if (!test(ary, k)){
                    System.out.println(k);
                    System.out.println(ok);
                    System.out.println("sad");
                    System.out.println(Arrays.toString(ary));
                    System.exit(0);
                }
            } catch (Exception e){
                System.out.println("oops");
                e.printStackTrace();
                System.exit(0);
            }
        }
        System.out.println("success");
    }

    public static void test(int start, int end){
        //quicksort starts slowing in the couple of millions
        //selectionsort starts slowing in the thousands
        //test with a lot of duplicates and with no duplicates
        long quickDiffAvg = 0;
        long selectionDiffAvg = 0;
        int numLoops = 0;
        for (int i = start; i <= end; i = i*2) {
            try {
              numLoops++;
                Random gen = new Random();
                int[] test = new int[i];
                int[] duplicate = new int[i];
                for (int j = 0; j < test.length; j++) {
                    int randGenInt = Math.abs(gen.nextInt() % 10000);
                    test[j] = randGenInt;
                    duplicate[j] = randGenInt;
                }
                long[] sortTimesAry = sortTimes(test);
                quickDiffAvg += sortTimesAry[0];
                selectionDiffAvg = sortTimesAry[1];
            } catch (Exception e) {
                e.printStackTrace();
                System.out.print("FAILURE ON LENGTH " + i);
            }
        }
        quickDiffAvg = quickDiffAvg / numLoops;
        selectionDiffAvg = selectionDiffAvg / numLoops;
        System.out.println("Arrays.sort: "+selectionDiffAvg+" milliseconds");
        System.out.println("quickSort: "+quickDiffAvg+" milliseconds");

    }

    public static boolean hasSortedRight(int[] test, int[] duplicate){
        Arrays.sort(duplicate);
        for (int i = 0; i < test.length; i++){
            if (test[i] != duplicate[i]){
                return false;
            }
        }
        return true;
    }

    public static boolean test(int[] data, int k){
        int kthElement = data[k];
        for (int i = 0; i < data.length; i++){
            if (i < k){
                if (data[i] > kthElement){
                    return false;
                }
            }
            else if (i > k){
                if (data[i] < kthElement){
                    return false;
                }
            }
        }
        return true;
    }

    //[0] is quicksort time, [1] is selectionsort time
    public static long[] sortTimes(int[] test){
        int[] duplicate = new int[test.length];
        for (int i = 0; i < test.length; i++){
            duplicate[i] = test[i];
        }
        long quickStartMillis = System.currentTimeMillis();
        Quick.quicksort(test);
        long quickEndMillis = System.currentTimeMillis();
        long quickDiffMillis = (quickEndMillis - quickStartMillis);
        long selectionStartMillis = System.currentTimeMillis();
        Arrays.sort(duplicate);
        long selectionEndMillis = System.currentTimeMillis();
        long selectionDiffMillis = (selectionEndMillis - selectionStartMillis);
        long[] sortTimes = new long[]{quickDiffMillis, selectionDiffMillis};
        return sortTimes;
    }

    public static void selectionSort(int [] ary) { //O(n^2); n passes with n work each
        int min;
        int indexOfMin = -1;
        int storer;
        for (int i = 0; i < ary.length-1; i++){
            min = ary[i];
            indexOfMin = i;
            //for loop has "i+1" so that the "first value" being iterated through doesn't
            //include the already established values to be minimums
            for (int n = i+1; n < ary.length; n++){
                if (ary[n] < min){
                    min = ary[n];
                    indexOfMin = n;
                }
            }
            //swaps the minimum and the "first" value
            storer = ary[i];
            ary[i] = min;
            ary[indexOfMin] = storer;
        }
    }
}
