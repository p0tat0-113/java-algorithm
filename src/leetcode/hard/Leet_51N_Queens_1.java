package leetcode.hard;

import java.util.ArrayList;
import java.util.List;

public class Leet_51N_Queens_1 {
    public List<List<String>> solveNQueens(int n) {
        ArrayList<List<Integer>> placed = new ArrayList<>();//placed는 각 행에서 퀸들이 배치된 위치를 저장한다.

        for (int column = 0; column < n; column++) {//0부터 n-1까지 가장 첫 행에 놓는 퀸의 위치를 바꿔가면서 dfs를 호출한다.
            dfs(0, column, n, placed);
        }

        return result;
    }

    List<List<String>> result = new ArrayList<>();
    private void dfs(int parentRow, int parentColumn, int n, ArrayList<List<Integer>> placed){
        if (parentRow+1 == n) {//경계조건, 재귀호출을 멈추고 return해서 다시 부모노드로 올라간다.
            placed.add(List.of(parentRow, parentColumn));

            printQueen(placed, n);
            //System.out.println(Arrays.deepToString(placed));

            placed.removeLast();
            return;
        }

        placed.add(List.of(parentRow, parentColumn));//부모노드에서 배치한 퀸의 좌표를 placed에 저장한다.
        //placed에는 현재 가지에서 배치된 모든 여왕들의 위치가 기록되어있다. 밑에서 이걸 바탕으로 유효성을 판단하여 재귀호출하며 가지치기를 한다.

        for (int column = 0; column < n; column++) {
            if (!checkIsValid(parentRow+1, column, placed)) {//부모들의 공격선상에 위치해 있으면 continue해서 유망하지 않은 쪽으로 뻗어나가는 것을 막는다.
                continue;
            }
            dfs(parentRow+1, column, n, placed);
        }

        placed.removeLast();//이쪽 방향은 다 탐색했으므로 placed에서 퀸의 좌표를 제거하고 부모노드로 거슬러 올라간다.
    }

    private boolean checkIsValid(int currentRow, int currentColumn, ArrayList<List<Integer>> placed) {
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

    private void printQueen(ArrayList<List<Integer>> placed, int n){
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
