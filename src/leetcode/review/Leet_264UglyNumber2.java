package leetcode.review;

import java.util.Arrays;

public class Leet_264UglyNumber2 {
    public static void main(String[] args) {
        Leet_264UglyNumber2 leet = new Leet_264UglyNumber2();
        System.out.println(leet.nthUglyNumber(10));
    }

    public int nthUglyNumber(int n) {
        int[] processArr = new int[n];
        processArr[0] = 1;

        int multi2Idx = 0;
        int multi3Idx = 0;
        int multi5Idx = 0;

        int result2 = 2;
        int result3 = 3;
        int result5 = 5;

        for (int i = 1; i < n; i++) {
            int minResult = Math.min(result2, Math.min(result3, result5));
            processArr[i] = minResult;

            if (minResult == result2) {
                multi2Idx++;
                result2 = 2 * processArr[multi2Idx];
            }
            if (minResult == result3) {
                multi3Idx++;
                result3 = 3 * processArr[multi3Idx];
            }
            if (minResult == result5){
                multi5Idx++;
                result5 = 5 * processArr[multi5Idx];
            }
        }

        System.out.println(Arrays.toString(processArr));

        return processArr[n-1];
    }
}
