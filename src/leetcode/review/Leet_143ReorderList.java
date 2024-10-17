package leetcode.review;

import java.util.ArrayDeque;

public class Leet_143ReorderList {
    public static void main(String[] args) {
        ListNode root1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, null))));
        ListNode root2 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5,null)))));

        ListNode slow = root1;
        ListNode fast = slow;
        while(fast != null && fast.next != null && fast.next.next != null) {//이렇게 하면 중간노드보다 한 칸 앞의 노드?를 얻을 수가 있다. 연결리스트 병합정렬을 다시 풀어보면서 고안해냈음.
            fast = fast.next.next;
            slow = slow.next;
        }
        System.out.println(slow);

        Leet_143ReorderList leet = new Leet_143ReorderList();

        leet.reorderList(root2);
        System.out.println(root2);

        leet.reorderList(root1);
        System.out.println(root1);

        ListNode root3 = new ListNode(1,new ListNode(2,null));
        leet.reorderList(root3);
        System.out.println(root3);
    }

    public void reorderList(ListNode head) {
        ListNode slow = head;
        ListNode fast = slow;
        while(fast != null && fast.next != null && fast.next.next != null) {//이렇게 하면 중간노드보다 한 칸 앞의 노드?를 얻을 수가 있다.
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode tempSlow = slow.next;//끼워넣어져야할 노드들만 있는 리스트
        //System.out.println(tempSlow);

        ArrayDeque<ListNode> stack = new ArrayDeque<>();
        while (tempSlow != null) {
            ListNode temp = tempSlow;
            tempSlow = tempSlow.next;
            temp.next = null;//각 노드의 연결을 다 끊어서 완전히 독립적인 개별적이 노드로 만들어서 스택에 집어넣는다. 혹시 모를 사이클을 피함.

            stack.push(temp);
        }

        slow.next = null;//리스트를 완전히 분리,
        //System.out.println(head);

        ListNode tempHead = head;
        while (!stack.isEmpty()) {//스택이 텅 빌때까지
            //중간에 끼워넣는다.
            ListNode popped = stack.pop();
            popped.next = tempHead.next;
            tempHead.next = popped;
            tempHead = tempHead.next.next;
        }

        //이걸 굳이 스택을 쓰지 않아도 될 것 같기도 하고....
    }
}
