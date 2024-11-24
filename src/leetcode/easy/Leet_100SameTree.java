package leetcode.easy;

import leetcode.TreeNode;

/*
두 트리가 같은지(구조적으로 같고, 숫자도 같아야 함.) 확인하는 문제다.
이거 572번 문제, SubTreeOfAnotherTree문제와 거의 비슷하다. 그 문제의 마이너 버전이라고 보면 된다.
*/
public class Leet_100SameTree {
    public static void main(String[] args) {
        Leet_100SameTree leet = new Leet_100SameTree();
        TreeNode p = new TreeNode(1);
        p.left = new TreeNode(2);
        p.right = new TreeNode(3);

        //TreeNode q = p;
        TreeNode q = new TreeNode(1);
        q.left = new TreeNode(3);
        q.right = new TreeNode(2);

        System.out.println(leet.isSameTree(p, q));
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {//둘 다 null이면 true를 반환하고 올라감.
            return true;
        }

        if (p == null || q == null) {//둘 중 하나만 null이면 구조적을 다른 상태다. false반환
            return false;
        }

        if (p.val != q.val) {//둘의 값이 다르면 안됨. false반환.
            return false;
        }

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);//왼쪽과 오른쪽 자식을 재귀적으로 검사. 둘 다 true여야 한다.
    }
}
