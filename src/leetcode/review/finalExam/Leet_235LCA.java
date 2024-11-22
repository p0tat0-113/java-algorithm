package leetcode.review.finalExam;

public class Leet_235LCA {
    public static void main(String[] args) {

    }

    //전에는 이걸 LinkedHashSet을 이용해서 출제자의 의도와 다르게 날먹수준으로 풀었지만
    //이번에는 https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/solutions/1347857/c-java-python-iterate-in-bst-picture-explain-time-o-h-space-o-1/
    //이 천재적인 solution을 이용해서 아주 간단하게 해결을 했다. root를 움직인다는 개념으로 보니까 문제가 굉장히 쉬워진다.
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int large = Math.max(p.val, q.val);
        int small = Math.min(p.val, q.val);

        while (root != null) {
            if (large < root.val) {//p,q가 root의 왼쪽 서브트리에 존재
                root = root.left;
            } else if (small > root.val) {//p,q가 root의 오른쪽 서브트리에 존재
                root = root.right;
            } else {//root가 p,q사이에 존재. small <= root.val <= large
                return root;
            }
        }
        return null;
    }
}
