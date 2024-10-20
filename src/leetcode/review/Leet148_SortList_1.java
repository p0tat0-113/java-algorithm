package leetcode.review;

public class Leet148_SortList_1 {
    public static void main(String[] args) {
        Leet148_SortList_1 leet = new Leet148_SortList_1();
        ListNode root = new ListNode(4, new ListNode(2, new ListNode(3, new ListNode(1, null))));
        System.out.println(leet.sortList(root));
    }

    public ListNode sortList(ListNode head) {
        quickSort(head, null);
        return head;
    }

    private void quickSort(ListNode head, ListNode tail){
        if (head != tail) {
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
            if (second.val < pivot || (second.val == pivot && count % 2 == 0)) {//중복된 요소가 많은 경우 효율적으로 처리하기 위한 코드
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
