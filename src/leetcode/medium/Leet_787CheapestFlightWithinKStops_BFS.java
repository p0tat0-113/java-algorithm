package leetcode.medium;

/*
노드의 총 갯수인 n, 간선정보(방향, 가중치) flights배열, 시작정점, 도착정점, 최대 경유 가능 노드 수 k를 입력 받는다.
중간에 최대 k개의 노드만 경유할 때 시작정점에서 도착정점으로 가는 최소비용을 찾는 문제다.

743번 NetworkDelayTime문제의 Node에 이 distance를 얻기 위해 경유한 노드의 수 값까지 기록하게 하는 식으로 해도 될 것 같기도 하고,
그냥 dfs로 풀어도 될 것 같기도 하다.

map에 각 노드의 간선정보를 입력하고, dfs로 그걸 타고 이동한다. 만약 타고 가다가 경유한 수가 k를 초과하면 그대로 리턴시켜버리고,
k를 초과하지 않고 목적지에 도달하면 비용을 기록하는 식으로 하면 될 듯. <- 이 방식으로 접근했고, 잘 작동되기는 하는데 간선의 수가 많은 경우 시간초과가 뜬다.
*/

import java.util.*;

public class Leet_787CheapestFlightWithinKStops_BFS {
    public static void main(String[] args) {
        Leet_787CheapestFlightWithinKStops_BFS leet = new Leet_787CheapestFlightWithinKStops_BFS();
        System.out.println(leet.findCheapestPrice(3, new int[][]{{0, 1, 100}, {1, 2, 100}, {0, 2, 500}}, 0, 2, 1));
        System.out.println(leet.findCheapestPrice(3, new int[][]{{0, 1, 100}, {1, 2, 100}, {0, 2, 500}}, 0, 2, 0));
    }

    //chatgpt가 알려준 bfs풀이 방식인데, 약간 독특하다. bfs이긴 한데, 안에서 돌아가는걸 보면 마치 다익스트라와 흡사함. <- 아니네 다익스트라가 아니라 벨만포드랑 비슷한 느낌이 있음.
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        List<int[]>[] lists = new ArrayList[n];//list[i]리스트는 i노드에서 출발하는 간선의 목적지노드|가중치 쌍 배열을 저장한다.
        for (int i = 0; i < n; i++) {
            lists[i] = new ArrayList<>();//우선 전부 빈 리스트를 채워넣는다.
        }
        for (int[] flight : flights) {//연결 정보를 입력함.
            lists[flight[0]].add(flight);
        }

        int[] distance = new int[n];//각 노드의 distance 값을 저장하는 배열. distance는 시작정점 r에 연결되기 위한 최소비용을 저장한다.
        Arrays.setAll(distance, e -> Integer.MAX_VALUE);//시작정점을 제외한 나머지의 distance는 모두 무한대로 설정한다.
        distance[src] = 0;

        LinkedList<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {src, 0});//큐에 현재노드|현재의distace 쌍의 배열을 저장한다.

        int stops = 0;//중간에 거치는 노드의 수
        while (!queue.isEmpty() && stops <= k) {
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {//queue.size(); 만큼 반복하면서 큐에서 노드를 뺀다. 이전에 큐에 추가된 것을 모두 뺌. bfs의 원리다. 그리고 이게 다 돌고나면 stops를 증가시킨다. 아주 영리한 알고리즘이네.
                int[] statusU = queue.pollFirst();//큐에서 노드를 하나 빼서 u로 선언
                int u = statusU[0];
                int distanceU = statusU[1];

                List<int[]> setV = lists[u];
                for (int[] statusV : setV) {//u의 인접노드들이 v들을 검사한다.
                    int v = statusV[1];
                    int Wuv = statusV[2];
                    int distanceV = distance[v];

                    //Relaxation
                    if (distanceV > distanceU + Wuv) {//거리가 업데이트될 경우, 업데이트된 더 짧은 거리로 다시 큐에 집어넣는다. 이 v를 통하면 다른 경로가 더 짧아질 수 있다.
                        int newDistance = distanceU + Wuv;
                        distance[v] = newDistance;
                        queue.offer(new int[] {v, newDistance});
                    }
                }
            }
            stops++;
        }

        //모든 과정이 끝나고 나면 distance[dst]는 k개 이하의 간선을 사용했을 때 src에서 dst까지 가는 최단거리를 나타낸다.
        System.out.println(Arrays.toString(distance));
        if (distance[dst] == Integer.MAX_VALUE) {
            return -1;
        }
        return distance[dst];
    }
}
