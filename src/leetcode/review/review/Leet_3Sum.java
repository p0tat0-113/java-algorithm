package leetcode.review.review;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Leet_3Sum {
    public static void main(String[] args) {
        Leet_3Sum leet = new Leet_3Sum();

        int[] arr1 = {2, 4, 5, 3, 1};
        leet.mergeSort(arr1, 0, arr1.length-1);
        System.out.println(Arrays.toString(arr1));

        System.out.println(leet.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        mergeSort(nums, 0, nums.length-1);//먼저 nums를 정렬한다.

        HashSet<List<Integer>> result = new HashSet<>();
        HashSet<Integer> log = new HashSet<>();

        for (int i = 0; i < nums.length-2; i++) {
            int targetNum = nums[i]*-1;
            if (log.contains(targetNum)) {
                continue;
            }
            log.add(targetNum);

            int low = i+1;
            int high = nums.length-1;

            while (low < high) {
                if (nums[low] + nums[high] < targetNum) {
                    low++;
                } else if (nums[low] + nums[high] > targetNum) {
                    high--;
                } else {
                    result.add(List.of(nums[i],nums[low],nums[high]));
                    low++;
                    high--;
                }
            }
        }

        return new ArrayList<>(result);
    }

    private void mergeSort(int[] arr, int s, int e){
        if (s < e) {
            int mid = (s+e)/2;

            mergeSort(arr,s,mid);
            mergeSort(arr,mid+1,e);

            merge(arr, s, mid, e);
        }
    }

    private void merge(int[] arr, int s, int mid, int e){
        int[] tempArr = new int[e - s + 1];
        int tempArrLength = 0;
        int left = s;
        int right = mid+1;

        while (left <= mid && right <= e) {
            if (arr[left] < arr[right]) {
                tempArr[tempArrLength++] = arr[left++];
            } else {
                tempArr[tempArrLength++] = arr[right++];
            }
        }

        while (left <= mid) {
            tempArr[tempArrLength++] = arr[left++];
        }
        while (right <= e) {
            tempArr[tempArrLength++] = arr[right++];
        }

        System.arraycopy(tempArr, 0, arr, s, tempArrLength);
    }
}
