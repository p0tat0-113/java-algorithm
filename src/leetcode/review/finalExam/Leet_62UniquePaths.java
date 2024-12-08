package leetcode.review.finalExam;

public class Leet_62UniquePaths {
    public static void main(String[] args) {
        Leet_62UniquePaths leet = new Leet_62UniquePaths();
        System.out.println(leet.uniquePaths(3, 7));
        System.out.println(leet.uniquePaths(3, 2));
    }

    //이것도 정말 간단한 DP문제다.
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < dp[0].length; i++) {
            dp[0][i] = 1;
        }
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }

        return dp[m-1][n-1];
    }
}
