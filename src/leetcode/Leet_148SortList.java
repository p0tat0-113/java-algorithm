package leetcode;

public class Leet_148SortList {

    public static void main(String[] args) {
        /*ListNode root = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6,null))))));
        System.out.println(getMidNode(root));*/

        ListNode root = new ListNode(4, new ListNode(2, new ListNode(1, new ListNode(3, null))));
        System.out.println(sortList(root));
    }

    public static ListNode sortList(ListNode head) {
        if (head == null) {
            return head;
        }

        return mergeSort(head);
    }

    //노드 간의 연결구조를 사용한다는 점을 제외하면 일반적인 병합정렬과 구조자체는 비슷하다.

    private static ListNode mergeSort(ListNode headNode){
        if (headNode.next != null) {
            ListNode midNode = getMidNode(headNode);//중간 노드 얻어옴.

            ListNode rightHeadNode = midNode.next;//midNode보다 한 칸 뒤의 노드, 분할된 오른쪽 노드의 머리다.
            midNode.next = null;//midNode의 연결을 끊어버림. headNode를 머리로 둔 리스트와 rightHeadNode를 머리로 둔 리스트 둘로 쪼개짐.

            //밑바닥에 도달할 때까지 재귀적으로 쪼갬.
            ListNode leftListNode = mergeSort(headNode);
            ListNode rightListNode = mergeSort(rightHeadNode);

            return merge(leftListNode, rightListNode);//정렬된 두 리스트를 인수로 받아서 합치고, 그 머리노드의 참조값을 반환한다.
        }

        return headNode;
    }
    private static ListNode merge(ListNode leftListNode, ListNode rightListNode) {
        ListNode tempHeadNode = new ListNode();//임시로 머리가 되어줄 노드, tempHeadNode에 왼쪽과 오른쪽 연결리스트의 각 노드들을 붙일 것이다.
        ListNode currentNode = tempHeadNode;//tempHeadNode로 시작되는 연결리스트의 최후미 노드의 참조값을 저장함.

        while (leftListNode != null && rightListNode != null) {
            if (leftListNode.val < rightListNode.val) {
                currentNode.next = leftListNode;
                currentNode = currentNode.next;//currentNode는 항상 제일 끝 노드의 참조값을 가리켜야 한다.

                leftListNode = leftListNode.next;

            } else if (rightListNode.val < leftListNode.val) {
                currentNode.next = rightListNode;
                currentNode = currentNode.next;

                rightListNode = rightListNode.next;
            }
        }

        while (leftListNode != null) {
            currentNode.next = leftListNode;
            currentNode = currentNode.next;

            leftListNode = leftListNode.next;
        }

        while (rightListNode != null) {
            currentNode.next = rightListNode;
            currentNode = currentNode.next;

            rightListNode = rightListNode.next;
        }

        return tempHeadNode.next;
    }

    //중간노드를 구하는 메서드, 정확히는 중간노드보다 한칸 앞의 노드의 참조값을 반환한다.
    //이 메서드에서 반환한 중간노드의 참조값이 연결리스트를 반으로 자르는 기준점이 된다.
    //ListNode의 내부 구현이 너무 빈약해서, 중간 노드를 얻기 위해 이런 힘든 방법을 써야한다.
    private static ListNode getMidNode(ListNode head){
        //두칸씩 전진하는 tempNode가 null이 되게 되면, midNode는 그 연결 리스트의 중간 노드를 가리키게 되어있다.
        ListNode midNode = head;
        ListNode one = head;//한칸씩 전진
        ListNode two = head;//두칸씩 전진

        while (two != null) {
            two = two.next;
            if (two == null) {
                break;
            }
            two = two.next;

            midNode = one;
            one = one.next;
        }
        return midNode;
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

    //처음에는 정말 단순하게 인덱스로 연결리스트의 각 노드에 접근하는 방식으로 하려고 했으나, 연결리스트 특성 상 배열의 길이가 길어질수록 너무 느릴 것 같아 포기.
    /*private static int getLength(ListNode head) {
        int length = 0;
        ListNode tempNode = head;
        while (tempNode != null) {
            length++;
            tempNode = tempNode.nextNode;
        }

        return length;
    }*/

    /*private static ListNode getNode(ListNode root, int index) {
        ListNode node = root;
        for (int i = 0; i < index; i++) {
            node = node.nextNode;
        }
        return node;
    }*/
}
