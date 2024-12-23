package baekjoon.shortestPath;

/*
n(1≤n≤1,000)개의 도시가 있다. 그리고 한 도시에서 출발하여 다른 도시에 도착하는 m(1≤m≤100,000)개의 버스가 있다.
우리는 A번째 도시에서 B번째 도시까지 가는데 드는 버스 비용을 최소화 시키려고 한다.
그러면 A번째 도시에서 B번째 도시 까지 가는데 드는 최소비용과 경로를 출력하여라. 항상 시작점에서 도착점으로의 경로가 존재한다.

##입력
첫째 줄에 도시의 개수 n(1≤n≤1,000)이 주어지고 둘째 줄에는 버스의 개수 m(1≤m≤100,000)이 주어진다. 그리고 셋째 줄부터 m+2줄까지 다음과 같은 버스의 정보가 주어진다.
먼저 처음에는 그 버스의 출발 도시의 번호가 주어진다. 그리고 그 다음에는 도착지의 도시 번호가 주어지고 또 그 버스 비용이 주어진다. 버스 비용은 0보다 크거나 같고, 100,000보다 작은 정수이다.
그리고 m+3째 줄에는 우리가 구하고자 하는 구간 출발점의 도시번호와 도착점의 도시번호가 주어진다.

다익스트라로 문제를 푸는데, 이완시킬 때 distance 정보 뿐만 아니라, 경로에 포함되어 있는 도시의 수, 그리고 경유한 도시 목록까지 저장해야 한다.
별도의 Vertex객체를 만들어서 푸는게 좋을 듯...?
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek_11779최소비용구하기2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int numberOfNodes = Integer.parseInt(br.readLine());
        //numberOfNodes개의 노드 생성
        Node[] nodes = new Node[numberOfNodes+1];
        for (int i = 1; i <= numberOfNodes; i++) {
            nodes[i] = new Node(i);
        }

        int numberOfEdges = Integer.parseInt(br.readLine());
        //각 노드들에 간선 정보를 입력
        for (int i = 0; i < numberOfEdges; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            nodes[Integer.parseInt(st.nextToken())].edges.add(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }

        //src, dst값을 입력받음.
        StringTokenizer st = new StringTokenizer(br.readLine());
        int src = Integer.parseInt(st.nextToken());
        int dst = Integer.parseInt(st.nextToken());
        nodes[src].distance = 0;//src노드의 distance값을 0으로 초기화

        //노드들을 minHeap에 담는다.
        PriorityQueue<Node> minHeap = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.distance, o2.distance));
        for (int i = 1; i <= numberOfNodes; i++) {
            minHeap.add(nodes[i]);
        }

        dijkstra(numberOfNodes, src, dst, nodes, new HashSet<>(), minHeap);
    }

    //그냥 다익스트라 알고리즘이다.
    private static void dijkstra(int n, int src, int dst, Node[] nodes, HashSet<Integer> setS, PriorityQueue<Node> minHeap) {
        while (true) {
            Node nodeU = minHeap.poll();
            setS.add(nodeU.label);

            if (nodeU.label == dst) {//minHeap에서 빼낸 노드의 label이 입력받은 dst값과 같으면 결과를 출력하고 끝낸다.
                printResult(nodeU);
                return;
            }

            for (int[] edge : nodeU.edges) {//nodeU의 인접노드들을 검사
                Node nodeV = nodes[edge[0]];
                int Wuv = edge[1];

                if (setS.contains(nodeV.label)) {//V-S에 속해있지 않은 것들은 패스
                    continue;
                }

                if (nodeV.distance > nodeU.distance + Wuv) {//if(Dv > Du + Wuv)
                    nodeV.distance = nodeU.distance + Wuv;//Relaxation

                    nodeV.cityCount = nodeU.cityCount + 1;

                    nodeV.visitedCities.clear();
                    nodeV.visitedCities.addAll(nodeU.visitedCities);
                    nodeV.visitedCities.add(nodeV.label);

                    minHeap.remove(nodeV);//distance값을 임의로 변경해서 힙이 깨졌기 때문에 뺐다가 다시 집어넣는다.
                    minHeap.add(nodeV);
                }
            }
        }
    }

    //결과 출력 함수
    private static void printResult(Node dst) {
        System.out.println(dst.distance);
        System.out.println(dst.cityCount);

        StringBuilder sb = new StringBuilder();
        for (Integer visitedCity : dst.visitedCities) {
            sb.append(visitedCity);
            sb.append(" ");
        }
        System.out.println(sb.toString());
    }

    private static class Node implements Comparable<Node>{
        int label;
        int distance = Integer.MAX_VALUE; //distance 값은 기본적으로 무한대로 설정
        int cityCount = 1; //경유한 도시 수는 출발 도시와 도착 도시도 포함해야 하므로 기본적으로 1로 설정한다.
        LinkedList<Integer> visitedCities = new LinkedList<Integer>();//visitedCitiec는 출발 도시와 도착도시까지 포함해야한다.
        ArrayList<int[]> edges = new ArrayList<int[]>();

        public Node(int label) {
            this.label = label;
            visitedCities.add(label);
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(distance, o.distance);
        }
    }
}

