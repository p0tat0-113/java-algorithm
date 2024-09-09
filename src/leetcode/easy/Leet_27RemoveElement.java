package leetcode.easy;

/*
Input: nums = [3,2,2,3], val = 3
Output: 2, nums = [2,2,_,_]
배열에서 val의 값을 제거하고, 아닌 값들은 다 앞으로 땡기라고 한다. 이 과정을 in-place로 하라고 함.
그리고 그 val을 제거한 배열의 길이를 반환하면 됨.

삽입하는 과정을 가리키는 변수 inserIdx를 하나 두고, 배열을 순회, 배열의 원소가 val이랑 다르면 insertIdx에 해당 원소를 삽입하고 insertIdx++
아니면 insertIdx를 그대로 두고, 해당 원소는 삽입하지 않고, 패스

0 <= nums.length <= 100

풀렸고, 속도 빠름. 바로 앞의 26번 문제와 유사하면서도 더 쉽네.
*/

import java.util.Arrays;

public class Leet_27RemoveElement {
    public static void main(String[] args) {
        System.out.println(removeElement(new int[]{0, 1, 2, 2, 3, 0, 4, 2}, 2));
    }

    public static int removeElement(int[] nums, int val) {
        int insertIdx = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[insertIdx++] = nums[i];
            }
        }

        //System.out.println(Arrays.toString(nums));

        return insertIdx;
    }
}
