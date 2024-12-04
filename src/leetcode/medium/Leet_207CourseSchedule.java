package leetcode.medium;

import java.util.ArrayList;

public class Leet_207CourseSchedule {
    public static void main(String[] args) {
        Leet_207CourseSchedule leet = new Leet_207CourseSchedule();
        System.out.println(leet.canFinish(2, new int[][]{{1, 0}}));
        System.out.println(leet.canFinish(2, new int[][]{{1, 0}, {0, 1}}));
    }

    //이 문제의 해결방법을 한마디로 정리하자면, dfs를 도는 중에 사이클이 발생하는지를 감지하는 것이다.
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

        int[] visited = new int[numCourses];//false는 방문하지 않은 것.

        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == 0) {
                if(!dfs(lists, visited, i)) {
                    return false;
                }
            }
        }
        return true;
    }

    //방문 상태를 단순히 true/false가 아니라, 0:미방문 1:방문중(현재 dfs가 돌아가는 중) 2:방문완료 이렇게 3가지 상태로 나누면 문제를 해결할 수 있다.
    //단순히 기존 코드에서 아래 visited[startPoint] = false를 제거한다고 해서 해결되지 않는다.
    private boolean dfs(ArrayList<ArrayList<Integer>> lists, int[] visited, int startPoint) {
        if (visited[startPoint] == 1) {//dfs 탐색 중에 이미 방문한 곳을 또 방문, 사이클이 존재한다는 의미이다. 바로 false반환
            return false;
        }
        if (visited[startPoint] == 2) {
            return true;
        }

        //더 이상 갈 곳이 없는 노드는 아래 반복문을 수행하지 않고 그대로 스무스하게 통과해서 2로 표시되고 true를 반환하며 끝낸다.
        //visited[startPoint] = true;
        visited[startPoint] = 1;

        for (Integer integer : lists.get(startPoint)) {
            if (!dfs(lists, visited, integer)) {
                return false;
            }
        }

        //visited[startPoint] = false; //이걸 제거한다고 문제가 해결되지 않음. 사이클이 아닌데도 사이클로 판단해버리는 찐빠가 발생한다.
        visited[startPoint] = 2;

        return true;
    }
}
