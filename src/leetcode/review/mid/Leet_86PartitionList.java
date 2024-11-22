package leetcode.review.mid;

public class Leet_86PartitionList {
    public static void main(String[] args) {
        ListNode root = new ListNode(1, new ListNode(4, new ListNode(3, new ListNode(2, new ListNode(5, new ListNode(2))))));
        System.out.println(root);

        Leet_86PartitionList leet = new Leet_86PartitionList();
        System.out.println("leet.partition(root) = " + leet.partition(root, 3));
    }

    public ListNode partition(ListNode head, int x) {
        ListNode tempHead1 = new ListNode();
        ListNode tail1 = tempHead1;
        ListNode tempHead2 = new ListNode();
        ListNode tail2 = tempHead2;

        while (head != null) {
            if (head.val < x) {
                tail1.next = head;
                tail1 = tail1.next;

                head = head.next;
                tail1.next = null;//이 부분의 코드로 혹시 모를 사이클 발생을 방지한다.
            } else {
                tail2.next = head;
                tail2 = tail2.next;

                head = head.next;
                tail2.next = null;//이 부분의 코드로 혹시 모를 사이클 발생을 방지한다.
            }
        }

        tail1.next = tempHead2.next;
        return tempHead1.next;
    }
}
