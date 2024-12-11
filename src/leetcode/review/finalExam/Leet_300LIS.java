package leetcode.review.finalExam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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

        //각 인덱스에서 현재 LIS에 도달하기 직전의 인덱스를 기록하면, 최종적으로 최댓값을 가지는 인덱스부터 시작하여 predecessor 배열을 따라가면서 실제 LIS를 구성할 수 있다.
        int[] predecessor = new int[nums.length];
        Arrays.setAll(predecessor, e -> -1);//LIS를 역추적 하기 위해 필요한 정보를 저장하는 배열

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                //이 부분도 약간의 수정
                if (nums[j] < nums[i] && dp[j]+1 > dp[i]) {
                    dp[i] = dp[j]+1;
                    predecessor[i] = j;
                }
                /*if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[j]+1, dp[i]);
                }*/
            }
        }

        System.out.println("dp = " + Arrays.toString(dp));

        //LIS의 끝 부분의 인덱스가 필요한 것이기 때문에 여기도 약간의 수정
        int max = Integer.MIN_VALUE;
        int maxIdx = -1;
        for (int i = 0; i < nums.length; i++) {
            if(max < dp[i]) {
                max = dp[i];
                maxIdx = i;
            }
        }
        /*for (int i : dp) {
            if (i > max) {
                max = i;
            }
        }*/

        //LIS역추적 과정
        ArrayList<Integer> actualLIS = new ArrayList<>();
        int currentIdx = maxIdx;
        while (currentIdx != -1) {
            actualLIS.add(nums[currentIdx]);
            currentIdx = predecessor[currentIdx];
        }
        Collections.reverse(actualLIS);
        System.out.println("actualLIS = " + actualLIS);

        System.out.println("maxIdx = " + maxIdx);
        return max;
    }
}