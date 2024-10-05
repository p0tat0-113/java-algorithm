package leetcode.medium;

import leetcode.ListNode;

/*
연결리스트를 회전시켜야 하는 문제
진짜로 회전시킬 필요는 없고,
Input: head = [1,2,3,4,5], k = 2
Output: [4,5,1,2,3]
Input: head = [0,1,2], k = 4
Output: [2,0,1]

이 예제들을 보고 회전 후에 리스트가 어떻게 변했는지만 규칙을 찾아내면 된다.
*/

public class Leet_61RotateList {
    public static void main(String[] args) {
        ListNode root = new ListNode(4, new ListNode(2, new ListNode(1, new ListNode(3, null))));

        System.out.println(rotateRight(root, 5));
    }

    public static ListNode rotateRight(ListNode head, int k) {
        //먼저 리스트의 전체 길이를 한 번 구해준다.
        int length = 0;
        ListNode temp = head;
        while (temp != null) {
            length++;
            temp = temp.next;
        }

        //리스트의 길이가 0이나 1이라면 바로 리턴함.
        if (length == 0 || length == 1) {
            return head;
        }

        //마지막에 회전되어있을 부분의 길이를 구한다.
        int rotatedLength = k % length;
            if (rotatedLength == 0) {//회전 횟수가 0이거나, 리스트의 길이와 회전하는 횟수가 같으면 작업을 할 필요가 없음. 그대로 head를 리턴함.
                return head;
            }

        //회전되어 있을 부분의 머리 노드보다 하나 앞의 노드(temp)를 구한다. 예를들어 [1,2,3,4] k=1 -> [4,1,2,3] 여기서 회전되어 있을 부분의 머리 노드는 4이고, 이것보다 한 칸 앞의 노드인 3을 찾는 것이다.
        //굳이 한 칸 앞의 노드를 찾는 것은 temp.next = null연산으로 연결을 끊기 위함. 4, 123 이렇게 끊어버림.
        temp = head;
        for (int i = 0; i < length-rotatedLength-1; i++) {
            temp = temp.next;
        }
        ListNode rotatedListHead = temp.next;//회전되어 있을 부분의 머리 노드를 구함,
        temp.next = null;

        //회전되어 있을 부분이 꼬리 노드를 구한다.
        ListNode rotatedListTail = rotatedListHead;
        while (rotatedListTail.next != null) {
            rotatedListTail = rotatedListTail.next;
        }

        rotatedListTail.next = head;

        return rotatedListHead;
    }
}
