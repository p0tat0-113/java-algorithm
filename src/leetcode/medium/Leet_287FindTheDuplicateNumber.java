package leetcode.medium;

import java.util.Arrays;
import java.util.HashSet;

public class Leet_287FindTheDuplicateNumber {
    public static void main(String[] args) {

    }


    //아래의 두 풀이방법은, 문제의 조건에 맞지 않는 풀이방법이다. 그리고 제출했을 때 수행시간이 느린걸 보니까 O(logn)의 풀이 방법이 있는 것으로 보인다.
    /*public int findDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length-1; i++) {
            if (nums[i] == nums[i+1]) {
                return nums[i];
            }
        }
        return 0;
    }*/

    public int findDuplicate(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }

        HashSet<Integer> set = new HashSet<>(max);
        for (int num : nums) {
            if (set.contains(num)) {
                return num;
            }
            set.add(num);
        }
        return 0;
    }
}
