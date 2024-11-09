package leetcode.medium;

import java.util.Arrays;

public class Leet_1143LongestCommonSubsequence {
    public static void main(String[] args) {
        Leet_1143LongestCommonSubsequence leet = new Leet_1143LongestCommonSubsequence();

        String text1 = "abcde";
        String text2 = "ace";
        System.out.println(leet.longestCommonSubsequence(text1, text2));

        String text3 = "abc";
        String text4 = "def";
        System.out.println(leet.longestCommonSubsequence(text3, text4));
    }

    //잘 동작하지만 37ms로 6.93%밖에 따지 못함. .split()을 해서 문자열 배열로 바뀌는 부분에서 낭비되는 시간이 많아보인다.
    //그냥 charAt()을 사용하게 코드를 고쳐보자.
    public int longestCommonSubsequence(String text1, String text2) {
        String[] X = text1.split("");
        String[] Y = text2.split("");

        int[][] matrix = new int[X.length + 1][Y.length + 1];

        //matrix의 첫번째 행과 첫번째 열을 0으로 초기화, 상대 문자열의 길이가 0인 경우에 대응한다.
        //생각해보니까 어차피 모든 원소가 0으로 초기화돼서 필요없음.
        /*for (int i = 0; i < matrix.length; i++) {
            matrix[i][0] = 0;
        }
        Arrays.fill(matrix[0], 0);*/

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (X[i-1].equals(Y[j-1])) {
                    matrix[i][j] = matrix[i-1][j-1] + 1;
                } else {
                    matrix[i][j] = Math.max(matrix[i-1][j], matrix[i][j-1]);
                }
            }
        }
        return matrix[X.length][Y.length];
    }
}
