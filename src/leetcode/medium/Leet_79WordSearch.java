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

    //다른 사람들 코드보다 실행속도가 좀 느렸는데, 잘 생각해보니까 visited를 따로 운용할 필요가 없다!!! 200번 number of islands문제와 비슷함.
    public boolean exist(char[][] board, String word) {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[0].length; column++) {
                if (dfs(row, column,0, word, board)) {
                    return true;
                }
            }
        }
        return false;
    }

    //이론상 문제 없어 보였는데 한가지 간과한게 있었음. 이미 한 번 방문한 곳을 또 방문해버릴 수도 있다.
    private boolean dfs(int row, int column, int depth, String word, char[][] board){
        if (depth == word.length()) {
            return true;
        }

        //row, column값이 범위를 벗어나거나, 앞서 이미 방문한 곳이거나, 깊이와 문자가 일치하지 않는 경우 false를 반환한다.
        if ((row < 0 || row >= board.length)
                || (column < 0 || column >= board[0].length)
                || board[row][column] == '@'
                || board[row][column] != word.charAt(depth)) {
            return false;
        }
        char temp = board[row][column];
        board[row][column] = '@';//별도의 visited를 운용하지 않고, 방문한 곳은 board에다가 바로 @표시를 한다.

        boolean goUP = dfs(row + 1, column, depth + 1, word, board);
        boolean goDOWN = dfs(row - 1, column, depth + 1, word, board);
        boolean goLEFT = dfs(row, column + 1, depth + 1, word, board);
        boolean goRIGHT = dfs(row, column - 1, depth + 1, word, board);

        board[row][column] = temp;//다시 원래의 문자로 되돌림.

        return (goUP || goDOWN || goLEFT || goRIGHT);
    }
}
