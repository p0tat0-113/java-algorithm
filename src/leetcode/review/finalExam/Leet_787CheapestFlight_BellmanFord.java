package leetcode.review.finalExam;

import java.util.Arrays;

/*
노드의 총 갯수인 n, 간선정보(방향, 가중치) flights배열, 시작정점, 도착정점, 최대 경유 가능 노드 수 k를 입력 받는다.
중간에 최대 k개의 노드만 경유할 때 시작정점에서 도착정점으로 가는 최소비용을 찾는 문제다.

743번 NetworkDelayTime문제의 Node에 이 distance를 얻기 위해 경유한 노드의 수 값까지 기록하게 하는 식으로 해도 될 것 같기도 하고,
그냥 dfs로 풀어도 될 것 같기도 하다.

map에 각 노드의 간선정보를 입력하고, dfs로 그걸 타고 이동한다. 만약 타고 가다가 경유한 수가 k를 초과하면 그대로 리턴시켜버리고,
k를 초과하지 않고 목적지에 도달하면 비용을 기록하는 식으로 하면 될 듯. <- 이 방식으로 접근했고, 잘 작동되기는 하는데 간선의 수가 많은 경우 시간초과가 뜬다.
*/
public class Leet_787CheapestFlight_BellmanFord {

    //이 문제를 벨만포드 방식으로 다시 풀어본다.
    /*
    단일 시작점에서 모든 정점까지의 최단거리를 구하는 벨만 포드 알고리즘의 수도코드
    BellmanFord(G) {
        foreach u in V {
            u.dis = infinite;
        }
        v.dis = 0;

        for i = 1 to |V|-1 {
            foreach (u,v) in E {
                if v.dis > u.dis + Wuv {
                    v.dis = u.dis + Wuv
                }
            }
        }
    }
    */
    public static void main(String[] args) {
        Leet_787CheapestFlight_BellmanFord leet = new Leet_787CheapestFlight_BellmanFord();
        int[][] flights1 = {{0, 1, 100}, {1, 2, 100}, {2, 0, 100}, {1, 3, 600}, {2, 3, 200}};
        System.out.println(leet.findCheapestPrice(4, flights1, 0, 3, 1));
        int[][] flights2 = {{0, 1, 100},{1, 2, 100},{0, 2, 500}};
        System.out.println(leet.findCheapestPrice(3, flights2, 0,2,1));
        System.out.println(leet.findCheapestPrice(3, flights2, 0,2,0));
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        //모든 정점의 distance값을 초기화
        int[] distances = new int[n];
        Arrays.setAll(distances, e -> Integer.MAX_VALUE);
        distances[src] = 0;

        for (int i = 0; i <= k; i++) {//최대 k개의 정점을 통과하는, 최대 k+1개의 간선을 가지는 최단경로를 계산
            int[] tempDistances = Arrays.copyOf(distances, n);//현재의 distance상태를 박제해놓는다.
            for (int[] flight : flights) {//모든 간선을 검사한다.
                int u = flight[0];
                int v = flight[1];
                int distanceU = tempDistances[u];
                int distanceV = distances[v];//원본 distances에서 가장 최신으로 이완된 distance값을 가져온다.
                int Wuv = flight[2];

                if (distanceU == Integer.MAX_VALUE) {//distanceU값이 아직 무한대이면 그대로 패스한다. 이건 이완을 일으킬 수가 없는 상태임.
                    continue;
                }
                if (distanceV > distanceU + Wuv) {
                    distances[v] = distanceU + Wuv;//이완의 결과는 원본 distances에 저장한다.
                }
            }
        }

        System.out.println(Arrays.toString(distances));
        return (distances[dst] != Integer.MAX_VALUE) ? distances[dst] : -1;
    }
}
