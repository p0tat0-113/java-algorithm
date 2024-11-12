package leetcode.medium;

import java.util.Arrays;
import java.util.HashSet;

/*
https://imgur.com/a/TnAyVet
이렇게 해결했다. DP문제이긴 하지만, 그리 어려운 문제는 아니었음.

1 <= nums.length <= 200
1 <= nums[i] <= 1000
All the elements of nums are unique.
1 <= target <= 1000
*/
public class Leet_377CombinationSumVI {
    public static void main(String[] args) {
        Leet_377CombinationSumVI leet = new Leet_377CombinationSumVI();
        System.out.println(leet.combinationSum4(new int[] {1, 2, 3}, 4));
        System.out.println(leet.combinationSum4(new int[] {9}, 3));
        System.out.println(leet.combinationSum4(new int[] {1,2,3,5}, 15));
        System.out.println(leet.combinationSum4(new int[] {1,2,3,4}, 4));
        System.out.println(leet.combinationSum4(new int[] {1,2,3,4}, 5));
        System.out.println(leet.combinationSum4(new int[] {1,2,3,4}, 6));
        System.out.println(leet.combinationSum4(new int[] {1,2,3,4}, 7));
    }

    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target+1];

        for (int num : nums) {
            if (num <= target) {
                dp[num] = 1;
            }
        }

        for (int i = 1; i < target+1; i++) {
            int sum = 0;

            for (int num : nums) {
                if (i-num >= 0) {
                    dp[i] += dp[i-num];
                }
            }
        }

        //System.out.println(Arrays.toString(dp));
        return dp[target];
    }
}
