package baekjoon.graphTraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
N개의 정점과 M개의 간선으로 구성된 무방향 그래프(undirected graph)가 주어진다. 정점 번호는 1번부터 N번이고 모든 간선의 가중치는 1이다. 정점 R에서 시작하여 깊이 우선 탐색으로 노드를 방문할 경우 노드의 방문 순서를 출력하자.
깊이 우선 탐색 의사 코드는 다음과 같다. 인접 정점은 오름차순으로 방문한다.

처음에 문제로 무방향 그래프가 주어진 다는 점을 까먹고 adjacentList[endPoint].add(startPoint); 이 코드를 넣지 않아서 틀리고 있었다.
그리고 처음에는 인접 정점들을 담기 위해 PriorityQueue를 사용했는데 claude에게 물어보니까 PriorityQueue는 최소 힙이므로 poll()할 때만 오름차순이 보장되고, forEach로 순회할 때는 순서가 보장되지 않는다고 한다.
ArrayList로 바꿔서 따로 정렬하니까 해결됨.
*/

public class Baek_24444BFS1 {
    private static int orderCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());//정점의 수
        int M = Integer.parseInt(st.nextToken());//간선의 수
        int R = Integer.parseInt(st.nextToken());//시작 정점

        int[] visitedOrderArr = new int[N + 1];//각 노드의 방문 순서를 기록하는 배열. 방문하지 않은 곳은 0으로 기록한다.
        List<Integer>[] adjacentList = new List[N+1];//adjacentList[i]에는 정점 i에 인접한 정점들이 기록된다.
        for (int i = 1; i <= N; i++) {
            adjacentList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {//간선 정보를 입력받아서 기록한다.
            st = new StringTokenizer(br.readLine());
            int startPoint = Integer.parseInt(st.nextToken());
            int endPoint = Integer.parseInt(st.nextToken());
            adjacentList[startPoint].add(endPoint);
            adjacentList[endPoint].add(startPoint);//무방향 그래프라서 이렇게 반대방향으로도 기록해야 한다.
        }
        for (int i = 1; i <= N; i++) {
            adjacentList[i].sort(null);//인접한 정렬들을 오름차순으로 정렬
        }

        ArrayDeque<Integer> queue = new ArrayDeque<>();//BFS 큐
        queue.add(R);//큐에 시작 정점을 집어넣음.

        while (!queue.isEmpty()) {//큐가 비기 전까지 계속 반복
            Integer polled = queue.poll();//큐에서 숫자 하나를 뺀다.
            if (visitedOrderArr[polled] != 0) {//이미 방문한 노드면 컨티뉴
                continue;
            }

            visitedOrderArr[polled] = ++orderCount;//방문 순서 표시

            for (Integer node : adjacentList[polled]) {//인접 정점들을 큐에 집어넣음.
                queue.add(node);
            }
        }

        //System.out.println(Arrays.toString(visitedOrderArr));
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(visitedOrderArr[i]);
            if (i == N) {
                break;
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
