package leetcode.review.finalExam;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Leet_77Combinations_bfs {
    public static void main(String[] args) {
        Leet_77Combinations_bfs leet = new Leet_77Combinations_bfs();
        System.out.println(leet.combine(4, 2));
        System.out.println(leet.combine(1, 1));
    }

    //이 문제를 bfs방식으로 다시 구현해 보았다.
    public List<List<Integer>> combine(int n, int k) {
        ArrayList<List<Integer>> result = new ArrayList<>();
        ArrayDeque<List<Integer>> queue = new ArrayDeque<>();

        for (int i = 1; i <= n; i++) {
            queue.add(new ArrayList<>(List.of(i)));
        }

        while (!queue.isEmpty()) {
            List<Integer> polledList = queue.pollFirst();

            if (polledList.size() == k) {
                result.add(polledList);
                continue;
            }

            Integer lastNum = polledList.getLast();
            for (int i = lastNum+1; i <= n; i++) {
                if (i > lastNum) {
                    ArrayList<Integer> newList = new ArrayList<>(polledList);
                    newList.add(i);
                    queue.add(newList);
                }
            }
        }

        return result;
    }
}
