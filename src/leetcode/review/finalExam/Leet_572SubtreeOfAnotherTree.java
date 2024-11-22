package leetcode.review.finalExam;

public class Leet_572SubtreeOfAnotherTree {
    public static void main(String[] args) {

    }

    //이 문제도 계속 해결을 못하다가 chatgpt도움을 받아서 해결했었다.
    //지금은 대충 어떻게 돌아가는지 아니까 큰 어려움 없이 해결할 수 있긴 함.
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) {
            return false;
        }

        if (recursion(root, subRoot)) {
            return true;
        }

        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    private boolean recursion(TreeNode root, TreeNode subRoot){
        if (root == null && subRoot == null) {
            return true;
        }

        if (root == null || subRoot == null) {
            return false;
        }

        //처음에 이 조건문과
        if (root.val != subRoot.val) {
            return false;
        }

        //이 부분을 and가 아니라 or로 처리를 해서 계속 2번째 테스트 케이스에서 걸렸었다. 머리를 좀 쓰자....
        return recursion(root.left, subRoot.left) && recursion(root.right, subRoot.right);
    }
}
