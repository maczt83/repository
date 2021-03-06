package ora20171130gyak;

import java.util.Stack;

public class Codility_feladatok {

    
    
    /*
    Egy stringet vizsgálunk, és megnézzük megfelelően van-e zárójelezve
    a Stack adatstruktúra segítségével.
    
    1. ()
       
    2. {[()]}
    
    */
    
    
    // 1
    
    // Codility // Stack and Queues // Nesting 100/100
    
    /*
    A string S consisting of N characters is called properly nested if:

    S is empty;
    S has the form "(U)" where U is a properly nested string;
    S has the form "VW" where V and W are properly nested strings.
    For example, string "(()(())())" is properly nested but string "())" isn't.

    Write a function:

    class Solution { public int solution(String S); }

    that, given a string S consisting of N characters, returns 1 if string S is properly nested and 0 otherwise.

    For example, given S = "(()(())())", the function should return 1 and given S = "())", the function should return 0, as explained above.

    Write an efficient algorithm for the following assumptions:

    N is an integer within the range [0..1,000,000];
    string S consists only of the characters "(" and/or ")".
    */
    
    public static int solution (String S){
        char actualChar;
        Stack<Character> charStack = new Stack<>();
        if (S.equals("")) {
            return 1;
        }
        if (S.charAt(0) == ')') {
                return 0;
            }
        for (int i = 0; i < S.length(); i++) {
            actualChar = S.charAt(i);
            if (actualChar == '(') {
                charStack.push(actualChar);
            }
            if (actualChar == ')') {
                if (charStack.isEmpty()) {
                    return 0;
                } else if (charStack.peek() == '('){
                    charStack.pop();
                }
            }
        }
        if (!charStack.isEmpty()) {
            return 0;
        }
        if (S.charAt(S.length() - 1) == '(') {
            return 0;
        }
        return 1;
    }
    
    //2 - 50% még nincs kész
    
    // Codility // Stack and Queues // Brackets 50/100
    
    /*
    A string S consisting of N characters is considered to be properly nested if any of the following conditions is true:

    S is empty;
    S has the form "(U)" or "[U]" or "{U}" where U is a properly nested string;
    S has the form "VW" where V and W are properly nested strings.
    For example, the string "{[()()]}" is properly nested but "([)()]" is not.

    Write a function:

    class Solution { public int solution(String S); }

    that, given a string S consisting of N characters, returns 1 if S is properly nested and 0 otherwise.

    For example, given S = "{[()()]}", the function should return 1 and given S = "([)()]", the function should return 0, as explained above.

    Write an efficient algorithm for the following assumptions:

    N is an integer within the range [0..200,000];
    string S consists only of the following characters: "(", "{", "[", "]", "}" and/or ")".
    */
    
    public static int solution2 (String S){
        char actualChar;
        Stack<Character> charStack = new Stack<>();
        if (S.equals("")) {
            return 1;
        }
        if (S.charAt(0) == ')' ||
            S.charAt(0) == ']' ||
            S.charAt(0) == '}') {
                return 0;
            }
        for (int i = 0; i < S.length(); i++) {
            actualChar = S.charAt(i);
            if (actualChar == '(') {
                charStack.push(actualChar);
            }
            if (actualChar == ')') {
                if (charStack.isEmpty()) {
                    return 0;
                } else if (charStack.peek() == '('){
                    charStack.pop();
                } else if (charStack.peek() == '['){
                    return 0;
                } else if (charStack.peek() == '{'){
                    return 0;
                }    
            }
            if (actualChar == '[') {
                if (charStack.isEmpty() || charStack.peek() == '{') {
                    charStack.push(actualChar);
                } else if (charStack.peek() == '('){
                    return 0;
                }
            }
            if (actualChar == ']') {
                if (charStack.isEmpty()) {
                    return 0;
                } else if (charStack.peek() == '['){
                    charStack.pop();
                } else if (charStack.peek() == '('){
                    return 0;
                } else if (charStack.peek() == '{'){
                    return 0;
                }    
            }
            if (actualChar == '{') {
                if (charStack.isEmpty()) {
                    charStack.push(actualChar);
                } else if (charStack.peek() == '('){
                    return 0;
                } else if (charStack.peek() == '['){
                    return 0;
                }
            }
            if (actualChar == '}') {
                if (charStack.isEmpty()) {
                    return 0;
                } else if (charStack.peek() == '{'){
                    charStack.pop();
                } else if (charStack.peek() == '('){
                    return 0;
                } else if (charStack.peek() == '['){
                    return 0;
                }    
            }
        }
        if (!charStack.isEmpty()) {
            return 0;
        }
        if (S.charAt(S.length() - 1) == '(' ||
            S.charAt(S.length() - 1) == '[' ||
            S.charAt(S.length() - 1) == '{') {
            return 0;
        }
        return 1;
    }
    
