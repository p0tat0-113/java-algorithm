package baekjoon.shortestPath;

/*
입력
첫째 줄에 정점의 개수 V와 간선의 개수 E가 주어진다. (1 ≤ V ≤ 20,000, 1 ≤ E ≤ 300,000) 모든 정점에는 1부터 V까지 번호가 매겨져 있다고 가정한다.
둘째 줄에는 시작 정점의 번호 K(1 ≤ K ≤ V)가 주어진다.
셋째 줄부터 E개의 줄에 걸쳐 각 간선을 나타내는 세 개의 정수 (u, v, w)가 순서대로 주어진다. 이는 u에서 v로 가는 가중치 w인 간선이 존재한다는 뜻이다.
u와 v는 서로 다르며 w는 10 이하의 자연수이다.
서로 다른 두 정점 사이에 여러 개의 간선이 존재할 수도 있음에 유의한다.

출력
첫째 줄부터 V개의 줄에 걸쳐, i번째 줄에 i번 정점으로의 최단 경로의 경로값을 출력한다.
시작점 자신은 0으로 출력하고, 경로가 존재하지 않는 경우에는 INF를 출력하면 된다.

리트코드 743번 NetworkDelayTime 문제와 유사하다. 거기서 풀 때는 별도의 내부 클래스를 정의해서 풀었었는데 뭔가 백준에서 그렇게 하면
실행속도가 너무 느려질 것 같은 느낌. distance를 별로도 빼서 한 번 구현해보자.
프림 알고리즘을 구현할 때 이런 방식으로 했었는데 다익스트라 알고리즘 자체가 프림의 변형이니 당연히 같은 방식으로 구현 가능할 듯.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek_1753최단경로_다익스트라 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine(), " ");
        int V = Integer.parseInt(stringTokenizer.nextToken());
        int E = Integer.parseInt(stringTokenizer.nextToken());

        int src = Integer.parseInt(br.readLine());

        List<int[]>[] edges = new List[V+1];//간선정보를 담을 배열 생성. edges[i]에는 i에서 시작되는 간선 정보들이 담긴다.
        for (int i = 0; i <= V; i++) {
            edges[i] = new LinkedList<>();
        }

        for (int i = 0; i < E; i++) {
            String[] input = br.readLine().split(" ");
            int u = Integer.parseInt(input[0]);
            int v = Integer.parseInt(input[1]);
            int Wuv = Integer.parseInt(input[2]);

            edges[u].add(new int[] {v, Wuv});//[도착노드 : 가중치] 쌍의 배열을 리스트에 저장한다.
        }

        int[] resultDistances = dijkstra(V, src, edges);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            if (resultDistances[i] != Integer.MAX_VALUE) {
                sb.append(resultDistances[i]);
            } else {
                sb.append("INF");
            }

            if (i == V) {
                continue;
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static int[] dijkstra(int n, int src, List<int[]>[] edges) {
        HashSet<Integer> setS = new HashSet<>();

        int[] distances = new int[n + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[src] = 0;//처음에 src가 아니라 1을 넣어서 잘못된 답이 나오고 있었던 듯....

        PriorityQueue<int[]> minHeap = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
        minHeap.add(new int[] {src, 0});//[정점 : 시작정점에서 이 정점까지의 최단거리] 쌍의 배열을 저장

        while (!minHeap.isEmpty()) {
            int[] polled = minHeap.poll();
            int u = polled[0];

            if (setS.contains(u)) {
                continue;
            }

            setS.add(u);
            int distanceU = distances[u];

            for (int[] edge : edges[u]) {
                int v = edge[0];

                if (setS.contains(v)) {//이미 집합 S에 들어간 노드는 최단거리가 확정되었으므로 이완 필요 X
                    continue;
                }

                int distanceV = distances[v];
                int Wuv = edge[1];

                if (distanceV > distanceU + Wuv) {//이완
                    distanceV = distanceU + Wuv;
                    distances[v] = distanceV;
                    minHeap.add(new int[] {v, distanceV});
                }
            }
        }

        return distances;
    }
}
