package leetcode.review.finalExam;

public class Leet_79WordSearch {
    public static void main(String[] args) {
        Leet_79WordSearch leet = new Leet_79WordSearch();
        char[][] board1 = {{'A','B','C','E'}, {'S','F','C','S'}, {'A','D','E','E'}};
        String word1 = "ABCCED";
        System.out.println(leet.exist(board1, word1));

        String word2 = "SEE";
        System.out.println(leet.exist(board1, word2));

        String word3 = "ABCB";
        System.out.println(leet.exist(board1, word3));
    }

    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, word, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int depth, int row, int column){
        if (depth == word.length()) {
            return true;
        }

        //row, column값이 이상하거나, 이미 방문한 곳이거나, 문자가 일치하지 않으면 return false
        if ((row < 0 || row >= board.length) || (column < 0 || column >= board[0].length)
                || board[row][column] == '0' || board[row][column] != word.charAt(depth)) {
            return false;
        }

        char character = board[row][column];
        board[row][column] = '0';//방문한 곳은 문자 '0'으로 대체, 별도의 visited매트릭스를 운용하지 않음.

        boolean goUP = dfs(board, word, depth+1, row-1, column);
        boolean goDOWN = dfs(board, word, depth+1, row+1, column);
        boolean goLEFT = dfs(board, word, depth+1, row, column-1);
        boolean goRIGHT = dfs(board, word, depth+1, row, column+1);

        board[row][column] = character;

        return goUP || goDOWN || goLEFT || goRIGHT;
    }
}
