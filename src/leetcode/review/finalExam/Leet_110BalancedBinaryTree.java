package leetcode.review.finalExam;

import com.sun.source.tree.Tree;

public class Leet_110BalancedBinaryTree {
    public static void main(String[] args) {
        Leet_110BalancedBinaryTree leet = new Leet_110BalancedBinaryTree();
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);
        root1.right.left = new TreeNode(15);
        root1.right.right = new TreeNode(7);
        //root1.right.right.right = new TreeNode(7);
        System.out.println(leet.isBalanced(root1));

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(2);
        root2.left.left = new TreeNode(3);
        root2.left.right = new TreeNode(3);
        root2.left.left.left = new TreeNode(4);
        root2.left.left.right = new TreeNode(4);
        System.out.println(leet.isBalanced(root2));
    }

    public boolean isBalanced(TreeNode root) {
        return process(root, 0);
    }

    int depth;
    private boolean process(TreeNode root, int depth){
        if (root == null) {
            this.depth = depth;
            return true;
        }

        boolean leftResult = process(root.left, depth + 1);
        int leftDepth = this.depth;

        boolean rightResult = process(root.right, depth + 1);
        int rightDepth = this.depth;

        if (!leftResult || !rightResult) {
            return false;
        }

        if (Math.abs(leftDepth - rightDepth) > 1) {
            return false;
        }
        this.depth = Integer.max(leftDepth, rightDepth);
        return true;
    }
}
