package leetcode.review.finalExam;

public class Leet_1143LCS {
    public static void main(String[] args) {

    }

    //LCS문제는 발표 준비를 하면서 내 머릿속에 확실히 들어와서 나름 자신있게 풀 수 있다.
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i < dp[0].length; i++) {
            dp[0][i] = 0;
        }

        for (int row = 1; row < dp.length; row++) {
            for (int column = 1; column < dp[0].length; column++) {
                if (text1.charAt(row-1) == text2.charAt(column-1)) {
                    dp[row][column] = dp[row-1][column-1] + 1;
                } else {
                    dp[row][column] = Math.max(dp[row-1][column],dp[row][column-1]);
                }
            }
        }

        return dp[text1.length()][text2.length()];
    }
}
