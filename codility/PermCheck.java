package pkg2018_07_30_codility_permcheck;

import java.util.Arrays;

/**
 *
 * @author Tiber
 */
public class Main {
    
    // Codility // Counting Elements // PermCheck
    
    /*
    A non-empty array A consisting of N integers is given.

    A permutation is a sequence containing each element from 1 to N once, and only once.

    For example, array A such that:

        A[0] = 4
        A[1] = 1
        A[2] = 3
        A[3] = 2
    is a permutation, but array A such that:

        A[0] = 4
        A[1] = 1
        A[2] = 3
    is not a permutation, because value 2 is missing.

    The goal is to check whether array A is a permutation.

    Write a function:

    class Solution { public int solution(int[] A); }

    that, given an array A, returns 1 if array A is a permutation and 0 if it is not.

    For example, given array A such that:

        A[0] = 4
        A[1] = 1
        A[2] = 3
        A[3] = 2
    the function should return 1.

    Given array A such that:

        A[0] = 4
        A[1] = 1
        A[2] = 3
    the function should return 0.

    Write an efficient algorithm for the following assumptions:

    N is an integer within the range [1..100,000];
    each element of array A is an integer within the range [1..1,000,000,000].
    */

    public static int[] testArray1 = {4, 2, 1, 5, 3, 8, 7, 6};
    public static int[] testArray2 = {4, 2, 1, 5, 3, 9, 7, 6, 3};
    public static int[] testArray3 = {2, 2};

    public static int solution(int[] A) {
        int minimum = Integer.MAX_VALUE;
        int maximum = Integer.MIN_VALUE;
        for (int i = 0; i < A.length; i++) {
            if (minimum > A[i]) {
                minimum = A[i];
            }
            if (maximum < A[i]) {
                maximum = A[i];
            }
        }
        if (((maximum - minimum) + 1) != A.length) {
            return 0;
        }
        int[] checkerArray = new int[A.length];
        Arrays.fill(checkerArray, 0);
        for (int i = 0; i < A.length; i++) {
            if (checkerArray[A[i] - minimum] == A[i]) {
                return 0;
            }
            checkerArray[A[i] - minimum] = A[i];
        }
        return 1;
    }

    public static void main(String[] args) {

        System.out.println(solution(testArray1));
        System.out.println(solution(testArray2));
        System.out.println(solution(testArray3));

    }

}
