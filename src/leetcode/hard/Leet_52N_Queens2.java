package leetcode.hard;

import java.util.ArrayDeque;

/*
51번 문제와 마찬가지로 N*N 크기의 체스판에서 N개의 퀸을 서로 공격하지 않게 배치하는 문제다.
이번에는 중복되지 않는 모든 가능한 경우의 수를 반환하라고 한다. 51번 문제를 풀었다면 충분히 날먹할 수 있음.
백트래킹에 익숙해지기 위해 처음부터 다시 구현해보자.
*/
public class Leet_52N_Queens2 {
    public static void main(String[] args) {
        Leet_52N_Queens2 leet = new Leet_52N_Queens2();
        System.out.println(leet.totalNQueens(4));
        System.out.println(leet.totalNQueens(8));
    }

    public int totalNQueens(int n) {
        dfs(n, new ArrayDeque<>());
        return count;
    }

    private int count = 0;//경우의 수 카운트 변수

    private void dfs(int n, ArrayDeque<int[]> stack) {
        if (stack.size() == n) {//경계조건
            count++;
            return;
        }

        int row = stack.size();//새로 배치하는 퀸의 행 인덱스
        for (int column = 0; column < n; column++) {//새로 배치하는 퀸의 열 인덱스
            if (!check(row, column, stack)) {//새로 배치하는 퀸의 위치가 기존 퀸들의 공격선상에 들어가는지 검사, 유망하지 않은 방향으로 뻗어나가는 것을 막는다.
                continue;
            }

            stack.add(new int[] {row, column});//stack에 새로 배치한 퀸의 위치를 담은 후 재귀호출하여 뻗어나간다.
            dfs(n, stack);//재귀호출
            stack.pollLast();
        }
    }

    private boolean check(int row, int column, ArrayDeque<int[]> stack) {
        for (int[] position : stack) {
            if (position[1] == column) {//수직방향 검사
                return false;
            }

            int gap = row - position[0];
            if (column == position[1] - gap || column == position[1] + gap) {//양쪽 대각선 방향 검사
                return false;
            }
        }

        return true;
    }
}
