package leetcode.review.finalExam;

import java.util.ArrayList;
import java.util.List;

public class Leet_207CourseSchedule {
    public static void main(String[] args) {
        Leet_207CourseSchedule leet = new Leet_207CourseSchedule();
        System.out.println(leet.canFinish(2, new int[][]{{1, 0}}));
        System.out.println(leet.canFinish(2, new int[][]{{1, 0}, {0, 1}}));
        System.out.println(leet.canFinish(5, new int[][]{{1,4},{2,4},{3,1},{3,2}}));
    }

    //위상정렬 문제라고는 하지만 결국 dfs 탐색 중에 사이클을 찾으면 되는 문제다.
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<int[]>[] edges = new List[numCourses];
        for (int i = 0; i < edges.length; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int[] prerequisite : prerequisites) {
            edges[prerequisite[1]].add(prerequisite);
        }

        int[] visited = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (visited[i] != 2) {
                if(!dfs(i, edges, visited)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean dfs(int startPoint, List<int[]>[] edges, int[] visited){
        if (visited[startPoint] == 1) {
            return false;
        }
        if (visited[startPoint] == 2) {
            return true;
        }

        visited[startPoint] = 1;
        for (int[] edge : edges[startPoint]) {
            //처음에 return dfs(edge[0], edges, visited)로 해서 세번째 케이스에서 답이 틀리게 나왔었다.
            //true일 때도 바로 return을 해버리니까 아래 visited[startPoint] = 2가 작동되지 않은 것이 문제였다.
            if (!dfs(edge[0], edges, visited)) {
                return false;
            }
        }
        visited[startPoint] = 2;

        return true;
    }
}
