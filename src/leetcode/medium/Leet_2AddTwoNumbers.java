package leetcode.medium;

/*
https://imgur.com/a/GO9TM21
수행시간 1ms로 100프로를 땄고, 메모리는 아쉽게도 44MB로 14프로만 땄다.
근데 나는 최대한 in-place로 하려고 이렇게 복잡하게 했는데, 다른 사람들 풀이 보니까 그냥 새로운 노드 만들어서 갖다 붙이는 식으로 함...
*/

public class Leet_2AddTwoNumbers {
    //문제의 설명에 따라 연결리스트의 노드를 간단하게 구현함.


    public static void main(String[] args) {

    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode base = new ListNode();//임시로 머리가 되어줄 노드를 만들고, l1, l2를 그 뒤에 붙인다. 아래 process에서 base, not_base가 그 자체로 null이 되는 상황을 최대한 피하는게 좋다.
        ListNode not_base = new ListNode();
        base.next = l1;
        not_base.next = l2;

        process(base, not_base, 0);

        return base.next;
    }

    private void process(ListNode base, ListNode not_base, int passToNext){
        if (base.next == null && not_base.next != null) {//base.next는 null인데 not_base.next는 null이 아닌 경우, 그러니까 base의 자릿수가 not_base보다 자릿수가 작은 경우
            base.next = not_base.next;//base.next와 not_base.next를 똑 때서 교환한다.
            not_base.next = null;
        }

        if (base.next == null && not_base.next == null) {//둘 다 끝난 경우
            if (passToNext != 0) {//이전 호출에서 이번 호출로 넘긴 수가 있으면
                base.next = new ListNode(passToNext);//새로운 자릿수를 하나 추가해줌.
            }
            return;//탈출
        }

        int baseVal = base.next.val;//base의 해당 자릿수 값
        int not_baseVal = 0;//not_base의 해당 자릿수 값
        if (not_base.next != null) {//not_base의 해당 자릿수가 있으면 값을 저장함.
            not_baseVal = not_base.next.val;
        }
        int plusResult = baseVal + not_baseVal + passToNext;//baseVal + not_baseVal + 이전 호출에서 넘긴 값까지 더한게 이 자릿수의 더하기 결과다.

        if (plusResult < 10) {//만약 plusResult가 10이상이면 다음 호출로 1을 넘겨야 한다.
            base.next.val = plusResult;
            passToNext = 0;
        } else {
            base.next.val = plusResult - 10;
            passToNext = 1;
        }

        if (not_base.next != null) {//이렇게 조건문을 달아서 재귀호출하는 이유는 not_base가 직접 null이 되는 상황을 피하기 위함이다. not_base가 null이 되면 바로 다음 호출 첫부분 not_base.next != null연산부터 예외가 발생함.
            process(base.next, not_base.next, passToNext);
        } else {
            process(base.next, not_base, passToNext);//다음호출에도 계속 not_base.next == null인 상태가 유지되게 인수를 넘겨줌.
        }
    }

    private static class ListNode{

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
