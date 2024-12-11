package leetcode.review.finalExam;

public class Leet_198HouseRobber1 {
    public static void main(String[] args) {
        Leet_198HouseRobber1 leet = new Leet_198HouseRobber1();
        System.out.println(leet.rob(new int[]{1,2,3,1}));
        System.out.println(leet.rob(new int[]{2,7,9,3,1}));
    }

    //dp[i] = max{dp[i-1], dp[i-2] + m[i]}
    public int rob(int[] nums) {
        //1번째 집과 2번째 집은 수동으로 초기화해줘야 한다.
        int[] dp = new int[nums.length];

        dp[0] = nums[0];
        if (nums.length == 1) {
            return dp[0];
        }

        dp[1] = Math.max(nums[0], nums[1]);
        if (nums.length == 2) {
            return dp[1];
        }

        //dp배열을 점화식대로 계속 채워나간다.
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
        }

        //System.out.println(Arrays.toString(dp));
        return dp[dp.length-1];
    }
}
