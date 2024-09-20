package leetcode.medium;

/*
m*n크기의 행렬이 주어지면 거기에 target숫자가 있는지 boolean으로 반환하는 문제다.
그런데 행렬의 각 행, 각 열이 모두 오름차순으로 정렬되어 있다.

Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
Output: true
*/

public class Leet_240SearchA2DMatrix2 {
    public static void main(String[] args) {

    }

    //우선 각각의 행에 이진탐색을 하는 방식으로 접근해보자.
    //제출해보니 당연히 잘 돌아감. 다만 7ms로, 20프로 밖에 못땄음. 50프로의 사람들은 5ms로 이 문제를 해결했다. 행뿐만 아니라 열도 정렬되어 있다는 것을 이용해야 하지 않을까 싶음.
    //아니면 그냥 이진탐색을 최적화 하면 될까?
    public boolean searchMatrix(int[][] matrix, int target) {
        //이 조건문들을 추가해서 target이 존재하지 않을 경우를 사전에 걸러주니까 성능이 6ms로 소폭 향상됐다.
        if (matrix[0][0] > target || matrix[matrix.length-1][matrix[matrix.length-1].length-1] < target) {//매트릭스가 5*5사이즈라고 할 때 [0][0]이 가장 작고, [4][4]가 가장 큰 수인 것으로 보인다.
            return false;
        }

        for (int[] nums : matrix) {
            if (nums[0] > target || nums[nums.length-1] < target) {
                continue;
            }

            if (binarySearch(nums, 0, nums.length-1, target)) {
                return true;
            }
        }
        return false;
    }

    private boolean binarySearch(int[] nums, int start, int end, int target) {
        if (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] > target) {
                return binarySearch(nums, start, mid-1, target);
            } else {
                return binarySearch(nums, mid+1, end, target);
            }
        }
        return false;
    }
}
