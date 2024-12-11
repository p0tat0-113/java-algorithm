package leetcode.review.finalExam;

import java.util.Arrays;

public class Leet_213HouseRobber2 {
    public static void main(String[] args) {
        Leet_213HouseRobber2 leet = new Leet_213HouseRobber2();
        System.out.println(leet.rob(new int[]{2,3,2}));
        System.out.println(leet.rob(new int[]{1,2,3,1}));
        System.out.println(leet.rob(new int[]{1,2,3}));
    }

    //앞의 house robber1 문제의 응용버전이다. 이번에는 집들이 원형으로 배치되어있음.
    //첫번째 집을 털면 마지막 집을 못털고, 마지막 집을 털면 첫번쨰 집을 털지 못한다.
    //이렇게 두가지 경우로 나누어서 house robber1 문제의 알고리즘을 약간 개조해서 2번 돌리면 된다.

    //dp[i] = max{dp[i-1], dp[i-2] + m[i]}
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        return Math.max(process(nums, 0, nums.length-2), process(nums, 1, nums.length-1));
    }

    private int process(int[] nums, int start, int end) {
        //1번째 집과 2번째 집은 수동으로 초기화해줘야 한다.
        int[] dp = new int[nums.length];
        dp[start] = nums[start];
        dp[start+1] = Math.max(nums[start], nums[start+1]);

        //dp배열을 점화식대로 계속 채워나간다.
        for (int i = start+2; i <= end; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
        }

        System.out.println(Arrays.toString(dp));
        if (start == 0) {//끝집은 제외하고 보는 경우
            return dp[dp.length-2];
        }
        return dp[dp.length-1];
    }
}
