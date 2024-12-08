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


    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> result = new ArrayList<>();
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.add(0);

        dfs(graph, stack, result, 0);

        return result;
    }

    private void dfs(int[][] graph, ArrayDeque<Integer> stack, List<List<Integer>> result, int currentNode){
        if (stack.peekLast() == graph.length-1) {
            result.add(new ArrayList<>(stack));
            return;
        }

        for (int node : graph[currentNode]) {
            stack.add(node);
            dfs(graph, stack, result, node);
            stack.pollLast();
        }
    }
}
