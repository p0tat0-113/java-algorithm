package leetcode.review.finalExam;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Leet_1584MInCostToConnectAllPoints {
    public static void main(String[] args) {
        Leet_1584MInCostToConnectAllPoints leet = new Leet_1584MInCostToConnectAllPoints();
        System.out.println(leet.minCostConnectPoints(new int[][]{{0,0}, {2,2}, {3,10}, {5,2}, {7,0}}));
    }

    public int minCostConnectPoints(int[][] points) {
        int n = points.length;//정점의 수
        int totalCost = 0;
        boolean[] used = new boolean[n];

        HashMap<Integer, Integer> map = new HashMap<>();//노드번호 : MST에 연결되기 위한 가중치 쌍으로 저장
        map.put(0,0);//0번 노드가 MST에 추가되기 위한 가중치는 0

        PriorityQueue<int[]> minHeap = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));//처음에 o[0]으로 값을 비교해서 계속 틀리고 있었음.
        minHeap.add(new int[]{0,0});

        while (!minHeap.isEmpty()) {
            int[] polledNode = minHeap.poll();
            int nodeNum = polledNode[0];
            int cost = polledNode[1];

            if (used[nodeNum]) {
                continue;
            }

            used[nodeNum] = true;
            totalCost += cost;

            for (int v = 0; v < n; v++) {//모든 노드들을 검사한다.
                if (!used[v]) {//if v = V-S
                    int newDistance = calculateManhattan(points[nodeNum], points[v]);//u를 통해 MST에 추가되는 비용을 계산

                    if (map.getOrDefault(v, Integer.MAX_VALUE) > newDistance) {//if v.cost > Wuv 처음에 map.getOrDefault(v, Integer.MAX_VALUE)가 아니라 cost를 넣는 이상한 짓을 해서 계속 틀리고 있었음. cost는 u가 MST에 추가되기 위한 비용이고 v와는 아무런 관련이 없음.
                        map.put(v, newDistance);
                        minHeap.add(new int[] {v, newDistance});
                    }
                }
            }
        }

        return totalCost;
    }

    private int calculateManhattan (int[] u, int[] v) {
        return Math.abs(u[0] - v[0]) + Math.abs(u[1] - v[1]);
    }
}
