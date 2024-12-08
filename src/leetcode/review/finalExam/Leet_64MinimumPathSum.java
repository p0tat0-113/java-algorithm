package leetcode.review.finalExam;

public class Leet_64MinimumPathSum {
    public static void main(String[] args) {
        Leet_64MinimumPathSum leet = new Leet_64MinimumPathSum();
        System.out.println(leet.minPathSum(new int[][]{{1,3,1},{1,5,1},{4,2,1}}));
        System.out.println(leet.minPathSum(new int[][]{{1,2,3},{4,5,6}}));
    }

    public int minPathSum(int[][] grid) {
        for (int i = 1; i < grid[0].length; i++) {
            grid[0][i] = grid[0][i] + grid[0][i-1];
        }
        for (int i = 1; i < grid.length; i++) {
            grid[i][0] = grid[i][0] + grid[i-1][0];
        }

        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                grid[i][j] = Math.min(grid[i-1][j], grid[i][j-1]) + grid[i][j];
            }
        }

        return grid[grid.length-1][grid[0].length-1];
    }
}
