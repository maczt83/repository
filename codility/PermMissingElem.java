package gyak20171202;

/**
 *
 * @author Tiber
 */
public class PermMissingElem {
    
    // Codility // Time Complexity // PermMissingElem
    
    /*
    An array A consisting of N different integers is given. The array contains integers in the range [1..(N + 1)], which means that exactly one element is missing.

    Your goal is to find that missing element.

    Write a function:

    class Solution { public int solution(int[] A); }

    that, given an array A, returns the value of the missing element.

    For example, given array A such that:

      A[0] = 2
      A[1] = 3
      A[2] = 1
      A[3] = 5
    the function should return 4, as it is the missing element.

    Write an efficient algorithm for the following assumptions:

    N is an integer within the range [0..100,000];
    the elements of A are all distinct;
    each element of array A is an integer within the range [1..(N + 1)].
    */

    public static int[] array1 = {2, 3, 1, 5};
    public static int[] array2 = {2, 3, 1, 5, 6, 4, 8, 9};
    public static int[] array3 = {11, 10, 8, 7, 6, 5, 4, 3, 2, 1};

    public static int solution(int[] A) {
        int[] rangeOfNumbers = new int[A.length + 1];
        for (int i = 0; i < rangeOfNumbers.length; i++) {
            rangeOfNumbers[i] = i + 1;
        }
        for (int i = 0; i < A.length; i++) {
            rangeOfNumbers[A[i] - 1] = 0;
        }
        for (int i = 0; i < rangeOfNumbers.length; i++) {
            if (rangeOfNumbers[i] != 0) {
                return rangeOfNumbers[i];
            }
        }
        return 0;
    }

}
