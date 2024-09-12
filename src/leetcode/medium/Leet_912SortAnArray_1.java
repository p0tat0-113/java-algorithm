package leetcode.medium;

/*
주어진 정수 배열을 정렬하기만 하는 아주 단순한 문제이다. 단 절대로 내장 정렬 함수는 쓰지 말라고 한다. 정렬 알고리즘을 직접 구현하라고 함.
이미 푼 문제이지만, 과제 제출을 위해 병합정렬 방식으로 다시 구현해본다.
*/

import java.util.Arrays;
import java.util.Random;

public class Leet_912SortAnArray_1 {
    public static void main(String[] args) {
        Leet_912SortAnArray_1 leet912SortAnArray = new Leet_912SortAnArray_1();
        System.out.println(Arrays.toString(leet912SortAnArray.sortArray(new int[]{5,1,1,2,0,0})));
    }

    public int[] sortArray(int[] nums) {
        mergeSort(nums, 0, nums.length-1);
        return nums;
    }

    private void mergeSort(int[] nums, int start, int end){
        if (start < end) {//배열의 길이가 1보다 클 때만 재귀호출
            int mid = (end+start)/2;
            mergeSort(nums,start,mid);
            mergeSort(nums,mid+1,end);
            merge(nums,start,mid,end);
        }
    }

    private void merge(int[] nums, int start, int mid, int end) {
        int[] tempArr = new int[end - start + 1];
        int tempArrCount = 0;
        int left  = start;//왼쪽 리스트의 시작 인덱스
        int right = mid+1;//오른쪽 인덱스의 시작 인덱스

        while(left <= mid && right <= end) {
            if (nums[left] <= nums[right]) {
                tempArr[tempArrCount++] = nums[left++];
            } else {
                tempArr[tempArrCount++] = nums[right++];
            }
        }

        while(left <= mid) {
            tempArr[tempArrCount++] = nums[left++];
        }
        while(right <= end) {
            tempArr[tempArrCount++] = nums[right++];
        }

        System.arraycopy(tempArr, 0, nums, start, tempArr.length);
    }


}
