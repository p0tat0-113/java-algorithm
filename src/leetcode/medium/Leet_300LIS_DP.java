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

public class Leet_300LIS_DP {
    public static void main(String[] args) {
        Leet_300LIS_DP leet = new Leet_300LIS_DP();

        int[] arr1 = {10,9,2,5,3,7,101,18};
        System.out.println(leet.lengthOfLIS(arr1));

        int[] arr2 = {0,1,0,3,2,3};
        System.out.println(leet.lengthOfLIS(arr2));

        int[] arr3 = {7,7,7,7,7,7,7};
        System.out.println(leet.lengthOfLIS(arr3));
    }

    public int lengthOfLIS(int[] nums) {
        //return lisRecursion(nums, nums.length, 0, -1);
        return lisDP(nums, nums.length);
    }

    private int lisDP(int[] nums, int n){
        //length[i]는 배열 nums에서 인덱스 i까지 고려했을 때, nums[k]로 끝나는 LIS의 길이를 나타낸다.
        int[] length = new int[n];

        //내부 반복문으로 k보다 작은 인덱스들을 하나씩 살펴 보면서 arr[i] < arr[k]인 것이 있을 경우, length[k] 를 업데이트
        for (int k = 0; k < n; k++){
            length[k] = 1;

            //내부 루프 (i): k 이전의 모든 인덱스 i를 확인하여, nums[i]가 nums[k]보다 작을 경우 (nums[i] < nums[k]), nums[k]를 nums[i] 뒤에 추가하여 새로운 LIS를 만들 수 있다.
            for (int i = 0; i < k; i++){

                //(1) i번째 인덱스에서 끝나는 최장 증가 부분 수열의 마지막에 arr[k]를 추가했을 때의 LIS 길이
                //(2) 추가하지 않고 기존의 length[k] 값
                //둘 중에 더 큰 값으로 length[k] 값을 업데이트합니다.
                //업데이트: length[k]는 length[i] + 1과 기존의 length[k] 중 더 큰 값으로 업데이트된다.
                //이는 nums[k]를 포함한 새로운 LIS의 길이가 기존의 것보다 길다면 이를 반영하기 위함이다.
                if(nums[i] < nums[k]){//nums[i]까지의 숫자로 이루어진 LIS에 nums[k]를 추가할 수 있는지 보는 것이다.
                    length[k] = Math.max(length[k], length[i] + 1);//length[i] + 1을 사용하는 이유는 현재 인덱스 k의 요소 nums[k]를 이전 인덱스 i에서 끝나는 증가 부분 수열에 추가함으로써 새로운 증가 부분 수열을 형성할 수 있기 때문
                }
            }
        }
        return length[n-1];
    }

    private int lisRecursion(int[] nums, int n, int currentIdx, int previousIdx){
        if (currentIdx == n) {
            return 0;
        }

        int includeCurrent = 0;
        //현재 원소를 포함하는 경우: 만약 nums[currentIdx] > nums[previousIdx]이면 LIS의 길이를 1 늘리고, currentIdx+1부터 계속 탐색한다.
        if (previousIdx == -1 || nums[currentIdx] > nums[previousIdx]) {
            includeCurrent = 1 + lisRecursion(nums, n, currentIdx+1, previousIdx+1);
        }
        //현재 원소를 포함하지 않는 경우: 만약 nums[currentIdx] < nums[previousIdx]이면 LIS의 길이를 늘리지 않고, currentIdx+1부터 계속 탐색한다.
        int excludeCurrent = lisRecursion(nums, n, currentIdx+1, previousIdx+1);

        return Math.max(includeCurrent, excludeCurrent);
    }
}
