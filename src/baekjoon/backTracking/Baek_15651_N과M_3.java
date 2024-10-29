package baekjoon.backTracking;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
이번에는 앞의 버전들과 문제 조건이 좀 다르다.
자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
- 1부터 N까지 자연수 중에서 M개를 고른 수열
- 같은 수를 여러 번 골라도 된다.
첫째 줄에 자연수 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 7)

같은 수를 여러번 골라도 된다는 점에서 다르다. 아 근데 이것도 생각해보니까 그냥 가지치기 조건을 삭제해주면 그만이다.
원래는 checkArr[]을 따로 운용을 하면서 숫자가 중복되는 방향으로는 가지를 뻗어나가지 못하게 통제를 했지만, 이 문제에서는 그 조건을 풀어주기만 하면 된다.
*/

public class Baek_15651_N과M_3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        //System.out.println("n: " + n + " m: " + m);

        List<List<Integer>> result = new ArrayList<>();
        ArrayDeque<Integer> currentNums = new ArrayDeque<>();

        //백트래킹 - dfs
        dfs(m, n, currentNums, result);

        //result 출력 코드
        StringBuilder sb = new StringBuilder();
        for (List<Integer> list : result) {
            for (Integer integer : list) {
                sb.append(integer);
                sb.append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void dfs(int m, int n, ArrayDeque<Integer> currentNums, List<List<Integer>> result) {
        //currentNums스택의 길이가 m과 같아졌으면 수열이 완성된 것이다. result에 지금 수열을 저장하고 이전으로 돌아간다.
        //기조조건, 경계조건이다. 재귀호출을 종료한다.
        //트래킹에서 해를 찾았을 때 현재 경로를 저장하고, 이전 단계로 되돌아가는 과정과 같다고 함.
        if (currentNums.size() == m) {
            result.add(new ArrayList<>(currentNums));
            return;
        }

        for (int i = 1; i <= n; i++) {
            //가지치기 조건 삭제

            //수열의 맨 뒤에 현재 숫자를 추가하고, checkArr[]에도 이 숫자를 사용했음을 표시한다. 그리고 재귀호출 함.
            currentNums.offer(i);
            dfs(m, n, currentNums, result);

            //재귀호출을 마치고 이쪽으로 오게되면 죽이됐던 밥이됐던 그 경로로의 탐색은 마친 상태다.
            //수열에서 현재 숫자를 제거, checkArr[]에서도 현재 숫자의 사용여부를 false로 바꾸고 다음 반복문을 위한 준비를 한다.
            currentNums.pollLast();
        }
    }
}
