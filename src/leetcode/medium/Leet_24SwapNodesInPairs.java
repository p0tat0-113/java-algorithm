package leetcode.medium;

import leetcode.ListNode;

import java.util.List;

/*
단방향 연결리스트가 주어지면, 인접한 노드끼리 swap을 해서 반환하면 되는 문제다. 이때 노드의 값을 건드리며 안되고, 노드 자체를 움직여야 한다는 제약조건이 붙어있음.
Input: head = [1,2,3,4]
Output: [2,1,4,3]
Input: head = []
Output: []
Input: head = [1]
Output: [1]
Input: head = [1,2,3]
Output: [2,1,3]
리스트의 길이가 0이거나 1이면 그대로 반환을 하고,
아니면 tempHeadNode에 붙여서 swap작업을 해주면 될 듯 하다. 간단해 보임.

스왑하는 부분에서 생각보다 애를 먹음....ㅠㅠ 그래도 0ms로 잘 풀었다.
*/

public class Leet_24SwapNodesInPairs {
    public static void main(String[] args) {
        ListNode root = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, null))));
        System.out.println(swapPairs(root));
    }

    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode tempHeadNode = new ListNode();
        tempHeadNode.next = head;//임시 머리 노드를 만들고, 입력받은 리스트를 그 뒤에 붙여줌. ex) 1-2-3-4 => 0-1-2-3-4
        ListNode currentNode = tempHeadNode;

        //반복문을 돌리기 위해서는 currentNode.next.next != null이 보장되어야 함.
        //앞의 조건들은 currentNode.next.next != null연산을 할 때 nullPointerException이 터지지 않게 하기위해 거르는 역할
        //ex)currentNode: 0-1-2-3-4
        while(currentNode != null && currentNode.next != null && currentNode.next.next != null) {
            ListNode temp = currentNode.next.next;//temp: 2-3-4

            currentNode.next.next = currentNode.next.next.next;//currentNode: 0-1-3-4
            temp.next = currentNode.next;//temp: 2-1-3-4
            currentNode.next = temp;//currentNode: 0-2-1-3-4

            currentNode = currentNode.next.next;//currentNode:1-3-4, 다음 반복문을 위한 전진
        }

        return tempHeadNode.next;
    }
}
