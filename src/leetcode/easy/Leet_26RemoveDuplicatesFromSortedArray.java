package leetcode.easy;

/*
Input: nums = [1,1,2]
Output: 2, nums = [1,2,_]
Explanation: Your function should return k = 2, with the first two elements of nums being 1 and 2 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).

in-place하게 nums에서 중복된 숫자들을 제거하라고 한다. 처음의 정렬된 순서는 그대로 유지해야 한다고 함. _로 표시된 부분은 무슨 숫자가 오던 상관 없다는 뜻이라고 함.

우선은 배열을 순회하면서 바로 전에 나온 숫자와, 지금 나온 숫자를 비교하면서 중복된건지 아닌지 판단을 하고,
숫자를 삽입할 자리를 가리키는 변수를 하나 두어서 지금 숫자가 새로 나온 숫자일 때만 거기에 삽입을 하고, 삽입할 자리를 가리키는 변수를 전진시키는 식으로 하면 될 듯.

1 <= nums.length <= 3 * 104
-100 <= nums[i] <= 100
nums is sorted in non-decreasing order.
*/

import java.util.Arrays;

public class Leet_26RemoveDuplicatesFromSortedArray {

    public static void main(String[] args) {
        Leet_26RemoveDuplicatesFromSortedArray leet = new Leet_26RemoveDuplicatesFromSortedArray();
        System.out.println(leet.removeDuplicates(new int[]{0, 0, 1, 2, 2, 3}));
    }

    //0,0,1,2,2,3
    public int removeDuplicates(int[] nums) {
        int previousNum = nums[0];//바로 이전 자리의 숫자를 가리키는 변수
        int currentNum;//현재 자리의 숫자를 가리키는 변수
        int insertionIdx = 1;//처음으로 출현한 숫자일 경우 삽입될 위치를 가리키는 변수, 이전에 나온 적 없는 새로운 숫자가 등장할 때 마다 1씩 증가한다.

        for (int i = 1; i < nums.length; i++) {
            currentNum = nums[i];
            if (previousNum != currentNum) {//이전에 나온 적 없는 새로운 숫자라면
                nums[insertionIdx++] = currentNum;//insertionIdx가 가리키는 자리에 삽입
            }
            previousNum = nums[i];//다음 회차를 위해 최신화
        }

        //System.out.println(Arrays.toString(nums));

        return insertionIdx;//insertionIdx를 그대로 반환하면 중복을 제거한 새로운 배열의 길이와 같다.
    }
}
