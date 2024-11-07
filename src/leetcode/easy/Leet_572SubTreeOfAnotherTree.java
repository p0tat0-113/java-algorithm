package leetcode.easy;

import leetcode.TreeNode;

/*
두 이진트리의 루트 root와 subRoot가 주어진다. 그러면 subRoot를 루트로 하는 작은 트리가 root를 루트로 하는 큰 트리에 포함되어 있는지 학인하면 되는 문제다.
뭔가 110번 문제도 그렇고 내가 알던 easy문제가 아니긴 한데 이건 그래도 비교적 쉬워보임. <- 안쉬움.

https://chatgpt.com/share/672ca0c8-dd84-8006-8b55-dc8a25253cb4
chatgpt의 도움을 받아서 겨우 해결했다.....
*/

public class Leet_572SubTreeOfAnotherTree {
    public static void main(String[] args) {
        Leet_572SubTreeOfAnotherTree leet = new Leet_572SubTreeOfAnotherTree();

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(4);
        root.right = new TreeNode(5);
        TreeNode subRoot = new TreeNode(5);

        //System.out.println(leet.isSubtree(root, subRoot));
        System.out.println(leet.isSubtree(root, subRoot));
    }

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) {//종료 조건. 서브트리를 찾으러 계속 내려가다가 결국 못 찾고, null에 닿으면 false를 반환한다.
            return false;
        }

        if (isSame(root, subRoot)) {
            return true;
        }

        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
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

    /*public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) return false;
        // 현재 노드가 subRoot와 동일한지 확인
        if (isSameTree(root, subRoot)) {//먼저 root노드가 subRoot와 동일한지 확인한다.
            return true;
        }
        // 왼쪽과 오른쪽 서브트리에서도 동일한지 재귀적으로 확인
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);//isSubtree()를 재귀호출 하며 서브트리의 왼쪽-오른쪽 서브트리를 재귀적으로 탐색한다, 이는 모든 가능한 위치에서 subRoot를 찾기 위함이다.
    }

    // 두 트리가 동일한지 확인하는 메소드
    private boolean isSameTree(TreeNode s, TreeNode t) {
        // 두 노드 모두 null인 경우 동일
        if (s == null && t == null) return true;
        // 한 쪽만 null인 경우 다름
        if (s == null || t == null) return false;
        // 현재 노드의 값이 다르면 다름
        if (s.val != t.val) return false;
        // 좌우 서브트리도 동일한지 재귀적으로 확인
        return isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
    }*/
}
