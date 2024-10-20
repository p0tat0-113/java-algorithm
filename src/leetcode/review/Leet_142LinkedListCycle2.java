package leetcode.review;

public class Leet_142LinkedListCycle2 {
    public static void main(String[] args) {
        ListNode root1 = new ListNode(3);
        root1.next = new ListNode(2);
        root1.next.next = new ListNode(0);
        root1.next.next.next = new ListNode(-4, root1.next);

        ListNode root2 = new ListNode(1);
        root2.next = new ListNode(2, root2);

        ListNode root3 = new ListNode(1);

        Leet_142LinkedListCycle2 leet = new Leet_142LinkedListCycle2();

        System.out.println(leet.detectCycle(root1).val);
        System.out.println(leet.detectCycle(root2).val);
        System.out.println(leet.detectCycle(root3));
    }

    //연결리스트에서 사이클을 있는지 탐지하고, 사이클이 존재한다면 사이클이 시작된 노드를, 사이클이 없다면 null을 반환해야 한다.
    //플로이드의 토끼와 거북이 알고리즘을 사용하면 쉽게 풀 수 있다.
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                slow = head;

                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }

        return null;
    }
}
