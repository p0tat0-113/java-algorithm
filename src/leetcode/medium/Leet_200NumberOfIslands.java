package leetcode.medium;

public class Leet_200NumberOfIslands {
    public static void main(String[] args) {

    }

    public int numIslands(char[][] grid) {
        //섬의 수
        int numberOfIslands = 0;

        //우선 grid와 똑같은 boolean 행렬을 만들어준다. bfs, dfs어떤 방식으로든 뻗어나가면서 방문한 곳을 표시한다.
        //boolean[][] visited = new boolean[grid.length][grid[0].length]; 생각해보니까 별도의 visited행렬을 만들 필요가 없다. 방문한 곳은 grid에 직접 2로 바꿔버리자.

        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[0].length; column++) {
                if (grid[row][column] == 2 || grid[row][column] == 0) {
                    continue;
                }

                dfs(row, column, grid);
                numberOfIslands++;
            }
        }

        printMatrix(grid);
        return numberOfIslands;
    }

    private void dfs(int row, int column, char[][] grid) {

        //row, column값이 유효하지 않거나, grid[row][column]이 이미 방문한 곳인 경우, 혹은 바다인 경우
        if ((row<0 || row >= grid.length) || (column<0 || column >= grid[0].length)
                || grid[row][column] == 2
                || grid[row][column] == 0) {
            return;
        }

        grid[row][column] = 2;//방문한 곳에 2 표시

        //4방향으로 뻗어나감.
        dfs(row-1, column, grid);
        dfs(row+1, column, grid);
        dfs(row, column-1, grid);
        dfs(row, column+1, grid);
    }

    private void printMatrix(char[][] grid) {
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[0].length; column++) {
                int num = grid[row][column];
                System.out.printf("%-2d ", num);
            }
            System.out.println();
        }
    }
}
