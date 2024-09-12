package leetcode.medium;

/*
Input: nums = [3,6,9,1]
Output: 3
Explanation: The sorted form of the array is [1,3,6,9], either (3,6) or (6,9) has the maximum difference 3.
Input: nums = [10]
Output: 0
Explanation: The array contains less than 2 elements, therefore return 0.

배열을 정렬하고, 숫자간의 간격 중 제일 큰 간격을 반환하는 문제다. 배열의 길이가 1이면 0을 반환하면 됨.
일단 문제 내용을 봤을 때는 별거 없어 보임. 배열을 정렬하고, for루프를 돌려서 갭을 계산하고, 그 중 가장 큰 값을 고르면 됨.

런타임 40ms로 60프로를 땄음, 최고는 아니지만 나쁘지 않은 듯.
*/

import java.util.Arrays;

public class Leet_164MaximumGap {
    public static void main(String[] args) {
        Leet_164MaximumGap leet = new Leet_164MaximumGap();
        System.out.println(leet.maximumGap(new int[]{3, 6, 9, 1}));
    }

    public int maximumGap(int[] nums) {
        Arrays.sort(nums);
        int max = 0;//최대값을 저장하는 변수
        int gap;//계산된 갭을 저장하는 변수

        for (int i = 0; i < nums.length-1; i++) {//인덱스가 0~4까지일 때 0~3까지만 반복을 돌림.
            gap = nums[i+1]-nums[i];//갭 계산
            if (max < gap) {
                max = gap;
            }
        }

        return max;
    }
}

