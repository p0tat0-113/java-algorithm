package leetcode.medium;

import java.util.*;

/*
최단거리 문제인데 다른 문제들과는 살짝 결이 다르다.
가중치가 주어지는게 좀 특이한데 어떤 간선을 통해 한 정점에서 다른 정점으로 갈 확률을 가중치로 준다.
그래서 start_node에서 end_node로 가는 가장 높은 확률의 경로를 찾는 문제다.
나는 일단 원래 내가 알던 다익스트라 알고리즘으로 해결했는데, 솔루션을 보니까 다른 더 간단하고 빠른 코드가 있는 것 같다. 나중에 다시 봐야할 듯.
*/
public class Leet_1514PathWithMaximumProbability {
    public static void main(String[] args) {
        Leet_1514PathWithMaximumProbability leet = new Leet_1514PathWithMaximumProbability();
        System.out.println(leet.maxProbability(3, new int[][]{{0, 1}, {1, 2}, {0, 2}}, new double[]{0.5, 0.5, 0.2}, 0, 2));
        System.out.println(leet.maxProbability(3, new int[][]{{0, 1}, {1, 2}, {0, 2}}, new double[]{0.5, 0.5, 0.3}, 0, 2));
        System.out.println(leet.maxProbability(3, new int[][]{{0, 1}}, new double[]{0.5}, 0, 2));
        System.out.println(leet.maxProbability(5, new int[][]{
                {1, 4},
                {2, 4},
                {0, 4},
                {0, 3},
                {0, 2},
                {2, 3}
        }, new double[]{0.37, 0.17, 0.93, 0.23, 0.39, 0.04}, 3, 4));//이 케이스에서 틀리고 있었는데, 이 문제에 주어진 그래프가 무향 그래프 라는 것을 까먹고 있었다.
        //무향 그래프는 양방향 유향 그래프로 치환할 수 있음.
    }

    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        double[] probabilities = new double[n];//확률을 저장해 놓은 배열. start_node는 1로 설정했다.
        Arrays.setAll(probabilities, e -> 0.0);
        probabilities[start_node] = 1.0;

        List<double[]>[] edgeList = new List[n];//간선 정보를 저장하는 배열들을 담는 리스트다. edges, succProb으로 쪼개져 있는 것을 뭉친다.
        for (int i = 0; i < n; i++) {
            edgeList[i] = new ArrayList<>();
        }
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            int src = edge[0];
            int dst = edge[1];
            double probability = succProb[i];
            edgeList[src].add(new double[] {src, dst, probability});
            edgeList[dst].add(new double[] {dst, src, probability});
        }

        //최소힙. 확률이 가장 높은 것을 빼줘야해서 compare()뒤에 -1을 곱해줬다.
        PriorityQueue<double[]> minHeap = new PriorityQueue<>((o1, o2) -> Double.compare(o1[1], o2[1]) * -1);
        minHeap.add(new double[] {start_node, 1.0});

        HashSet<Double> setS = new HashSet<>();

        while (!minHeap.isEmpty()) {
            double nodeU = minHeap.poll()[0];
            double probabilityU = probabilities[(int)nodeU];

            if (nodeU == end_node) {
                break;
            }

            if (setS.contains(nodeU)) {
                continue;
            }

            setS.add(nodeU);

            for (double[] edge : edgeList[(int)nodeU]) {
                double nodeV = edge[1];
                double probabilityV = probabilities[(int)nodeV];
                double edgeProbability = edge[2];

                if (setS.contains(nodeV)) {
                    continue;
                }

                if (probabilityV < probabilityU * edgeProbability) {//이완
                    probabilityV = probabilityU * edgeProbability;
                    probabilities[(int)nodeV] = probabilityV;
                    minHeap.add(new double[] {nodeV, probabilityV});
                }
            }
        }

        System.out.println(Arrays.toString(probabilities));
        return probabilities[end_node];
    }
}
