package leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/*
https://imgur.com/a/0yNLeZr
LCS문제의 응용버전이다. 처음에는 어떻게 풀어야 하나 좀 막막했는데 문제를 통해 힌트를 얻어서 LCS알고리즘을 약간 변형해서 해결함.
*/

public class Leet_300LongestIncreasingSubsequence {
    public static void main(String[] args) {
        Leet_300LongestIncreasingSubsequence leet = new Leet_300LongestIncreasingSubsequence();

        int[] arr1 = {10,9,2,5,3,7,101,18};
        System.out.println(leet.lengthOfLIS(arr1));

        int[] arr2 = {0,1,0,3,2,3};
        System.out.println(leet.lengthOfLIS(arr2));

        int[] arr3 = {7,7,7,7,7,7,7};
        System.out.println(leet.lengthOfLIS(arr3));
    }

    public int lengthOfLIS(int[] nums) {
        //nums의 정렬된 버전인 sortedNums를 만든다. 이후 nums와 sortedNums의 LCS(Longest Common Subsequence)를 구할 것이다.
        //중복을 제거하고 리스트로 만들어서 정렬한다. {7,7,7,7,7,7,7} 같은 케이스에 대응하기 위해 중복을 제거한다.
        HashSet<Integer> set = new HashSet<>();
        Arrays.stream(nums).forEach(e -> set.add(e));
        ArrayList<Integer> sortedNums = new ArrayList<>(set);
        sortedNums.sort(null);

        int[][] matrix = new int[nums.length+1][sortedNums.size()+1];//i,j 둘 중 하나가 0인 상황을 위해 첫번째 열과 첫번째 행을 0으로 초기화 하는 과정은 생략해도 됨.

        for (int i = 1; i <= nums.length; i++) {
            for (int j = 1; j <= sortedNums.size(); j++) {
                if (nums[i-1] == sortedNums.get(j-1)) {
                    matrix[i][j] = matrix[i-1][j-1] + 1;
                } else {
                    matrix[i][j] = Math.max(matrix[i-1][j], matrix[i][j-1]);
                }
            }
        }

        //printMatrix(matrix);

        return matrix[nums.length][sortedNums.size()];
    }

    private static void printMatrix(int[][] matrix){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.printf("%-3d", matrix[i][j]);
            }
            System.out.println();
        }
    }
}
