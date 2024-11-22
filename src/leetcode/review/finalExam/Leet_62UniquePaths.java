package leetcode.review.finalExam;

public class Leet_62UniquePaths {
    public static void main(String[] args) {

    }

    //이것도 정말 간단한 DP문제다.
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < dp[0].length; i++) {
            dp[0][i] = 1;
        }

        for (int row = 1; row < dp.length; row++) {
            for (int column = 1; column < dp[0].length; column++) {
                dp[row][column] = dp[row][column-1] + dp[row-1][column];
            }
        }

        return dp[m-1][n-1];
    }
}
