package leetcode.medium;

import leetcode.TreeNode;

public class Leet_105ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public static void main(String[] args) {

    }

    int[] preorder;
    int[] inorder;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;
        return process(0, 0, inorder.length-1);
    }

    private TreeNode process(int rootIdx, int s, int e){
        if (rootIdx < preorder.length && s <= e) {
            TreeNode root = new TreeNode(preorder[rootIdx]);

            //inorder에서 root의 위치를 확인한다.
            int rootIdxInInorder = 0;
            for (int i = s; i <= e; i++) {
                if (preorder[rootIdx] == inorder[i]) {
                    rootIdxInInorder = i;
                    break;
                }
            }

            int k = rootIdxInInorder-s+1;//root의 inorder부분 배열 내에서의 등수

            if (k != 1) {
                root.left = process(rootIdx + 1, s, rootIdxInInorder-1);
            }
            root.right = process(rootIdx + k, rootIdxInInorder+1, e);


            return root;
        } else {
            return null;
        }
    }

    //처음의 코드
    /*private TreeNode process(int rootIdx, int s, int e){
        if (rootIdx < preorder.length && s <= e) {
            TreeNode root = new TreeNode(preorder[rootIdx]);

            root.left = process(rootIdx + 1, s, rootIdx-1);
            root.right = process(rootIdx + 2, rootIdx+1, e);

            return root;
        } else {
            return null;
        }
    }*/
}
