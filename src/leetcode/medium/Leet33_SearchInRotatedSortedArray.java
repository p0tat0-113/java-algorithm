package leetcode.medium;

/*
81번 문제랑 거의 비슷함. 날먹 쌉가능.
약간만 수정해서 돌렸더니 0ms로 아주 빠르게 잘 돌아감.
*/

public class Leet33_SearchInRotatedSortedArray {
    public static void main(String[] args) {
    }

    public static int search(int[] nums, int target) {
        int leftStart = 0;
        int leftEnd = -1;
        int rightStart = 0;
        int rightEnd = nums.length-1;

        //우선 최소값과 최대값을 각각 찾는다.
        int max = nums[0];
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
            if (nums[i] < min) {
                min = nums[i];
            }
        }

        for (int i = 0; i < nums.length-1; i++) {
            if (nums[i] == max && nums[i+1] == min) {//최대값-최소값이 연결되어 있는 부분을 찾는 것. 찾게되면 왼쪽과 오른쪽은 각각 정렬된 상태다.
                leftEnd = i;
                rightStart = i+1;
            }
        }
        //만약 여기서 연결부분을 찾지 못했다면 배열이 rotate되지 않은 상태인 것이다.(좀 더 정확히 말하자면 pivot의 인덱스가 0인 것)
        //그냥 전체배열을 이진탐색 돌리면 된다. 그렇게 되도록 leftStart...rightEnd변수 값을 설정해 놓았음.

        int left = binarySearch(nums, leftStart, leftEnd, target);
        int right = binarySearch(nums, rightStart, rightEnd, target);

        if (left != -1) {
            return left;
        }
        if (right != -1) {
            return right;
        }
        return -1;
    }

    //이진탐색 알고리즘.
    private static int binarySearch(int[] nums, int start, int end, int target){
        if(start <= end) {
            int mid = (start+end)/2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                return binarySearch(nums, mid+1, end, target);//return을 빼먹어서 틀림. return이 붙어있어야 return true를 한게 타고 올라간다.
            } else {
                return binarySearch(nums, start, mid-1, target);
            }
        }

        return -1;
    }

}
