
public class ListMergeSort {
    public static void main(String[] args) {
        ListNode root1 = new ListNode(3, new ListNode(2, null));
        ListNode root2 = new ListNode(3, new ListNode(2, new ListNode(5, new ListNode(4, new ListNode(1)))));

        ListMergeSort leet = new ListMergeSort();
        System.out.println(leet.mergeSort(root1));
        System.out.println(leet.mergeSort(root2));
    }

    private ListNode mergeSort(ListNode root){
        if (root.next != null) {
            ListNode slow = root;
            ListNode fast = root;
            while (fast != null && fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }

            ListNode left = root;
            ListNode right = slow.next;
            slow.next = null;

            left = mergeSort(left);
            right = mergeSort(right);

            return merge(left, right);
        }
        return root;
    }

    private ListNode merge(ListNode left, ListNode right){
        ListNode tempHeadNode = new ListNode(0);
        ListNode curr = tempHeadNode;

        while (left != null && right != null) {
            if (left.val < right.val) {
                curr.next = left;

                curr = curr.next;
                left = left.next;
            } else {
                curr.next = right;

                curr = curr.next;
                right = right.next;
            }
        }

        if (left != null) {
            curr.next = left;
        }
        if (right != null) {
            curr.next = right;
        }

        return tempHeadNode.next;
    }
}
