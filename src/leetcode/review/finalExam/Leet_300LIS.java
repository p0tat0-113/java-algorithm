package leetcode.review.finalExam;

import java.util.Arrays;

public class Leet_300LIS {
    public static void main(String[] args) {
        Leet_300LIS leet = new Leet_300LIS();
        System.out.println(leet.lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
        System.out.println(leet.lengthOfLIS(new int[]{0,1,0,3,2,3}));
        System.out.println(leet.lengthOfLIS(new int[]{7,7,7,7,7,7}));
    }

    public int lengthOfLIS(int[] nums) {
        //System.out.println(Arrays.toString(nums));
        int[] arr = new int[nums.length];//arr[i]는 nums[i]로 끝나는 LIS의 길이를 의미한다.
        Arrays.setAll(arr, e -> 1);

        for (int i = 1; i < nums.length; i++) {//1 ~ n-1까지 순회
            for (int k = 0; k < i; k++) {//0 ~ i-1까지 순회
                if (nums[i] > nums[k]) {
                    arr[i] = Integer.max(arr[k]+1, arr[i]);
                    //처음에 이 부분을 arr[i] = Integer.max(arr[k]+1, arr[i]+1) 이렇게 처리하고 있어서 계속 너무 큰 값이 나오고 있었음. 원리를 잘 생각하고 문제를 풀자.
                    //2,5,3,7 이런 상황에서 문제가 발생했었다.
                }
            }
            //System.out.println(Arrays.toString(arr));
        }

        //System.out.println(Arrays.toString(arr));

        int max = Integer.MIN_VALUE;
        for (int i : arr) {
            if (max < i) {
                max = i;
            }
        }
        return max;
    }
}
