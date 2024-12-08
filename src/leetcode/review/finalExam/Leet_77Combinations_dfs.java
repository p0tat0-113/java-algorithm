package leetcode.review.finalExam;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Leet_77Combinations_dfs {
    public static void main(String[] args) {
        Leet_77Combinations_dfs leet = new Leet_77Combinations_dfs();
        System.out.println(leet.combine(4, 2));
        System.out.println(leet.combine(1, 1));
    }

    //이 문제를 dfs방식으로 다시 구현해 보았다.
    //전에 풀었던 방식과는 조금 다른 특이한 방식으로 풀긴 했는데 어쨌든 잘 작동되네.
    public List<List<Integer>> combine(int n, int k) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        ArrayList<List<Integer>> result = new ArrayList<>();

        dfs(n, k, stack, result);
        return result;
    }

    private void dfs(int n, int k, ArrayDeque<Integer> stack, ArrayList<List<Integer>> result){
        if (stack.size() == k) {
            result.add(new ArrayList<>(stack));
            return;//처음에 return을 빼먹어서 스택오버플로우 터짐ㅋㅋ
        }

        int start = 1;
        if (!stack.isEmpty()) {
            start = stack.peekLast() + 1;
        }

        for (int i = start; i <= n; i++) {
            stack.add(i);
            dfs(n, k, stack, result);
            stack.pollLast();
        }
    }
}
