package leetcode.medium;

import java.util.*;

public class Leet_1584MInCostToConnectAllPoints {
    public static void main(String[] args) {

    }

    /*프림 알고리즘을 사용한다고 할 때
    MST에 연결되기 위한 가중치가 가장 작은 정점을 골라내면서 반복
    각 정점의 가중지를 저장하는 HashMap이 있어야 할 것으로 보임. points인덱스 -  MST에 연결되기 위한 최소 가중치 쌍
    문제는 MST에 새로운 정점이 추가될 떄, 해당 정점의 인접 정점들의 가중치를 업데이트 해야하는데, 인접정점은 어떻게 골라내야 할까?
    그냥 모든 정점의 가중치를 업데이트하는 방식으로 해도 되긴 하겠다만, 정점의 수가 최대 1000개인 상황에서 이건 그다지 좋은 방법 같지는 않다.

    일단 모든 정점의 가중치를 업데이트 하는 방식으로 한다고 하자. V-S에서 MST에 포함되기 위한 가중치가 가장 작은 것을 골라내야 하는데
    이건 priority queue(최소힙)를 사용하면 될 것 같다. 교재에서도 힙을 사용하는 방식에 대한 언급이 나온다.
    그리고 이 모든 과정을 편하게 진행하려면 map으로 어쩌구 하는 것 보다는 별도의 클래스를 만들어서 한 번 wrap을 하는게 좋아보인다.
    아 근데 생각해보니까 인접 정점이 아닌 것들까지 업데이트 하면 알고리즘이 제대로 안돌아가네...*/

    public int minCostConnectPoints(int[][] points) {
        int sum = 0;//MST의 모든 간선 가중치 합

        /*HashSet<Node> q = new HashSet<>();//아직 MST에 추가되지 않은 정점들의 집합
        q.add(new Node(points[0][0], points[0][1], 0));
        for (int i = 1; i < points.length; i++) {
            q.add(new Node(points[i][0], points[i][1]));
        }*/

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Node(points[0][0], points[0][1], 0));
        for (int i = 1; i < points.length; i++) {
            priorityQueue.add(new Node(points[i][0], points[i][1]));
        }

        while(!priorityQueue.isEmpty()) {
            Node polledNode = priorityQueue.poll();
            sum += polledNode.cost;

            for (Node node : priorityQueue) {

            }
        }
    }

    private static class Node implements Comparable<Node>{
        int[] pos;
        int cost;

        public Node(int x, int y) {
            this.pos = new int[] {x,y};
            cost = Integer.MAX_VALUE;
        }

        public Node(int x, int y, int cost) {
            this.pos = new int[] {x,y};
            this.cost = cost;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.deepEquals(pos, node.pos);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(pos);
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
}
