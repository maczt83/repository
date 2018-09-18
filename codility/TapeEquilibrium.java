package ora20171129gyak;

public class Codility1 {
    /*
    
    // Codility // Time Complexity // TapeEquilibrium 100/100
    
    A non-empty zero-indexed array A consisting of N integers is given. Array A represents numbers on a tape.

    Any integer P, such that 0 < P < N, splits this tape into two non-empty parts: 
    A[0], A[1], ..., A[P − 1] and A[P], A[P + 1], ..., A[N − 1].

    The difference between the two parts is the value of: 
    |(A[0] + A[1] + ... + A[P − 1]) − (A[P] + A[P + 1] + ... + A[N − 1])|

    In other words, it is the absolute difference between the sum of the first part 
    and the sum of the second part.

    For example, consider array A such that:

    A[0] = 3
    A[1] = 1
    A[2] = 2
    A[3] = 4
    A[4] = 3
    We can split this tape in four places:

    P = 1, difference = |3 − 10| = 7 
    P = 2, difference = |4 − 9| = 5 
    P = 3, difference = |6 − 7| = 1 
    P = 4, difference = |10 − 3| = 7 
    Write a function:

    class Solution { public int solution(int[] A); }

    that, given a non-empty zero-indexed array A of N integers, returns the minimal difference 
    that can be achieved.

    For example, given:

    A[0] = 3
    A[1] = 1
    A[2] = 2
    A[3] = 4
    A[4] = 3
    the function should return 1, as explained above.

    Assume that:

    N is an integer within the range [2..100,000];
    each element of array A is an integer within the range [−1,000..1,000].
    Complexity:

    expected worst-case time complexity is O(N);
    expected worst-case space complexity is O(N), beyond input storage 
    (not counting the storage required for input arguments).
    Elements of input arrays can be modified.
    */
    
    public static int[] array = {5,5,6,7,9,4,6};
    public static int[] array2 = {1,2,3,4,5,6,7};
    public static int[] array3 = {1,2,4,-4,5,6,-7};
    public static int[] array4 = {11,-3,7,1,20,-8,9,10};
    
    public static int solution (int[] A){
        int p = 1;
        int preSum = 0;
        for (int i = 0; i < A.length; i++) {
            preSum += Math.abs(A[i]);
        }
        int min = Integer.MAX_VALUE;
        int arr1Sum = 0;
        int arr2Sum = 0;
        for (int i = 0; i < A.length; i++) {
             
            for (int j = 0; j < p; j++) {
                arr1Sum += A[j];
            }
            for (int j = p; j < A.length; j++) {
                arr2Sum += A[j];
            }
            if (min > Math.abs(arr1Sum - arr2Sum)){
                min = Math.abs(arr1Sum - arr2Sum);
            }
            p++;
            arr1Sum = 0;
            arr2Sum = 0;   
        }
        return min;
    }
    
    public static int sumOfDivided(int[] arr, int idx) {
        int arr1Sum = 0;
        int arr2Sum = 0;
        for (int j = 0; j < idx; j++) {
            arr1Sum += arr[j];
        }
        for (int j = idx; j < arr.length; j++) {
            arr2Sum += arr[j];
        }
        return Math.abs(arr1Sum - arr2Sum);
        }
    
    
    public static int solution2(int[] array){
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < array.length; i++) {
            int j = array[i];
            int sum = sumOfDivided(array, i);
            if (min > sum) {
                min = sum;
            }
        }
        return min;
    }
    
    public static int solution3 (int[] A){
        int i;
        int left = 0;
        int right = 0;
        int sum = 0;
        int min = Integer.MAX_VALUE;
        for (i = 0; i < A.length; i++) {
            sum += A[i];
        }
        left = A[0];
        for (i = 1; i < A.length; i++) {
            right = sum - left;
            min = Math.min(min, Math.abs(right-left));
            left += A[i];
        }
        return min;
    }
    
    
    public static void main(String[] args) {

        System.out.println(solution(array));
        System.out.println(solution2(array));
        System.out.println(solution3(array));
        System.out.println(solution(array2));
        System.out.println(solution2(array2));
        System.out.println(solution3(array2));
        System.out.println(solution(array3));
        System.out.println(solution2(array3));
        System.out.println(solution3(array3));
        System.out.println(solution(array4));
        System.out.println(solution2(array4));
        System.out.println(solution3(array4));
        
        
    }
    
    
    
    
    
}
