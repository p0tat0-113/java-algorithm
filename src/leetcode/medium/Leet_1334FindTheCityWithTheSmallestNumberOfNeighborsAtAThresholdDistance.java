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

    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] matrix = new int[n][n];

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
                    matrix[i][j] = newDistance;
                }
            }
        }

        //printMatrix(matrix);

        int maxLabel = Integer.MIN_VALUE;
        int minScore = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int tempLabel = i;
            int tempScore = 0;
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] != 0 && matrix[i][j] != INF) {
                    tempScore++;
                }
            }

            if (minScore > tempScore) {
                minScore = tempScore;
                maxLabel = tempLabel;
            } else if (minScore == tempScore) {
                if (maxLabel < tempLabel) {
                    maxLabel = tempLabel;
                }
            }
        }

        return maxLabel;
    }

    private void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
        System.out.println("--------------");
    }
}