    // Codility // Stack and Queues / Fish 100/100
    
    /*
    You are given two non-empty zero-indexed arrays A and B consisting of N integers. 
    Arrays A and B represent N voracious fish in a river, ordered downstream along the flow of the river.

    The fish are numbered from 0 to N − 1. If P and Q are two fish and P < Q, 
    then fish P is initially upstream of fish Q. Initially, each fish has a unique position.

    Fish number P is represented by A[P] and B[P]. Array A contains the sizes of the fish. 
    All its elements are unique. Array B contains the directions of the fish. 
    It contains only 0s and/or 1s, where:

    0 represents a fish flowing upstream,
    1 represents a fish flowing downstream.
    If two fish move in opposite directions and there are no other (living) fish between them,
    they will eventually meet each other. 
    Then only one fish can stay alive − the larger fish eats the smaller one. 
    More precisely, we say that two fish P and Q meet each other
    when P < Q, B[P] = 1 and B[Q] = 0, and there are no living fish between them. After they meet:

    If A[P] > A[Q] then P eats Q, and P will still be flowing downstream,
    If A[Q] > A[P] then Q eats P, and Q will still be flowing upstream.
    We assume that all the fish are flowing at the same speed. 
    That is, fish moving in the same direction never meet. 
    The goal is to calculate the number of fish that will stay alive.

    For example, consider arrays A and B such that:

    A[0] = 4    B[0] = 0
    A[1] = 3    B[1] = 1
    A[2] = 2    B[2] = 0
    A[3] = 1    B[3] = 0
    A[4] = 5    B[4] = 0
    Initially all the fish are alive and all except fish number 1 are moving upstream. 
    Fish number 1 meets fish number 2 and eats it, then it meets fish number 3 and eats it too. 
    Finally, it meets fish number 4 and is eaten by it. The remaining two fish, number 0 and 4, 
    never meet and therefore stay alive.

    Write a function:

    class Solution { public int solution(int[] A, int[] B); }
    that, given two non-empty zero-indexed arrays A and B consisting of N integers, 
    returns the number of fish that will stay alive.

    For example, given the arrays shown above, the function should return 2, as explained above.

    Assume that:

    N is an integer within the range [1..100,000];
    each element of array A is an integer within the range [0..1,000,000,000];
    each element of array B is an integer that can have one of the following values: 0, 1;
    the elements of A are all distinct.
    Complexity:

    expected worst-case time complexity is O(N);
    expected worst-case space complexity is O(N), beyond input storage 
    (not counting the storage required for input arguments).
    */
           
    public static int[] array1A = {4,3,2,1,5};
    public static int[] array1B = {0,1,0,0,0};
    
    public static int[] array2A = {1,8,3,2,1,7,2,1};
    public static int[] array2B = {0,0,1,1,0,0,1,0};
    
    private static final int DIR_UP = 0;
    private static final int DIR_DWN = 1;
           
     public static int solution(int[] A, int[] B) {
        int counter = 0;
        int actStr;
        int actDir;
        Stack<Integer> fishDwn = new Stack<>();
        for (int i = 0; i < A.length; i++) {
            actStr = A[i];
            actDir = B[i];
            if (actDir == DIR_UP && fishDwn.isEmpty()) {
                counter++;
            } else if (actDir == DIR_UP) {
                while (!fishDwn.isEmpty() && actStr > fishDwn.peek()){
                    fishDwn.pop();
                }
                if (fishDwn.isEmpty()){
                    counter++;
                }
            }    
            else if (actDir == DIR_DWN) {
                fishDwn.push(actStr);
            }
            
        }
        counter += fishDwn.size();
        return counter;
    }
    
    
    
    
    // Main
    
    public static void main(String[] args) {

        // 1
        
        String s = "{[(wrt()rw)wr]}{wr[((fddsgf))]}";
        System.out.println(solution2(s));
//        System.out.println(solution(array1A, array1B));
//        System.out.println(solution(array2A, array2B));
        
        
        
        
        
        
        
        
        
        
    }
    
    
    
    
    
    
    
    
    
}
