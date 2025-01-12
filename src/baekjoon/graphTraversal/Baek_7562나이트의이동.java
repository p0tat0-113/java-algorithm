package baekjoon.graphTraversal;

import java.util.HashSet;
import java.util.LinkedList;

public class Baek_7562나이트의이동 {
    public static void main(String[] args) {
        /*process(8, 0,0,7,0);
        process(8, 0,0,0,0);
        process(100, 0,0,30,50);
        process(100, 0,0,1,2);*/

        process(7, 4,4,0,0);
    }

    private static HashSet<Integer> visitedRow = new HashSet<>();
    private static HashSet<Integer> visitedColumn = new HashSet<>();
    private static LinkedList<int[]> queue = new LinkedList<>();

    private static void process(int size, int rowX, int colX, int rowY, int colY) {
        visitedRow.clear();
        visitedColumn.clear();
        queue.clear();

        int moveCount = -1;
        queue.add(new int[] {rowX, colX});
        visitedRow.add(rowX);
        visitedColumn.add(colX);

        while (!queue.isEmpty()) {
            moveCount++;
            int currentQueueSize = queue.size();

            for (int i = 0; i < currentQueueSize; i++) {
                int[] polled = queue.poll();

                if (polled[0] == rowY && polled[1] == colY) {//목적지에 도착
                    System.out.println(moveCount);
                    return;
                }

                addToQueue(size, polled[0]-2, polled[1]+1);
                addToQueue(size, polled[0]-1, polled[1]+2);
                addToQueue(size, polled[0]+2, polled[1]+1);
                addToQueue(size, polled[0]+1, polled[1]+2);

                addToQueue(size, polled[0]-2, polled[1]-1);
                addToQueue(size, polled[0]-1, polled[1]-2);
                addToQueue(size, polled[0]+2, polled[1]-1);
                addToQueue(size, polled[0]+1, polled[1]-2);
            }
        }

        System.out.println("맨밑");
        System.out.println(moveCount);
    }

    private static void addToQueue(int size, int row, int column) {
        if (row < 0 || row >= size || column < 0 || column >= size || isVisited(row, column)) {
            return;
        }

        visitedRow.add(row);
        visitedColumn.add(column);
        queue.add(new int[] {row, column});
    }

    //해당 좌표에 방문한 적이 있는지 검사하는 메서드
    private static boolean isVisited(int row, int column) {
        return visitedRow.contains(row) && visitedColumn.contains(column);
    }
}
