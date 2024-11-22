package leetcode.review.review;

import leetcode.review.ListNode;
import java.util.ArrayDeque;

public class Leet_143ReorderList {
    public static void main(String[] args) {
        Leet_143ReorderList leet = new Leet_143ReorderList();
        ListNode list1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, null))));
        ListNode list2 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null)))));

        leet.reorderList(list1);
        leet.reorderList(list2);
    }

    public void reorderList(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null && fast.next.next != null) {//<-이렇게 하면 아주 간단하게 삽입될 노드들의 바로 앞 노드를 얻을 수 있다.
            slow = slow.next;
            fast = fast.next.next;
        }

        //System.out.println(slow);

        ArrayDeque<ListNode> stack = new ArrayDeque<>();
        ListNode temp = slow.next;
        slow.next = null;

        while (temp != null) {
            ListNode temptemp = temp.next;
            temp.next = null;
            stack.push(temp);
            temp = temptemp;
        }

        System.out.println(stack);

        temp = head;
        while (!stack.isEmpty()) {
            ListNode gonnaInserted = stack.pop();
            gonnaInserted.next = temp.next;
            temp.next = gonnaInserted;

            temp = temp.next.next;
        }

        System.out.println(head);
    }
}
