package leetcode.medium;

import leetcode.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
102번 BinaryTreeLevelOrderTraversal 문제의 변형 버전이다.
102번 문제는 위에서 아래 층으로, 각 층마다 왼쪽에서 오른쪽으로 순회하면 되는 문제였는데,
이 문제는 짝수층에서는 오른쪽에서 왼쪽, 홀수 층에서는 왼쪽에서 오른쪽으로 순회하라고 한다.
기존 코드를 약간만 손봐주면 쉽게 풀릴 것으로 보임.
*/

public class Leet_103BinaryTreeZigZagLevelOrderTraversal {
    public static void main(String[] args) {

    }

    private List<List<Integer>> resultList = new ArrayList<List<Integer>>();
    private LinkedList<TreeNode> queue = new LinkedList<>(); // 보통 무슨 구현체를 썼더라....?

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return resultList;
        }

        int currentLevel = 1;
        queue.offer(root);

        while (!queue.isEmpty()) {
            int queueSize = queue.size(); // 현재 큐에 들어가 있는 노드의 수를 센다.

            ArrayList<Integer> newLevel = new ArrayList<>();
            resultList.add(newLevel);

            for (int i = 0; i < queueSize; i++) { // 원래 큐에 들어가 있던, 그러니까 현재 층의 노드의 수 만큼만 뺀다.
                TreeNode polled = null;

                if (currentLevel % 2 == 0) { // 현재 층이 짝수인 경우 뒤에서 빼고
                    polled = queue.pollLast();

                    if (polled.right != null) { // 자식들은 오른쪽, 왼쪽 자식 순서대로 맨 앞에 집어넣음.
                        queue.offerFirst(polled.right);
                    }
                    if (polled.left != null) {
                        queue.offerFirst(polled.left);
                    }
                } else { // 현재 층이 홀수인 경우에는 앞에서 빼고
                    polled = queue.pollFirst();

                    if (polled.left != null) { // 자식들은 왼쪽, 오른쪽 자식 순서대로 맨 뒤에 집어넣음.
                        queue.offerLast(polled.left);
                    }
                    if (polled.right != null) {
                        queue.offerLast(polled.right);
                    }
                }

                newLevel.add(polled.val);
            }

            currentLevel++; // 층 수 올림.
        }

        return resultList;
    }
}
