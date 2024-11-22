package leetcode.review.mid;

public class Leet_230KthSmallestElementInBST {
    public static void main(String[] args) {
        Leet_230KthSmallestElementInBST leet = new Leet_230KthSmallestElementInBST();

        TreeNode root1 = new TreeNode(3, new TreeNode(1, null, new TreeNode(2)), new TreeNode(4));
        System.out.println(leet.kthSmallest(root1, 1));
    }

    int k;
    int result;

    //BST에서 k번째로 작은 수를 찾아야 하는 문제. 중위순회를 이용하면 간단하게 해결할 수 있다.
    public int kthSmallest(TreeNode root, int k) {
        this.k = k;
        process(root);
        return result;
    }

    private void process(TreeNode root){
        if (root != null) {
            process(root.left);
            k--;
            if (k == 0) {
                result = root.val;
                return;
            }
            process(root.right);
        }
    }
}
