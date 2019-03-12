import java.util.Random;

public class VDriver{
    public static void main(String[]args){
        /*
        int[]ary = { 2, 10, 15, 23, 0,  5};  //sorted :  {0,2,5,10,15,23}
        System.out.println(Quick.quickselect( ary , 0 )); //would return 0
        System.out.println(Quick.quickselect( ary , 1 ));  //would return 2
        System.out.println(Quick.quickselect( ary , 2 ));  //would return 5
        System.out.println(Quick.quickselect( ary , 3 ));  //would return 10
        System.out.println(Quick.quickselect( ary , 4 )); // would return 15
        System.out.println(Quick.quickselect( ary , 5 ));  //would return 23
        */

        for (int i = 1; i < 1000; i++) {
            try {
                Random gen = new Random();
                int[] test = new int[i];
                for (int j = 0; j < test.length; j++) {
                    test[j] = gen.nextInt();
                }
                int k = Math.abs(gen.nextInt()) % test.length;
                int kthElement = Quick.quickselect(test, k);
                if (!test(test, k, kthElement)) {
                    System.out.print("FAILURE ON LENGTH " + i);
                    System.exit(1);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.print("FAILURE ON LENGTH " + i);
                System.exit(1);
            }
        }
        System.out.println("R: SUCCESS"); //R for random

        Random why = new Random();
        int[] ugh = new int[10000];
        for (int i = 0; i < ugh.length; i++){
            ugh[i] = Math.abs(why.nextInt(2));
        }
        int kthElement = Quick.quickselect(ugh,100);
        System.out.println(kthElement);
        System.out.println(test(ugh, 100, kthElement));

        kthElement = Quick.quickselect(ugh,7000);
        System.out.println(kthElement);
        System.out.println(test(ugh, 7000, kthElement));
    }

    public static boolean test(int[] data, int k, int kthElement){
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
}