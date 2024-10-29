package easyAlgorithm.backTracking;

import java.util.ArrayList;
import java.util.List;

//백트래킹 깊이 우선 탐색으로 미로에서 길을 찾아본다.
public class MazeSolver {
    private int[][] maze;//미로
    private int row = maze.length;//미로의 행
    private int column = maze[0].length;//미로의 열
    private List<int[]> path = new ArrayList<>();//미로를 빠져나가는 경로 기록
    private boolean[][] visited = new boolean[row][column];//해당 좌표 방문 기록, maze와 똑같은 크기로 만든다. 모두 기본적으로 false로 초기화 됨.

    public MazeSolver(int[][] maze) {
        this.maze = maze;
    }

    private boolean solveMaze(int x, int y){
        if (isValid(x, y)) {//현재좌표가 유효하다면
            path.add(new int[] {x, y});//미로를 빠져나가는 경로에 현재 좌표를 기록하고
            visited[x][y] = true;//현재 좌표를 방문한 것을 기록한다.
            //만약 이후 상하좌우 그 어느 것으로도 갈 수 있는 방법이 없다면 위의 기록한 것들을 다시 되돌리고 false를 반환한다.

            if ()
        }
        return false;
    }

    //이동한 좌표의 유효성을 검사한다.
    private boolean isValid(int x, int y){
        //x,y값이 유효하고, 미로상에서 벽이 아니어야 하며, 아직 방문하지 않은 곳이어야 한다.
        if ((x >= 0 && x < row) && (y >= 0 && y < column) && maze[x][y] == 0 && !visited[row][column]) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        MazeSolver mazeSolver = new MazeSolver(new int[][] {
                {0, 0, 1, 1},
                {1, 0, 1, 1},
                {1, 0, 0, 0},
                {1, 1, 1, 0}
        });


    }
}
