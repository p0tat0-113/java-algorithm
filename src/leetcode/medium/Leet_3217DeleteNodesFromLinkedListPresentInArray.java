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
*/

public class Leet_3217DeleteNodesFromLinkedListPresentInArray {

    public static void main(String[] args) {
        Leet_3217DeleteNodesFromLinkedListPresentInArray leet3217 = new Leet_3217DeleteNodesFromLinkedListPresentInArray();
        System.out.println(leet3217.modifiedList(new int[]{1, 2, 3}, new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null)))))));
    }

    public ListNode modifiedList(int[] nums, ListNode head) {
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
            }

            //검사를 통과하지 못하면, 즉 중간에 nums와 다른 숫자가 섞여있으면 그냥 패스한다.

            //다음 검사를 위한 초기화 과정
            searchNode = searchNode.next;//searchNode한 칸 앞으로 전진
            currentSearchNode = searchNode;
            searchCount = 0;
        }

        return tempHeadNode.next;
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
