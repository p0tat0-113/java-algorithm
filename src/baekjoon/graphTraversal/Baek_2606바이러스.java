package baekjoon.graphTraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/*
입력
첫째 줄에는 컴퓨터의 수가 주어진다. 컴퓨터의 수는 100 이하인 양의 정수이고 각 컴퓨터에는 1번 부터 차례대로 번호가 매겨진다. 둘째 줄에는 네트워크 상에서 직접 연결되어 있는 컴퓨터 쌍의 수가 주어진다.
이어서 그 수만큼 한 줄에 한 쌍씩 네트워크 상에서 직접 연결되어 있는 컴퓨터의 번호 쌍이 주어진다.

출력
1번 컴퓨터가 웜 바이러스에 걸렸을 때, 1번 컴퓨터를 통해 웜 바이러스에 걸리게 되는 컴퓨터의 수를 첫째 줄에 출력한다.

간단한 그래프 순회 문제다. 문제의 입력으로 무방향 그래프가 주어지고, 연결되어 있는 정점들의 수를 세어서 반환하면 되는 문제다.
*/

public class Baek_2606바이러스 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());//정점의 수
        int m = Integer.parseInt(br.readLine());//간선의 수

        boolean[] used = new boolean[n + 1];//방문한 노드 정보 기록
        List<Integer>[] edges = new List[n+1];//간선 정보 기록
        for (int i = 0; i <= n; i++) {
            edges[i] = new LinkedList<>();
        }

        for (int i = 0; i < m; i++) {//간선 정보를 입력받아서 기록한다. 무방향 그래프이기 때문에 양쪽으로 기록한다.
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            edges[s].add(e);
            edges[e].add(s);
        }

        dfs(edges, used, 1);
        System.out.println(count);
    }

    private static int count = -1;

    //간단한 dfs 코드
    private static void dfs(List<Integer>[] edges, boolean[] used, int node) {
        used[node] = true;
        count++;

        for (Integer i : edges[node]) {
            if (used[i]) {
                continue;
            }
            dfs(edges, used, i);
        }
    }
}
