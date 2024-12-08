package leetcode.review.finalExam;

import java.util.*;

public class Leet_1584MInCostToConnectAllPoints {
    public static void main(String[] args) {
        Leet_1584MInCostToConnectAllPoints leet = new Leet_1584MInCostToConnectAllPoints();
        System.out.println(leet.minCostConnectPoints(new int[][]{{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}}));
        System.out.println(leet.minCostConnectPoints(new int[][]{{3, 12}, {-2, 5}, {-4, 1}}));
    }

    public int minCostConnectPoints(int[][] points) {
        int n = points.length;

        int[] costs = new int[n];//각 노드의 MST에 추가되기 위한 최소비용을 저장하는 배열
        Arrays.setAll(costs, e -> Integer.MAX_VALUE);
        costs[0] = 0;

        HashSet<Integer> setS = new HashSet<>();//MST에 추가된 노드들은 여기에 보관

        //MST에 추가되기 위한 비용이 적은 순으로 나오는 minHeap
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
        minHeap.add(new int[] {0,0});//노드번호 - MST에 추가되기 윈한 cost쌍으로 저장

        int costSum = 0;//MST의 간선 가중치 총합

        while (!minHeap.isEmpty()) {
            int[] uStatus = minHeap.poll();//비용이 가장 작은 노드 u를 꺼낸다. 처음에는 0번 노드가 나오게 되어있음.
            int u = uStatus[0];

            if (setS.contains(u)) {//이미 S에 추가된 노드인 경우 continue, 이런 코드가 필요한 이유는 아래에서 minHeap을 사용하는 방식과 관련이 있다.
                continue;
            }

            costSum += uStatus[1];//costSum에 더함.
            setS.add(u);//집합 S에 u를 집어넣음.

            for (int v = 0; v < n; v++) {//집합 S에 들어간 노드를 제외한 모든 노드를 검사
                if (setS.contains(v)) {
                    continue;
                }
                int Wuv = manhattan(points[u], points[v]);//u를 통해 MST에 추가되는 비용을 계산
                int vCost = costs[v];
                if (vCost > Wuv) {
                    vCost = Wuv;
                    costs[v] = vCost;
                    minHeap.add(new int[] {v, vCost});//minHeap에 들어가있던 기존 v의 비용 정보를 삭제하지 않고, 그냥 새로운 비용으로 집어넣음. 기존 정보는 위에서 집합 S에 들어가 있는지 검사하는 부분에서 걸러짐.
                }
            }
        }

        return costSum;
    }

    private int manhattan(int[] x, int[] y) {
        return Math.abs(x[0] - y[0]) + Math.abs(x[1] - y[1]);
    }
}

