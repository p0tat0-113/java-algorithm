package leetcode.review.finalExam;

import java.util.*;

/*
노드의 총 갯수인 n, 간선정보(방향, 가중치) flights배열, 시작정점, 도착정점, 최대 경유 가능 노드 수 k를 입력 받는다.
중간에 최대 k개의 노드만 경유할 때 시작정점에서 도착정점으로 가는 최소비용을 찾는 문제다.

743번 NetworkDelayTime문제의 Node에 이 distance를 얻기 위해 경유한 노드의 수 값까지 기록하게 하는 식으로 해도 될 것 같기도 하고,
그냥 dfs로 풀어도 될 것 같기도 하다.

map에 각 노드의 간선정보를 입력하고, dfs로 그걸 타고 이동한다. 만약 타고 가다가 경유한 수가 k를 초과하면 그대로 리턴시켜버리고,
k를 초과하지 않고 목적지에 도달하면 비용을 기록하는 식으로 하면 될 듯. <- 이 방식으로 접근했고, 잘 작동되기는 하는데 간선의 수가 많은 경우 시간초과가 뜬다.
*/
public class Leet_787CheapestFlight_BFS {
    //이 문제를 bfs방식으로 다시 풀어보자.
    public static void main(String[] args) {
        Leet_787CheapestFlight_BFS leet = new Leet_787CheapestFlight_BFS();
        int[][] flights1 = {{0, 1, 100}, {1, 2, 100}, {2, 0, 100}, {1, 3, 600}, {2, 3, 200}};
        System.out.println(leet.findCheapestPrice(4, flights1, 0, 3, 1));
        int[][] flight2 = {{0,1,100},{1,2,100},{0,2,500}};
        System.out.println(leet.findCheapestPrice(3, flight2, 0,2,1));
        System.out.println(leet.findCheapestPrice(3, flight2, 0,2,0));
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[src] = 0;

        List<int[]>[] edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int[] flight : flights) {
            edges[flight[0]].add(flight);
        }

        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {src, 0});

        int stops = 0;
        while (!queue.isEmpty() && stops <= k) {
            int limit = queue.size();
            for (int i = 0; i < limit; i++) {
                int[] statusU = queue.pollFirst();
                int u = statusU[0];
                int distanceU = statusU[1];

                for (int[] statusV : edges[u]) {
                    int v = statusV[1];
                    int distanceV = distances[v];
                    int Wuv = statusV[2];

                    if (distanceV > distanceU + Wuv) {
                        distanceV = distanceU + Wuv;
                        distances[v] = distanceV;
                        queue.add(new int[] {v, distanceV});
                    }
                }
            }
            stops++;//이거 빼먹지 말자.
        }
        //System.out.println(Arrays.toString(distances));
        return (distances[dst] != Integer.MAX_VALUE) ? distances[dst] : -1;
    }
}

