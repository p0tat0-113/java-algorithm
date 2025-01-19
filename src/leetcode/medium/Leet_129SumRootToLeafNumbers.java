package leetcode.medium;

import leetcode.TreeNode;

/*
You are given the root of a binary tree containing digits from 0 to 9 only.

Each root-to-leaf path in the tree represents a number.

For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
Return the total sum of all root-to-leaf numbers. Test cases are generated so that the answer will fit in a 32-bit integer.

A leaf node is a node with no children.

오랜만에 했더니 dfs도 살짝 헷갈리네. 그래도 간단한 문제여서 금방 풀었음.
*/

public class Leet_129SumRootToLeafNumbers {
    public static void main(String[] args) {
        Leet_129SumRootToLeafNumbers leet = new Leet_129SumRootToLeafNumbers();

        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);

        System.out.println(leet.sumNumbers(root1));//25가 나와야 함.
    }

    public int sumNumbers(TreeNode root) {
        dfs(root, 0);
        return sum;
    }

    private int sum = 0;

    //별도의 스택은 필요 없어 보임. 깊이 정보를 가지고 있는 변수가 하나 있어야 함.
    //root = [4,9,0,null,1] 케이스에서 틀렸는데, 생각해보니까 양쪽 자식이 다 없어야 리프노드로 취급됨. 그냥 무지성으로 더하면 안됨.
    private boolean dfs(TreeNode root, int previousNum) {
        if (root == null) {//null인 경우 trur를 반환
            return true;
        }

        previousNum = previousNum * 10;//위에서 내려온 이전 숫자에 10을 곱해서 자릿수를 하나 올린다.
        int newNum = previousNum + root.val;//거기에 현재 노드의 숫자를 더해서 새 숫자를 만들어냄.

        boolean leftResult = dfs(root.left, newNum);
        boolean rightResult = dfs(root.right, newNum);

        if (leftResult && rightResult) {//양쪽 자식이 다 없는 경우 리프노드로 판정. sum에 값을 더한다.
            sum += newNum;
        }

        return false;
    }
}
