package leetcode.review;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Leet_15_3Sum {
    public static void main(String[] args) {
        Leet_15_3Sum leet = new Leet_15_3Sum();
        System.out.println(leet.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
        System.out.println(leet.threeSum(new int[]{0, 1, 1}));
        System.out.println(leet.threeSum(new int[]{0, 0, 0}));
    }

    //풀긴 풀었는데 530ms가 나온다... 처음 풀었을 때 보다 더 심각한데
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        HashSet<List<Integer>> result = new HashSet<>();//확인된 조합을 담아두는 set. 중복을 제거한다.
        HashSet<Integer> logSet = new HashSet<>();//이미 검사한 targetNum을 담아두는 set, 이거 하나 추가한 걸로 시간이 530ms 에서 46ms로 줄어든다....
        //내가 처음에 풀 때는 이걸 쓰고 있었음.

        for (int i = 0; i < nums.length-2; i++) {//탐색의 범위를 줄여나감. 이래야
            int targetNum = nums[i] * -1;

            if (logSet.contains(targetNum)) {//이미 검사한 targetNum이면 다시 반복문의 처음으로 돌아감.
                continue;
            }
            logSet.add(targetNum);

            int left = i+1;
            int right = nums.length-1;

            while(left < right){
                if (nums[left] + nums[right] < targetNum) {
                    left++;
                }
                if (nums[left] + nums[right] > targetNum) {
                    right--;
                }
                if (left < right && nums[left] + nums[right] == targetNum){//nums[left] + nums[right] == targetNum
                    result.add(List.of(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                }
            }
        }

        return result.stream().toList();
    }
}
