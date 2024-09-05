package leetcode;

public class Leet_41MergeTwoSortedList {

    private static class ListNode{
        int val;
        ListNode next;

        public ListNode() {
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            ListNode node = this;
            StringBuilder sb = new StringBuilder();
            while (node != null) {
                sb.append(node.val);
                sb.append(" -> ");
                node = node.next;
            }

            return sb.toString();
        }
    }

    //오름차순으로 정렬해야 함. 병합 정렬의 병합 과정과 똑같다.
    private static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode tempHeadNode = new ListNode();//임시로 머리가 되어줄 노드, 이 뒤에 왼쪽과 오른쪽 리스트의 노드들을 붙인다.
        ListNode currentNode = tempHeadNode;//맨 뒤 노드의 참조값을 가리킨다.

        while (list1 != null && list2 != null) {
            if(list1.val <= list2.val) {
                currentNode.next = list1;
                currentNode = currentNode.next;

                list1 = list1.next;
            } else if (list2.val < list1.val) {
                currentNode.next = list2;
                currentNode = currentNode.next;

                list2 = list2.next;
            }
        }

        while (list1 != null) {
            currentNode.next = list1;
            currentNode = currentNode.next;

            list1 = list1.next;
        }

        while (list2 != null) {
            currentNode.next = list2;
            currentNode = currentNode.next;

            list2 = list2.next;
        }

        return tempHeadNode.next;
    }

    public static void main(String[] args) {
        ListNode list1 = new ListNode(1, new ListNode(2, new ListNode(4, null)));
        ListNode list2 = new ListNode(1, new ListNode(3, new ListNode(4, null)));

        System.out.println(mergeTwoLists(list1, list2));
    }
}
