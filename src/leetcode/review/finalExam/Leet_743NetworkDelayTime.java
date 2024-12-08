package leetcode.review.finalExam;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

public class Leet_743NetworkDelayTime {
    public static void main(String[] args) {
        Leet_743NetworkDelayTime leet = new Leet_743NetworkDelayTime();
        System.out.println(leet.networkDelayTime(new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2));
        System.out.println(leet.networkDelayTime(new int[][]{{1,2,1}}, 2, 1));
        System.out.println(leet.networkDelayTime(new int[][]{{1,2,1}}, 2, 2));
    }

    //다익스트라 알고리즘 구현.
    public int networkDelayTime(int[][] times, int n, int k) {
        Node[] nodeArr = new Node[n + 1];//1~N까지의 노드를 생성
        for (int i = 1; i <= n; i++) {
            nodeArr[i] = new Node(i, Integer.MAX_VALUE);
        }
        nodeArr[k].distance = 0;

        for (int[] time : times) {//간선정보 입력
            int u = time[0];
            nodeArr[u].edges.add(time);
        }

        HashSet<Integer> setS = new HashSet<>();//이완시 특정 노드가 V-S에 속해있는지 알아내기 위해 생성

        PriorityQueue<Node> minHeap = new PriorityQueue<>();//최소힙. 노드의 distance를 기준으로 정렬됨.
        for (int i = 1; i <= n; i++) {
            minHeap.add(nodeArr[i]);
        }

        while (!minHeap.isEmpty()) {
            Node u = minHeap.poll();
            setS.add(u.label);

            if (u.distance == Integer.MAX_VALUE) {
                return -1;
            }
            if (minHeap.isEmpty()) {//가장 마지막으로 꺼내진 노드의 distance값을 반환한다.
                return u.distance;
            }

            for (int[] edge : u.edges) {
                Node v = nodeArr[edge[1]];
                int Wuv = edge[2];

                if (!setS.contains(v.label) && v.distance > u.distance + Wuv) {
                    v.distance = u.distance + Wuv;
                    minHeap.remove(v);
                    minHeap.add(v);
                }
            }
        }

        return 0;
    }

    private static class Node implements Comparable<Node>{
        int label;
        int distance;
        List<int[]> edges = new ArrayList<>();

        public Node(int label, int distance) {
            this.label = label;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(distance, o.distance);
        }
    }
}
