package leetcode.review.mid;

import java.util.Arrays;

public class Leet_48RotateImage {
    public static void main(String[] args) {
        Leet_48RotateImage leet = new Leet_48RotateImage();
        //int[][] matrix = new int[][]{{1,2,3}, {4,5,6}, {7,8,9}};
        int[][] matrix = new int[][] {{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
        leet.rotate(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }

    public void rotate(int[][] matrix) {
        for (int i = 0; i < matrix.length/2; i++) {
            for (int j = i; j < matrix.length-i-1; j++) {
                int x = i;
                int y = j;
                int num = matrix[i][j];
                for (int k = 0; k < 4; k++) {
                    //1,2 -> 2,1
                    //0,0 -> 0,2
                    //2,0 -> 0,0
                    int temp = x;
                    x = y;
                    y = matrix.length-temp-1;

                    int tempNum = matrix[x][y];
                    matrix[x][y] = num;
                    num = tempNum;
                }
            }
        }
    }
}
