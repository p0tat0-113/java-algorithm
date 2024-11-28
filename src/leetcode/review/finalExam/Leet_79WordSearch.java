package leetcode.review.finalExam;

public class Leet_79WordSearch {
    public static void main(String[] args) {

    }

    public boolean exist(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];

        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[0].length; column++) {
                if (dfs(row, column, board, visited, word, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean dfs(int row, int column, char[][] board, boolean[][] visited, String word, int length){
        if (length == word.length()) {
            return true;
        }

        if ((row < 0 || row >= board.length) || (column < 0 || column >= board[0].length)) {
            return false;
        }
        if (visited[row][column]) { //<- 이거까지 빼먹지 말자.
            return false;
        }
        if (board[row][column] != word.charAt(length)) {
            return false;
        }

        visited[row][column] = true;

        boolean goLeft = dfs(row, column-1, board,visited, word, length+1);
        boolean goRight = dfs(row, column+1, board,visited, word, length+1);
        boolean goUp = dfs(row-1, column, board,visited, word, length+1);
        boolean goDown = dfs(row+1, column, board,visited, word, length+1);

        visited[row][column] = false;

        return goLeft || goRight || goUp || goDown;
    }
}
