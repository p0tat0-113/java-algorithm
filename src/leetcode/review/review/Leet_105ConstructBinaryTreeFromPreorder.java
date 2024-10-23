import java.util.HashMap;

public class Leet_105ConstructBinaryTreeFromPreorder {
    public static void main(String[] args) {
        Leet_105ConstructBinaryTreeFromPreorder leet = new Leet_105ConstructBinaryTreeFromPreorder();
        System.out.println(leet.buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7}));
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return process(0, 0, inorder.length-1, map, preorder, inorder);
    }

    private TreeNode process(int rootIdx, int s, int e, HashMap<Integer, Integer> map, int[] preorder, int[] inorder){
        if (s <= e) {
            TreeNode root = new TreeNode(preorder[rootIdx]);

            int rootIdxInInorder = map.get(preorder[rootIdx]);
            int k = rootIdxInInorder-s+1;

            root.left = process(rootIdx+1, s, rootIdxInInorder-1, map, preorder, inorder);
            root.right = process(rootIdx+k, rootIdxInInorder+1, e, map, preorder, inorder);

            return root;
        }

        return null;
    }
}
