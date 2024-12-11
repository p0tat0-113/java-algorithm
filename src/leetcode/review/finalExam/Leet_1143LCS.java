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
        printMatrix(dp);
        System.out.println(getLCS(dp, text1, text2));
        return dp[text1.length()][text2.length()];
    }

    private void printMatrix(int[][] matrix){
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }

    //dp 테이블을 역추적해서 실제 LCS가 뭔지 알아낸다.
    private String getLCS(int[][] matrix, String text1, String text2){
        int row = text1.length();
        int column = text2.length();

        StringBuilder sb = new StringBuilder();
        while (row > 0 && column > 0) {//현재위치 row, column이 0보다 큰 동안 반복한다.
            if (text1.charAt(row-1) == text2.charAt(column-1)) {//text1.charAt(row-1) == text2.charAt(column-1 인 경우 sb에 해당 문자열을 추가하고, 대각선 방향으로 역으로 이동한다.
                sb.append(text1.charAt(row - 1));
                row--;
                column--;
            } else {
                if (matrix[row - 1][column] > matrix[row][column - 1]) {//위에 있는 애가 더 크면 위로 이동
                    row--;
                } else {//아니면 왼쪽으로 이동
                    column--;
                }
            }
        }

        sb.reverse();
        return sb.toString();
    }
}
