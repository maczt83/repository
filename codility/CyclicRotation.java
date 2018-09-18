package gyak20171222;

public class CyclicRotation {

    // Codility // Arrays // CyclicRotation 87/100
    
    /*
    An array A consisting of N integers is given. Rotation of the array means that each element is shifted right by one index, and the last element of the array is moved to the first place. For example, the rotation of array A = [3, 8, 9, 7, 6] is [6, 3, 8, 9, 7] (elements are shifted right by one index and 6 is moved to the first place).

    The goal is to rotate array A K times; that is, each element of A will be shifted to the right K times.

    Write a function:

    class Solution { public int[] solution(int[] A, int K); }

    that, given an array A consisting of N integers and an integer K, returns the array A rotated K times.

    For example, given

    A = [3, 8, 9, 7, 6]
    K = 3
    the function should return [9, 7, 6, 3, 8]. Three rotations were made:

    [3, 8, 9, 7, 6] -> [6, 3, 8, 9, 7]
    [6, 3, 8, 9, 7] -> [7, 6, 3, 8, 9]
    [7, 6, 3, 8, 9] -> [9, 7, 6, 3, 8]
    For another example, given

    A = [0, 0, 0]
    K = 1
    the function should return [0, 0, 0]

    Given

    A = [1, 2, 3, 4]
    K = 4
    the function should return [1, 2, 3, 4]

    Assume that:

    N and K are integers within the range [0..100];
    each element of array A is an integer within the range [−1,000..1,000].
    In your solution, focus on correctness. The performance of your solution will not be the focus of the assessment.
    */
    
    public static int[] aArr = {3,8,9,7,6};
    public static int kInt1 = 3;
    
    public static int[] bArr = {4,5,6};
    public static int kInt2 = 4;
    
    public static int[] cArr = {-7,-8,-9,-10,-11};
    public static int kInt3 = 12;
            
    public static int[] solution(int[] A, int K) {
        if (A.length == 0) {
            return null;
        }
        int[] newArr = new int[A.length];
        int X;
        if (K == A.length) {
            return A;
        } else if (K < A.length) {
            X = A.length - K;
            for (int i = 0; i < A.length; i++) {
                if (i > X - 1) {
                    newArr[i - X] = A[i];
                } else {
                    newArr[i + K] = A[i];
                }
            }
        } else {
            X = A.length - (K % A.length);
            for (int i = 0; i < A.length; i++) {
                if (i > X - 1) {
                    newArr[i - X] = A[i];
                } else {
                    newArr[i + (K % A.length)] = A[i];
                }
            }
        }
        return newArr;
    }
    
}
