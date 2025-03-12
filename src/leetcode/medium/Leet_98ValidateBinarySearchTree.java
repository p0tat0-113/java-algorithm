package leetcode.medium;

/*
문제 자체는 간단하다. 주어지는 이진트리가 BST가 맞는지 판별하기만 하면 된다.
BST이기 위해서는 왼쪽 자식은 부모보다 작고, 오른쪽 자식은 부모보다 커야한다.

그냥 깊이 우선 탐색으로 풀면 될 것 같음. 알고리리즘을 오랜만에 손대보긴 하지만, 그래도 막 그렇게 어려울 것 같지는 않다.

잠깐만 이게 무지성으로 쉽게 풀 문제는 아니네. 까다로운 케이스들이 존재함.

아 알겠다.
답은 "중위순회"에 있는 것 같다. BST라면 중위순회를 돌렸을 때 키들이 오름차순으로 정렬되어 있어야 한다. 만약 정렬되어있지 않다면 이건 잘못된 것.
*/

import leetcode.TreeNode;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Leet_98ValidateBinarySearchTree {
    public static void main(String[] args) {
        Leet_98ValidateBinarySearchTree leet = new Leet_98ValidateBinarySearchTree();

        TreeNode root1 = new TreeNode(2);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(2);

        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(4);
        root2.right = new TreeNode(6);
        root2.right.left = new TreeNode(3);
        root2.right.right = new TreeNode(7);

        TreeNode root3 = new TreeNode(5);
        root3.left = new TreeNode(1);
        root3.right = new TreeNode(4);
        root3.right.left = new TreeNode(3);
        root3.right.right = new TreeNode(6);

        System.out.println(leet.isValidBST(root1));
        System.out.println(leet.isValidBST(root2));
        System.out.println(leet.isValidBST(root3));
    }

    public boolean isValidBST(TreeNode root) {
        //printInorder(root);
        //return dfs(root);

        return useInorder(root);
    }

    private ArrayList<Integer> list = new ArrayList<>(); // 중위순회 결과를 저장하는 리스트

    // 중위순회 사용.
    private boolean useInorder(TreeNode root) {
        if (root == null) {
            return true;
        }

        boolean leftResult = useInorder(root.left);
        if (!leftResult) { //결과가 false로 나오면 이미 조진 상태이기 때문에 그대로 리턴해서 false를 올려보냄.
            return false;
        }

        // 리스트의 마지막 값을 가져와서 크기를 비교한다. 만약 바로 이전 값보다 작거나 같다면 false를 반환함.
        if (!list.isEmpty() && root.val <= list.getLast()) {
            return false;
        }
        list.addLast(root.val);

        boolean rightResult = useInorder(root.right);
        if (!rightResult) {
            return false;
        }

        return true;
    }

//    private boolean dfs(TreeNode root) {
//        if (root == null) {//끝까지 문제없이 내려옴.
//            return true;
//        }
//
//        if ((root.left != null && root.left.val >= root.val) || (root.right != null && root.right.val <= root.val)) {
//            return false;
//        }
//
//        boolean leftResult = dfs(root.left);
//        boolean rightResult = dfs(root.right);
//
//        return leftResult && rightResult;
//    }
//
//    private void printInorder(TreeNode root) {
//        if (root == null) {
//            return;
//        }
//
//        printInorder(root.left);
//        System.out.println(root.val);
//        printInorder(root.right);
//    }
}
