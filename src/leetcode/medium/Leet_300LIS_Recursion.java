package leetcode.medium;

/*
https://imgur.com/a/0yNLeZr
앞에서 위의 방법으로 풀긴 했지만, 이건 그리 좋은 방법은 아닌 것 같다.
이 문제의 본질을 제대로 파악하면서 문제를 풀어보자.
우선 문제의 OptimalSubstructure를 파악해야 한다.

각 원소마다 두가지 선택지가 있다.
1. LIS에 현재 원소를 포함: 현재 원소가 이전 원소보다 큰 경우
2. LIS에 현재 원소를 포함하지 않음: 현재 원소가 이전 원소보다 작은 경우, 이때 현재 원소를 건너뛰고 다음 원소로 이동한다.

점화식을 구성하자면 이렇게 된다.
기저조건: currentIdx가 n에 도달함. 더 이상 탐색할 원소가 없으므로 0을 반환.
현재 원소를 포함하는 경우: 만약 nums[currentIdx] > nums[previousIdx]이면 LIS의 길이를 1 늘리고, currentIdx+1부터 계속 탐색한다.
현재 원소를 포함하지 않는 경우: 만약 nums[currentIdx] < nums[previousIdx]이면 LIS의 길이를 늘리지 않고, currentIdx+1부터 계속 탐색한다.

includeCurrent = 0
if(nums[currentIdx] > nums[previousIdx]) includeCurrent = 1 + LIS(currentIdx+1,currentIdx)//LIS길이 1증가, currentIdx, previousIdx도 1증가시켜서 계속 탐색한다. .
excludeCurrent = LIS(currentIdx+1,currentIdx)

return max(includeCurrent, excludeCurrent)
*/

public class Leet_300LIS_Recursion {
    public static void main(String[] args) {
        Leet_300LIS_Recursion leet = new Leet_300LIS_Recursion();

        int[] arr1 = {10,9,2,5,3,7,101,18};
        System.out.println(leet.lengthOfLIS(arr1));

        int[] arr2 = {0,1,0,3,2,3};
        System.out.println(leet.lengthOfLIS(arr2));

        int[] arr3 = {7,7,7,7,7,7,7};
        System.out.println(leet.lengthOfLIS(arr3));
    }

    public int lengthOfLIS(int[] nums) {
        return lisRecursion(nums, nums.length, 0, -1);
    }

    private int lisRecursion(int[] nums, int n, int currentIdx, int previousIdx){
        if (currentIdx == n) {
            return 0;
        }

        int includeCurrent = 0;
        if (previousIdx == -1 || nums[currentIdx] > nums[previousIdx]) {
            includeCurrent = 1 + lisRecursion(nums, n, currentIdx+1, previousIdx+1);
        }
        int excludeCurrent = lisRecursion(nums, n, currentIdx+1, previousIdx+1);

        return Math.max(includeCurrent, excludeCurrent);
    }
}
