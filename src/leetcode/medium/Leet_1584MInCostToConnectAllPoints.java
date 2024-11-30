package leetcode.medium;

import java.util.*;

public class Leet_1584MInCostToConnectAllPoints {
    public static void main(String[] args) {

    }

    //기존 코드가 좀 별로인 것 같아서 solutions를 보고 다시 작성함.
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;//노드의 수
        boolean[] visited = new boolean[n];//이미 힙에서 꺼낸 노드를 표시. 집합 S의 역할을 한다.

        //각 노드의 가중치를 저장한다. 어떤 노드가 key로 등록되어 있지 않은 경우에는 가중치를 Integer.MAX_VALUE로 인식. 가중치:노드번호 쌍으로 구성
        HashMap<Integer, Integer> heap_dict = new HashMap<>();
        heap_dict.put(0, 0);

        //최소 힙. 배열은 [가중치, 노드번호]로 구성
        PriorityQueue<int[]> min_heap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));//Comparator를 재정의
        min_heap.add(new int[]{0, 0});

        int mst_weight = 0;//가중치의 합

        //min_heap이 다 빌 때까지 반복
        while (!min_heap.isEmpty()) {
            int[] polledNode = min_heap.poll();

            //weight: 가중치, nodeNum:노드의 번호
            int weight = polledNode[0], nodeNum = polledNode[1];

            if (visited[nodeNum]) {//u에 이미 방문했거나, 힙에 있는게 더 작은 가중치로 이완되기 전의 legacy인 경우 continue
                continue;
            }

            visited[nodeNum] = true;//해당 노드를 S에 집어넣었음을 표시
            mst_weight += weight;//가중치를 더함.

            for (int v = 0; v < n; ++v) {//n은 points.length. 이 반복문은 모든 노드를 한 번씩 검사한다.
                if (!visited[v]) {//이미 S에 들어간 노드는 패스
                    int new_distance = manhattan_distance(points[nodeNum], points[v]);//S에 새로 추가된 노드 u를 통해 MST에 연결되는 경우의 가중치를 계산
                    if (new_distance < heap_dict.getOrDefault(v, Integer.MAX_VALUE)) {//새로 계산된 가중치가 더 작은 경우 이완시킨다.
                        heap_dict.put(v, new_distance);
                        min_heap.add(new int[]{new_distance, v});
                    }
                }
            }
        }

        return mst_weight;
    }

    public int manhattan_distance(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }
}
