package leetcode.medium;

import com.sun.source.tree.Tree;
import leetcode.TreeNode;

import java.util.LinkedList;

public class Leet_99RecoverBinarySearchTree {
    public static void main(String[] args) {
        Leet_99RecoverBinarySearchTree leet = new Leet_99RecoverBinarySearchTree();

        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(4);
        root1.right.left = new TreeNode(2);

        leet.printPreorder(root1);
        System.out.println();

        leet.recoverTree(root1);

        leet.printPreorder(root1);
        System.out.println();

        System.out.println("----------------");

        TreeNode root2 = new TreeNode(2);
        root2.left = new TreeNode(3);
        root2.right = new TreeNode(1);

        leet.printPreorder(root2);
        System.out.println();

        leet.recoverTree(root2);

        leet.printPreorder(root2);
        System.out.println();

        System.out.println("----------------");

        TreeNode root3 = new TreeNode(3);
        root3.right = new TreeNode(2);
        root3.right.right = new TreeNode(1);

        leet.printPreorder(root3);
        System.out.println();

        leet.recoverTree(root3);

        leet.printPreorder(root3);
        System.out.println();


    }

    public void recoverTree(TreeNode root) {
        LinkedList<TreeNode> smallerNodesStack = new LinkedList<>();
        LinkedList<TreeNode> biggerNodesStack = new LinkedList<>();
        LinkedList<TreeNode[]> violated = new LinkedList<TreeNode[]>();

        dfs(root, smallerNodesStack,biggerNodesStack, violated);

        if (violated.size() == 1) {
            int temp = violated.get(0)[0].val;
            violated.get(0)[0].val = violated.get(0)[1].val;
            violated.get(0)[1].val = temp;
        } else {
            int temp = violated.get(0)[1].val;
            violated.get(0)[1].val = violated.get(1)[1].val;
            violated.get(1)[1].val = temp;
        }

        //System.out.println(violated.size());
    }

    //자꾸 테스트 케이스가 하나씩 걸려서 코드를 좀 많이 고쳐야 할 것 같다...
    //일단 경로를 쭉 탐색하고 있는 동안의 violation포인트가 몇개가 나오던지 무시하고 가장 최신 것만 쭉 밀고 가는게 맞는 것 같다. 그래야 테스트케이스4 같은 경우가 해결됨.
    //아 근데 이렇게 하면 또 테스트케이스5가 해결이 안됨... 뭔가 다른 돌파구가 필요한데...

    private void dfs(TreeNode root, LinkedList<TreeNode> smallerNodesStack, LinkedList<TreeNode> biggerNodesStack, LinkedList<TreeNode[]> violated){
        if (root == null) {
            return;
        }

        if (!smallerNodesStack.isEmpty()) {
            TreeNode latestSmallerNode = smallerNodesStack.peekLast();
            if (latestSmallerNode.val > root.val) {//조건위반감지

                if (!violated.isEmpty()) {
                    TreeNode[] treeNodes = violated.peekLast();
                    if (latestSmallerNode == treeNodes[1]) {
                        latestSmallerNode = treeNodes[0];
                        violated.pollLast();
                    }
                }
                violated.add(new TreeNode[] {latestSmallerNode,root});
                System.out.println(violated);
                //return;
            }
        }

        if (!biggerNodesStack.isEmpty()) {
            TreeNode latestBiggerNode = biggerNodesStack.peekLast();
            if (latestBiggerNode.val < root.val) {//조건위반감지
                if (!violated.isEmpty()) {
                    TreeNode[] treeNodes = violated.peekLast();
                    if (latestBiggerNode == treeNodes[1]) {
                        latestBiggerNode = treeNodes[0];
                        violated.pollLast();
                    }
                }
                violated.add(new TreeNode[] {latestBiggerNode,root});
                System.out.println(violated);
                //return;
            }
        }

        biggerNodesStack.add(root);
        dfs(root.left, smallerNodesStack, biggerNodesStack, violated);
        biggerNodesStack.pollLast();

        smallerNodesStack.add(root);
        dfs(root.right, smallerNodesStack, biggerNodesStack, violated);
        smallerNodesStack.pollLast();
    }

    private void printPreorder(TreeNode root) {
        if (root == null) {
            return;
        }

        System.out.print(root.val + " ");
        printPreorder(root.left);
        printPreorder(root.right);
    }
}
