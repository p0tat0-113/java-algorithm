package leetcode.medium;

import java.util.ArrayList;

public class Leet_207CourseSchedule {
    public static void main(String[] args) {
        Leet_207CourseSchedule leet = new Leet_207CourseSchedule();
        System.out.println(leet.canFinish(2, new int[][]{{1, 0}}));
        System.out.println(leet.canFinish(2, new int[][]{{1, 0}, {0, 1}}));
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //lists[i]는 노드i에서 출발하는 모든 노드들의 번호를 저장한다.
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            lists.add(new ArrayList<>());
        }

        for (int[] prerequisite : prerequisites) {//prerequisite[0]: 가길 원하는 노드,  prerequisite[1]: prerequisite[0]으로 가기 위해 거쳐가야 하는 노드
            int startingPoint = prerequisite[1];
            int destination = prerequisite[0];
            lists.get(startingPoint).add(destination);
        }

        boolean[] visited = new boolean[numCourses];//false는 방문하지 않은 것.

        for (int i = 0; i < numCourses; i++) {
            if(!dfs(lists, visited, i)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(ArrayList<ArrayList<Integer>> lists, boolean[] visited, int startPoint) {
        if (visited[startPoint]) {//이미 방문한 곳을 또 방문, 사이클이 존재한다는 의미이다. 바로 false반환
            return false;
        }
        if (lists.get(startPoint).isEmpty()) {
            return true;
        }

        visited[startPoint] = true;

        for (Integer integer : lists.get(startPoint)) {
            if (!dfs(lists, visited, integer)) {
                return false;
            }
        }

        visited[startPoint] = false;

        return true;
    }
}
