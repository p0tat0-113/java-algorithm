package baekjoon.backTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//이렇게 푸니까 작동이 되긴 하는데 백준에 제출해본 결과 메모리를 초과해버림..... 메모리 제한이 128MB인데도 뚫어 버리네....
//무식하게 placed에다가 마킹을 하고, 마킹한 지점의 좌표들을 기록하는 방식을 쓰지 말고,
//앞서 부모노드들에서 배치된 퀸들의 좌표를 바탕으로 무결성 위반여부를 계산하는 함수를 만드는 것이 현명해보인다.
public class Baek_9663_N_Queen {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        boolean[][] placed = new boolean[n][n];//퀸을 놓은 위치를 기록

        for (int column = 0; column < n; column++) {
            dfs(0, column, n-1, n, placed);
        }
        //dfs(0, 2, n-1, n, placed);
        System.out.println(count);

        /*boolean[][] matrix = new boolean[5][5];
        mark(0,2, matrix);
        System.out.println(Arrays.deepToString(matrix));*/
    }

    static int count = 0;
    private static void dfs(int parentRow, int parentColumn, int remain, int n, boolean[][] placed){
        if (remain == 0) {
            count++;
            //System.out.println(Arrays.deepToString(placed));
            return;
        }

        ArrayList<List<Integer>> markedPoints = mark(parentRow, parentColumn, placed);//부모노드에서 배치한 퀸의 위치를 기반으로 공격범위를 마킹한다.
        for (int column = 0; column < n; column++) {
            if (placed[parentRow+1][column]) {//부모들의 공격범위에 들어가 있으면 continue해서 유망하지 않은 쪽으로 뻗어나가는 것을 막는다.
                continue;
            }
            dfs(parentRow+1, column, remain-1, n, placed);
        }
        unmark(markedPoints, placed);//공격범위 마킹을 지우고, 다시 부모 쪽으로 거슬러 올라간다.
        //혹시 unmark 할 때 겹쳐지는 부모의 공격범위까지 같이 지우나? <- 맞다 이거였네!!!! 자신이 표시한 것만 지우게 하니까 제대로 작동함. 코드가 많이 난잡하기 하지만....

    }

    //마킹은 아래쪽으로만 하면 된다.
    private static ArrayList<List<Integer>> mark(int row, int column, boolean[][] placed) {
        //마킹한 부분의 좌표를 기록하는 2차원 리스트
        ArrayList<List<Integer>> log = new ArrayList<List<Integer>>();

        //아래 직각 방향으로 마킹
        for (int i = row+1; i < placed.length; i++) {
            if (placed[i][column]) {//이미 마킹되어 있는 좌표는 건너 뜀.
                continue;
            }
            placed[i][column] = true;
            log.add(List.of(i, column));
        }
        //아래 왼쪽 대각선 방향으로 마킹
        int tempRow = row+1;
        int tempColumn = column-1;
        while (tempRow < placed.length && tempColumn >= 0) {
            if (!placed[tempRow][tempColumn]) {//이미 마킹되어 있는 좌표는 건너 뜀.
                placed[tempRow][tempColumn] = true;
                log.add(List.of(tempRow, tempColumn));
            }
            tempRow++;
            tempColumn--;
        }
        //아래 오른쪽 대각선 방향으로 마킹
        tempRow = row+1;
        tempColumn = column+1;
        while (tempRow < placed.length && tempColumn < placed[0].length) {
            if (!placed[tempRow][tempColumn]) {//이미 마킹되어 있는 좌표는 건너 뜀.
                placed[tempRow][tempColumn] = true;
                log.add(List.of(tempRow, tempColumn));
            }
            tempRow++;
            tempColumn++;
        }

        return log;
    }

    private static void unmark(ArrayList<List<Integer>> log, boolean[][] placed) {
        for (List<Integer> list : log) {
            placed[list.get(0)][list.get(1)] = false;
        }
    }

    /*private static void unmark(int row, int column, boolean[][] placed) {
        //아래 직각 방향으로 마킹
        for (int i = row+1; i < placed.length; i++) {
            placed[i][column] = false;
        }
        //아래 왼쪽 대각선 방향으로 마킹
        int tempRow = row+1;
        int tempColumn = column-1;
        while (tempRow < placed.length && tempColumn >= 0) {
            placed[tempRow][tempColumn] = false;
            tempRow++;
            tempColumn--;
        }
        //아래 오른쪽 대각선 방향으로 마킹
        tempRow = row+1;
        tempColumn = column+1;
        while (tempRow < placed.length && tempColumn < placed[0].length) {
            placed[tempRow][tempColumn] = false;
            tempRow++;
            tempColumn++;
        }
    }*/
}
