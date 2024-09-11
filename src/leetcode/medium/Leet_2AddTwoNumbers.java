package leetcode.medium;

public class Leet_2AddTwoNumbers {
    //문제의 설명에 따라 연결리스트의 노드를 간단하게 구현함.
    private static class ListNode{

        public static void main(String[] args) {

        }

        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode base = new ListNode();
            ListNode not_base = new ListNode();
            base.next = l1;
            not_base.next = l2;

            process(base, not_base, 0);

            return base.next;
        }

        private void process(ListNode base, ListNode not_base, int passToNext){
            if (base.next == null && not_base.next != null) {
                base.next = not_base.next;
                not_base.next = null;
            }

            if (base.next == null && not_base.next == null) {
                if (passToNext != 0) {
                    base.next = new ListNode(passToNext);
                }
                return;
            }

            int baseVal = base.next.val;
            int not_baseVal = 0;
            if (not_base.next != null) {
                not_baseVal = not_base.next.val;
            }
            int plusResult = baseVal + not_baseVal + passToNext;

            if (plusResult < 10) {
                base.next.val = plusResult;
                passToNext = 0;
            } else {
                base.next.val = plusResult - 10;
                passToNext = 1;
            }

            if (not_base.next != null) {
                process(base.next, not_base.next, passToNext);
            } else {
                process(base.next, not_base, passToNext);
            }
        }

        int val;
        ListNode next;

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
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
