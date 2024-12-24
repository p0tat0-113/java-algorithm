package leetcode.medium;

import java.util.*;

/*
플로이드 워셜 알고리즘의 응용 문제다.
맞긴 맞았는데 시간이 좀 오래걸림. 내가 문제의 조건에 맞는 답을 찾는 과정에서 불필요하게 복잡한 솔루션을 사용한 것도 있는 듯...
*/
public class Leet_1334FindTheCityWithTheSmallestNumberOfNeighborsAtAThresholdDistance {
    public static void main(String[] args) {
        Leet_1334FindTheCityWithTheSmallestNumberOfNeighborsAtAThresholdDistance leet = new Leet_1334FindTheCityWithTheSmallestNumberOfNeighborsAtAThresholdDistance();
        System.out.println(leet.findTheCity(4, new int[][]{{0, 1, 3}, {1, 2, 1}, {1, 3, 4}, {2, 3, 1}}, 4));
        System.out.println(leet.findTheCity(5, new int[][]{{0, 1, 2}, {0, 4, 8}, {1, 2, 3}, {1, 4, 2}, {2, 3, 1}, {3, 4, 1}}, 2));
    }

    private static int INF = 10_000_000;

    private static class Score {
        public Score(int label, int score) {
            this.label = label;
            this.score = score;
        }

        int label;
        int score;

        @Override
        public String toString() {
            return "Score{" +
                    "label=" + label +
                    ", score=" + score +
                    '}';
        }
    }

    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] matrix = new int[n][n];

        //matrix의 각 행에서 어떤 열에서 업데이트가 일어났는지 기록하기 위한 HashSet타입의 배열
        Set<Integer>[] updated = new Set[n];//HashSet타입의 배열을 선언하려면 이렇게 하면 된다. new HashSet<Integer>[n] 이런식으로 직접적으로 표현하면 제네릭 타입의 배열 선언을 허용하지 않는다고 컴파일 에러 발생함.
        for (int i = 0; i < n; i++) {
            updated[i] = new HashSet<>();
        }

        //각 노드의 점수가 몇 점인지 기록하는 내부클래스 Score타입의 배열. 이후에 이걸 정렬해서 최종적인 답을 구할 것이다.
        Score[] scores = new Score[n];
        for (int i = 0; i < n; i++) {
            scores[i] = new Score(i,0);
        }

        //초기화 과정
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == i) {
                    matrix[i][j] = 0;//자기자신에게 가는 비용
                    continue;
                }
                matrix[i][j] = INF;
            }
        }

        //k=0인 경우 초기화
        for (int[] edge : edges) {
            int i = edge[0];
            int j = edge[1];
            int weight = edge[2];

            if (weight > distanceThreshold) {//가중치가 distanceThreshold를 넘으면 업데이트 하지 않음.
                continue;
            }

            if (matrix[i][j] > weight) {
                updated[i].add(j);//각 행의 어떤 열에서 업데이트가 일어났는지 기록
                updated[j].add(i);
                matrix[i][j] = weight;
                matrix[j][i] = weight;
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int newDistance = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                    if (newDistance > distanceThreshold) {//비용이 distanceThreshold를 넘으면 업데이트 하지 않음.
                        continue;
                    }
                    updated[i].add(j);
                    matrix[i][j] = newDistance;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            scores[i].score = updated[i].size();
        }

        //score를 기준으로 오름차순으로 정렬, score가 같은 경우 label을 기준으로 내림차순으로 정렬
        Arrays.sort(scores, (o1, o2) -> {
            if (Integer.compare(o1.score, o2.score) == 0) {
                return Integer.compare(o1.label, o2.label) * -1;
            }
            return Integer.compare(o1.score, o2.score);
        });

        //System.out.println(Arrays.toString(scores));

        return scores[0].label;
    }

    private void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
        System.out.println("--------------");
    }
}
