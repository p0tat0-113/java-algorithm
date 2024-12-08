package leetcode.review.finalExam;

import com.sun.source.tree.Tree;

import java.util.HashMap;

public class Leet_105ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public static void main(String[] args) {
        Leet_105ConstructBinaryTreeFromPreorderAndInorderTraversal leet = new Leet_105ConstructBinaryTreeFromPreorderAndInorderTraversal();
        TreeNode result = leet.buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println(leet.isSame(root, result));
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return process(preorder, inorder, 0, preorder.length-1, 0, map);
    }

    //전위순회: 왼쪽 자식의 인덱스는 바로 알 수 있음. 그러나 오른쪽 자식의 인덱스는 확실하지 않음. 왼쪽 자식의 서브트리가 어느정도 되는지 모르기 때문이다.
    //중위순회: 왼쪽 자식의 서브트리 크기를 알아낼 수 있음.
    private TreeNode process(int[] preorder, int[] inorder, int start, int end, int rootIdx, HashMap<Integer, Integer> map) {
        TreeNode root = null;
        if (start <= end) {
            root = new TreeNode(preorder[rootIdx]);
            Integer indexOfRootInInorder = map.get(preorder[rootIdx]);
            int k = indexOfRootInInorder - start + 1;//inorder에서의 상대적인 등수

            root.left = process(preorder, inorder, start, indexOfRootInInorder-1,rootIdx+1, map);
            root.right = process(preorder, inorder, indexOfRootInInorder+1, end, rootIdx+k, map);
        }
        return root;
    }

    private boolean isSame(TreeNode root, TreeNode subRoot){
        if (root == null && subRoot == null) {
            return true;
        }

        if (root == null || subRoot == null) {
            return false;
        }

        if (root.val != subRoot.val) {
            return false;
        }

        return  isSame(root.left, subRoot.left) && isSame(root.right, subRoot.right);
    }
}