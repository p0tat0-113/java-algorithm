package leetcode.medium;

import leetcode.TreeNode;

/*
문제를 풀긴 풀었는데 이게 재귀호출이라서 문제에서 follow up으로 제시하는 것처럼 공간복잡도가 O(1)은 아니다.
https://imgur.com/a/3FfsWyb
*/

public class Leet_99RecoverBinarySearchTree {
    public static void main(String[] args) {
        Leet_99RecoverBinarySearchTree leet = new Leet_99RecoverBinarySearchTree();

        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(4);
        root1.right.left = new TreeNode(2);

        TreeNode root2 = new TreeNode(2);
        root2.left = new TreeNode(3);
        root2.right = new TreeNode(1);

        TreeNode root3 = new TreeNode(3);
        root3.right = new TreeNode(2);
        root3.right.right = new TreeNode(1);

        leet.recoverTree(root1);
        leet.recoverTree(root2);
        leet.recoverTree(root3);
    }

    private TreeNode prev = null;
    private TreeNode first = null;
    private TreeNode second = null;

    public void recoverTree(TreeNode root) {
        inorder(root);
        //System.out.println(first.val);
        //System.out.println(second.val);
        //System.out.println("---------------------");

        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    private void inorder(TreeNode root) {
        if (root == null)  {
            return;
        }

        inorder(root.left);

        //여기에서 제약조건 위반을 감지한다. BST 중위순회에서 현재 숫자는 앞의 숫자보다 커야 한다.
        if (prev != null) {//지금이 첫 시작점이 아닌 경우
            if (prev.val > root.val) {//제약조건 위반상황이 발생. 현재 숫자가 앞의 숫자보다 작다.
                if (first == null) {//이전까지 제약조건 위반상황이 없었던 경우.
                    first = prev;
                    second = root;
                } else {//이전에 제약조건 위반상황이 있었던 경우. [3,null,2,null,1], [2,3,1] 이런 테스트케이스에서 요 상황이 발생한다.
                    second = root;
                }
            }
        }
        prev = root;//prev로 현재 루트를 집어넣고 다음으로 넘어간다.

        inorder(root.right);
    }
}
