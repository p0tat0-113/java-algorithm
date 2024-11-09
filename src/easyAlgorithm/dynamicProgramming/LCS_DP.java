package easyAlgorithm.dynamicProgramming;

public class LCS_DP {
    public static void main(String[] args) {
        /*String stringX = "ADBDC";
        String stringY = "ABC";*/
        String stringX = "ABCBDAB";
        String stringY = "BDCAB";

        String[] stringsX = stringX.split("");
        String[] stringsY = stringY.split("");
        int[][] matrix = new int[stringsX.length + 1][stringsY.length + 1];

        System.out.println(dp(stringsX, stringsY, matrix));
        printMatrix(matrix);
    }

    //recursion(i,j)는 X의 길이가 i일 때와, Y의 길이가 j일 때의 LCS의 길이를 의미한다.
    private static int dp(String[] X, String[] Y, int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][0] = 0;
        }
        for (int i = 0; i < matrix[0].length; i++) {
            matrix[0][i] = 0;
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (X[i-1].equals(Y[j-1])) {
                    matrix[i][j] = matrix[i-1][j-1] + 1;
                } else {
                    matrix[i][j] = Math.max(matrix[i-1][j], matrix[i][j-1]);
                }
            }
        }

        return matrix[X.length][Y.length];
    }

    private static void printMatrix(int[][] matrix){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.printf("%-3d", matrix[i][j]);
            }
            System.out.println();
        }
    }
}
