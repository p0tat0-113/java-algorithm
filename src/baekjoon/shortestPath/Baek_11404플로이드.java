package baekjoon.shortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
플로이드 워셜 알고리즘 문제다. 이걸 직접 구현해보는건 처음이네.
일단 모든 쌍 최단거리를 나타내야 하는데 이거를 유향 가중치 그래프로 표현해야 할 것 같다. 인접 행렬로 나타내야 할 듯.
https://imgur.com/a/onVr6Sx
*/
public class Baek_11404플로이드 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[][] edges = new int[m][3];
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            edges[i] = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }

        floyd(n, m, edges);
    }

    private static int INF = 10_000_000;//계속 틀리고 있었는데 문제가 여기에 있었다. 내가 처음에는 INF값을 1_000_000으로 설정했었는데 문제 조건상 정점은 최대 100개이고, 가중치는 최대 100_000이다. 따라서 100 * 100_000인 10_000_000으로 설정해야했다.

    private static void floyd(int n, int m, int[][] edges) {
        int[][] matrix = new int[n + 1][n + 1];


        for (int i = 1; i <= n; i++) {
            Arrays.setAll(matrix[i], e -> INF);//처음에는 Integer.MAX_VALUE로 했었는데 오버플로우가 발생하면서 값이 이상하게 들어가는 문제가 발생했었다.
        }
        for (int i = 1; i <= n; i++) {
                matrix[i][i] = 0;//자기 자신에게 가는 비용을 0으로 초기화
        }

        //우선 경유하는 정점의 수가 0인 경우부터 초기화한다. 경계조건
        for (int[] edge : edges) {
            if (matrix[edge[0]][edge[1]] > edge[2]) {
                matrix[edge[0]][edge[1]] = edge[2];
            }
        }

        //경유하는 정점의 수가 1~n인 경우
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                }
            }
        }

        //printMatrix(matrix);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i][j] == INF) {
                    sb.append(0);
                } else  {
                    sb.append(matrix[i][j]);//i에서 j로 갈 수 없는 경우에는 그 자리에 0을 출력해야 한다.
                }
                sb.append(" ");
            }
            sb.append("\n");
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb.toString());
    }

    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }
}
