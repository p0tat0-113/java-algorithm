package leetcode.medium;

/*
https://imgur.com/a/A15E0gR
야호 리팩토링 성공!!!
리트코드 12ms나오는 코드의 아이디어를 차용했다. 코드 읽어내는 실력이 좀 많이 늘어난 듯?
8ms 달성함.
*/

import java.util.*;

public class Leet_2662MinimumCostOfAPathWithSpecialRoads_1 {
    public static void main(String[] args) {
        Leet_2662MinimumCostOfAPathWithSpecialRoads_1 leet = new Leet_2662MinimumCostOfAPathWithSpecialRoads_1();

        System.out.println(leet.minimumCost(new int[]{1, 1}, new int[]{4, 5}, new int[][]{{1, 2, 3, 3, 2}, {3, 4, 4, 5, 1}}));
        System.out.println(leet.minimumCost(new int[]{3, 2}, new int[]{5, 7}, new int[][]{{5, 7, 3, 2, 1}, {3, 2, 3, 4, 4}, {3, 3, 5, 5, 5}, {3, 4, 5, 6, 6}}));
        System.out.println(leet.minimumCost(new int[]{1, 1}, new int[]{10, 4},
                new int[][]{
                        {4, 2, 1, 1, 3},
                        {1, 2, 7, 4, 4},
                        {10, 3, 6, 1, 2},
                        {6, 1, 1, 2, 3}
                }
        ));
        System.out.println(leet.minimumCost(new int[]{1, 1}, new int[]{8, 7},
                new int[][]{
                        {7, 7, 8, 7, 2},
                        {6, 5, 1, 7, 4},
                        {6, 1, 7, 7, 1},
                        {8, 6, 4, 7, 2}
                }
        ));
    }

    public int minimumCost(int[] start, int[] target, int[][] specialRoads) {
        int n = specialRoads.length;

        int[] distances = new int[n];//start에서 각 special road의 도착점까지 가는 최소비용(distance)를 기록하는 배열

        for (int i = 0; i < n; i++) {
            int specialStartX = specialRoads[i][0];
            int specialStartY = specialRoads[i][1];
            //int specialTargetX = specialRoads[i][2];
            //int specialTargetY = specialRoads[i][3];
            int specialW_start_target = specialRoads[i][4];

            distances[i] = getDistance(start[0], start[1], specialStartX, specialStartY) + specialW_start_target;
        }

        PriorityQueue<int[]> minHeap = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
        for (int i = 0; i < n; i++) {
            minHeap.add(new int[] {i, distances[i]});
        }

        //이제 다익스트라 알고리즘을 돌려서 start에서 각 special road의 도착점까지 가는 최소비용을 구한다.
        //진짜 지름길 역할을 하는 special road가 여러개 이어지면서 거리가 단축되는 루트를 찾는 것이다.
        //지금 이 문제의 특수성 때문에, 일반적인 다익스트라처럼 최소힙에서 나왔다고 바로 setS에 집어넣어서 이 알고리즘에서 제외시켜서는 안될 것 같다.
        while (!minHeap.isEmpty()) {
            int[] polled = minHeap.poll();
            int u = polled[0];
            int distanceU = polled[1];

            if (distanceU > distances[u]) {
                continue;
            }

            for (int v = 0; v < n; v++) {
                if (v == u) {
                    continue;
                }

                int distanceV = distances[v];

                //start - 루트u - 루트v 이렇게 루트u를 꼽싸리 꼈을 때 비용이 줄어드는지 보는 것이다.
                int uTargetX = specialRoads[u][2];
                int uTargetY = specialRoads[u][3];
                int vStartX = specialRoads[v][0];
                int vStartY = specialRoads[v][1];
                int W_vStart_vTarget = specialRoads[v][4];
                int newDistance = distanceU + getDistance(uTargetX, uTargetY, vStartX, vStartY) + W_vStart_vTarget;

                //만약 루트u가 꼽사리 꼈을 때 비용이 더 줄어든다면
                if (distanceV > newDistance) {
                    minHeap.add(new int[] {v, newDistance});
                    distances[v] = newDistance;
                }
            }
        }

        int minDistanceWithSpecialRoads = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int specialTargetX = specialRoads[i][2];
            int specialTargetY = specialRoads[i][3];
            int distanceWithSpecialRoad = distances[i] + getDistance(specialTargetX, specialTargetY, target[0], target[1]);

            if (distanceWithSpecialRoad < minDistanceWithSpecialRoads) {
                minDistanceWithSpecialRoads = distanceWithSpecialRoad;
            }
        }

        int directDistance = getDistance(start[0], start[1], target[0], target[1]);
        if (directDistance < minDistanceWithSpecialRoads) {
            //System.out.println("direct!");
            return directDistance;
        }

        return minDistanceWithSpecialRoads;
    }

    private int getDistance(int startX, int startY, int targetX, int targetY) {
        return Math.abs(targetX-startX) + Math.abs(targetY-startY);
    }
}
