package leetcode.review.finalExam;

public class Leet_110BalancedBinaryTree {
    public static void main(String[] args) {

    }

    public boolean isBalanced(TreeNode root) {
        return recursion(root, 0);
    }

    //문제를 다시 풀어보고, 다른 풀이들이 더 낫다 싶으면 그걸로 바꾸려고 했는데, 다른 풀이들도 핵심은 내 방식이랑 똑같음.
    //다만 조금 더 세련되게 바꿈. 이래도 똑같이 0ms로 시간 잘 나오네.

    int depth;
    private boolean recursion(TreeNode root, int depth) {
        if (root == null) {
            this.depth = depth;
            return true;
        }

        boolean leftResult = recursion(root.left, depth+1);
        int leftDepth = this.depth;
        boolean rightResult = recursion(root.right, depth+1);
        int rightDepth = this.depth;

        if (!leftResult || !rightResult) {//둘 중 하나라도 false이면 false반환.
            return false;
        }

        if (Math.abs(leftDepth - rightDepth) > 1) {//양쪽의 깊이 차이가 1보다 크면 false를 반환한다.
            return false;
        }

        this.depth = Integer.max(leftDepth, rightDepth);

        return true;
    }
}
