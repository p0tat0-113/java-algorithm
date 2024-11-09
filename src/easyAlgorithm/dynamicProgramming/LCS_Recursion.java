package easyAlgorithm.dynamicProgramming;

public class LCS_Recursion {
    public static void main(String[] args) {
        /*String stringX = "ADBDC";
        String stringY = "ABC";*/
        String stringX = "ABCBDAB";
        String stringY = "BDCAB";

        String[] stringsX = stringX.split("");
        String[] stringsY = stringY.split("");

        System.out.println(recursion(stringsX.length, stringsY.length, stringsX, stringsY));
    }

    //recursion(i,j)는 X의 길이가 i일 때와, Y의 길이가 j일 때의 LCS의 길이를 의미한다.
    private static int recursion(int i, int j, String[] X, String[] Y) {
        if (i == 0 || j == 0) {
            return 0;
        }

        if (X[i-1].equals(Y[j-1])) {//Xi == Yj
            return recursion(i-1, j-1, X, Y) + 1;
        } else {//Xi != Yj
            return Math.max(recursion(i-1, j, X, Y), recursion(i, j-1, X, Y));
        }
    }
}
