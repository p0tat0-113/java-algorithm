package leetcode.medium;

import leetcode.ListNode;

/*
과제로 이 문제를 다시 받았댜. 이번에는 반드시 퀵소트로 구현해야 한다.
저번에는 기준원소를 중심으로 리스트를 분할하는 과정에서 단방향 연결리스트라는 제약 때문에 좀 어려움이 있었지만, 이번에는 퀵소트 방식으로 꼭 풀어보자.
분할과정에서 책에 나오던 것과 같은 방식을 쓰면 가능할 것 같기도 함. 그 방식에서는 1구역의 끝은 가리키는 변수와 2구역의 시작을 가리키는 변수 둘 다 오른쪽으로만 움직이니까.
*/

public class Leet_148SortList_1 {

    public static void main(String[] args) {
        //ListNode root = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6,null))))));
        //System.out.println(getMidNode(root));

        ListNode root = new ListNode(4, new ListNode(2, new ListNode(1, new ListNode(3, null))));
        System.out.println(sortList(root));
    }

    //정렬된 리스트가 들어오는 케이스에서 time limit exceeded가 발생함. 퀵 정렬은 정렬된 배열이 들어오는 경우 0:n으로 분할되면서 O(n^2)의 시간복잡도가 나온다.
    //이를 해결하기 위해서 배열을 퀵소트로 정렬할 때는 pivot이 될 수를 랜덤하게 고르는 방식을 썼었는데, 이걸 연결리스트에도 적용하려면 어떻게 해야하지?
    //정렬된 배열이 들어오는 경우 카운터로 중간에 있는 노드가 pivot이 되게 할까? <- 이렇게 하니까 풀리긴 풀리는데 381ms가 나옴....
    //혹시 모든 숫자가 중복된 케이스도 처리해줘야 하는걸까? <- 이 예상이 적중함. 이 케이스까지 처리해주니까 실행시간이 10ms로 즐어들었다.
    public static ListNode sortList(ListNode head) {
        quickSort(head, null);
        return head;
    }

    private static void quickSort(ListNode head, ListNode tail) {
        if (head != tail) {

            //중간에 있는 노드를 찾아서 pivot으로 사용한다. 정렬된 리스트가 들어오는 경우에 대한 카운터
            ListNode slow = head;
            ListNode fast = head;
            while(fast != tail && fast.next != tail) {
                fast = fast.next.next;
                slow = slow.next;
            }
            int temp = head.val;
            head.val = slow.val;
            slow.val = temp;

            ListNode pivot = partition(head, tail);
            quickSort(head, pivot);
            quickSort(pivot.next, tail);//pivot.next가 아니라 pivot을 넣어서 계속 스택오버플로우가 발생하고 있었다. 능지이슈가 심각함.
        }
    }

    private static ListNode partition(ListNode head, ListNode tail){
        ListNode pivot = head;
        ListNode low = head;//1구역의 맨 끝 노드
        ListNode high = head.next;//2구역의 맨 앞 노드

        int count = 0;

        while (high != tail) {//high가 부분리스트의 끝에 닿을 때까지 반복
            count++;
            if (high.val > pivot.val) {
                high = high.next;
                continue;
            } else if (high.val == pivot.val) {
                //리스트에 중복된 숫자들이 많은 경우에 대한 카운터이다. 중복된 숫자인 경우 확률적으로 양쪽으로 반반씩 나눠서 분할을 하게 된다.
                if (count % 2 == 0) {
                    high = high.next;
                    continue;
                }
            }
            //high가 가리키는 수가 pivot 보다 작은 경우 low = low.next하고, low <-> high교환
            low = low.next;

            int temp = low.val;
            low.val = high.val;
            high.val = temp;

            high = high.next;
        }

        //pivot과 1구역의 맨 끝 노드를 교환해준다.
        int temp = pivot.val;
        pivot.val = low.val;
        low.val = temp;

        return low;
    }
}
