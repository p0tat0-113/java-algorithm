package java_study.collections.queue;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

//우선순위큐를 사용하는 방법에 대해서 알아본다.

public class PriorityQueue1 {
    public static void main(String[] args) {
        Queue<String> queue = new PriorityQueue<>();//PriorityQueue는 정렬된 상태로 유지됨. 별도의 Comparator를 넣을 수 있음.
        //기본적으로는 자연순서, 오름차순으로 정렬된다.

        queue.addAll(List.of("B", "A", "C"));//여러 요소를 한 번에 넣을 수 있다.
        System.out.println(queue);

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());

        //문자열의 길이를 기준으로 오름차순으로 정렬되게끔 Comparator를 구현함.
        Queue<String> customQueue1 = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        });

        customQueue1.addAll(List.of("bbbb","aaa","cc"));
        System.out.println(customQueue1);//[cc, bbbb, aaa] 이건 그냥 큐의 내부배열을 출력하는 것, 힙 구조를 유지하는 방식으로 정렬되어있다.
        //PriorityQueue의 중요한 특징은, 큐에서 요소를 꺼낼 때마다 정렬된 상태가 유지된다는 점(루트를 하나 꺼내고, 스며내리기를 수행함)

        System.out.println(customQueue1.poll());
        System.out.println(customQueue1.poll());
        System.out.println(customQueue1.poll());

        //완전이진트리를 힙구조로 수선하는데는 O(n)
        //삽입, 삭제 연산을 하는데는 O(logn)의 시간복잡도를 가진다.
    }
}
