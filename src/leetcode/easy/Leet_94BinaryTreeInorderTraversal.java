package leetcode.easy;

import leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
아주 간단한 중위순회 문제다. 일본 여행 갔다오느라 거의 2주 동안 알고리즘 문제를 안풀어서 재활을 위해 풀어본다.
*/

public class Leet_94BinaryTreeInorderTraversal {
    public static void main(String[] args) {
        Leet_94BinaryTreeInorderTraversal leet = new Leet_94BinaryTreeInorderTraversal();
        TreeNode root1 = new TreeNode(1);
        root1.right = new TreeNode(2);
        root1.right.left = new TreeNode(3);

        System.out.println(leet.inorderTraversal(root1));
        System.out.println(leet.inorderTraversal(null));
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        recursion(root, list);
        return list;
    }

    private void recursion(TreeNode root, List<Integer> list) {
        if (root != null) {
            recursion(root.left, list);
            list.add(root.val);
            recursion(root.right, list);
        }
        return;
    }
}
