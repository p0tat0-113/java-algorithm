package leetcode.hard;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

//앞에서 작성한 Leet_51N_Queens_1 코드를 다시 리팩토링 해본다. dfs에 첫 진입하는 부분을 쓸데없이 밖으로 빼놓아서 코드가 복잡해졌음.
//단순히 첫 진입을 빼 놓았다기 보다는 쓸데없이 재귀적으로 연결을 시켜놨다고 해야하나...?
public class Leet_51N_Queens_2 {
    public static void main(String[] args) {
        Leet_51N_Queens_2 leet = new Leet_51N_Queens_2();
        System.out.println(leet.solveNQueens(4));
        System.out.println(leet.solveNQueens(8));
    }

    public List<List<String>> solveNQueens(int n) {
        ArrayDeque<List<Integer>> placed = new ArrayDeque<>();//placed는 각 행에서 퀸들이 배치된 위치를 저장한다.

        dfs(n, placed);

        return result;
    }

    List<List<String>> result = new ArrayList<>();
    private void dfs(int n, ArrayDeque<List<Integer>> placed){
        if (placed.size() == n) {//경계조건, 재귀호출을 멈추고 return해서 다시 부모노드로 올라간다.
            printQueen(placed, n);
            return;
        }

        int row = placed.size();
        for (int column = 0; column < n; column++) {
            if (!checkIsValid(row, column, placed)) {//부모들의 공격선상에 위치해 있으면 continue해서 유망하지 않은 쪽으로 뻗어나가는 것을 막는다.
                continue;
            }

            placed.offer(List.of(row, column));

            dfs(n, placed);

            placed.pollLast();
        }
    }

    private boolean checkIsValid(int currentRow, int currentColumn, ArrayDeque<List<Integer>> placed) {
        //수직 방향으로 공격선상에 들어가있는지 확인
        for (List<Integer> list : placed) {
            if (list.get(1) == currentColumn) {//원래 배치됐던 퀸과 열이 같으면 false 반환.
                return false;
            }

            //대각선 방향으로 공격선상에 들어가있는지 확인
            int gap = list.get(0) - currentRow;//원래 배치됐던 퀸과 지금 배치하려고 하는 퀸의 높이 차이 확인
            if (list.get(1) - gap == currentColumn || list.get(1) + gap == currentColumn) {
                return false;
            }
        }

        return true;
    }

    private void printQueen(ArrayDeque<List<Integer>> placed, int n){
        ArrayList<String> smallResult = new ArrayList<>();

        for (List<Integer> list : placed) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                if (i == list.get(1)) {
                    sb.append("Q");
                    continue;
                }
                sb.append(".");
            }
            smallResult.add(sb.toString());
        }

        result.add(smallResult);
    }
}
