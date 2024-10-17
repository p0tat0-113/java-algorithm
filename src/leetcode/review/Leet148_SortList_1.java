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
        if (head != tail) {//리스트의 길이가 2이상이면 재귀호출, 처음에 head.next = null이딴식으로 해서 제대로 안굴러감.
            ListNode slow = head;
            ListNode fast = head;
            while(fast != tail && fast.next != tail) {//여기도 처음에 fast != null이딴식으로 해서 제대로 안굴러감.
                fast = fast.next.next;
                slow = slow.next;
            }
            int temp = slow.val;
            slow.val = head.val;
            head.val = temp;

            ListNode mid = partition(head, tail);

            quickSort(head, mid);
            quickSort(mid.next, tail);//이 부분도 처음에 quickSort(mid, tail); 이렇게 해서 제대로 분할이 안됨.
        }
    }

    private ListNode partition(ListNode head, ListNode tail){
        int pivot = head.val;
        ListNode first = head;
        ListNode second = head.next;
        int count = 0;

        //[3,4,1,2]
        while(second != tail) {
            if (second.val < pivot) {
                first = first.next;
                int temp = first.val;
                first.val = second.val;
                second.val = temp;
            } else if (second.val == pivot && count % 2 == 0) {
                first = first.next;
                int temp = first.val;
                first.val = second.val;
                second.val = temp;
            }
            second = second.next;
            count++;
        }

        //[1,2,3,4,5]
        head.val = first.val;
        first.val = pivot;

        return first;
    }
}
