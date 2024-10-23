public class InsertionSortLIst {
    public static void main(String[] args) {
        InsertionSortLIst leet = new InsertionSortLIst();

        ListNode list1 = new ListNode(2, new ListNode(4, new ListNode(5, new ListNode(3, new ListNode(1, null)))));
        System.out.println(leet.insertionSortList(list1));

    }

    public ListNode insertionSortList(ListNode head) {
        ListNode tempHead = new ListNode(0);
        ListNode curr = tempHead;

        tempHead.next = head;
        head = head.next;
        tempHead.next.next = null;

        while (head != null) {
            curr = tempHead;
            ListNode temp = head;
            head = head.next;
            temp.next = null;
            boolean isInserted = false;

            while (curr.next != null) {
                if (temp.val < curr.next.val) {
                    temp.next = curr.next;
                    curr.next = temp;
                    break;
                }
                curr = curr.next;
            }

            if (!isInserted) {
                curr.next = temp;
            }
        }

        return tempHead.next;
    }
}
