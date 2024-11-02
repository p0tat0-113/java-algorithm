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

    //0ms까지 성능 개선했다... 앞에서 매 재귀호출마다 littleQueue를 생성해서 인수로 넣어주는 코드를 삭제하고 재귀호출한 함수가 동작을 끝낼 때마다 큐에서 값을 빼도록 리팩토링 하고 보니까
    //큐에 값이 2개가 있는 경우가 없었음. 그래서 이러면 그냥 일반 변수를 쓰면 되는거 아닌가?라는 생각이 들어서 코드를 고쳐보니까 잘 작동됨.
    //원리는 간단하다. 가장 말단 서브트리부터 시작해서 서브트리 양쪽의 높이를 비교. 양쪽의 높이 차가 1이하라면 계속 그 중 최악의 깊이를 올려보내는 것.
    int subtreeDepth;

    public boolean isBalanced(TreeNode root) {
        return search(root, 0);
    }

    private boolean search(TreeNode root, int currDepth) {
        if (root == null) {
            subtreeDepth = currDepth;
            return true;
        }

        boolean resultLeft = search(root.left, currDepth+1);
        if (!resultLeft) {
            return false;
        }
        int leftDepth = subtreeDepth;

        boolean resultRight = search(root.right, currDepth+1);
        if (!resultRight) {
            return false;
        }
        int rightDepth = subtreeDepth;

        if (leftDepth < rightDepth) {//leftDepth가 더 큰 수를 가지게 swap함.
            int temp = leftDepth;
            leftDepth = rightDepth;
            rightDepth = temp;
        }

        if (leftDepth - rightDepth > 1) {//서브트리의 양쪽 높이 차이가 1보다 큰 경우 return false
            return false;
        }

        subtreeDepth = leftDepth;//서브트리의 양쪽 높이 중 더 깊은 쪽. 그러니까 최악의 깊이를 다시 queue에 담음.
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
