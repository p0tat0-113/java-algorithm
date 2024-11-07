package leetcode.medium;

import java.util.Arrays;

/*
간단한 DP문제다. 우선 bottom-up방식으로 해결함. https://imgur.com/a/xDuuivb
나중에 훈련을 위해 top-down방식으로도 풀어보자.
*/

public class Leet_62UniquePaths {
    public static void main(String[] args) {
        Leet_62UniquePaths leet = new Leet_62UniquePaths();
        leet.uniquePaths(3,7);
    }

    public int uniquePaths(int m, int n) {
        int[][] matrix = new int[m][n];

        for (int i = 0; i < matrix.length; i++) {//각 행의 첫번째 열을 1로 초기화
            matrix[i][0] = 1;
        }
        for (int i = 0; i < matrix[0].length; i++) {//첫번째 행의 모든 열을 1로 초기화
            matrix[0][i] = 1;
        }

        //반복해서 bottom-up방식으로 matirx를 채워나간다.
        for (int row = 1; row < matrix.length; row++) {
            for (int column = 1; column < matrix[0].length; column++) {
                matrix[row][column] = matrix[row-1][column] + matrix[row][column-1];
            }
        }

        //printMatrix(matrix);

        return matrix[m-1][n-1];
    }

    private void printMatrix(int matrix[][]){
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }
}
