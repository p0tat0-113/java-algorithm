package leetcode.review;

public class Leet_147InsertionSortList {
    public static void main(String[] args) {
        //ListNode root = new ListNode(4, new ListNode(2, new ListNode(1, new ListNode(3,null))));
        ListNode root = new ListNode(-1, new ListNode(5, new ListNode(3, new ListNode(4,new ListNode(0, null)))));
        Leet_147InsertionSortList leet = new Leet_147InsertionSortList();
        System.out.println(leet.insertionSortList(root));
    }

    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode tempHeadNode = new ListNode();
        ListNode curr = tempHeadNode;
        tempHeadNode.next = head;

        head = head.next;
        tempHeadNode.next.next = null;//이미 정렬된 부분과 정렬되지 않은 부분을 완전히 분리

        while (head != null) {
            ListNode temp = head;
            head = head.next;
            curr = tempHeadNode;
            boolean isInserted = false;

            while (curr.next != null) {
                if (temp.val < curr.next.val) {
                    isInserted = true;
                    temp.next = curr.next;
                    curr.next = temp;
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

    /*public ListNode insertionSortList(ListNode head) {
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
            curr = tempHeadNode;
            head = head.next;
            boolean isInserted = false;

            while(curr.next != null) {
                if (temp.val < curr.next.val) {
                    temp.next = curr.next;
                    curr.next = temp;
                    isInserted = true;
                    break;
                }
                curr = curr.next;
            }
            if (!isInserted) {//삽입되지 않았다면 그냥 맨 뒤에 갖다 붙임.
                curr.next = temp;
                curr.next.next = null;//임시리스트의 맨 끝은 항상 null로 만들어서 깔끔하게 해준다. 이래야 사이클이 안생기고 뒤탈이 없음.
            }
        }

        return tempHeadNode.next;
    }*/
}
