package leetcode.medium;

/*
다익스트라 알고리즘을 사용해야 하는 최단거리 문제다.

n개의 노드로 이루어진 그래프가 주어진다. 각 노드는 1~n으로 라벨링 되어 있음. 유향그래프이고, 간선에 가중치가 주어진다.
간선은 times[][]배열로 주어지는데 times[i] = (출발노드, 도착노드, 가중치)이다.

출발 노드 k를 주면 k에서 n까지 가는 최단거리를 알아내는 문제다. k에서 n까지 가는 것이 불가능한 경우 -1을 반환한다. <- 아님.
문제를 잘못 이해하고 있었다. 노드 k에서 신호를 보낼 때 모든 n개의 노드가 신호를 수신하는데 걸리는 최소시간을 반환하는 것이다. 모든 n개 노드가 신호를 수신하는 것이 불가능한 경우 -1을 반환해야 함.

다익스트라 알고리즘은 기본적으로 프림알고리즘과 유사하다. 어떤 새로운 노드가 S에 추가되었을 때 그 노드의 인접노드들에 대해
그 노드를 통해 연결되면 비용이 줄어드는 경우 비용을 업데이트 하는 방식으로 작동한다.

각 노드들은 cost값을 가져야 한다. 각 노드는 자신이 어디에 연결되어 있는지는 모른다. 자신이 연결하는 노드만 알고있음.
각 노드는 자신이 연결하는 노드들과, 그 노드들과 연결되는 각 간선의 가중치 정보를 알고 있어야 한다.

시작 노드 k의 cost는 0으로 설정해 놓아야 함.
*/

import java.util.*;

public class Leet_743NetworkDelayTime {
    public static void main(String[] args) {
        Leet_743NetworkDelayTime leet = new Leet_743NetworkDelayTime();
        System.out.println(leet.networkDelayTime(new int[][]{{2, 1, 1,}, {2, 3, 1}, {3, 4, 1}}, 4, 2));
        System.out.println(leet.networkDelayTime(new int[][]{{1, 2, 1}}, 2, 1));
        System.out.println(leet.networkDelayTime(new int[][]{{1, 2, 1}}, 2, 2));
    }

    public int networkDelayTime(int[][] times, int n, int k) {
        HashMap<Integer, Node> mapQ = new HashMap<>();

        //1~n노드들을 생성해서 mapQ에 넣어준다. k노드의 경우 cost를 0으로 설정한다.
        for (int label = 1; label <= n; label++) {
            Node newNode = new Node(label, Integer.MAX_VALUE);
            if (label == k) {
                newNode.cost = 0;
            }
            mapQ.put(label, newNode);
        }
        //노드들에 간선 정보를 입력
        for (int[] time : times) {
            Node node = mapQ.get(time[0]);
            node.edge.add(time);
        }

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(mapQ.values());//노드들을 가지고 있는 priorityQueue생성. cost를 기준으로 최소힙 구성.
        while(true) {
            Node u = priorityQueue.poll();//cost가 가장 작은 것을 꺼냄. 맨 처음에는 시작정점 k가 나올 것이다.
            mapQ.remove(u.label);//mapQ에서도 제거

            //만약 꺼낸 노드의 cost가 Integer.MAX_VALUE라면 얘는 지금 아무 노드와도 연결되어 있지 않아서 여태껏 cost가 업데이트 되지 못한 것이다. 즉 모든 n개 노드가 신호를 수신하는 것이 불가능한 경우
            if (u.cost == Integer.MAX_VALUE) {
                return -1;
            }

            //mapQ가 빈 상태라면 방금 꺼낸 u가 마지막 노드인 것. u의 cost가 답이다.
            if (mapQ.isEmpty()) {
                return u.cost;
            }

            //u의 인접노드들에 대해 cost 업데이트 - for v in u.adjlist: if(v=V-S and d(v)>d(u)+w(u,v))
            for (int[] time : u.edge) {
                if (mapQ.containsKey(time[1])) {//if(v = V-S)
                    Node v = mapQ.get(time[1]);
                    if (v.cost > u.cost + time[2]) {//if(d(v) > d(u) + w(u,v))
                        v.cost = u.cost + time[2];

                        priorityQueue.remove(v);//cost가 업데이트 되면서 힙이 깨져서 우선 삭제하고 다시 집어넣는다.
                        priorityQueue.add(v);
                    }
                }
            }
        }
    }

    private static class Node implements Comparable<Node>{
        int label;
        int cost;
        List<int[]> edge = new ArrayList<>();//안에 [출발노드, 도착노드, 가중치] 배열이 들어간다.

        public Node(int label) {
            this.label = label;
        }

        public Node(int label, int cost) {
            this.label = label;
            this.cost = cost;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return label == node.label;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(label);
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
}

