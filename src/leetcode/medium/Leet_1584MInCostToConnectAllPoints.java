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
    <- 근데 문제에서 제시되는 정점들은 어떠한 연결관계도 제시되어 있지 않아서 인접정점이라는 개념 자체가 없다고 봐야할 듯. 모든 정점의 비용을 업데이트하는게 맞아 보인다.

    일단 모든 정점의 가중치를 업데이트 하는 방식으로 한다고 하자. V-S에서 MST에 포함되기 위한 가중치가 가장 작은 것을 골라내야 하는데
    이건 priority queue(최소힙)를 사용하면 될 것 같다. 교재에서도 힙을 사용하는 방식에 대한 언급이 나온다.
    그리고 이 모든 과정을 편하게 진행하려면 map으로 어쩌구 하는 것 보다는 별도의 클래스를 만들어서 한 번 wrap을 하는게 좋아보인다.
    아 근데 생각해보니까 인접 정점이 아닌 것들까지 업데이트 하면 알고리즘이 제대로 안돌아가네... <- 아님 무지성으로 업데이트를 하는게 아니라
    v.cost > W(u,v)인 경우에만 업데이트를 한다.*/

    public int minCostConnectPoints(int[][] points) {
        int sum = 0;//MST의 모든 간선 가중치 합

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Node(points[0][0], points[0][1], 0));
        for (int i = 1; i < points.length; i++) {
            priorityQueue.add(new Node(points[i][0], points[i][1]));
        }

        while(!priorityQueue.isEmpty()) {
            Node polledNode = priorityQueue.poll();//MST에 추가되는 비용이 가장 적게 드는 정점을 MST에 추가한다.
            //System.out.println(polledNode.cost);
            sum += polledNode.cost;

            //큐에 있는 모든 노드를 순회하면서 cost 업데이트
            //방금 MST에 추가된 정점을 통해 MST에 연결될 경우 비용이 더 적게 드는 정점들은 비용을 업데이트 시킨다.
            List<Node> updatedNodes = new ArrayList<>();
            for (Node node : priorityQueue) {
                int newCost = Math.abs(polledNode.x - node.x) + Math.abs(polledNode.y - node.y);
                if (node.cost > newCost) {
                    updatedNodes.add(node);
                }
            }

            // 업데이트된 노드를 제거 후 새로운 cost로 다시 삽입
            for (Node node : updatedNodes) {
                priorityQueue.remove(node);  // O(n) 시간 복잡도
                node.cost = Math.abs(polledNode.x - node.x) + Math.abs(polledNode.y - node.y);
                priorityQueue.add(node);     // 힙에 다시 삽입하여 올바른 위치에 배치
            }

            //System.out.println(priorityQueue);
        }

        return sum;
    }

    //처리를 편하게 하기 위해 정점을 Node내부 클래스로 wrap한다.
    //priority queue에 넣을 거라서 Comparable인터페이스를 구현함.
    private static class Node implements Comparable<Node>{
        int x;
        int y;
        int cost;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
            cost = Integer.MAX_VALUE;
        }

        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }


        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    ", cost=" + cost +
                    '}';
        }
    }
}
