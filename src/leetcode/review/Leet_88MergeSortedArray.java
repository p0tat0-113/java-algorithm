package leetcode.review;

import java.util.Arrays;

public class Leet_88MergeSortedArray {
    public static void main(String[] args) {
        Leet_88MergeSortedArray leet = new Leet_88MergeSortedArray();
        int[] nums1 = new int[] {1,2,3,0,0,0};
        int[] nums2 = new int[] {2,5,6};
        leet.merge(nums1,3, nums2, 3);
        System.out.println(Arrays.toString(nums1));
    }

    //그냥 병합정렬의 합병 하듯이 하면 되는 듯?
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] nums1duplicated = new int[m + n];
        for (int i = 0; i < m+n; i++) {
            nums1duplicated[i] = nums1[i];
        }

        int tempLength = 0;
        int left = 0;
        int right = 0;

        while (left < m && right < n) {
            if (nums1duplicated[left] < nums2[right]) {
                nums1[tempLength++] = nums1duplicated[left++];
            } else {
                nums1[tempLength++] = nums2[right++];
            }
        }

        while (left < m) {
            nums1[tempLength++] = nums1duplicated[left++];
        }
        while (right < n) {
            nums1[tempLength++] = nums2[right++];
        }
    }
}
