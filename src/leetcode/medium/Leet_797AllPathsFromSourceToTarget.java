package leetcode.medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/*
사이클이 없는 유향그래프가 주어진다. n개의 노드는 각각 0 ~ n-1번으로 라벨링 되어있다.
0번 노드부터 n-1번 노드까지 이르는 모든 경로를 리턴하면 되는 문제다.

Input: graph = [[1,2],[3],[3],[]]
Output: [[0,1,3],[0,2,3]]
Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.

처음 봤을 때는 좀 어려워보였지만, 잘 생각해보면 그리 어려운 문제는 아니였다.
*/
public class Leet_797AllPathsFromSourceToTarget {
    public static void main(String[] args) {

    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        ArrayList<List<Integer>> result = new ArrayList<>();
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.add(0);

        dfs(result, graph, stack);
        return result;
    }

    private void dfs(List<List<Integer>> result, int[][] graph, ArrayDeque<Integer> stack) {
        int currentNode = stack.peekLast();//stack에 미리 0을 채워놓았기 때문에, 어떤 경우에도 NullPointerException은 발생하지 않는다. 아마도...?

        //현재 노드가 n-1인 경우 return. 이 문제에서 원하는 것이 0번 노드부터 n-1번 노드까지 가는 모든 경로를 구하는 것이다.
        //처음에는 graph[currentNode].length == 0 이렇게 조건을 걸어서 [[4,3,1],[3,2,4],[],[4],[]] 이 케이스에서 틀렸었음.
        if(currentNode == graph.length-1) {
            result.add(new ArrayList<>(stack));
            return;
        }

        for (int i = 0; i < graph[currentNode].length; i++) {
            stack.add(graph[currentNode][i]);
            dfs(result, graph, stack);
            stack.pollLast();
        }
    }
}
