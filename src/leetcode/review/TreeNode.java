package leetcode.review;

public class TreeNode {
    public TreeNode left;
    public TreeNode right;
    public int val;

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.left = left;
        this.right = right;
        this.val = val;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        search(this, sb);
        return sb.toString();
    }

    private void search(TreeNode root, StringBuilder sb){
        if (root != null) {
            search(root.left, sb);
            sb.append(root.val);
            sb.append(" ");
            search(root.right, sb);
        }
    }
}
