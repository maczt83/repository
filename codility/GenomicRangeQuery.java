package gyak20171202;

import java.util.TreeSet;

public class GenomicRangeQuery {
    
    // Codility // Prefix Sums // GenomicRangeQuery 25/100
    
    /*
    A DNA sequence can be represented as a string consisting of the letters A, C, G and T, which correspond to the types of successive nucleotides in the sequence. Each nucleotide has an impact factor, which is an integer. Nucleotides of types A, C, G and T have impact factors of 1, 2, 3 and 4, respectively. You are going to answer several queries of the form: What is the minimal impact factor of nucleotides contained in a particular part of the given DNA sequence?

    The DNA sequence is given as a non-empty string S = S[0]S[1]...S[N-1] consisting of N characters. There are M queries, which are given in non-empty arrays P and Q, each consisting of M integers. The K-th query (0 ≤ K < M) requires you to find the minimal impact factor of nucleotides contained in the DNA sequence between positions P[K] and Q[K] (inclusive).

    For example, consider string S = CAGCCTA and arrays P, Q such that:

        P[0] = 2    Q[0] = 4
        P[1] = 5    Q[1] = 5
        P[2] = 0    Q[2] = 6
    The answers to these M = 3 queries are as follows:

    The part of the DNA between positions 2 and 4 contains nucleotides G and C (twice), whose impact factors are 3 and 2 respectively, so the answer is 2.
    The part between positions 5 and 5 contains a single nucleotide T, whose impact factor is 4, so the answer is 4.
    The part between positions 0 and 6 (the whole string) contains all nucleotides, in particular nucleotide A whose impact factor is 1, so the answer is 1.
    Write a function:

    class Solution { public int[] solution(String S, int[] P, int[] Q); }

    that, given a non-empty string S consisting of N characters and two non-empty arrays P and Q consisting of M integers, returns an array consisting of M integers specifying the consecutive answers to all queries.

    Result array should be returned as an array of integers.

    For example, given the string S = CAGCCTA and arrays P, Q such that:

        P[0] = 2    Q[0] = 4
        P[1] = 5    Q[1] = 5
        P[2] = 0    Q[2] = 6
    the function should return the values [2, 4, 1], as explained above.

    Write an efficient algorithm for the following assumptions:

    N is an integer within the range [1..100,000];
    M is an integer within the range [1..50,000];
    each element of arrays P, Q is an integer within the range [0..N − 1];
    P[K] ≤ Q[K], where 0 ≤ K < M;
    string S consists only of upper-case English letters A, C, G, T.    
    */

    public static final String exGen = "CAGCCTA";
    public static final String exGen2 = "AACCGGTT";

    public static final int[] arrP = {2, 5, 0};
    public static final int[] arrQ = {4, 5, 6};

    public static int[] solution(String S, int[] P, int[] Q) {
        int[] result = new int[P.length];
        String[] actualString = new String[P.length];
        for (int i = 0; i < P.length; i++) {
            if (Q[i] - P[i] == 0) {
                actualString[i] = Character.toString(S.charAt(P[i]));
            } else {
                actualString[i] = S.substring(P[i], Q[i]);
            }
        }
        for (int i = 0; i < actualString.length; i++) {
            if (actualString[i].contains("A")) {
                result[i] = 1;
            } else if (actualString[i].contains("C")) {
                result[i] = 2;
            } else if (actualString[i].contains("G")) {
                result[i] = 3;
            } else if (actualString[i].contains("T")) {
                result[i] = 4;
            }
        }

        return result;
    }
    
    // nem működik

    public static int[] solution2(String S, int[] P, int[] Q) {
        TreeSet<Integer> intSet = new TreeSet<>();
        int[] result = new int[P.length];
        String[] actualString = new String[P.length];
        String converted = "";
        for (int i = 0; i < S.length(); i++) {
            switch (S.charAt(i)) {
                case 'A':
                    if (converted.isEmpty()) {
                        converted = "1";
                    }
                    converted.concat("1");
                    break;
                case 'C':
                    if (converted.isEmpty()) {
                        converted = "2";
                    }
                    converted.concat("2");
                    break;
                case 'G':
                    if (converted.isEmpty()) {
                        converted = "3";
                    }
                    converted.concat("3");
                    break;
                case 'T':
                    if (converted.isEmpty()) {
                        converted = "4";
                    }
                    converted.concat("4");
                    break;
            }
        }
        for (int i = 0; i < P.length; i++) {
            if (Q[i] - P[i] == 0) {
                actualString[i] = Character.toString(converted.charAt(P[i]));
            } else {
                actualString[i] = converted.substring(P[i], Q[i]);
            }
        }
        for (int i = 0; i < actualString.length; i++) {
            if (actualString[i].contains("1")) {
                result[i] = 1;
            } else if (actualString[i].contains("2")) {
                result[i] = 2;
            } else if (actualString[i].contains("3")) {
                result[i] = 3;
            } else if (actualString[i].contains("4")) {
                result[i] = 4;
            }
        }
        return result;
    }

    //javításra szorul
    
    public static int[] solution3(String S, int[] P, int[] Q) {
        int[] stringToNumbers = new int[S.length()];
        for (int i = 0; i < S.length(); i++) {
            switch (S.charAt(i)) {
                case 'A':
                    stringToNumbers[i] = 1;
                    break;
                case 'C':
                    stringToNumbers[i] = 2;
                    break;
                case 'G':
                    stringToNumbers[i] = 3;
                    break;
                case 'T':
                    stringToNumbers[i] = 4;
                    break;
            }
        }
        int[] solution = new int[P.length];
        for (int i = 0; i < P.length; i++) {
            int minNum = 4;
            if (P[i] == Q[i]) {
                minNum = stringToNumbers[P[i]];
            } else {
                for (int j = P[i]; j < Q[i]; j++) {
                    if (minNum > stringToNumbers[j]) {
                        minNum = stringToNumbers[j];
                    }
                }
            }
            solution[i] = minNum;
        }
        return solution;
    }

}
