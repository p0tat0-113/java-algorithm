package leetcode.review.finalExam;

public class Leet_235LCA {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(6);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(8);
        root1.left.left = new TreeNode(0);
        root1.left.right = new TreeNode(4);
        root1.left.right.left = new TreeNode(3);
        root1.left.right.right = new TreeNode(5);
        root1.right.left = new TreeNode(7);
        root1.right.right = new TreeNode(9);

        Leet_235LCA leet = new Leet_235LCA();
        System.out.println(leet.lowestCommonAncestor(root1, new TreeNode(2), new TreeNode(8)).val);
        System.out.println(leet.lowestCommonAncestor(root1, new TreeNode(2), new TreeNode(4)).val);

    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int small = Integer.min(p.val, q.val);
        int big = Integer.max(p.val, q.val);

        while (root != null) {
            if (big < root.val) {
                root = root.left;
            } else if (small > root.val) {
                root = root.right;
            } else {
                return root;
            }
        }

        return null;
    }
}
