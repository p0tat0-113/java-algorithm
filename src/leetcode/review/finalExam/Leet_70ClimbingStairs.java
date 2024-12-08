package leetcode.review.finalExam;

public class Leet_70ClimbingStairs {
    public static void main(String[] args) {
        Leet_70ClimbingStairs leet = new Leet_70ClimbingStairs();
        System.out.println(leet.climbStairs(3));
        System.out.println(leet.climbStairs(4));
    }

    //1걸음 혹은 2걸음
    //1 : 1
    //2 : 2
    //3 : 3
    //4 : 5
    /*
    1 1 1 1
    2 2
    1 1 2
    2 1 1
    1 2 1
    */
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }

        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }
}
