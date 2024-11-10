package leetcode.medium;

import java.util.Arrays;

public class Leet_300LongestIncreasingSubsequence {
    public static void main(String[] args) {
        Leet_300LongestIncreasingSubsequence leet = new Leet_300LongestIncreasingSubsequence();

        int[] arr1 = {10,9,2,5,3,7,101,18};
        System.out.println(leet.lengthOfLIS(arr1));

        int[] arr2 = {0,1,0,3,2,3};
        System.out.println(leet.lengthOfLIS(arr2));

        int[] arr3 = {7,7,7,7,7,7,7};
        System.out.println(leet.lengthOfLIS(arr3));
    }

    public int lengthOfLIS(int[] nums) {
        //nums의 정렬된 버전인 sortedNums를 만든다. 이후 nums와 sortedNums의 LCS(Longest Common Subsequence)를 구할 것이다.
        int[] sortedNums = new int[nums.length];
        System.arraycopy(nums, 0, sortedNums, 0, nums.length);
        Arrays.sort(sortedNums);

        int[][] matrix = new int[nums.length+1][nums.length+1];//i,j 둘 중 하나가 0인 상황을 위해 첫번째 열과 첫번째 행을 0으로 초기화 하는 과정은 생략해도 됨.

        for (int i = 1; i <= nums.length; i++) {
            for (int j = 1; j <= nums.length; j++) {
                if (nums[i-1] == sortedNums[j-1]) {
                    matrix[i][j] = matrix[i-1][j-1] + 1;
                } else {
                    matrix[i][j] = Math.max(matrix[i-1][j], matrix[i][j-1]);
                }
            }
        }

        return matrix[nums.length][nums.length];
    }
}
