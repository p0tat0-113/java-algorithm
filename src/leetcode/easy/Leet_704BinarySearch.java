package leetcode.easy;

/*
Given an array of integers nums which is sorted in ascending order, and an integer target,
write a function to search target in nums. If target exists, then return its index.
Otherwise, return -1.

그냥 간단한 배열 이진탐색 문제다. target숫자를 찾으면 그 숫자의 인덱스를 반환하면 되고, 못 찾았으면 -1을 반환하면 된다.
이미 몇번 풀어본 경험이 있기 때문에 아주 easy함.
5분컷ㅅㅅ
*/

public class Leet_704BinarySearch {
    public static void main(String[] args) {
        System.out.println(search(new int[]{-1, 0, 3, 5, 9, 12}, 9));
        System.out.println(search(new int[]{-1, 0, 3, 5, 9, 12}, 10));
    }

    public static int search(int[] nums, int target) {
        return binarySearch(nums, target, 0, nums.length-1);
    }

    public static int binarySearch(int[] nums, int target, int s, int e){
        if (s <= e) {
            int mid = (s+e)/2;//중앙 인덱스를 구함.
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {//왼쪽으로 분기
                return binarySearch(nums, target, s, mid-1);
            } else {
                return binarySearch(nums, target, mid+1, e);
            }
        }

        return -1;
    }
}
