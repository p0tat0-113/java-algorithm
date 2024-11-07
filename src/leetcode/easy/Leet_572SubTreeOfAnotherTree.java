package leetcode.easy;

import leetcode.TreeNode;

import java.util.ArrayList;
import java.util.LinkedHashSet;

/*
두 이진트리의 루트 root와 subRoot가 주어진다. 그러면 subRoot를 루트로 하는 작은 트리가 root를 루트로 하는 큰 트리에 포함되어 있는지 학인하면 되는 문제다.
뭔가 110번 문제도 그렇고 내가 알던 easy문제가 아니긴 한데 이건 그래도 비교적 쉬워보임.

우선 root트리에서 탐색을 해서 subRoot와 같은 키 값을 가진 노드를 찾는다.
그리고 subsRoot트리에서 중위순회를 하던 뭘 하던 아무튼 전체 노드를 다 탐색을 하면서 키 값들이 같은지 비교를 하면 될 듯 하다.
*/

public class Leet_572SubTreeOfAnotherTree {
    public static void main(String[] args) {
        Leet_572SubTreeOfAnotherTree leet = new Leet_572SubTreeOfAnotherTree();

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(4);
        root.right = new TreeNode(5);
        TreeNode subRoot = root;

        //System.out.println(leet.isSubtree(root, subRoot));
        leet.isSubtree(root, subRoot);
    }

    //다시 생각해보니까 가장 말단 서브트리부터 재귀적으로 트리에 있는 각각의 서브트리를 비교하는 방식으로 해야할 것 같다.
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root  == null) {
            return true;
        }


    }

    public boolean process(TreeNode root, )

    //이 방법으로도 안된다. 어디까지가 그 서브트리인지 구분을 못해서, 밑에 엉뚱한게 하나 달려있는 경우 제대로 구분을 못함.
    /*public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        StringBuilder sbRoot = new StringBuilder();
        StringBuilder sbSubRoot = new StringBuilder();

        recordAllNodes(root, sbRoot);
        recordAllNodes(subRoot, sbSubRoot);

        for (int i = 0; i < sbRoot.length() - sbSubRoot.length(); i++) {
            if (sbRoot.substring(i, i+sbSubRoot.length()).contentEquals(sbSubRoot)) {
                return true;
            }
        }

        return false;
    }

    private void recordAllNodes(TreeNode root, StringBuilder sb){
        if (root != null) {
            recordAllNodes(root.left, sb);
            sb.append(root.val);
            recordAllNodes(root.right, sb);
        }
    }*/

    /*TreeNode theNode = new TreeNode(Integer.MIN_VALUE);
    boolean result = true;

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        searchNode(root, subRoot.val);
        if (theNode.val == Integer.MIN_VALUE) {
            return false;
        }

        System.out.println("theNode = " + theNode.val);

        compareTree(subRoot, theNode);
        return result;
    }

    //트리가 이진탐색트리가 아니라 그냥 무지성 트리라서 트리 전체를 탐색하면서 찾아야 함.
    private void searchNode(TreeNode root, int key) {
        if (root != null) {
            if (root.val == key) {
                theNode = root;
            }
            searchNode(root.left, key);
            searchNode(root.right, key);
        }
    }

    private void compareTree(TreeNode subRoot, TreeNode theNode) {
        if (subRoot != null) {
            if (theNode == null) {
                System.out.println("theNode == null");
                result = false;
                return;
            }
            System.out.println(subRoot.val + "|" + theNode.val);
            if (subRoot.val != theNode.val) {
                System.out.println("subRoot.val != theNode.val");
                result = false;
                return;
            }
            compareTree(subRoot.left, theNode.left);
            compareTree(subRoot.right, theNode.right);
            return;
        }
        if (theNode != null) {
            result = false;
            return;
        }
    }*/
}
