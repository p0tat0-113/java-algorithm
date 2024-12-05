package leetcode.review.finalExam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
        //간선 정보를 배열에 저장한다. lists[i]는 i노드의 간선정보들이 저장되어 있다.
        List<int[]>[] lists = new List[n];
        for (int i = 0; i < n; i++) {
            lists[i] = new ArrayList<>();
        }
        for (int[] flight : flights) {
           lists[flight[0]].add(flight);
        }

        int[] distance = new int[n];
        Arrays.setAll(distance, e -> Integer.MAX_VALUE);
        //distance[0] = 0; 처음에 이렇게 해서 틀렸다. 시작점은 무조건 0번 노드가 아니라, 따로 src라는 변수로 입력으로 들어온다!
        distance[src] = 0;//사실 이 코드도 없어도 되긴 함.

        LinkedList<int[]> queue = new LinkedList<>();
        //queue.offer(new int[] {0,0}); 이것도 처음에 이렇게 해서 틀림.
        queue.offer(new int[] {src,0});

        int stops = 0;
        while (!queue.isEmpty() && stops <= k) {//k단계까지만 bfs과정을 진행한다.
            int t = queue.size();
            for (int i = 0; i < t; i++) {
                int[] uStatus = queue.pollFirst();
                int u = uStatus[0];
                int uDistance = uStatus[1];

                //u의 인접노드인 v를 모두 검사
                List<int[]> adjlist = lists[u];
                for (int[] vStatus : adjlist) {
                    int v = vStatus[1];
                    int vDistance = distance[v];
                    int Wuv = vStatus[2];
                    if (vDistance > uDistance + Wuv) {//Relaxation
                        vDistance = uDistance + Wuv;
                        distance[v] = vDistance;//distance배열에 이완된 비용을 업데이트시킨다
                        queue.offer(new int[] {v, vDistance});//이완된 비용으로 다시 큐에 집어넣음.
                    }
                }
            }
            stops++;
        }

        //System.out.println(Arrays.toString(distance));
        return (distance[dst] != Integer.MAX_VALUE) ? distance[dst] : -1;
    }
}
