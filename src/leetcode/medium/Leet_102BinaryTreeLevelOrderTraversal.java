package leetcode.medium;

import leetcode.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
너비 우선 탐색 문제인데 간단하다.
각 층의 노드들의 val을 리스트에 담아서 반환하기만 하면 된다.

Input: root = [3,9,20,null,null,15,7]
Output: [[3],[9,20],[15,7]]
*/

public class Leet_102BinaryTreeLevelOrderTraversal {
    public static void main(String[] args) {

    }

    private List<List<Integer>> resultList = new ArrayList<List<Integer>>();
    private Queue<TreeNode> queue = new LinkedList<>(); // 보통 무슨 구현체를 썼더라....?

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return resultList;
        }

        queue.offer(root);

        while (!queue.isEmpty()) {
            int queueSize = queue.size(); // 현재 큐에 들어가 있는 노드의 수를 센다.

            ArrayList<Integer> newLevel = new ArrayList<>();
            resultList.add(newLevel);

            for (int i = 0; i < queueSize; i++) { // 원래 큐에 들어가 있던, 그러니까 현재 층의 노드의 수 만큼만 뺀다.
                TreeNode polled = queue.poll();
                newLevel.add(polled.val);

                if (polled.left != null) {
                    queue.offer(polled.left);
                }
                if (polled.right != null) {
                    queue.offer(polled.right);
                }
            }
        }

        return resultList;
    }
}
