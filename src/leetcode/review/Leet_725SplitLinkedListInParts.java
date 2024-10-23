package leetcode.review;

import java.util.Arrays;
import java.util.List;

public class Leet_725SplitLinkedListInParts {
    public static void main(String[] args) {
        Leet_725SplitLinkedListInParts leet = new Leet_725SplitLinkedListInParts();

        ListNode root1 = new ListNode(1, new ListNode(2, new ListNode(3, null)));
        System.out.println(Arrays.toString(leet.splitListToParts(root1, 5)));

        ListNode root2 = new ListNode(1, new ListNode(2,
                new ListNode(3, new ListNode(4,
                        new ListNode(5, new ListNode(6,
                                new ListNode(7, new ListNode(8,
                                        new ListNode(9, new ListNode(10, null))))))))));
        System.out.println(Arrays.toString(leet.splitListToParts(root2, 3)));

    }

    //연결리스트를 k개로 최대한 균등하게 분할해야 하는 문제다.
    public ListNode[] splitListToParts(ListNode head, int k) {
        int jump = 0;
        int remainder = 0;

        int length = 0;
        ListNode temp = head;
        while (temp != null) {
            temp = temp.next;
            length++;
        }

        if (length<=k) {
            jump = 1;
            remainder = 0;
        } else {
            jump = length/k;
            remainder = length%k;
        }

        ListNode[] result = new ListNode[k];
        int resultCount = 0;
        ListNode curr = head;

        while (head != null) {
            for (int i = 0; i < jump-1; i++) {
                curr = curr.next;
            }

            if (remainder > 0) {
                curr = curr.next;
                remainder--;
            }

            temp = curr;
            curr = curr.next;//curr은 다음으로 이동. 끊어냈을 때 오른쪽 리스트의 머리 부분
            temp.next = null;//연결을 끊어낸다.

            result[resultCount++] = head;
            head = curr;
        }

        return result;
    }
}
