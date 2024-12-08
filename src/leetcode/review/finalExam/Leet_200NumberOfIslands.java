package leetcode.review.finalExam;

import java.util.ArrayDeque;

public class Leet_200NumberOfIslands {
    public static void main(String[] args) {
        Leet_200NumberOfIslands leet = new Leet_200NumberOfIslands();
        char[][] grid1 = {{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}};
        char[][] grid2 = {{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}};
        System.out.println(leet.numIslands(grid1));
        System.out.println(leet.numIslands(grid2));
    }

    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != '0') {
                    check(grid, i, j);
                    count ++;
                }
            }
        }

        return count;
    }

    //시간초과가 발생하는 문제를 해결하다 보니까 신기한 코드가 나왔네ㅋㅋ
    private void check(char[][] grid, int startRow, int startColumn) {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {startRow, startColumn});
        grid[startRow][startColumn] = '0';

        while (!queue.isEmpty()) {
            int[] polled = queue.pollFirst();//처음에 getFirst()를 썼더니 코드 실행이 제대로 안됐음.

            addToQueue(queue, grid, polled[0]+1, polled[1]);
            addToQueue(queue, grid, polled[0]-1, polled[1]);
            addToQueue(queue, grid, polled[0], polled[1]+1);
            addToQueue(queue, grid, polled[0], polled[1]-1);
        }
    }

    //bfs방식으로 구현할 때 어떤식으로 하던 중요한 것은 이미 큐에 들어가 있는 좌표가 다시 다른 경로를 통해 큐에 중복으로 추가되는 것을 막아야 한다는 것이다.
    private void addToQueue(ArrayDeque<int[]> queue, char[][] grid, int row, int column){
        if ((row < 0 || row >= grid.length) || (column < 0 || column >= grid[0].length)) {
            return;
        }
        if (grid[row][column] == '0') {
            return;
        }

        queue.add(new int[] {row, column});//그리고 이쪽에서 바로 큐에 넣어줌.
        grid[row][column] = '0';//이쪽에서 바로 0표시를 해준다. 이미 큐에 들어간 위치의 좌표는 중복되게 큐에 추가되는 것을 막을 수 있음.
    }
}
