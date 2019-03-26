package pkg2019_02_09_hr_drawingbook;

/**
 *
 * @author Tiber
 */
//https://www.hackerrank.com/challenges/drawing-book/problem
public class Main {

    public static void main(String[] args) {
        System.out.println(pageCount(7, 1));
        System.out.println(pageCount(5, 4));
        System.out.println(pageCount(9, 8));
        System.out.println(pageCount(6, 2));
        System.out.println(pageCount(7, 4));
        System.out.println(pageCount(14, 9));
        System.out.println(pageCount(14, 8));
    }

    static int pageCount(int n, int p) {

        int frontCounter = 0;
        int backCounter = 0;
        int fcResult = 0;
        int bcResult = 0;

        if ((p == 1) || n == p || ((n % 2) != 0) && (n - 1 == p)) {
            return 0;
        }

        for (int i = 0; i < n; i++) {
            if (frontCounter == p) {
                fcResult = i / 2;
                break;
            }
            frontCounter++;
        }

        int nNum = n;
        if (nNum % 2 == 0) {
            nNum++;
        }

        for (int i = nNum; i > -1; i--) {
            if (backCounter == p) {
                bcResult = i / 2;
                break;
            }
            backCounter++;
        }

        if (fcResult < bcResult) {
            return fcResult;
        } else {
            return bcResult;
        }
    }
}
