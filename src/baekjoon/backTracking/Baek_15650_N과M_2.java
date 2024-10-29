package baekjoon.backTracking;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열을 모두 찾아내야 한다.
//첫째 줄에 자연수 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 8)
//한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다. 중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.
//수열은 사전 순으로 증가하는 순서로 출력해야 한다.

//앞에서 푼 문제와 거의 같지만, 이번에는 추가 조건으로 각 수열이 오름차순이여야 한다. 가지치기 조건만 하나 더 추가하면 간단하게 해결될 것으로 보인다.

public class Baek_15650_N과M_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        //System.out.println("n: " + n + " m: " + m);

        List<List<Integer>> result = new ArrayList<>();
        boolean[] checkArr = new boolean[n];
        ArrayDeque<Integer> currentNums = new ArrayDeque<>();

        //백트래킹 - dfs
        dfs(m, n, currentNums, result, checkArr);

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

    private static void dfs(int m, int n, ArrayDeque<Integer> currentNums, List<List<Integer>> result, boolean[] checkArr) {
        //currentNums스택의 길이가 m과 같아졌으면 수열이 완성된 것이다. result에 지금 수열을 저장하고 이전으로 돌아간다.
        //기조조건, 경계조건이다. 재귀호출을 종료한다.
        //트래킹에서 해를 찾았을 때 현재 경로를 저장하고, 이전 단계로 되돌아가는 과정과 같다고 함.
        if (currentNums.size() == m) {
            result.add(new ArrayList<>(currentNums));
            return;
        }

        for (int i = 1; i <= n; i++) {
            //가지치기, checkArr[]을 통해서 이미 수열에 들어간 숫자가 다시 들어가지 않게 한다. continue로 건너 뜀으로써 유망하지 않은 경로로의 확장을 방지한다.
            if (checkArr[i-1] || (currentNums.peekLast() != null && currentNums.peekLast() > i)) {//|| (currentNums.peekLast() != null && currentNums.peekLast() > i)이 조건만 추가하면 해결된다.
                continue;
            }

            //수열의 맨 뒤에 현재 숫자를 추가하고, checkArr[]에도 이 숫자를 사용했음을 표시한다. 그리고 재귀호출 함.
            currentNums.offer(i);
            checkArr[i-1] = true;
            dfs(m, n, currentNums, result, checkArr);

            //재귀호출을 마치고 이쪽으로 오게되면 죽이됐던 밥이됐던 그 경로로의 탐색은 마친 상태다.
            //수열에서 현재 숫자를 제거, checkArr[]에서도 현재 숫자의 사용여부를 false로 바꾸고 다음 반복문을 위한 준비를 한다.
            currentNums.pollLast();
            checkArr[i-1] = false;
        }
    }
}
