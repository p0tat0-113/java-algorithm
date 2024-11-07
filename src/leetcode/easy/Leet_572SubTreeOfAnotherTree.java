package leetcode.easy;

import leetcode.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

/*
두 이진트리의 루트 root와 subRoot가 주어진다. 그러면 subRoot를 루트로 하는 작은 트리가 root를 루트로 하는 큰 트리에 포함되어 있는지 학인하면 되는 문제다.
뭔가 110번 문제도 그렇고 내가 알던 easy문제가 아니긴 한데 이건 그래도 비교적 쉬워보임.
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

        /*List<Integer> list1 = List.of(1, 2, 3);
        List<Integer> list2 = List.of(1, 2, 3);
        System.out.println(list1.equals(list2));*/
    }

    //다시 생각해보니까 가장 말단 서브트리부터 재귀적으로 트리에 있는 각각의 서브트리를 비교하는 방식으로 해야할 것 같다.
    //근데 이렇게 해도 또 다른 케이스에서 걸리네. 도대체 기준이 뭔지 모르겠다ㅅㅂ
    //근데 이것도 코드가 좀 잘못됐긴 했네. 왼쪽과 오른쪽에서 각각 서브트리를 퍼오고, 그 다음에 합쳐야 하는데 이건 그런 구조가 아님.
    /*public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        ArrayList<Integer> rootList = new ArrayList<>();
        ArrayList<Integer> subRootList = new ArrayList<>();

        process(subRoot, subRootList, rootList);
        System.out.println(subRootList);

        boolean processResult = process(root, rootList, subRootList);
        System.out.println(rootList);
        return processResult;
    }

    public boolean process(TreeNode root, ArrayList<Integer> recordSet, ArrayList<Integer> set) {
        if (root  == null) {
            return false;
        }

        boolean leftResult = process(root.left, recordSet, set);
        if (leftResult) {
            return true;
        }

        boolean rightResult = process(root.right, recordSet, set);
        if (rightResult) {
            return true;
        }

        recordSet.add(root.val);

        if (recordSet.equals(set)) {
            return true;
        }
        return false;
    }*/
}
