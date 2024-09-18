package leetcode.easy;

/*
중복된 숫자가 없는 정렬된 배열과 targetNum이 하나 주어진다.
배열에 해당 숫자가 있으면 그 인덱스를 반환하고, 없다면 targetNum이 삽입되기에 알맞은 인덱스를 반환하면 된다.
시간복잡도가 O(logn)이여야 한다고 하니 이진탐색으로 풀어야 한다.
끝내 찾지 못하면 기준원소와 비교하여서 기준원소의 인덱스보다 하나 작거나 큰 인덱스를 반환하면 될 듯.

런타임 0ms로 잘 돌아감.

이런 더 단순하고 좋은 풀이도 있네.
https://leetcode.com/problems/search-insert-position/solutions/5361984/video-return-middle-or-left-pointer/
*/

public class Leet_35SearchInsertPosition {
    public static void main(String[] args) {
        //System.out.println(binarySearch(new int[]{1, 3, 5, 6}, 0, 3, 2));
        System.out.println(searchInsert(new int[]{1, 3}, 0));
    }

    public static int searchInsert(int[] nums, int target) {
        return binarySearch(nums,0,nums.length-1,target);
    }

    //nums = [1,3,5,6] target = 2
    private static int binarySearch(int[] nums, int start, int end, int targetNum){
        if (start <= end) {
            int mid = (start+end)/2;//중간지점을 구함.

            if (nums[mid] == targetNum) {
                return mid;
            } else if (nums[mid] > targetNum && start != end && start != mid) {
                //start와 end가 다르고 start와 mid가 다를 때만 참. start와 end가 같은 시점에서  nums[mid] != targetNum 이라는 것은 이미 target을 찾지 못했다는 것.
                //그리고 start와 mid가 같은 상태에서 재귀호출을 하는 것은 이미 target을 찾는게 물 건너간 상태다. 재귀호출하지 않고 바로 뒤로 넘김.
                //&& start != mid 이 부분이 없어서 nums = [1,3], target = 0 일 때 -1을 반환하는 바람에 틀렸다.
                return binarySearch(nums, start, mid-1, targetNum);
            } else if (nums[mid] < targetNum && start != end && end != mid) {
                //start와 end가 다르고 end와 mid가 다를 때만 참
                return binarySearch(nums, mid+1, end, targetNum);
            } else {//끝내 target을 찾지 못했을 때 여기로 온다.
                return (nums[mid] > targetNum) ? mid : mid+1;
            }
        }

        return -1;
    }
}
