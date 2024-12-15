package baekjoon.dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/*
dp 최소 행렬 곱 비용을 구하는 문제랑 비슷한 것 같아서 쉽게 생각하고 덤볐는데 생각보다 좀 애를 먹었다.
재귀적으로 두 집단을 더하는 비용까지는 쉽게 구했지만, 비용의 누적까지 챙기는데 좀 애를 먹었음.
https://imgur.com/a/VXiLHJc
*/

public class Baek_11066CombineFiles {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<int[]> arrayList = new ArrayList<>();

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int length = Integer.parseInt(br.readLine());
            int[] arr = new int[length];

            String[] strings = br.readLine().split(" ");
            for (int j = 0; j < arr.length; j++) {
                arr[j] = Integer.parseInt(strings[j]);
            }

            arrayList.add(arr);
        }

        for (int[] arr : arrayList) {
            process(arr.length, arr);
        }
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
                    int temp = dp[i][k] + dp[k+1][j] + cost[i][k] + cost[k+1][j];//두 집단의 비용 누적 + 두 집단을 더하는 비용
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
        System.out.println(dp[0][dp[0].length-1]);//결과 출력
    }

    private static void printMatrix(int[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
    }
}
