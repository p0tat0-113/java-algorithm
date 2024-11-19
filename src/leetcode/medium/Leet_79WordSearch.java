package leetcode.medium;

public class Leet_79WordSearch {
    public static void main(String[] args) {
        char[][] board1 = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        char[][] board2 = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'E', 'S'}, {'A', 'D', 'E', 'E'}};

        Leet_79WordSearch leet = new Leet_79WordSearch();
        System.out.println(leet.exist(board1, "ABCCED"));
        System.out.println(leet.exist(board2, "ABCESEEEFS"));

        /*
        A B C E
        S F E S
        A D E E
        */
    }

    public boolean exist(char[][] board, String word) {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[0].length; column++) {
                if (dfs(row, column,0, word, board, new boolean[board.length][board[0].length])) {
                    return true;
                }
            }
        }
        return false;
    }

    //이론상 문제 없어 보였는데 한가지 간과한게 있었음. 이미 한 번 방문한 곳을 또 방문해버릴 수도 있다.
    private boolean dfs(int row, int column, int depth, String word, char[][] board, boolean[][] visited){
        if (depth == word.length()) {
            return true;
        }

        if ((row < 0 || row >= board.length)
                || (column < 0 || column >= board[0].length)
                || visited[row][column]) {
            return false;
        }

        visited[row][column] = true;

        if (board[row][column] != word.charAt(depth)) {
            return false;
        }

        boolean goUP = dfs(row + 1, column, depth + 1, word, board, visited);
        boolean goDOWN = dfs(row - 1, column, depth + 1, word, board, visited);
        boolean goLEFT = dfs(row, column + 1, depth + 1, word, board, visited);
        boolean goRIGHT = dfs(row, column - 1, depth + 1, word, board, visited);

        return (goUP || goDOWN || goLEFT || goRIGHT);
    }
}
