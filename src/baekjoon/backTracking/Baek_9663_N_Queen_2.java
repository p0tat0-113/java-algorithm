package baekjoon.backTracking;

import java.util.ArrayList;
import java.util.Scanner;

//이렇게 푸니까 작동이 되긴 하는데 백준에 제출해본 결과 메모리를 초과해버림..... 메모리 제한이 128MB인데도 뚫어 버리네....
//무식하게 placed에다가 마킹을 하고, 마킹한 지점의 좌표들을 기록하는 방식을 쓰지 말고,
//앞서 부모노드들에서 배치된 퀸들의 좌표를 바탕으로 무결성 위반여부를 계산하는 함수를 만드는 것이 현명해보인다.

//와! 생각한대로 만드니까 잘 작동한다!!!!
public class Baek_9663_N_Queen_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        ArrayList<ArrayList<Integer>> placed = new ArrayList<>();

        for (int column = 0; column < n; column++) {
            dfs(0, column, n, placed);
        }
        System.out.println(count);
    }

    static int count = 0;
    private static void dfs(int parentRow, int parentColumn, int n, ArrayList<ArrayList<Integer>> placed){
        if (parentRow+1 == n) {
            count++;
            //System.out.println(Arrays.deepToString(placed));
            return;
        }

        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(parentRow);
        temp.add(parentColumn);
        placed.add(temp);

        for (int column = 0; column < n; column++) {
            if (!checkIsValid(parentRow+1, column, placed)) {//부모들의 공격범위에 들어가 있으면 continue해서 유망하지 않은 쪽으로 뻗어나가는 것을 막는다.
                continue;
            }
            dfs(parentRow+1, column, n, placed);
        }

        placed.remove(placed.size()-1);
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
