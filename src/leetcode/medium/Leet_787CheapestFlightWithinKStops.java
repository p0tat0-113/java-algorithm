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

public class Leet_787CheapestFlightWithinKStops {
    public static void main(String[] args) {
        Leet_787CheapestFlightWithinKStops leet = new Leet_787CheapestFlightWithinKStops();
        System.out.println(leet.findCheapestPrice(3, new int[][]{{0, 1, 100}, {1, 2, 100}, {0, 2, 500}}, 0, 2, 1));
        System.out.println(leet.findCheapestPrice(3, new int[][]{{0, 1, 100}, {1, 2, 100}, {0, 2, 500}}, 0, 2, 0));
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        List<int[]>[] lists = new ArrayList[n];//list[i]리스트는 i노드에서 출발하는 간선의 목적지노드:가중치 쌍 배열을 저장한다.

        for (int[] flight : flights) {
            if (lists[flight[0]] == null) {
                lists[flight[0]] = new ArrayList<>();
            }

            lists[flight[0]].add(flight);//목적지 노드:가중치 쌍으로 저장
        }

        //큐에는 마지막으로 지나친 노드의 번호:이제까지 통과한 노드의 수:가중치의 합 조합의 배열을 저장해야할 것 같다.
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {src,-1,0});
        int minCost = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            int[] polled = queue.pollFirst();
            if (polled[1] <= k && polled[0] == dst) {
                if (minCost > polled[2]) {
                    minCost = polled[2];
                }
                continue;
            }

            if (lists[polled[0]] == null) {
                continue;
            }

            for (int[] flight : lists[polled[0]]) {
                if (polled[2] + flight[2] >= minCost || polled[1] + 1 > k) {//비용이 minCost를 초과하거나, 지나는 노드의 수가 k를 넘게 되면 그쪽으로는 뻗어나가지 않는다.
                    continue;
                }

                queue.add(new int[] {flight[1], polled[1] + 1, polled[2] + flight[2]});
            }
        }

        return minCost;
    }
}
