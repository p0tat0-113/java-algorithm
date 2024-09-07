package leetcode.medium;

/*
Input: nums = [1,2,3], head = [1,2,3,4,5]
Output: [4,5]

Input: nums = [1], head = [1,2,1,2,1,2]
Output: [2,2,2]

Input: nums = [5], head = [1,2,3,4]
Output: [1,2,3,4]

head에서 nums를 모두 제거해야 하는 문제다.
nums의 요소는 모두 unique, 즉 중복되지 않았다고 한다.
head에는 nums에는 없는 숫자를 가진 최소 한 개의 노드를 가지고 있다고 한다.

우선은 https://imgur.com/a/dhds1pR 이렇게 계획을 짰다. 순차적으로 검사를 하고, 아니면 그 부분을 통째로 건너뛰는 식으로 최대한 효율성을 챙겨보려고 했다.
근데 변수가있네. nums는 연결리스트가 아니라 그냥 배열임..


계획한 대로 코드를 짰고, 맨 처음 테스트코드 3개는 잘 작동했음. 그런데 문제가 발생했다.
nums = [1,2,6] head = [9,6,9] 인 상황에서 [9,9]가 나와야 하는데, 내 코드는 [9,6,9]를 뱉었다. 내가 문제 조건을 처음부터 잘못이해하고 있었던 것...
잘 읽어보니 "removing all nodes from the linked list that have a value that exists in nums"라고 한다. 이거랑은 조금 다른 탐색 방법이 필요했던 것...

https://imgur.com/a/wJKmR1u 새롭게 알고리즘을 구성했다. 드디어 잘 작동된다!!!


근데 생각해보니까 내가 이걸 왜 이렇게 복잡하게 풀었지? 그냥 set에 없는 값을 가지고 있는 노드를 tempHeadNode뒤에 갖다 붙이면 되는거 아닌가...?
다시 풀어보자...

근데 이렇게 해도 여전히 느리네... 어떻게 풀던 O(n)일 수 밖에 없을 것 같은데...
*/

import java.util.Arrays;
import java.util.HashSet;

public class Leet_3217DeleteNodesFromLinkedListPresentInArray {

    public static void main(String[] args) {
        Leet_3217DeleteNodesFromLinkedListPresentInArray leet3217 = new Leet_3217DeleteNodesFromLinkedListPresentInArray();
        System.out.println(leet3217.modifiedList(new int[]{5}, new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null)))))));
    }

    public ListNode modifiedList(int[] nums, ListNode head) {
        HashSet<Integer> set = new HashSet<>(Arrays.stream(nums).boxed().toList());
        ListNode tempHeadNode = new ListNode();//임시로 머리노드가 되어줄 노드
        ListNode tempTailNode = tempHeadNode;//임시 리스트의 제일 끝 노드를 가리키는 변수

        ListNode node = head;
        while (node != null) {
            if (!set.contains(node.val)) {
                tempTailNode.next = node;
                tempTailNode = tempTailNode.next;
            }

            node = node.next;
        }

        tempTailNode.next = null;//뒤에 쓸데없이 붙어있을 수 있는, set에 없는 값을 가지고 있는 노드들을 털어버린다.

        return tempHeadNode.next;
    }

    /*public ListNode modifiedList(int[] nums, ListNode head) {
        HashSet<Integer> set = new HashSet<>(Arrays.stream(nums).boxed().toList());
        ListNode tempHeadNode = new ListNode();//임시로 머리노드가 되어줄 노드, 맨 앞노드를 포함해서 제거해야할 때 편하게 하기 위함이다.
        tempHeadNode.next = head;

        ListNode searchNode = tempHeadNode;//검사의 시작점이 되는 노드보다 한 칸 앞의 노드다.
        ListNode currentSearchNode = searchNode;//매 검사가 시작될 때는 searchNode와 같다. 이후 currentSearchNode = currentSearchNode.next 연산을 하면서 전진하게 될 것이다.

        while (searchNode != null) {
            if (currentSearchNode.next != null && set.contains(currentSearchNode.next.val)) {
                currentSearchNode = currentSearchNode.next;
            } else {
                searchNode.next = currentSearchNode.next;//currentSearchNode.next는 이미 set에 없는 값을 가지고 있는 노드로 판명되었다.
                searchNode = currentSearchNode.next;
                currentSearchNode = searchNode;
            }
        }

        return tempHeadNode.next;
    }*/

    /*public ListNode modifiedList(int[] nums, ListNode head) {
        ListNode tempHeadNode = new ListNode();//임시로 머리노드가 되어줄 노드, 맨 앞노드를 포함해서 제거해야할 때 편하게 하기 위함이다.
        tempHeadNode.next = head;

        ListNode searchNode = tempHeadNode;//검사의 시작점이 되는 노드보다 한 칸 앞의 노드다.
        ListNode currentSearchNode = searchNode;//매 검사가 시작될 때는 searchNode와 같다. 이후 currentSearchNode = currentSearchNode.next 연산을 하면서 전진하게 될 것이다.
        int searchCount = 0;//매 검사마다 지금이 몇번째 검사를 통과한 노드인지 체크해줄 겸 nums에 접근할 때 인덱스로 사용할 변수이다.

        while (searchNode != null) {

            //nums의 길이만큼 반복하면서 nums와 일치하는지 검사한다.
            for (int i = 0; i < nums.length; i++) {
                //처음에는 currentSearchNode.next != null && 이 부분이 없어서, 반복문 도중 currentSearchNode.next가 null이 되면서 nullPointerException이 발생했다.
                if (currentSearchNode.next != null && currentSearchNode.next.val == nums[searchCount]) {
                    currentSearchNode = currentSearchNode.next;
                    searchCount++;
                } else {
                    break;
                }
            }

            if (searchCount == nums.length) {//검사를 통과했음, nums와 같은 부분을 찾았다는 것. 해당 부분을 삭제한다.
                searchNode.next = currentSearchNode.next;
            } else {//검사를 통과하지 못하면, 즉 중간에 nums와 다른 숫자가 섞여있으면 그냥 그 부분을 통째로 패스하고, 그 다음노드부터 다시 검사한다.
                searchNode = currentSearchNode.next;
            }

            //다음 검사를 위한 초기화 과정
            currentSearchNode = searchNode;
            searchCount = 0;
        }

        return tempHeadNode.next;
    }*/

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
