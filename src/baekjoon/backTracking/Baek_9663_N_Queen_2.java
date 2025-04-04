package baekjoon.backTracking;

import java.util.ArrayList;
import java.util.Scanner;

//이렇게 푸니까 작동이 되긴 하는데 백준에 제출해본 결과 메모리를 초과해버림..... 메모리 제한이 128MB인데도 뚫어 버리네....
//무식하게 placed에다가 마킹을 하고, 마킹한 지점의 좌표들을 기록하는 방식을 쓰지 말고,
//앞서 부모노드들에서 배치된 퀸들의 좌표를 바탕으로 무결성 위반여부를 계산하는 함수를 만드는 것이 현명해보인다.

//와! 생각한대로 만드니까 잘 작동한다!!!! 근데 백준에 제출하니까 또 메모리 초과 뜸.....
public class Baek_9663_N_Queen_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        ArrayList<ArrayList<Integer>> placed = new ArrayList<>();//placed는 각 행에서 퀸들이 배치된 위치를 저장한다.

        for (int column = 0; column < n; column++) {//0부터 n-1까지 가장 첫 행에 놓는 퀸의 위치를 바꿔가면서 dfs를 호출한다.
            dfs(0, column, n, placed);
        }
        System.out.println(count);
    }

    static int count = 0;
    private static void dfs(int parentRow, int parentColumn, int n, ArrayList<ArrayList<Integer>> placed){
        if (parentRow == n-1) {//경계조건, 재귀호출을 멈추고 return해서 다시 부모노드로 올라간다.
            count++;
            //System.out.println(Arrays.deepToString(placed));
            return;
        }

        ArrayList<Integer> temp = new ArrayList<>();//부모노드에서 배치한 퀸의 좌표를 placed에 저장한다. placed에는 현재 가지에서 배치된 모든 여왕들의 위치가 기록되어있다. 밑에서 이걸 바탕으로 유효성을 판단하여 재귀호출하며 가지치기를 한다.
        temp.add(parentRow);
        temp.add(parentColumn);
        placed.add(temp);

        //그리고 가지를 뻗어나가며 재귀호출한다.
        for (int column = 0; column < n; column++) {
            if (!checkIsValid(parentRow+1, column, placed)) {//부모들의 공격범위에 들어가 있으면 continue해서 유망하지 않은 쪽으로 뻗어나가는 것을 막는다.
                continue;
            }
            dfs(parentRow+1, column, n, placed);
        }

        placed.remove(placed.size()-1);//이쪽 방향은 다 탐색했으므로 placed에서 퀸의 좌표를 제거하고 부모노드로 거슬러 올라간다.
    }

    private static boolean checkIsValid(int currentRow, int currentColumn, ArrayList<ArrayList<Integer>> placed) {
        for (ArrayList<Integer> list : placed) {
            if (list.get(1) == currentColumn) {//원래 배치됐던 퀸과 열이 같으면 false 반환. 수직으로 같은 위치에 있는 경우
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
}
