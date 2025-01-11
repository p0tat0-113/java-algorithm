package baekjoon.graphTraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
미로에서 1은 이동할 수 있는 칸을 나타내고, 0은 이동할 수 없는 칸을 나타낸다. 이러한 미로가 주어졌을 때, (1, 1)에서 출발하여 (N, M)의 위치로 이동할 때 지나야 하는 최소의 칸 수를 구하는 프로그램을 작성하시오.
한 칸에서 다른 칸으로 이동할 때, 서로 인접한 칸으로만 이동할 수 있다.
위의 예에서는 15칸을 지나야 (N, M)의 위치로 이동할 수 있다. 칸을 셀 때에는 시작 위치와 도착 위치도 포함한다.

입력
첫째 줄에 두 정수 N, M(2 ≤ N, M ≤ 100)이 주어진다. 다음 N개의 줄에는 M개의 정수로 미로가 주어진다. 각각의 수들은 붙어서 입력으로 주어진다.

출력
첫째 줄에 지나야 하는 최소의 칸 수를 출력한다. 항상 도착위치로 이동할 수 있는 경우만 입력으로 주어진다.

bfs를 사용해서 최단경로를 찾는 문제다. bfs의 특성을 사용하여서 해결해야 한다. Leet_787CheapestFlight 문제를 bfs로 풀던 것을 다시 보면 좋을 듯.
*/
public class Baek_2178미로탐색 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        String[][] maze = new String[n][m];
        for (int i = 0; i < n; i++) {
            maze[i] = br.readLine().split("");
        }

        int distanceCount = 0;
        LinkedList<int[]> queue = new LinkedList<>();
        queue.add(new int[] {0,0});

        while (!queue.isEmpty()) {
            distanceCount++;
            int currentQueueSize = queue.size();//이 부분이 매우 중요! BFS를 이용한 Leet_787CheapestFlight 문제 풀이를 하면서 경험을 얻었었다.

            for (int i = 0; i < currentQueueSize; i++) {
                int[] polled = queue.poll();
                maze[polled[0]][polled[1]] = "0";

                if (polled[0] == n-1 && polled[1] == m-1) {
                    System.out.println(distanceCount);
                    return;
                }

                addToQueue(maze, queue, polled[0]+1, polled[1]);
                addToQueue(maze, queue, polled[0]-1, polled[1]);
                addToQueue(maze, queue, polled[0], polled[1]+1);
                addToQueue(maze, queue, polled[0], polled[1]-1);
            }
        }

        System.out.println(distanceCount);
    }

    //여기에서 규칙위반 여부를 조사해서 큐에 추가한다.
    private static void addToQueue(String[][] maze, LinkedList<int[]> queue, int row, int column) {
        if (row < 0 || row >= maze.length || column < 0 || column >= maze[0].length || maze[row][column].equals("0")) {
            return;
        }

        queue.add(new int[] {row, column});
    }
}
