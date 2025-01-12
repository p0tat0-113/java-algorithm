package baekjoon.graphTraversal;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

/*
문제
수빈이는 동생과 숨바꼭질을 하고 있다. 수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 동생은 점 K(0 ≤ K ≤ 100,000)에 있다. 수빈이는 걷거나 순간이동을 할 수 있다.
만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다. 순간이동을 하는 경우에는 1초 후에 2*X의 위치로 이동하게 된다.
수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하는 프로그램을 작성하시오.
입력
첫 번째 줄에 수빈이가 있는 위치 N과 동생이 있는 위치 K가 주어진다. N과 K는 정수이다.

문제만 보면 dp로 풀어야 할 것 같은데 bfs로 푸는 문제다... 신기하네
그리 어렵지는 않은 문제였음.
*/

public class Baek_1697숨바꼭질 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();//수빈이의 위치
        int k = scanner.nextInt();//동생의 위치

        int distanceCount = -1;//소요시간 기록

        HashSet<Integer> visited = new HashSet<>();//방문한 위치 X를 기록
        visited.add(n);

        LinkedList<Integer> queue = new LinkedList<>();//bfs큐
        queue.add(n);

        while (!queue.isEmpty()) {
            distanceCount++;
            int currentQueueSize = queue.size();//최단거리를 계산하는 거라서 이 부분이 중요하다.

            for (int i = 0; i < currentQueueSize; i++) {
                Integer polled = queue.poll();

                if (polled == k) {
                    System.out.println(distanceCount);
                    return;
                }

                addToQueue(visited, queue, polled-1);
                addToQueue(visited, queue, polled+1);
                addToQueue(visited, queue, polled*2);
            }
        }
    }

    private static void addToQueue(HashSet<Integer> visited, LinkedList<Integer> queue, int x) {
        if (x < 0 || x > 100000 || visited.contains(x)) {//범위를 벗어나거나 이미 방문한 지점이면 큐에 추가하지 않고 빠져나감.
            return;
        }
        queue.add(x);
        visited.add(x);
    }
}
