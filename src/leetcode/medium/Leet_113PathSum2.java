package leetcode.medium;

import leetcode.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where the sum of the node values in the path equals targetSum.
Each path should be returned as a list of the node values, not node references.

A root-to-leaf path is a path starting from the root and ending at any leaf node. A leaf is a node with no children.

간단한 dfs 문제였다. 129번 문제랑 비슷하네.
*/

public class Leet_113PathSum2 {
    public static void main(String[] args) {

    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        LinkedList<Integer> stack = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();

        dfs(result, stack, root, 0, targetSum);

        return result;
    }

    private void dfs(List<List<Integer>> result, LinkedList<Integer> stack, TreeNode root, int sum, int targetSum) {
        if (root == null) {
            return;
        }

        sum += root.val;
        stack.offer(root.val);

        if (root.left == null && root.right == null) {//좌우 자식이 모두 없는 리프노드이고, 합이 targetSum과 일치하면 result에 현재 경로를 추가한다.
            if (sum == targetSum) {
                result.add(new LinkedList<>(stack));
            }
            stack.pollLast();//답이 되게 이상하세 나와서 왜 그런가 했는데 pollLast()를 써야하는데 poll()을 쓰고 있어서 그랬음.
            return;
        }

        dfs(result, stack, root.left, sum, targetSum);
        dfs(result, stack, root.right, sum, targetSum);

        stack.pollLast();
    }
}
