package leetcode.review.review;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        search(this,sb);
        return sb.toString();
    }

    private void search(TreeNode root, StringBuilder sb){
        if (root != null) {
            search(root.left, sb);
            sb.append(root.val);
            sb.append(", ");
            search(root.right, sb);
        }
    }
}
