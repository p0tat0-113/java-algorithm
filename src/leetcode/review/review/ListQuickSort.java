public class ListQuickSort {
    public static void main(String[] args) {
        ListQuickSort leet = new ListQuickSort();

        ListNode root1 = new ListNode(2, new ListNode(4, new ListNode(5, new ListNode(3, new ListNode(1, null)))));
        leet.quickSort(root1, null);
        System.out.println(root1);

        ListNode root2 = new ListNode(2);
        leet.quickSort(root2, null);
        System.out.println(root2);

        ListNode root3 = new ListNode(2, new ListNode(2, new ListNode(2, new ListNode(2, new ListNode(2, null)))));
        leet.quickSort(root3, null);
        System.out.println(root3);
    }

    private void quickSort(ListNode head, ListNode tail){
        if (head != tail) {
            ListNode slow = head;
            ListNode fast = head;
            while (fast != tail && fast.next != tail) {
                slow = slow.next;
                fast = fast.next.next;
            }
            int temp = head.val;
            head.val = slow.val;
            slow.val = temp;

            ListNode pivot = partition(head, tail);

            quickSort(head, pivot);
            quickSort(pivot.next, tail);
        }
    }

    private ListNode partition(ListNode head, ListNode tail){
        int pivot = head.val;

        ListNode first = head;
        ListNode second = head.next;
        int count = 0;

        while (second != tail) {
            if (second.val < pivot || (count % 2 == 0 && second.val == pivot)) {
                first = first.next;

                int temp = first.val;
                first.val = second.val;
                second.val = temp;
            }
            second = second.next;
            count++;
        }

        head.val = first.val;
        first.val = pivot;

        return first;
    }
}
