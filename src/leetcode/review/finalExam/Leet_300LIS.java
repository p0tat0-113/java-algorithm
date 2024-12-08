package leetcode.review.finalExam;

import java.util.Arrays;

public class Leet_300LIS {
    public static void main(String[] args) {
        Leet_300LIS leet = new Leet_300LIS();
        System.out.println(leet.lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
        System.out.println(leet.lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3}));
        System.out.println(leet.lengthOfLIS(new int[]{7, 7, 7, 7, 7, 7}));
    }

    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.setAll(dp, e -> 1);

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[j]+1, dp[i]);
                }
            }
        }

        //System.out.println(Arrays.toString(dp));

        int max = Integer.MIN_VALUE;
        for (int i : dp) {
            if (i > max) {
                max = i;
            }
        }

        return max;
    }
}