package leetcode.review;

public class Leet_147InsertionSortList {
    public static void main(String[] args) {
        //ListNode root = new ListNode(4, new ListNode(2, new ListNode(1, new ListNode(3,null))));
        ListNode root = new ListNode(-1, new ListNode(5, new ListNode(3, new ListNode(4,new ListNode(0, null)))));
        Leet_147InsertionSortList leet = new Leet_147InsertionSortList();
        System.out.println(leet.insertionSortList(root));
    }

    public ListNode insertionSortList(ListNode head) {
        if (head.next == null) {
            return head;
        }

        ListNode tempHeadNode = new ListNode();
        tempHeadNode.next = head;
        head = head.next;
        tempHeadNode.next.next = null;
        ListNode curr = tempHeadNode;


        while(head != null) {
            ListNode temp = head;
            head = head.next;
            boolean isInserted = false;
            curr = tempHeadNode;

            while(curr.next != null) {
                if (temp.val < curr.next.val) {
                    temp.next = curr.next;
                    curr.next = temp;
                    isInserted = true;
                    break;
                }
                curr = curr.next;
            }
            if (!isInserted) {
                curr.next = temp;
                curr.next.next = null;
            }
        }

        return tempHeadNode.next;
    }
}
