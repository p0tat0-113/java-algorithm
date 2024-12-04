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
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Leet_787CheapestFlightWithinKStops_BellmanFord {
    //이번에는 진짜 벨만포드로 풀어본다. 이 문제는 벨만포드로 푸는게 가장 간단해보임. 우선 벨만포드의 수도코드부터 보자
    /*
    BellmanFord(G) {
        foreach u in V {
            u.d = infinite;
        }
        r.d = 0;

        for i = 1 to |V|-1 {
            foreach (u,v) in E {
                if v.d > u.d + Wuv {
                    v.d = u.d + Wuv
                }
            }
        }
    }
    */
    //벨만포드는 모든 간선을 검사하는 것을 V-1번 반복해서 시작정점에서 모든정점에 이르는 최단거리를 계산한다.
    //모든 간선을 검사하는 과정을 i번 반복하면 간선을 최대 i개 사용했을 때의 최단거리가 구해진다.
    //이것을 V-1에서 k로 바꾸기만 하면 된다.
    //지금 이 문제는 입력으로 간선을 던져주기 때문에 벨만포드로 구현하기에 딱 좋다.

    public static void main(String[] args) {
        Leet_787CheapestFlightWithinKStops_BellmanFord leet = new Leet_787CheapestFlightWithinKStops_BellmanFord();
        System.out.println(leet.findCheapestPrice(3, new int[][]{{0, 1, 100}, {1, 2, 100}, {0, 2, 500}}, 0, 2, 1));
        System.out.println(leet.findCheapestPrice(3, new int[][]{{0, 1, 100}, {1, 2, 100}, {0, 2, 500}}, 0, 2, 0));
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] distanceArr = new int[n];
        Arrays.setAll(distanceArr, e -> Integer.MAX_VALUE);
        distanceArr[src] = 0;

        for (int i = 0; i <= k; i++) {
            int[] tempDistanceArr = Arrays.copyOf(distanceArr, distanceArr.length);//모든 간선을 검사하기 전의 distanceArr을 박제해놓는다.

            for (int[] flight : flights) {
                //계산할 때 필요한 가중치들은 tempDistanceArr에서 가져오고
                int dU = tempDistanceArr[flight[0]];
                int v = flight[1];
                int dV = distanceArr[v];//dV = tempDistanceArr[v]로 해서 값이 이상하게 나오고 있었다. 중요!!!!
                //dV = tempDistanceArr[v]로 했을 때의 문제점이 뭐냐면 v로 가는 경로가 이렇게 (u1,v,100) (u2,v,200) 2개가 있다고 치자. 100으로 가는 짧은 길을 찾아서 이걸로 원본 distanceArr을 업데이트 해 놓았음에도
                //이걸 모르고 (u2,v,200) 이 경로로 가는 비용으로 덮어버릴 수가 있다. 내가 d값을 계속 반영하고 있는 distanceArr에 있는 가장 최신의 작은 값을 가져와야 하는 것이다.
                int Wuv = flight[2];

                if (dU == Integer.MAX_VALUE) {
                    continue;
                }
                if (dV > dU + Wuv) {
                    //저장만 distanceArr에 이루어지게 하는 식으로 최단거리를 구하는 것이 연쇄적으로 일어나지 않게 할 수 있다.
                    distanceArr[v] = dU + Wuv;
                }
            }
        }

        //System.out.println(Arrays.toString(distanceArr));
        return (distanceArr[dst] != Integer.MAX_VALUE) ? distanceArr[dst] : -1;
    }
}
