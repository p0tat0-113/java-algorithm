package baekjoon.legacy;/*
* 색종이 만들기 - 실버2 시간제한 1초 메모리 제한 128MB
* 아래 <그림 1>과 같이 여러개의 정사각형칸들로 이루어진 정사각형 모양의 종이가 주어져 있고, 각 정사각형들은 하얀색으로 칠해져 있거나 파란색으로 칠해져 있다.
* 주어진 종이를 일정한 규칙에 따라 잘라서 다양한 크기를 가진 정사각형 모양의 하얀색 또는 파란색 색종이를 만들려고 한다.
*
* 전체 종이의 크기가 N×N(N=2k, k는 1 이상 7 이하의 자연수) 이라면 종이를 자르는 규칙은 다음과 같다.

전체 종이가 모두 같은 색으로 칠해져 있지 않으면 가로와 세로로 중간 부분을 잘라서
* <그림 2>의 I, II, III, IV와 같이 똑같은 크기의 네 개의 N/2 × N/2색종이로 나눈다.
* 나누어진 종이 I, II, III, IV 각각에 대해서도 앞에서와 마찬가지로 모두 같은 색으로 칠해져 있지 않으면 같은 방법으로 똑같은 크기의 네 개의 색종이로 나눈다.
* 이와 같은 과정을 잘라진 종이가 모두 하얀색 또는 모두 파란색으로 칠해져 있거나, 하나의 정사각형 칸이 되어 더 이상 자를 수 없을 때까지 반복한다.
*
* 입력으로 주어진 종이의 한 변의 길이 N과 각 정사각형칸의 색(하얀색 또는 파란색)이 주어질 때 잘라진 하얀색 색종이와 파란색 색종이의 개수를 구하는 프로그램을 작성하시오.*/

//재귀로 어렵지 않게 풀 수 있을 듯 함.

//왼쪽 상단의 좌표와 한 변의 길이만 인수로 넘기게끔 리팩토링

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek2630_2 {
    private static int length;
    private static Integer[][] paper;
    private static int whiteCount = 0;
    private static int blueCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        length = Integer.parseInt(br.readLine());
        paper = new Integer[length][length];
        for (int i = 0; i < length; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < length; j++) {
                paper[i][j] = Integer.parseInt(input[j]);
            }
        }

        divide(0,0,length);

        System.out.println(whiteCount);
        System.out.println(blueCount);
    }

    //왼쪽 상단, 오른쪽 하단의 좌표를 받아서 전부 다 같은 색인지 검사함.
    //전부 다 파란색이거나, 전부 다 하얀색이면 각 카운트에 1을 더하고 true를 반환, 해당되지 않으면 false를 반환함.
    private static boolean checkIfSolid(int row, int col, int length){
        int count = 0; //파란색이 1이므로, 전부 다 더했을 때 사각형의 넓이와 같던가 혹은 0이어야 함.

        for (int i = row; i <= row+length; i++) {
            for (int j = col; j <= col+length; j++) {
                count += paper[i][j];
            }
        }

        if (count == (length*length)) {
            blueCount++;
            return true;
        } else if (count == 0) {
            whiteCount++;
            return true;
        } else {
            return false;
        }
    }

    //왼쪽 상단, 오른쪽 하단 좌표를 인수로 받는다.
    private static void divide(int row1, int col1, int length){
        if (length == 1){ //길이가 1이면 파란색인지 흰색인지 검사해서 1을 더하고, 리턴함.
            checkIfSolid(row1,col1,length);
            return;
        }

        if (!checkIfSolid(row1,col1,length)){//checkIfSolid의 결과가 false이면 재귀적으로 쪼개야 함.
            int half = length/2;

            //왼쪽 위, 왼쪽 아래, 오른쪽 위, 오른쪽 아래, 한 방위씩 맡아서 재귀적으로 쪼갠다.
            divide(row1,col1,half);
            divide(row1+half,col1,half);
            divide(row1,col1+half,half);
            divide(row1+half,col1+half,half);

        }
    }
}
