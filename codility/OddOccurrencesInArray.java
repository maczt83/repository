package gyak20171202;

import java.util.ArrayList;

public class OddOccurrencesInArray {
    
    // Codility // Arrays // OddOccurrencesInArray 88/100
    
    /*
    A non-empty array A consisting of N integers is given. The array contains an odd number of elements, and each element of the array can be paired with another element that has the same value, except for one element that is left unpaired.

    For example, in array A such that:

      A[0] = 9  A[1] = 3  A[2] = 9
      A[3] = 3  A[4] = 9  A[5] = 7
      A[6] = 9
    the elements at indexes 0 and 2 have value 9,
    the elements at indexes 1 and 3 have value 3,
    the elements at indexes 4 and 6 have value 9,
    the element at index 5 has value 7 and is unpaired.
    Write a function:

    class Solution { public int solution(int[] A); }

    that, given an array A consisting of N integers fulfilling the above conditions, returns the value of the unpaired element.

    For example, given array A such that:

      A[0] = 9  A[1] = 3  A[2] = 9
      A[3] = 3  A[4] = 9  A[5] = 7
      A[6] = 9
    the function should return 7, as explained in the example above.

    Write an efficient algorithm for the following assumptions:

    N is an odd integer within the range [1..1,000,000];
    each element of array A is an integer within the range [1..1,000,000,000];
    all but one of the values in A occur an even number of times.
    */

    public static int[] oddArray = {9, 3, 9, 3, 9, 7, 9};
    public static int[] oddArray2 = {12, 8, 3, 7, 9, 12, 3, 9, 7, 11, 9, 8, 9};

    public static int solution(int[] A) {
        int result = 0;
        ArrayList<Integer> numberList = new ArrayList<>();
        numberList.add(0);
        for (int i = 0; i < A.length; i++) {
            if (!numberList.contains(A[i])) {
                numberList.add(A[i]);
            } else if (numberList.contains(A[i])) {
                numberList.remove(numberList.indexOf(A[i]));
            }
        }
        numberList.remove(0);
        result = numberList.get(0);
        return result;
    }

    public static int solution2(int[] A) {
        int biggestElement = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] > biggestElement) {
                biggestElement = A[i];
            }
        }
        int[] checkerArray = new int[biggestElement];
        for (int i = 0; i < checkerArray.length; i++) {
            checkerArray[i] = 0;
        }
        for (int i = 0; i < A.length; i++) {
            if (checkerArray[A[i] - 1] == 0) {
                checkerArray[A[i] - 1] = 1;
            } else {
                checkerArray[A[i] - 1] = 0;
            }
        }
        int oddIndex = 0;
        for (int i = 0; i < checkerArray.length; i++) {
            if (checkerArray[i] == 1) {
                oddIndex = i + 1;
            }
        }
        return oddIndex;
    }

}
