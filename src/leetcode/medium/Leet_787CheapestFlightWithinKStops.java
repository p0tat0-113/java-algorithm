package leetcode.medium;

/*
노드의 총 갯수인 n, 간선정보(방향, 가중치) flights배열, 시작정점, 도착정점, 최대 경유 가능 노드 수 k를 입력 받는다.
중간에 최대 k개의 노드만 경유할 때 시작정점에서 도착정점으로 가는 최소비용을 찾는 문제다.

743번 NetworkDelayTime문제의 Node에 이 distance를 얻기 위해 경유한 노드의 수 값까지 기록하게 하는 식으로 해도 될 것 같기도 하고,
그냥 dfs로 풀어도 될 것 같기도 하다.

map에 각 노드의 간선정보를 입력하고, dfs로 그걸 타고 이동한다. 만약 타고 가다가 경유한 수가 k를 초과하면 그대로 리턴시켜버리고,
k를 초과하지 않고 목적지에 도달하면 비용을 기록하는 식으로 하면 될 듯. <- 이 방식으로 접근했고, 잘 작동되기는 하는데 간선의 수가 많은 경우 시간초과가 뜬다.
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Leet_787CheapestFlightWithinKStops {
    public static void main(String[] args) {
        Leet_787CheapestFlightWithinKStops leet = new Leet_787CheapestFlightWithinKStops();
        System.out.println(leet.findCheapestPrice(3, new int[][]{{0, 1, 100}, {1, 2, 100}, {0, 2, 500}}, 0, 2, 1));
        leet.minCost = Integer.MAX_VALUE;
        System.out.println(leet.findCheapestPrice(3, new int[][]{{0, 1, 100}, {1, 2, 100}, {0, 2, 500}}, 0, 2, 0));
        leet.minCost = Integer.MAX_VALUE;
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        HashMap<Integer, List<int[]>> edgeHashMap = new HashMap<>();
        for (int[] flight : flights) {
            if (edgeHashMap.containsKey(flight[0])) {
            } else {
                edgeHashMap.put(flight[0], new ArrayList<>());
            }
            edgeHashMap.get(flight[0]).add(flight);
        }
        //System.out.println(edgeHashMap);

        dfs(src, dst, 0, -1, k, edgeHashMap);

        if (minCost == Integer.MAX_VALUE) {
            return -1;
        }

        return minCost;
    }

    int minCost = Integer.MAX_VALUE;

    //totalCost는 0, passedNode는 -1로 시작해야 한다.
    private void dfs(int currentNode, int dst, int totalCost, int passedNode, int k, HashMap<Integer, List<int[]>> edgeHashMap) {
        if (passedNode > k) {
            return;
        }
        //이 부분의 코드를 약간 수정하여 성능을 끌어올렸지만, 노드가 100이고 간선은 훨씬 더 많은 상황에서는 여전히 시간초과가 발생함. 테스트 케이스가 좀 악랄함....
        if (totalCost > minCost) {
            return;
        }
        if (currentNode == dst) {
            minCost = totalCost;
            return;
        }

        List<int[]> flights = edgeHashMap.get(currentNode);

        if (flights == null) {//<- 처음에 이 부분을 빼먹어서 nullPointerException이 발생했었다.
            return;
        }

        for (int[] flight : flights) {
            totalCost += flight[2];
            dfs(flight[1], dst, totalCost, passedNode+1, k, edgeHashMap);
            totalCost -= flight[2];
        }
    }
}
