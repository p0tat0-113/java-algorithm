package easyAlgorithm.backTracking;

import java.util.ArrayList;
import java.util.List;

//백트래킹, 깊이 우선 탐색으로 미로에서 길을 찾아본다.
//가능한 모든 경로는 시도하면서, 조건에 맞지 않으면 즉시 그 경로를 포기하고 다른 경로를 탐색한다.
public class MazeSolver {
    private int[][] maze;//미로
    private int row;//미로의 행
    private int column;//미로의 열
    private List<List<Integer>> path = new ArrayList<>();//미로를 빠져나가는 경로 기록
    private boolean[][] visited;//해당 좌표 방문 기록, maze와 똑같은 크기로 만든다. 모두 기본적으로 false로 초기화 됨. 이미 방문한 곳을 다시 방문하는 것을 막는다.

    public MazeSolver(int[][] maze) {
        this.maze = maze;
        this.row = maze.length;
        this.column = maze[0].length;
        visited = new boolean[row][column];
    }

    private boolean solveMaze(int x, int y){
        //목적지에 도착함.
        if (x == row-1 && y == column-1) {
            path.add(List.of(x,y));//미로를 빠져나가는 경로에 현재 좌표를 기록하고
            visited[x][y] = true;//현재 좌표를 방문한 것을 기록한다.

            return true;
        }

        if (isValid(x, y)) {//먼저 입력받은 좌표의 유효성부터 검사한다. 현재좌표가 유효하다면
            path.add(List.of(x,y));//미로를 빠져나가는 경로에 현재 좌표를 기록하고
            visited[x][y] = true;//현재 좌표를 방문한 것을 기록한다.
            //만약 이후 상하좌우 그 어느 것으로도 갈 수 있는 방법이 없다면 위의 기록한 것들을 다시 되돌리고 false를 반환한다.

            if (solveMaze(x+1, y)) {
                return true;
            }
            if (solveMaze(x-1, y)) {
                return true;
            }
            if (solveMaze(x, y+1)) {
                return true;
            }
            if (solveMaze(x, y-1)) {
                return true;
            }

            //만약 위 네개의 if문중 어느 하나도 return true되지 못했다면
            //막다른 길에 진입한 것이다. 뒤로 역돌격 해야함. 앞에서 기록했던 것을 다 지우고 호출한 쪽으로 false를 반환한다.
            path.removeLast();
            visited[x][y] = false;
        }
        return false;
    }

    //이동한 좌표의 유효성을 검사한다.
    private boolean isValid(int x, int y){
        //x,y값이 유효하고, 미로상에서 벽이 아니어야 하며, 아직 방문하지 않은 곳이어야 한다.
        if ((x >= 0 && x < row) && (y >= 0 && y < column) && maze[x][y] == 0 && !visited[x][y]) {
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

        if (mazeSolver.solveMaze(0,0)) {
            System.out.println(mazeSolver.path);
        } else {
            System.out.println("경로 없음.");
        }
    }
}
