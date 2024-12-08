package leetcode.review.finalExam;

public class Leet_572SubtreeOfAnotherTree {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(4);
        root1.right = new TreeNode(5);
        root1.left.left = new TreeNode(1);
        root1.left.right = new TreeNode(2);
        //root1.left.left.left = new TreeNode(1);


        TreeNode subRoot1 = new TreeNode(4);
        subRoot1.left = new TreeNode(1);
        subRoot1.right = new TreeNode(2);

        Leet_572SubtreeOfAnotherTree leet = new Leet_572SubtreeOfAnotherTree();
        System.out.println(leet.isSame(root1.left, subRoot1));//isSame이 제대로 작동하는지 먼저 검증했다.
        System.out.println(leet.isSubtree(root1, subRoot1));//처음에 이 부분에서 isSame을 호출하고 있어서 계속 이상한 답이 나오고 있었음.
    }

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) {
            return false;
        }

        if (isSame(root, subRoot)) {
            return true;
        }

        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);//처음에 이 부분에서 subRoot.left, subroot.right를 인수로 넘겨서 이상한 답이 나올 뻔 했음.
    }

    private boolean isSame(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) {
            return true;
        }

        if (root == null || subRoot == null) {
            return false;
        }

        if (root.val != subRoot.val) {
            return false;
        }

        return isSame(root.left, subRoot.left) && isSame(root.right, subRoot.right);
    }
}
