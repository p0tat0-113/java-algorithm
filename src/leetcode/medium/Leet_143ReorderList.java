package leetcode.medium;

import leetcode.ListNode;
import java.util.ArrayDeque;

/*
https://imgur.com/a/XFVv8oG 나의 풀이 방식
*/

public class Leet_143ReorderList {
    public static void main(String[] args) {
        ListNode list1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4,null))));
        ListNode list2 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4,new ListNode(5, null)))));

        //우선 중간노드를 구하는 코드부터 테스트
        //getMidNode(list1); //3 -> 4
        //getMidNode(list2); //4 -> 5
        //getMidNode(new ListNode(1)); //null 이거 결과를 보니까 리스트의 길이가 1일 때는 바로 return 시켜야 할 듯

        reorderList(list1);
        reorderList(list2);
    }

    //맞긴 맞았는데 수행시간이 4ms로 좀 느리다. 대부분의 사람들이 2ms를 찍음...
    public static void reorderList(ListNode head) {
        if (head.next == null) {
            return;
        }

        ArrayDeque<ListNode> saveStack = new ArrayDeque<>();//Stack은 레거시라서 성능이 나쁘고, ArrayDeque를 쓰는게 좋다고 배웠었음.
        ListNode saveStartNode = getMidNode(head);//중간에 삽입될 노드들의 시작점을 구함.

        //중간에 삽입될 노드들을 스택에 저장, 뒤에서 스택에서 하나씩 꺼내서 중간에 삽입할 것이다.
        ListNode temp = saveStartNode;
        while (temp != null) {
            saveStack.push(temp);
            temp = temp.next;
        }
        //System.out.println(saveStack);

        temp = head;
        while (!saveStack.isEmpty()) {//saveStack이 빌 때까지 반복한다.
            ListNode savedNode = saveStack.pop();//꺼내서
            savedNode.next = temp.next;//삽입하고
            temp.next = savedNode;

            temp = temp.next.next;//건너뛰어서 다음 삽입위치로 넘어간다.
        }

        temp.next = null;//작업이 다 끝나긴 했지만 맨 끝 노드의 next속성 연결이 아직 살아있어서 사이클이 발생하기 때문에 이걸 끊어줘야 한다.

        //System.out.println(head);
    }

    private static ListNode getMidNode(ListNode head) {
        //연결리스트의 중간노드를 효율적을 찾는 전략. 이거 이름이 뭐더라...?
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        if (fast != null) {//리스트의 길이가 홀수인 경우 slow를 한 칸 더 앞으로 보냄.
            slow = slow.next;
        }

        return slow;
    }
}
