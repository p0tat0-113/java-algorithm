package leetcode.easy;

import com.sun.source.tree.Tree;
import leetcode.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;

/*
어떤 이진트리의 루트를 하나 던져주면, 그 트리의 균형이 맞는지, height-balanced인지 확인하는 문제다.
*/

public class Leet_110BalancedBinaryTree {
    public static void main(String[] args) {
        /*PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(5);
        queue.add(3);
        for (Integer integer : queue) {
            System.out.println(integer);
        }*/

        Leet_110BalancedBinaryTree leet = new Leet_110BalancedBinaryTree();

        TreeNode root1 = new TreeNode(4);
        root1.left = new TreeNode(8);
        System.out.println(leet.isBalanced(root1));

        root1.left.left = new TreeNode(10);
        System.out.println(leet.isBalanced(root1));

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.left.left = new TreeNode(3);
        root2.left.left.left = new TreeNode(4);
        root2.right = new TreeNode(2);
        root2.right.right = new TreeNode(3);
        root2.right.right.right = new TreeNode(4);
        System.out.println(leet.isBalanced(root2));
    }

    public boolean isBalanced(TreeNode root) {
        return search(root, 0, new PriorityQueue<>());
    }

    private boolean search(TreeNode root, int depth, PriorityQueue<Integer> queue) {
        if (root == null) {
            queue.add(depth);
            return true;
        }

        PriorityQueue<Integer> littleQueue = new PriorityQueue<>();
        boolean resultLeft = search(root.left, depth+1, littleQueue);
        boolean resultRight = search(root.right, depth+1, littleQueue);

        if (!resultLeft || !resultRight) {
            return false;
        }

        int small = littleQueue.poll();
        int big = littleQueue.poll();

        if (big - small > 1) {
            return false;
        }

        queue.add(big);
        return true;
    }

    /*public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        ArrayList<Integer> depthList = new ArrayList<Integer>();

        search(root, 0, depthList);
        System.out.println(depthList);

        int max = depthList.getFirst();
        int min = depthList.getFirst();

        for (int i = 1; i < depthList.size(); i++) {
            if (depthList.get(i) > max) {
                max = depthList.get(i);
            }
            if (depthList.get(i) < min) {
                min = depthList.get(i);
            }
        }

        if (max - min <= 1) {
            return true;
        } else {
            return false;
        }
    }

    *//*private void search(TreeNode root, int depth, ArrayList<Integer> depthList){
        if (root != null) {
            search(root.left, depth+1, depthList);
            search(root.right, depth+1, depthList);
            return;
        }
        depthList.add(depth);
    }*//*

    private void search(TreeNode root, int depth, ArrayList<Integer> depthList){
        if (root.left == null && root.right == null) {
            depthList.add(depth);
        }

        if (root.left != null) {
            search(root.left, depth+1, depthList);
        }
        if (root.right != null) {
            search(root.right, depth+1, depthList);
        }
    }*/
}
