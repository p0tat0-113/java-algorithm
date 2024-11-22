package leetcode.review.finalExam;

public class Leet_70ClimbingStairs {
    public static void main(String[] args) {

    }

    //아주 간단한 DP문제다. 피보나치 수를 만드는 것과 같은 규칙으로 움직임.
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }

        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }
}
