package leetcode.medium;

/*2차원 배열을 회전시키는 문제다. 1학년 때 c언어로 한 번 풀어봤던 문제임. 그런데 이번에는 조금 다른게 inplace로 해야 한다.
간단하게 다른 2차원 배열을 하나 더 만들어서 하는 식으로는 안됨.
말로 설명하기 좀 어려운데 한 귀퉁이씩 옮겨가고, 그자리에 있던 것은 temp에 저장해놓고, 그 위치에서 또 다시 옮기고 하는 방식으로 하면 되지 않을까?

Input:  [[1,2,3],[4,5,6],[7,8,9]]
Output: [[7,4,1],[8,5,2],[9,6,3]]

일단 규칙을 찾아보자.
1 : [0][0] -> [0][2]
2 : [0][1] -> [1][2]
3 : [0][2] -> [2][2]
8 : [2][1] -> [1][0]
오른쪽의 인덱스가 왼쪽으로 가고, 오른쪽 인덱스에는 최대인덱스-왼쪽인덱스가 오는 건가? 아마 맞는 듯.
*/

import java.util.Arrays;

public class Leet_48RotateImage {
    public static void main(String[] args) {
        int[][] matrix = {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        rotate(matrix);
        System.out.println(Arrays.deepToString(matrix));
        //답: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
    }

    public static void rotate(int[][] matrix) {
        for (int i = 0; i < matrix.length/2; i++) {//행렬 길이의 반절 만큼 반복함. 회전시키는 깊이를 점점 늘려가는 반복문

            //한 계층 전체를 회전시키는 반복문
            //해당하는 계층의 한 행의 길이 - 1 만큼만 반복하면 된다. 행의 오른쪽 맨 끝의 숫자는 왼쪽 맨 끝의 숫자를 회전시킬 때 이미 교체된 상태이므로 건드리지 않아도 됨.
            for (int j = i; j < matrix.length-1-i; j++) {
                //시작점 세팅
                int row = i;
                int column = j;
                int num = matrix[row][column];

                //해당 계층의 4개의 숫자를 회전시키는 반복문
                for (int k = 0; k < 4; k++) {
                    //행인덱스 = 열인덱스, 열인덱스 = matirx의 최대인덱스 - 행인덱스
                    int tempRow = row;
                    row = column;
                    column = matrix.length - 1 - tempRow;//최대 인덱스 - row

                    //굴러온 돌로 박힌 돌을 빼버리고, 굴러온 돌은 num에 저장한다.
                    int tempNum = num;
                    num = matrix[row][column];
                    matrix[row][column] = tempNum;

                    //디버깅용 코드
                    /*for (int l = 0; l < 4; l++) {
                        System.out.println(Arrays.toString(matrix[l]));
                    }
                    System.out.println("----");*/
                }
                //System.out.println("------------------");
            }
        }
    }
}
