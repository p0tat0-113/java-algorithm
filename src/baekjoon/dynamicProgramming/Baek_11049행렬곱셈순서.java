package baekjoon.dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
이건 그냥 알고리즘 이론 시간에 배웠던 행렬곱셈순서 문제다.
점화식을 알고 있어서 쉽게 풀 수 있음.
*/
public class Baek_11049행렬곱셈순서 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()) ;
        int[] arr = new int[n + 1];

        for (int i = 0; i < n; i++) {
            String[] strings = br.readLine().split(" ");
            arr[i] = Integer.parseInt(strings[0]);
            arr[i+1] = Integer.parseInt(strings[1]);
        }

        System.out.println(matrixChain(n, arr));
    }

    private static int matrixChain(int n, int[] p){
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = 0;
        }

        for (int r = 1; r < n; r++) {
            for (int i = 0; i < n-r; i++) {
                int j = i + r;

                int min = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int temp = dp[i][k] + dp[k+1][j] + p[i] * p[k+1] * p[j+1];//원래는 p[i-1] * p[k] * p[j]이지만 여기는 인덱스 문제로 이렇게 함.
                    if (min > temp) {
                        min = temp;
                    }
                }

                dp[i][j] = min;
            }
        }
        System.out.println(Arrays.toString(p));
        printMatrix(dp);
        return dp[0][dp[0].length-1];
    }

    private static void printMatrix(int[][] dp) {
        for (int[] ints : dp) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
