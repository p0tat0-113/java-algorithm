package leetcode.review.mid;

public class Leet148_SortList {
    public static void main(String[] args) {
        Leet148_SortList leet = new Leet148_SortList();
        ListNode root = new ListNode(4, new ListNode(2, new ListNode(1, new ListNode(3, null))));
        System.out.println("leet.mergeSort(root) = " + leet.mergeSort(root));
    }

    public ListNode sortList(ListNode head) {
        return mergeSort(head);
    }

    private ListNode mergeSort(ListNode head){//tail은 맨 끝보다 하나 뒤
        if (head.next != null) {
            //중간 노드를 얻는다.
            ListNode temp = head;
            ListNode mid = temp;
            while(temp != null && temp.next != null && temp.next.next != null) {//이렇게 && temp.next.next != null 이걸 하나 더 붙여서 중간 노드보다 하나 앞의 노드를 얻어낸다! 이게 좀 중요함.
                temp = temp.next.next;
                mid = mid.next;
            }

            //중간노드를 기준으로 리스트를 끊어준다.
            ListNode right =  mid.next;
            mid.next = null;
            ListNode left = head;

            //중간노드를 기준으로 분할 ex) [1,2]
            left = mergeSort(left);
            right = mergeSort(right);

            return merge(left, right);
        }
        return head;
    }

    private ListNode merge(ListNode left, ListNode right){
        ListNode tempHeadNode = new ListNode();
        ListNode curr = tempHeadNode;

        while(left != null && right != null) {
            if (left.val < right.val) {
                curr.next = left;

                left = left.next;
                curr = curr.next;
            } else {
                curr.next = right;

                right = right.next;
                curr = curr.next;
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
