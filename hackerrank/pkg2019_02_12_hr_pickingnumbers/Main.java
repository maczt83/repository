package pkg2019_02_12_hr_pickingnumbers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author Tiber
 */
//https://www.hackerrank.com/challenges/picking-numbers/problem
public class Main {

    static List<Integer> sampleNumbers = new ArrayList<>();

    public static void main(String[] args) {

        sampleNumbers.add(1);
        sampleNumbers.add(4);
        sampleNumbers.add(2);
        sampleNumbers.add(3);
        sampleNumbers.add(5);
        sampleNumbers.add(1);
        sampleNumbers.add(2);
        sampleNumbers.add(1);
        sampleNumbers.add(4);
        sampleNumbers.add(3);

        System.out.println(pickingNumbers(sampleNumbers));
    }

    public static int pickingNumbers(List<Integer> a) {

        HashSet<Integer> actualNumbers = new HashSet<>();

        for (Integer a1 : a) {
            actualNumbers.add(a1);
        }

        int counter = 0;

        for (Integer actualNumber : actualNumbers) {
            int innerCounter = 0;
            for (Integer a1 : a) {
                if (a1 > actualNumber + 1) {
                    continue;
                }
                if (a1 == actualNumber || a1 == actualNumber + 1) {
                    innerCounter++;
                }
                if (counter < innerCounter) {
                    counter = innerCounter;
                }
            }
        }

        return counter;
    }

}
