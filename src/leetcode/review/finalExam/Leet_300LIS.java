package leetcode.review.finalExam;

import java.util.Arrays;

public class Leet_300LIS {
    public static void main(String[] args) {
        Leet_300LIS leet = new Leet_300LIS();
        System.out.println(leet.lengthOfLIS(new int[] {10,9,2,5,3,7,101,18}));
        System.out.println(leet.lengthOfLIS(new int[] {0,1,0,3,2,3}));
        System.out.println(leet.lengthOfLIS(new int[] {7,7,7,7,7,7}));
    }

    //LIS longest increasing subsequence의 길이를 구해야 하는 문제다.
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];//dp[i]는 i번째 원소까지 고려했을 때의 LIS길이다.
        Arrays.setAll(dp, e -> 1);//어떠한 경우에도 LIS의 길이는 최소한 1이다.

        int max = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {//i번째 원소가 그 앞에 있는 j번째 원소보다 크다면, i번째 원소까지 고려했을 때의 LIS의 길이는 최소한 j번째 원소까지 고려했을 때의 LIS의 길이 + 1이 된다.
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            //dp의 숫자들 중 가장 큰 수를 고르는 과정
            if (max < dp[i]) {
                max = dp[i];
            }
        }
        //System.out.println(Arrays.toString(dp));
        return max;
    }

    //LIS longest increasing subsequence의 길이를 구해야 하는 문제다.
    //처음에 여기까지만 하고 끝냈었는데 여기에서 끝내면 안된다. 원리를 생각해보면 당연하게도 nums의 마지막 원소라고 답이 아니다.
    /*public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];//dp[i]는 i번째 원소까지 고려했을 때의 LIS길이다.
        Arrays.setAll(dp, e -> 1);//어떠한 경우에도 LIS의 길이는 최소한 1이다.

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }
        //System.out.println(Arrays.toString(dp));
        return dp[nums.length-1];
    }*/
}
