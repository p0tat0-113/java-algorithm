package leetcode.review.finalExam;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Leet_797AllPathsFromSourceToTarget {
    public static void main(String[] args) {
        Leet_797AllPathsFromSourceToTarget leet = new Leet_797AllPathsFromSourceToTarget();
        System.out.println(leet.allPathsSourceTarget(new int[][]{{1, 2}, {3}, {3}, {}}));
        System.out.println(leet.allPathsSourceTarget(new int[][]{{4,3,1}, {3,2,4}, {3}, {4}, {}}));
    }

    List<List<Integer>> result;
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        result = new ArrayList<>();
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.add(0);

        dfs(graph, stack);
        return result;
    }

    private void dfs(int[][] graph, ArrayDeque<Integer> stack){
        if (stack.peekLast() == graph.length-1) {//생각해보니까 이 문제는 depth의 관점으로 보면 안됨. 바로 0번노드에서 n-1번 노드로 직행을 할 수도 있는거다.
            result.add(new ArrayList<>(stack));
            return;
        }

        for (int node : graph[stack.peekLast()]) {
            stack.add(node);
            dfs(graph, stack);
            stack.pollLast();
        }
    }
}
