package leetcode.review.finalExam;

import java.util.Arrays;

public class Leet_1143LCS {
    public static void main(String[] args) {
        Leet_1143LCS leet = new Leet_1143LCS();
        System.out.println(leet.longestCommonSubsequence("abcde", "ace"));
        System.out.println(leet.longestCommonSubsequence("abc", "acb"));
    }

    //LCS문제는 발표 준비를 하면서 내 머릿속에 확실히 들어와서 나름 자신있게 풀 수 있다.
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        //printMatrix(dp);
        return dp[text1.length()][text2.length()];
    }

    private void printMatrix(int[][] matrix){
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }
}
