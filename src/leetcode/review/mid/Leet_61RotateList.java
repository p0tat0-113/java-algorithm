package leetcode.review.mid;

public class Leet_61RotateList {
    public static void main(String[] args) {
        Leet_61RotateList leet = new Leet_61RotateList();

        ListNode root1 = new ListNode(0, new ListNode(1, new ListNode(2)));
        System.out.println("leet.rotateRight(root1, 1) = " + leet.rotateRight(root1, 1));
        ListNode root2 = new ListNode(0, new ListNode(1, new ListNode(2)));
        System.out.println("leet.rotateRight(root1, 2) = " + leet.rotateRight(root2, 2));
    }

    //연결리스트를 회전시켜야 하는 문제다.
    public ListNode rotateRight(ListNode head, int k) {
        //먼저 리스트의 길이를 얻어낸다.
        int length = 0;
        ListNode temp = head;
        while (temp != null) {
            length++;
            temp = temp.next;
        }

        if (head == null || head.next == null || k % length == 0) {//리스트의 길이가 0,1 혹은 회전시킬 필요가 없는 경우 그대로 리턴시킨다.
            return head;
        }

        //똑 떼어내야할 부분보다 한 칸 앞의 노드를 찾는다.
        temp = head;
        for (int i = 0; i < length - (k % length) - 1; i++) {
            temp = temp.next;
        }

        ListNode rotated = temp.next;//회전되어 있는 부분의 머리 rotated
        temp.next = null;

        /*System.out.println(rotated);
        System.out.println(head);
        System.out.println("-------------");*/

        temp = rotated;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = head;

        return rotated;
    }
}
