package leetcode.medium;

/*
easy난이도인 141번 LinkedListCycle문제에서 더 발전한 문제다. 이제는 사이클을 탐지하는 것 뿐만 아니라 사이클의 시작점을 찾아야 함. 사이클이 존재하기 않으면 null을 반환하라고 함.
플로이드의 토끼와 거북이 알고리즘을 적용해야 한다.
287번 문제 FindDuplicatedNumber문제가 이 문제를 그대로 배열로 옮겨서 한 번 꼬아놓은 문제다. 사이클을 일으키는 노드는 next속성으로, 이미 다른 노드가 next로 가지고 있던 참조값을 중복되게 가지고 있음.
아무튼 두개의 서로 다른 노드가 똑같은 노드를 가리키고 있다는 것이다.
*/

import leetcode.easy.Leet_141LinkedListCycle;

public class Leet_142LinkedListCycle2 {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null) {//fast가 연결리스트의 끝에 닿게 되면 사이클이 존재하지 않는 것. null을 반환한다.
            slow = slow.next;//slow는 한 칸씩 이동
            fast = fast.next.next;//fast는 두 칸씩 이동

            if (slow == fast) {//slow와 fast가 만나게 되면 사이클이 존재하는 것.
                // 이제 사이클의 시작점을 찾아야 한다.
                while(head != fast) {
                    head = head.next;
                    fast = fast.next;
                }
                return slow;
            }
        }

        return null;
    }

    //문제의 설명에 따라 연결리스트의 노드를 간단하게 구현함.
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
}
