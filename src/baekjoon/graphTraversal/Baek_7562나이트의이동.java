package baekjoon.graphTraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Baek_7562나이트의이동 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            int size = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());
            int rowX = Integer.parseInt(st.nextToken());
            int colX = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int rowY = Integer.parseInt(st.nextToken());
            int colY = Integer.parseInt(st.nextToken());

            process(size, rowX, colX, rowY, colY);
        }
    }

    private static LinkedList<int[]> queue = new LinkedList<>();

    private static void process(int size, int rowX, int colX, int rowY, int colY) {
        boolean[][] visited = new boolean[size][size];//결국 일반적인 방문 지점 표시 방식으로 돌아옴.
        queue.clear();

        int moveCount = -1;
        queue.add(new int[] {rowX, colX});
        visited[rowX][colX] = true;

        while (!queue.isEmpty()) {
            moveCount++;
            int currentQueueSize = queue.size();

            for (int i = 0; i < currentQueueSize; i++) {
                int[] polled = queue.poll();

                if (polled[0] == rowY && polled[1] == colY) {//목적지에 도착
                    System.out.println(moveCount);
                    return;
                }

                addToQueue(visited, size, polled[0]-2, polled[1]+1);
                addToQueue(visited, size, polled[0]-1, polled[1]+2);
                addToQueue(visited, size, polled[0]+2, polled[1]+1);
                addToQueue(visited, size, polled[0]+1, polled[1]+2);

                addToQueue(visited, size, polled[0]-2, polled[1]-1);
                addToQueue(visited, size, polled[0]-1, polled[1]-2);
                addToQueue(visited, size, polled[0]+2, polled[1]-1);
                addToQueue(visited, size, polled[0]+1, polled[1]-2);
            }
        }

        System.out.println(moveCount);
    }

    private static void addToQueue(boolean[][] visited, int size, int row, int column) {
        if (row < 0 || row >= size || column < 0 || column >= size || visited[row][column]) {
            return;
        }

        visited[row][column] = true;
        queue.add(new int[] {row, column});
    }
}
