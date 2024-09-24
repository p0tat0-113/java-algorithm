package leetcode;

//자주 쓰이는 ListNode를 아예 별개의 클래스로 분리함. 매번 복붙하지 말고, 임포트해서 쓰자.
public class ListNode {
    public int val;
    public ListNode next;

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
            if (node.next != null) {
                sb.append(" -> ");
            }
            node = node.next;
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new ListNode(1, new ListNode(2, new ListNode(3, null))));
    }
}
