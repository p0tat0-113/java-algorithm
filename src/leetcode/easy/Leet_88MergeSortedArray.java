package leetcode.easy;

/*
정렬된 두 배열을 하나의 정렬된 배열로 만드는 문제다. 그냥 병합정렬의 병합과정이랑 똑같이 하면 됨.
*/

public class Leet_88MergeSortedArray {
    public static void main(String[] args) {

    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] result = new int[m + n];
        int count = 0;
        int nums1Count = 0;
        int nums2Count = 0;

        while (nums1Count != m && nums2Count != n) {
            if (nums1[nums1Count] < nums2[nums2Count]) {
                result[count++] = nums1[nums1Count++];

            } else {
                result[count++] = nums2[nums2Count++];
            }
        }

        while (nums1Count != m) {
            result[count++] = nums1[nums1Count++];
        }

        while (nums2Count != n) {
            result[count++] = nums2[nums2Count++];
        }

        System.arraycopy(result,0,nums1,0,result.length);
    }
}
