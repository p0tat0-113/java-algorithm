package leetcode.medium;

/*
또 연결리스트를 정렬하는 문제다... 그런데 이번에는 삽입정렬을 구현하라고 함....

배열을 가지고 삽입 정렬을 할 때와는 다른 접근 방식이 필요해보임.
배열로 삽입 정렬을 할 때는 부분 배열의 뒤에서부터 값을 비교하면서 옮겼지만,
연결리스트로 구현하려면, 부분배열의 앞에서부터 값을 비교를 하고, 맞는 위치에 삽입을 해야할 것 같다.

노드의 갯수는 최대 5000개, 값의 범위는 -5000~5000이라고 한다. 그리고 오름차순으로 정렬해야 한다.
*/

public class Leet_147InsertionSortLIst {

    public static void main(String[] args) {
        Leet_147InsertionSortLIst leet147InsertionSortLIst = new Leet_147InsertionSortLIst();

        ListNode root = new ListNode(4, new ListNode(2, new ListNode(1, new ListNode(3, null))));
        System.out.println(leet147InsertionSortLIst.insertionSortList(root));
    }

    public ListNode insertionSortList(ListNode head) {
        ListNode tempHeadNode = new ListNode(-10000, head);//임시로 머리가 되어줄 노드, 맨 앞에 삽입해야 하는 경우를 위해서 이렇게 했다.일부로 값을 -10000으로 해서 얘보다 작은 노드가 나올 수 없게 함.
        ListNode currentNode = tempHeadNode;//node변수에 담겨있는 노드와 비교되는 노드를 가리키는 변수, 맨 앞에서부터 쭉 훑게 될 것이다. currentNode는 비교하는 노드보다 한 칸 앞의 노드를 가리킨다. 그래야 필요할 때 node를 그 앞에 삽입할 수 있기 때문이다.
        ListNode tailOfSmallList = head;//부분 리스트의 맨 끝 노드를 가리키는 변수

        ListNode node = tailOfSmallList.next;//비교하고 삽입될 노드, 부분 배열의 맨 끝에 노드에 바로 붙어있는 노드다.

        while (node != null) {
            //이 부분 하나 바꿔준걸로 리트코드 상에서 실행속도가 꽤 빨라짐.
            while (currentNode != tailOfSmallList) {//개선사항 currentNode != node -> currentNode != tailOfSmallList currentNode가 tailOfSmallList를 가리키게 되면 currentNode.next는 node를 가리키게 되기 때문에 아무런 의미가 없음.
                if (currentNode.next.val > node.val) {//node가 중간에 들어갈 자리를 찾았으면
                    tailOfSmallList.next = node.next;//tailOfSmallList -> node -> node.next 연결을 tailOfSmallList -> node.next 이렇게 만든다. node를 깍두기로 만들어버림ㅠ
                    //node.next = null; 바로 뒤에서 node.next = currentNode.next 연산을 하기 때문에 무의미하다.

                    //node를 맞는 위치에 중간에 삽입함.
                    node.next = currentNode.next;
                    currentNode.next = node;

                    break;
                }

                currentNode = currentNode.next;
            }

            if (tailOfSmallList.next == node) {//노드가 그냥 제자리에 그대로 있는 경우 tailOfSmallList를 node로 업데이트 해준다. node가 중간에 삽입됐다면 tailOfSmallList를 그대로 유지하면 된다.
                tailOfSmallList = node;
            }

            node = tailOfSmallList.next;//다음으로 비교하고 삽입될 노드 설정
            currentNode = tempHeadNode;//currentNode 초기화
        }

        //처음에 return head라고 해서 4 -> 만 출력돼서 좀 헤맸음. 정렬 과정 중에 head가 가리키는 노드의 위치가 계속 바뀌기 때문에 tempHeadNode.next를 반환해야 한다.
        return tempHeadNode.next;
    }

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
