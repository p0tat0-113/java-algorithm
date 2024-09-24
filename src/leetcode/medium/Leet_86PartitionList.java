package leetcode.medium;

/*
연결리스트를 다루는 문제다.
연결리스트가 주어지고, 숫자 x가 주어진다.
x보다 작은 숫자들은 모두 x보다 크거나 같은 숫자들의 앞에 오게 해야한다. 그런데 이때 원래의 상대적인 순서를 그대로 유지해야 한다.
단순히 정렬때린다고 되는게 아니라는 얘기임.'

어떻게 풀어야 되나 고민해봤는데, 이 문제도 역시 임시머리노드를 이용하면 어렵지 않게 풀릴 것 같다.
임시머리노드를 2개 만든다. 하나는 x보다 작은 노드들을 갖다 붙이고, 다른 하나는 x보다 크거나 같은 노드들을 갖다 붙인다.
그리고 마지막에 앞의 리스트 뒤에 뒤의 리스트를 그냥 갖다 붙이면 됨. 이렇게 하면 상대적인 순서는 그대로 유지되면서 규칙에 맞게 분할된다.

이렇게 풀었더니 0ms로 100%다 재꼈음. 간단한 문제였다.
*/

import leetcode.ListNode;

public class Leet_86PartitionList {
    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(4, new ListNode(3, new ListNode(2, new ListNode(5, new ListNode(2, null))))));
        System.out.println(partition(head, 3));
    }

    public static ListNode partition(ListNode head, int x) {
        ListNode lessNumbersHead = new ListNode();
        ListNode lessNumbersTail = lessNumbersHead;
        ListNode greaterNumbersHead = new ListNode();
        ListNode greaterNumbersTail = greaterNumbersHead;

        while(head != null) {
            if (head.val < x) {
                lessNumbersTail.next = head;
                lessNumbersTail = lessNumbersTail.next;
            } else {
                greaterNumbersTail.next = head;
                greaterNumbersTail = greaterNumbersTail.next;
            }
            head = head.next;
        }

        greaterNumbersTail.next = null;//이 부분이 빠져서 리스트에서 사이클이 발생했음.
        lessNumbersTail.next = greaterNumbersHead.next;
        return lessNumbersHead.next;
    }
}
