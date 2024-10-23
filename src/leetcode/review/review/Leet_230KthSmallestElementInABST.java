public class Leet_230KthSmallestElementInABST {
    public static void main(String[] args) {
        Leet_230KthSmallestElementInABST leet = new Leet_230KthSmallestElementInABST();

        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(1);
        root1.left.right = new TreeNode(2);
        root1.right = new TreeNode(4);
        System.out.println(leet.kthSmallest(root1, 1));

        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(3);
        root2.left.right = new TreeNode(4);
        root2.left.left = new TreeNode(2);
        root2.left.left.left = new TreeNode(1);
        root2.right = new TreeNode(6);
        System.out.println(leet.kthSmallest(root2, 3));
    }

    int k;
    int result;

    public int kthSmallest(TreeNode root, int k) {
        this.k = k;
        search(root);
        return result;
    }

    private void search(TreeNode root){
        if (root != null) {
            search(root.left);
            k--;
            if (k == 0) {
                result = root.val;
                return;
            }
            search(root.right);
        }
    }
}
