package leetcode.medium;

import leetcode.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
이 문제도 102번 BinaryTreeLevelOrderTraversal 문제의 변형이다.
이번에는 밑에서부터 위로, 각 층마다는 똑같이 왼쪽에서 오른쪽으로 훑으면 되는 문제인데,
그냥 102번 문제 코드를 그대로 가져다 쓰고, 마지막에 resultList를 뒤집어서 리턴하기만 하면 해결되는 문제였다.
*/

public class Leet_107BinaryTreeLevelOrderTraversal2 {
    public static void main(String[] args) {

    }

    private List<List<Integer>> resultList = new ArrayList<List<Integer>>();
    private Queue<TreeNode> queue = new LinkedList<>(); // 보통 무슨 구현체를 썼더라....?

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
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

        return resultList.reversed();
    }
}
