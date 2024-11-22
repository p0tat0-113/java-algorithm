package leetcode.review.finalExam;

import java.util.HashMap;

public class Leet105_ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public static void main(String[] args) {

    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return process(preorder, inorder, map, 0, 0, inorder.length-1);
    }

    //전위순회와 중위순회를 상호보완 시켜서 원래의 이진트리를 만들어내는 문제인데, 오랜만에 다시 하려니까 생각이 안나서 좀 힘들었다ㅋㅋ
    private TreeNode process(int[] preorder, int[] inorder, HashMap<Integer, Integer> map, int rootIdx, int start, int end) {
        if (start > end) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[rootIdx]);

        Integer rootIdxOnInorder = map.get(preorder[rootIdx]);
        int k = rootIdxOnInorder - start + 1;//inorder start~end 범위 내에서 preorder[rootIdx]의 상대적인 순위를 나타낸다.

        root.left = process(preorder, inorder, map, rootIdx+1, start, rootIdxOnInorder-1);
        root.right = process(preorder, inorder, map, rootIdx+k, rootIdxOnInorder+1, end);

        return root;
    }
}
