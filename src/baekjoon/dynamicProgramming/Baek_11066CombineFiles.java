package baekjoon.dynamicProgramming;

import java.util.Arrays;

public class Baek_11066CombineFiles {
    public static void main(String[] args) {
        process(4, new int[] {40,30,30,50});
        process(15, new int[] {1,21,3,4,5,35,5,4,3,5,98,21,14,17,32});
    }

    private static void process(int n, int[] nums) {
        int[][] dp = new int[n][n];//얘는 비용의 누적을 저장한다.
        for (int i = 0; i < n; i++) {
            dp[i][i] = 0;
        }

        int[][] cost = new int[n][n];//얘는 단순히 두 집단을 더하는 비용을 저장한다.
        for (int i = 0; i < n; i++) {
            cost[i][i] = nums[i];
        }

        for (int r = 1; r < n; r++) {
            for (int i = 0; i < n-r; i++) {
                int j = i + r;

                int min = Integer.MAX_VALUE;//가장 최소의 누적비용
                int minK = -1;//누적비용이 가장 최소일 때의 k값

                for (int k = i; k < j; k++) {//i <= k <= j-1
                    int temp = dp[i][k] + dp[k+1][j] + cost[i][k] + cost[k+1][j];
                    if (min > temp) {
                        min = temp;
                        minK = k;
                    }
                }
                cost[i][j] = cost[i][minK] + cost[minK+1][j];
                dp[i][j] = min;
            }
        }

        printMatrix(dp);
        printMatrix(cost);
    }

    private static void printMatrix(int[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
    }
}
