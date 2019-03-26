package pkg2019_02_11_hr_formingamagicsquare;

/**
 *
 * @author Tiber
 */
//https://www.hackerrank.com/challenges/magic-square-forming/problem
public class Main {

    static int[][] sampleSquare01 = {{5, 3, 4}, {1, 5, 8}, {6, 4, 2}}; // 7
    static int[][] sampleSquare02 = {{4, 8, 2}, {4, 5, 7}, {6, 1, 6}}; // 4
    static int[][] sampleSquare03 = {{9, 2, 4}, {7, 3, 3}, {4, 3, 8}}; //20
    static int[][] sampleSquare04 = {{9, 8, 9}, {1, 6, 7}, {8, 9, 7}}; //23
    static int[][] sampleSquare05 = {{4, 9, 2}, {3, 5, 7}, {8, 1, 6}}; // 0
    static int[][] sampleSquare06 = {{4, 5, 8}, {2, 4, 1}, {1, 9, 7}}; //14
    static int[][] sampleSquare07 = {{1, 3, 8}, {6, 4, 1}, {2, 6, 5}}; // 9
    static int[][] sampleSquare08 = {{2, 5, 4}, {4, 6, 9}, {4, 5, 2}}; //16

    public static void main(String[] args) {

        System.out.println(formingMagicSquare(sampleSquare01));
        System.out.println(formingMagicSquare(sampleSquare02));
        System.out.println(formingMagicSquare(sampleSquare03));
        System.out.println(formingMagicSquare(sampleSquare04));
        System.out.println(formingMagicSquare(sampleSquare05));
        System.out.println(formingMagicSquare(sampleSquare06));
        System.out.println(formingMagicSquare(sampleSquare07));
        System.out.println(formingMagicSquare(sampleSquare08));

    }

    static int formingMagicSquare(int[][] s) {

        int minimumDifference = Integer.MAX_VALUE;

        int[][] magicSquare01 = {{2, 7, 6}, {9, 5, 1}, {4, 3, 8}};
        int[][] magicSquare02 = {{2, 9, 4}, {7, 5, 3}, {6, 1, 8}};
        int[][] magicSquare03 = {{4, 9, 2}, {3, 5, 7}, {8, 1, 6}};
        int[][] magicSquare04 = {{6, 7, 2}, {1, 5, 9}, {8, 3, 4}};
        int[][] magicSquare05 = {{8, 1, 6}, {3, 5, 7}, {4, 9, 2}};
        int[][] magicSquare06 = {{8, 3, 4}, {1, 5, 9}, {6, 7, 2}};
        int[][] magicSquare07 = {{4, 3, 8}, {9, 5, 1}, {2, 7, 6}};
        int[][] magicSquare08 = {{6, 1, 8}, {7, 5, 3}, {2, 9, 4}};

        int[] magicSquareDifferences = {0, 0, 0, 0, 0, 0, 0, 0};

        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s[i].length; j++) {
                magicSquareDifferences[0] += Math.abs(s[i][j] - magicSquare01[i][j]);
                magicSquareDifferences[1] += Math.abs(s[i][j] - magicSquare02[i][j]);
                magicSquareDifferences[2] += Math.abs(s[i][j] - magicSquare03[i][j]);
                magicSquareDifferences[3] += Math.abs(s[i][j] - magicSquare04[i][j]);
                magicSquareDifferences[4] += Math.abs(s[i][j] - magicSquare05[i][j]);
                magicSquareDifferences[5] += Math.abs(s[i][j] - magicSquare06[i][j]);
                magicSquareDifferences[6] += Math.abs(s[i][j] - magicSquare07[i][j]);
                magicSquareDifferences[7] += Math.abs(s[i][j] - magicSquare08[i][j]);

            }
        }

        for (int i = 0; i < magicSquareDifferences.length; i++) {
            if (minimumDifference > magicSquareDifferences[i]) {
                minimumDifference = magicSquareDifferences[i];
            }
        }

        return minimumDifference;
    }

}
