package leetcode.medium;

import java.util.Arrays;

/*
https://imgur.com/a/GryfP1y
2ms로 풀기는 풀었는데, 어떻게 해야 이걸 1ms로 줄일 수 있을지는 잘 모르겠네...
*/

public class Leet_725SplitLinkedListInParts {
    public static void main(String[] args) {
        ListNode root = new ListNode(1, new ListNode(2, new ListNode(3, null)));
        Leet_725SplitLinkedListInParts leet = new Leet_725SplitLinkedListInParts();
        System.out.println(Arrays.toString(leet.splitListToParts(root, 5)));
    }

    //input: [0,1,2,3,4] k = 3 일 때 내 코드는 [[0,1,2],[3],[4]]가 나오지만, [[0,1],[2,3],[4]]가 나와야 한다고 함. 나머지가 2이상일 때 첫 부분에 몰빵하지 말고, 균등하게 나눠야 한다는 것.
    public ListNode[] splitListToParts(ListNode head, int k) {

        ListNode[] result = new ListNode[k];//결과 배열을 생성하고, 요소를 모두 null로 초기화 함.
        Arrays.setAll(result, i -> null);

        if (head == null) {//만약 head가 null이면 그대로 반환함.
            return result;
        }

        //먼저 리스트를 한 번 순회하면서 리스트의 길이를 구한다.
        int length = 0;
        ListNode tempNode = head;
        while (tempNode != null) {
            length++;
            tempNode = tempNode.next;
        }

        //만약 length % k == length, 즉 length가 k보다 작으면 k를 length로 바꿔버린다.
        if (length % k == length) {
            k = length;
        }

        ListNode tailNode = head;//부분 리스트의 맨 끝 노드를 가리키는 변수
        int rest = length%k;//length를 k로 나눈 나머지다.

        for (int i = 0; i < k; i++) {
            result[i] = head;

            for (int j = 0; j < length/k-1; j++) {//tailNode가 부분 리스트의 맨 끝 노드에 위치하게 하기 위해, length/k보다 하나 적게 움직이게 한다.
                tailNode = tailNode.next;
            }

            if (rest > 0) {//tailNode가 한 칸 씩 더 움직이게 한다. 앞 쪽 부분리스트부터 균등하게 length/k 보다 하나씩 더 길게 함.
                tailNode = tailNode.next;
                rest--;
            }

            head = tailNode.next;//head는 다음 부분리스트의 머리가 되게 함.
            tailNode.next = null;//앞쪽 부분리스트와 뒤쪽 부분리스트의 연결을 해제함.
            tailNode = head;//tailNode를 다시 head와 같은 위치로 초기화.
        }

        return result;
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
