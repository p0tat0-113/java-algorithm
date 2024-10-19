package leetcode.review;

import java.util.HashMap;
import java.util.HashSet;

public class Leet_105ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        Leet_105ConstructBinaryTreeFromPreorderAndInorderTraversal leet = new Leet_105ConstructBinaryTreeFromPreorderAndInorderTraversal();
        System.out.println(leet.buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7}));
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return process(0, 0, preorder.length-1, preorder, inorder, map);
    }

    private TreeNode process (int rootIdx, int s, int e, int[] preorder, int[] inorder, HashMap<Integer, Integer> map) {
        if (s <= e) {
            TreeNode root = new TreeNode(preorder[rootIdx]);

            int rootIdxInInorder = map.get(preorder[rootIdx]);
            int k = rootIdxInInorder-s+1;

            root.left = process(rootIdx+1, s, rootIdxInInorder-1, preorder, inorder, map);//처음에 rootIdxInInorder-1이 아닌 rootIdx-1로 해서 제대로 작동이 안되었었음.
            root.right = process(rootIdx+k, rootIdxInInorder+1, e, preorder, inorder, map);

            return root;
        }
        return null;
    }
}
