package pkg2018_11_03_hr_betweentwosets;

/**
 *
 * @author Tiber
 */
//https://www.hackerrank.com/challenges/between-two-sets/problem
public class Main {

    public static int[] firstArray1 = {2, 6};
    public static int[] secondArray1 = {24, 36};

    public static int[] firstArray2 = {2, 4};
    public static int[] secondArray2 = {16, 32, 96};

    public static int[] firstArray3 = {2, 3};
    public static int[] secondArray3 = {12, 24, 48};

    public static int[] firstArray4 = {3, 4};
    public static int[] secondArray4 = {24, 48};

    public static int[] firstArray5 = {3, 9, 6};
    public static int[] secondArray5 = {36, 72};

    public static void main(String[] args) {

        System.out.println(getTotalX(firstArray1, secondArray1));
        System.out.println(getTotalX(firstArray2, secondArray2));
        System.out.println(getTotalX(firstArray3, secondArray3));
        System.out.println(getTotalX(firstArray4, secondArray4));
        System.out.println(getTotalX(firstArray5, secondArray5));
    }

    public static int getTotalX(int[] a, int[] b) {

        int counter = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < a.length; i++) {
            if (min > a[i]) {
                min = a[i];
            }
        }

        for (int i = 0; i < b.length; i++) {
            if (max < b[i]) {
                max = b[i];
            }
        }

        outer:
        for (int i = min; i <= max; i++) {
            for (int j = 0; j < a.length; j++) {
                if (i % a[j] != 0) {
                    continue outer;
                }
            }
            for (int j = 0; j < b.length; j++) {
                if (b[j] % i != 0) {
                    continue outer;
                }
            }
            counter++;
        }
        return counter;
    }
}
