package leetcode.review.mid;

public class Leet_704BinarySearch {
    public static void main(String[] args) {
        Leet_704BinarySearch leet = new Leet_704BinarySearch();
        System.out.println(leet.search(new int[]{-1, 0, 3, 5, 9, 12}, 9));
        System.out.println(leet.search(new int[]{-1, 0, 3, 5, 10, 12}, 9));
        System.out.println(leet.search(new int[]{1}, 1));
        System.out.println(leet.search(new int[]{}, 1));
    }

    public int search(int[] nums, int target) {
        return binarySearch(nums, target, 0, nums.length-1);
    }

    private int binarySearch(int[] nums, int target, int s, int e){
        if (s <= e) {
            int mid = (s+e)/2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                return binarySearch(nums, target, s, mid-1);
            } else {
                return binarySearch(nums, target, mid+1, e);
            }
        }
        return -1;
    }
}
