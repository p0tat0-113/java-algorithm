package leetcode.review.review;

import leetcode.review.ListNode;

public class Leet_61RotateList {
    public static void main(String[] args) {
        Leet_61RotateList leet = new Leet_61RotateList();

        ListNode root1 = new ListNode(0, new ListNode(1, new ListNode(2, null)));
        System.out.println(leet.rotateRight(root1, 1));

        ListNode root2 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null)))));
        System.out.println(leet.rotateRight(root2, 2));
    }

    public ListNode rotateRight(ListNode head, int k) {
        ListNode temp = head;
        int length = 0;
        while (temp != null) {
            temp = temp.next;
            length++;
        }

        if (head == null || k == 0) {
            return head;
        }

        int remainder = k%length;

        if (remainder == 0) {
            return head;
        }

        ListNode rotated = head;
        for (int i = 0; i < length-remainder-1; i++) {
            rotated = rotated.next;
        }

        temp = rotated;
        rotated = rotated.next;
        temp.next = null;

        temp = rotated;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = head;

        return rotated;
    }
}
