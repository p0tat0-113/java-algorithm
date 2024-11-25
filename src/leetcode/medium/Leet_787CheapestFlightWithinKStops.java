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

    //dfs로 푸는게 불가능한건가 싶어서 solution을 찾아보니까 dfs로 푼 사례가 있음. 코드가 거의 비슷하긴 한데 나와 다른 점이 있다면 map이 아니라 2차원 리스트를 썼다는 점 정도....?
    //이렇게 했는데도 안되고, solutios의 코드를 그대로 돌려봐도 시간초과가 뜨는걸 보니까 dfs방식은 지금의 테스트 케이스는 뚫을 수 없는 것으로 보인다..../
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        List<int[]>[] edgeList = new List[n];
        for (int[] flight : flights) {
            if(edgeList[flight[0]] == null) {
                edgeList[flight[0]] = new ArrayList<>();
            }
            edgeList[flight[0]].add(flight);
        }

        boolean[] visited = new boolean[n];//혹시 사이클이 발생하는게 문제일 수도 있지 않을까 싶어서 방문한 노드를 기록시키려고 한다.
        visited[src] = true;

        dfs(src, dst, 0, -1, k, edgeList, visited);

        if (minCost == Integer.MAX_VALUE) {
            return -1;
        }

        return minCost;
    }

    int minCost = Integer.MAX_VALUE;

    //totalCost는 0, passedNode는 -1로 시작해야 한다.
    private void dfs(int currentNode, int dst, int totalCost, int passedNode, int k, List<int[]>[] edgeList, boolean[] visited) {

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

        List<int[]> flights = edgeList[currentNode];

        if (flights == null) {//<- 처음에 이 부분을 빼먹어서 nullPointerException이 발생했었다.
            return;
        }

        for (int[] flight : flights) {
            if (visited[flight[1]]) {
                continue;
            }
            totalCost += flight[2];
            visited[flight[1]] = true;

            dfs(flight[1], dst, totalCost, passedNode+1, k, edgeList, visited);

            totalCost -= flight[2];
            visited[flight[1]] = false;
        }
    }
}
