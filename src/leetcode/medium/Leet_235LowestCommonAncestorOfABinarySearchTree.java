package leetcode.medium;

/*
이진탐색트리의 루트와 두 노드 p,q를 주면 p,q의 LCA(LowestCommonAncestor)를 찾는 문제다. https://en.wikipedia.org/wiki/Lowest_common_ancestor
p를 찾기까지 만나는 노드와, q를 찾기까지 만나는 노드를 리스트에 기록해두던가 하고, 거기에서 공통되는 것을 찾으면 되지 않을까 싶음.
좀 더 구체적으로 얘기하자면 입력 순서가 유지되는 LinkedHashSet에 노드들을 저장하고, 이걸 교집합울 하면 거기에서 가장 뒤에 있는 노드가 LCA가 되지 않을까 싶음.

오! 이렇게 하니까 완전 개날먹으로 풀림ㅋㅋ

All Node.val are unique.
p != q
p and q will exist in the BST.
*/

import leetcode.TreeNode;

import java.util.LinkedHashSet;

public class Leet_235LowestCommonAncestorOfABinarySearchTree {
    public static void main(String[] args) {
        Leet_235LowestCommonAncestorOfABinarySearchTree leet = new Leet_235LowestCommonAncestorOfABinarySearchTree();
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        LinkedHashSet<TreeNode> setP = new LinkedHashSet<>();//LinkedHashSet은 입력 순서를 유지하는 HashSet이다.
        LinkedHashSet<TreeNode> setQ = new LinkedHashSet<>();

        search(root, p, setP);//p를 만나기 까지의 탐색 경로를 setP에 저장
        search(root, q, setQ);//q를 만나기 까지의 탐색 경로를 setQ에 저장

        setP.retainAll(setQ);//교집합을 한다.
        return setP.getLast();//교집한된 결과 set에서 가장 뒤에 있는 원소가 바로 LCA다. 끝!!!
    }

    //문제의 조건에서 p and q will exist in the BST 라고 했으므로 target은 무조건 있다고 가정하고 탐색을 한다.
    //target을 탐색하는 동안 만나는 모든 노드들을 LinkedHashSet에 저장한다.
    private void search(TreeNode root, TreeNode target, LinkedHashSet<TreeNode> set){
        if (root.val == target.val) {//target을 찾았다. 재귀호출을 멈춤.
            set.add(target);
        } else if (root.val > target.val) {
            set.add(root);
            search(root.left, target, set);
        } else {//root.val < target.val
            set.add(root);
            search(root.right, target, set);
        }
    }
}
