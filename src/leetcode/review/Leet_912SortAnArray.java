package leetcode.review;

import java.util.Arrays;
import java.util.Random;

public class Leet_912SortAnArray {
    public static void main(String[] args) {
        Leet_912SortAnArray leet = new Leet_912SortAnArray();
        System.out.println(Arrays.toString(leet.sortArray(new int[]{5, 1, 1, 2, 0, 0})));
        System.out.println(Arrays.toString(leet.sortArray(new int[]{5,2,3,1})));
    }

    public int[] sortArray(int[] nums) {
        Random random = new Random();
        quickSort(nums, 0, nums.length-1, random);
        return nums;
    }

    private void quickSort(int[] nums, int s, int e, Random random) {
        if (s < e) {

            //pivot을 랜덤으로 선정
            int randomIdx = random.nextInt(s,e+1);
            int temp = nums[randomIdx];
            nums[randomIdx] = nums[s];//이 부분을 멍청하게 nums[0]으로 해버려서 몇 분 헤맴.
            nums[s] = temp;

            //분할, pivot의 인덱스를 얻음.
            int pivot = partition(nums, s, e);

            quickSort(nums, s, pivot-1, random);
            quickSort(nums, pivot+1, e, random);
        }
    }

    private int partition(int nums[], int s, int e){
        int pivot = nums[s];
        int low = s+1;
        int high = e;

        while (low <= high) {
            if (nums[low] < pivot) {
                low++;
            } else if (nums[high] > pivot) {
                high--;
            } else {
                int temp = nums[low];
                nums[low] = nums[high];
                nums[high] = temp;
                low++;
                high--;
            }
        }

        nums[s] = nums[high];
        nums[high] = pivot;

        return high;
    }
}
