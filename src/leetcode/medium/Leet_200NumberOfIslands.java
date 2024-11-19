package leetcode.medium;

import java.util.ArrayDeque;

public class Leet_200NumberOfIslands {
    public static void main(String[] args) {
        Leet_200NumberOfIslands leet = new Leet_200NumberOfIslands();

        char[][] grid1 = new char[][] {
                {'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}
        };
        System.out.println(leet.numIslands(grid1));

        char[][] grid2 = new char[][] {
                {'1','1','0','0','0'},{'1','1','0','0','0'},{'0','0','1','0','0'},{'0','0','0','1','1'}
        };
        System.out.println(leet.numIslands(grid2));
    }

    //근데 생각해보니까 방문한 곳도 그냥 '0'으로 바꿔버리면 되네. 굳이 방문한 곳과 바다를 구분할 필요가 없음.
    //계속 방문하면 안될 곳까지 방문하는 문제가 있었는데 입력으로 '1' 이렇게 문자라 들어오는데 나는 이걸 계속 단순 숫자라고 생각하고 있어서 조건문에서 처리가 안되던 거였음.
    public int numIslands(char[][] grid) {
        //섬의 수
        int numberOfIslands = 0;

        //우선 grid와 똑같은 boolean 행렬을 만들어준다. bfs, dfs어떤 방식으로든 뻗어나가면서 방문한 곳을 표시한다.
        //boolean[][] visited = new boolean[grid.length][grid[0].length]; 생각해보니까 별도의 visited행렬을 만들 필요가 없다. 방문한 곳은 grid에 직접 2로 바꿔버리자.

        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[0].length; column++) {
                if (grid[row][column] == '0') {
                    continue;
                }
                bfs(row, column, grid);
                numberOfIslands++;
                //printMatrix(grid);
            }
        }

        return numberOfIslands;
    }

    //이번에는 한 섬을 모두 0으로 바꾸어 가는 과정을 bfs로 구현해보자.
    private void bfs(int row, int column, char[][] grid) {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {row, column});

        while(!queue.isEmpty()) {
            int[] polled = queue.pollFirst();

            if ((polled[0] < 0 || polled[0] >= grid.length)
                    || (polled[1] < 0 || polled[1] >= grid[0].length)
                    || grid[polled[0]][polled[1]] == '0') {
                continue;
            }
            grid[polled[0]][polled[1]] = '0';

            queue.add(new int[] {polled[0]+1, polled[1]});
            queue.add(new int[] {polled[0]-1, polled[1]});
            queue.add(new int[] {polled[0], polled[1]+1});
            queue.add(new int[] {polled[0], polled[1]-1});
        }
    }

    private void printMatrix(char[][] grid) {
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[0].length; column++) {
                int num = grid[row][column];
                System.out.printf("%-2c ", num);
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }
}
