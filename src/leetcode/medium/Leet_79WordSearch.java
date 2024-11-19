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
        boolean[][] visited = new boolean[board.length][board[0].length];

        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[0].length; column++) {
                if (dfs(row, column,0, word, board, visited)) {
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

        //row, column값이 범위를 벗어나거나, 앞서 이미 방문한 곳이거나, 깊이와 문자가 일치하지 않는 경우 false를 반환한다.
        if ((row < 0 || row >= board.length)
                || (column < 0 || column >= board[0].length)
                || visited[row][column]
                || board[row][column] != word.charAt(depth)) {
            return false;
        }
        visited[row][column] = true;

        boolean goUP = dfs(row + 1, column, depth + 1, word, board, visited);
        boolean goDOWN = dfs(row - 1, column, depth + 1, word, board, visited);
        boolean goLEFT = dfs(row, column + 1, depth + 1, word, board, visited);
        boolean goRIGHT = dfs(row, column - 1, depth + 1, word, board, visited);

        visited[row][column] = false;//계속 테스트케이스가 하나씩 걸려서 뭐가 문제인가 했는데 이 부분이 없어서 그랬다. dfs면 재귀호출이 끝나고 돌와왔을 때 visited를 다시 해제해주는게 당연한건데 이걸 빼먹었네.

        return (goUP || goDOWN || goLEFT || goRIGHT);
    }
}
