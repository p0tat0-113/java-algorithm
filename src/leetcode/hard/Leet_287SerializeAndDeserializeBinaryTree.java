package leetcode.hard;

import com.sun.source.tree.Tree;
import leetcode.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
이진트리를 직렬화하고, 역직렬화하는 메서드를 구현해야 하는 문제다.
There is no restriction on how your serialization/deserialization algorithm should work.
구현하는데 특별한 제약은 없다고 한다. 창의성을 발휘해보라고 함. 하드 난이도라 그런가 뭔가 이제까지 풀어봤던 문제들과는 좀 다르네ㅋㅋ

https://support.leetcode.com/hc/en-us/articles/32442719377939-How-to-create-test-cases-on-LeetCode#h_01J5EGREAW3NAEJ14XC07GRW1A
리트코드에서는 위 링크로 들어가면 나오는 방식으로 이진트리를 직렬화함. 트리 레벨 별로 왼쪽에서 오른쪽으로 직렬화하는 방식이다.

직렬화하는 것 자체는 큰 문제가 아닌데, 역직렬화 하는 게 어려워 보이네.
일단 리트코드 방식으로 직렬화와 역직렬화를 구현해보자.

일단 이렇게 하니까 풀림. 13ms로 73%를 땄다.
*/

public class Leet_287SerializeAndDeserializeBinaryTree {
    public static void main(String[] args) {
        TreeNode test1 = new TreeNode(5);
        test1.left = new TreeNode(4);
        test1.left.left = new TreeNode(3);
        test1.left.left.left = new TreeNode(-1);
        test1.right = new TreeNode(7);
        test1.right.left = new TreeNode(2);
        test1.right.left.left = new TreeNode(9);

        TreeNode test2 = new TreeNode(1);
        test2.right = new TreeNode(2);
        test2.right.left = new TreeNode(3);

        Leet_287SerializeAndDeserializeBinaryTree leet = new Leet_287SerializeAndDeserializeBinaryTree();

        // [5,4,7,3,null,2,null,-1,null,9]
        System.out.println(leet.serialize(test1));

        // [1,null,2,3]
        System.out.println(leet.serialize(test2));
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder serialized = new StringBuilder(); // 가변 객체인 StringBuilder를 쓰는 게 효율적임.

        // 직렬화는 리트코드 방식으로 수행한다. 트리 레벨 별로 왼쪽에서 오른쪽으로 직렬화한다. bfs알고리즘을 사용.
        if (root == null) {
            return serialized.toString();
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int currentQueueSize = queue.size();
            for (int i = 0; i < currentQueueSize; i++) {
                TreeNode polled = queue.poll();

                if (polled == null) {
                    serialized.append("null").append(" ");
                    continue;
                }

                // 자식이 null이라도 일단 큐에 집어넣음 다음 레벨에서 위 조건문에 의해 null로 직렬화되고 거기서 대가 끊김.
                queue.offer(polled.left);
                queue.offer(polled.right);

                serialized.append(polled.val).append(" ");
            }
        }

        return serialized.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.isEmpty()) {
            return null;
        }

        // 역직렬화도 직렬화와 마찬가지로 레벨별로 왼쪽에서 오른쪽으로 수행한다.

        String[] serializedNodesArr = data.split(" ");

        TreeNode rootNode = new TreeNode(Integer.parseInt(serializedNodesArr[0])); // 1번째 레벨인 루트 노드는 직접 생성해서 큐에 집어넣음.

        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.offer(rootNode);

        // 2번째 레벨부터 시작
        int startIdx = 1; // 레벨 시작 인덱스
        int endIdx = 2; // 레벨 끝 인덱스
        int nextLevelSize; // 이 변수가 핵심이다.

        while (!nodeQueue.isEmpty()) {
            nextLevelSize = 0;

            for (int i = startIdx; i < endIdx; i = i+2) { // startIdx에서 endIdx까지 2씩 건너뜀. 반복횟수가 이전 층의 노드 수와 같음.
                TreeNode polled = nodeQueue.poll(); // 큐에서 노드를 하나 뺌.

                // null이 아닌 경우 자식이 있다는 것(직렬화된 data에서 다음 레벨에 2개의 연결된 자식 값을 가지고 있다는 것). nextLevelSize에 2를 더한다.
                if (!serializedNodesArr[i].equals("null")) { // 왼쪽 노드
                    TreeNode newNode = new TreeNode(Integer.parseInt(serializedNodesArr[i]));
                    polled.left = newNode;
                    nodeQueue.offer(newNode);
                    nextLevelSize += 2;
                }
                if (!serializedNodesArr[i+1].equals("null")) { // 오른쪽 노드
                    TreeNode newNode = new TreeNode(Integer.parseInt(serializedNodesArr[i + 1]));
                    polled.right = newNode;
                    nodeQueue.offer(newNode);
                    nextLevelSize += 2;
                }
            }

            // 다음 레벨을 위해 startIdx, endIdx 재설정
            int temp = endIdx;
            startIdx = endIdx + 1;
            endIdx = temp + nextLevelSize;
        }

        return rootNode;
    }
}
