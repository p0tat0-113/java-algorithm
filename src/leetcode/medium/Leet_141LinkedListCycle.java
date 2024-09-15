package leetcode.medium;

/*
연결리스트의 머리노드를 던져주면, 연걸리스트에 '사이클'이 존재하는지 파악하는 문제다.
282번 FindTheDuplicatedNumber, 142번 LinkedListCycle2문제와 연결됨.
한 번에 한 칸씩 움지이는 slow, 두 칸씩 움직이는 fast가 만나게 되면 사이클이 존재하는 것. 만나지 않고, fast가 null에 닿게 되면 사이클이 존재하지 않는 것이다.
*/

public class Leet_141LinkedListCycle {
    public static void main(String[] args) {

    }

    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null) {//fast가 연결리스트의 끝에 닿게 되면 사이클이 존재하지 않는 것. false를 반환한다.
            slow = slow.next;//slow는 한 칸씩 이동
            fast = fast.next.next;//fast는 두 칸씩 이동

            if (slow == fast) {//slow와 fast가 만나게 되면 사이클이 존재하는 것.
                return true;
            }
        }

        return false;
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
