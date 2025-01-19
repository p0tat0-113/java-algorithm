package leetcode.medium;

import leetcode.TreeNode;

/*
Given the root of a binary tree, flatten the tree into a "linked list":

The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
The "linked list" should be in the same order as a pre-order traversal of the binary tree.

Follow up: Can you flatten the tree in-place (with O(1) extra space)?

follo up의 in-place로 수행하라는 조건 때문에 난이도가 조금 올라가는 문제다. 그래도 그림 그려가면서 하니까 막 엄청 어렵지는 않았음 금방 풀었다!
https://imgur.com/KltdKIo

그런데 solutions를 보니까 비슷하지만 조금 더 세련된 코드들도 있는 듯.
*/

public class Leet_114FlatternBinaryTreeToLinkedList {
    public static void main(String[] args) {

    }

    //이게 되네...ㅋㅋㅋ inplace로 되게 하라고 해서 좀 어려움을 느꼈는데 잘 생각해보고 일단 시도해 보니까 바로 풀림!!!
    public void flatten(TreeNode root) {
        TreeNode currentNode = new TreeNode();//연결리스트의 맨 끝단을 가리키는 변수. 구현을 쉽게 하기 위해서 일단 빈 노드를 하나 생성했음.

        while (root != null) {
            currentNode.right = root;
            currentNode = currentNode.right;//루트 노드를 뺴서 연결리스트 맨 끝부분에 갖다 붙이는 과정

            if (root.left != null) {//루트의 왼쪽 자식이 있는 경우, 왼쪽 서브트리의 맨 오른쪽 아래 노드의 오른쪽에, 오른쪽 서브트리를 통째로 가져다가 붙인다. 이래야지만 전위순회의 순서가 그대로 유지됨.
                TreeNode rightTailNode = root.left;
                while (rightTailNode.right != null) {
                    rightTailNode = rightTailNode.right;
                }
                rightTailNode.right = root.right;

                root = root.left;//왼쪽 자식 노드를 루트로 만든다.
            } else {
                root = root.right;//오른쪽 자식 노드를 루트로 만든다.
            }

            currentNode.left = null;//참조 문제를 예방하기 위해 그냥 다 썰어버림.
            currentNode.right = null;
        }
    }
}
