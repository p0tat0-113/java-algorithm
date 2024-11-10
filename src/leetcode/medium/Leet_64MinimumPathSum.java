package leetcode.medium;

/*
m*n크기의 matrix에서 좌상단에서 우하단까지 도달해야 한다. 이때 오른쪽과 아래쪽으로만 움직일 수 있으며, 지나가는 점수의 합을 최소화해야 한다.
그냥 뭐... 별거 없다. 쉽게 배우는 알고리즘에서 나온 행렬 길찾기 문제의 반대 버전이라고 보면된다. 쉬운 DP문제임.

m == grid.length
n == grid[i].length
1 <= m, n <= 200
0 <= grid[i][j] <= 200
*/
public class Leet_64MinimumPathSum {
    public static void main(String[] args) {
        Leet_64MinimumPathSum leet = new Leet_64MinimumPathSum();

        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(leet.minPathSum(grid));
    }

    public int minPathSum(int[][] grid) {
        for (int i = 1; i < grid[0].length; i++) {
            grid[0][i] += grid[0][i-1];
        }
        for (int i = 1; i < grid.length; i++) {
            grid[i][0] += grid[i-1][0];
        }

        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                grid[i][j] += Math.min(grid[i-1][j], grid[i][j-1]);
            }
        }

        return grid[grid.length-1][grid[0].length-1];
    }
}
