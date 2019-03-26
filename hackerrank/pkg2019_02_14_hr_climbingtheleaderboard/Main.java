package pkg2019_02_14_hr_climbingtheleaderboard;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 *
 * @author Tiber
 */
//https://www.hackerrank.com/challenges/climbing-the-leaderboard/problem
public class Main {

    static int[] sampleScores01 = {100, 90, 90, 80, 75, 60};
    static int[] aliceSampleScores01 = {50, 65, 77, 90, 102};

    static int[] sampleScores02 = {100, 100, 50, 40, 40, 20, 10};
    static int[] aliceSampleScores02 = {5, 25, 50, 120};

    public static void main(String[] args) {
        int[] solution01 = climbingLeaderboard(sampleScores01, aliceSampleScores01);
        for (int i = 0; i < solution01.length; i++) {
            System.out.println(solution01[i]);
        }
        System.out.println("-- -- -- -- -- -- -- --");

        int[] solution02 = climbingLeaderboard(sampleScores02, aliceSampleScores02);
        for (int i = 0; i < solution02.length; i++) {
            System.out.println(solution02[i]);
        }

    }

    static int[] climbingLeaderboard(int[] scores, int[] alice) {

        int[] updatedScores = IntStream.of(scores).distinct().toArray();
        Arrays.sort(updatedScores);

        int placing = 0;
        int[] alicePlacings = new int[alice.length];

        for (int i = 0; i < alice.length; i++) {

            int result = Arrays.binarySearch(updatedScores, alice[i]);
            if (result < 0) {
                placing = updatedScores.length + 2 + result;
            } else {
                placing = updatedScores.length - result;
            }
            alicePlacings[i] = placing;

        }

        return alicePlacings;
    }

}
