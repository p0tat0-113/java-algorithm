package leetcode.review;

public class ListNode {
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
        ListNode temp = this;
        StringBuilder stringBuilder = new StringBuilder();
        while(temp != null) {
            stringBuilder.append(temp.val);
            stringBuilder.append(" -> ");
            temp = temp.next;
        }
        return stringBuilder.toString();
    }
}
