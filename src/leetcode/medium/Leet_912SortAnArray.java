package leetcode.medium;

/*
주어진 정수 배열을 정렬하기만 하는 아주 단순한 문제이다. 단 절대로 내장 정렬 함수는 쓰지 말라고 한다. 정렬 알고리즘을 직접 구현하라고 함.
퀵소트 방식으로 한 번 직접 구현해보자. 주어지는 배열의 숫자들이 중복될 수 있다고 한다.
*/

import java.util.Arrays;
import java.util.Random;

public class Leet_912SortAnArray {
    public int[] sortArray(int[] nums) {
        Random random = new Random();
        quickSort(nums, 0, nums.length-1, random);
        return nums;
    }

    private void quickSort(int[] nums, int start, int end, Random random){
        if (start < end) {
            //이미 정렬된 배열이 들어오는 경우에 대비해서 Pivot을 랜덤한 숫자로 정해준다.
            int randomIdx = random.nextInt(end-start+1) + start;
            int temp = nums[start];
            nums[start] = nums[randomIdx];
            nums[randomIdx] = temp;

            int mid = partition(nums, start, end);

            quickSort(nums, start, mid-1, random);
            quickSort(nums, mid+1, end, random);//이 부분에서 end를 넣어줘야 하는데 실수로 start를 넣어서 정렬이 안되는 문제를 해결하느라 고민함.
        }
    }

    private int partition(int[] nums, int start, int end) {
        int pivot = nums[start];
        int low = start+1;
        int high = end;

        while (low <= high) {//low와 high가 교차되지 않는 동안 계속 반복
            if (nums[low] < pivot) {
                low++;
            } else if (nums[high] > pivot) {
                high--;
            } else {
                int temp = nums[low];
                nums[low] = nums[high];
                nums[high] = temp;

                //이 부분을 빼먹어서 정렬이 제대로 되지 않은 경우가 발생함.
                low++;
                high--;
            }
        }

        nums[start] = nums[high];
        nums[high] = pivot;

        return high;
    }

    public static void main(String[] args) {
        Leet_912SortAnArray leet912SortAnArray = new Leet_912SortAnArray();
        System.out.println(Arrays.toString(leet912SortAnArray.sortArray(new int[]{5,1,1,2,0,0})));
    }
}
