package easyAlgorithm.dynamicProgramming;

/*책에 나오는 행렬 곱셈 순서 문제를 먼저 재귀로 구현해본다.
점화식은 아래와 같다.

c(i,j) = {
if i=j -> 0
if i<j -> min{c(i,k) + c(k+1,j) + p(i-1)*p(k)*(j)} <- i <= k <= j-1
}
*/

import java.util.Arrays;

public class 행렬곱셈순서문제_DP {
    public static void main(String[] args) {

        int n = 4;
        int[] p = {10, 30, 5, 60, 10};//A1 ~ A4, 각 행렬의 크기를 기록해놓은 배열이다.
        int[][] matrix = new int[n+1][n+1];

        System.out.println(dp(n, p, matrix));
        printMatrix(matrix);
    }

    //bottom - up 방식으로 구현.
    private static int dp(int n, int[] p, int[][] matrix){
        //matrix[i][j]는 행렬 Ai ~ Aj를 곱하는 최소비용을 의미한다. 의미한다.

        for (int i = 1; i <= n; i++) {//먼저 i==j인 경우를 모두 표시
            matrix[i][i] = 0;
        }

        for (int r = 1; r <= n; r++) {
            for (int i = 1; i <= n - r; i++) {
                int j = i+r;

                //m[i][j] = min{m[i][k] + m[k+1][j] + p[i]*p[k]*p[j]}
                int min = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {//k에 따라 분할되는 비율, 괄호가 처지는 비율이 달라진다. i:j-i
                    int temp = matrix[i][k] + matrix[k+1][j] + p[i-1]*p[k]*p[j];
                    if (min > temp) {
                        min = temp;
                    }
                }
                matrix[i][j] = min;
            }
        }

        return matrix[1][n];
    }

    private static void printMatrix(int[][] matrix){
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix.length; j++) {
                System.out.printf("%-5d", matrix[i][j]);
            }
            System.out.println();
        }
    }
}
