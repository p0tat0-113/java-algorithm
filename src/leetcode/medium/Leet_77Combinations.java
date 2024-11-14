package leetcode.medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/*
드디어 과제로 백트래킹 문제가 나오기 시작했다!
두 수 n과 k가 주어진다. 1부터 n까지의 범위에서 k개의 숫자를 이용해서 만들 수 있는 모든 수열을 찾는 문제다.
사실 중간고사 이후 풀었던 백준 N과 M문제와 거의 같은 문제다.

Input: n = 4, k = 2
Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
Explanation: There are 4 choose 2 = 6 total combinations.
Note that combinations are unordered, i.e., [1,2] and [2,1] are considered to be the same combination.
예시인데 [1,2] [2,1]은 같은걸로 취급하라고 한다. 이걸 어떻게 처리를 해야하나 조금 고민을 했는데
그냥 저 수열이 오름차순이나 되게 하면 됨. 더 내려가서 찾을 수 있는 조건으로 앞에 있던 수보다 커야한다는 조건을 달면 된다.
*/

public class Leet_77Combinations {
    public static void main(String[] args) {
        /*ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.add(1);
        deque.add(2);
        System.out.println(deque.pollLast());
        System.out.println(deque.pollLast());*/

        Leet_77Combinations leet = new Leet_77Combinations();
        System.out.println(leet.combine(4, 2));
    }

    public List<List<Integer>> combine(int n, int k) {
        ArrayList<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[n];
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        dfs(result, stack, used, n, k);
        return result;
    }

    private void dfs(List<List<Integer>> result, ArrayDeque<Integer> stack, boolean[] used, int n, int depth){
        if (depth == 0) {
            result.add(new ArrayList<>(stack));
            return;
        }

        for (int i = 1; i <= n; i++) {//1~n
            //처음에는 (used[i-1] || stack.peekLast() > i)이렇게 해서 스택이 비어있는 상황에서 NullPointerException이 발생했었다.
            if (used[i-1] || (!stack.isEmpty() && stack.peekLast() > i)) {//이미 사용된 숫자이거나, 스택에 가장 마지막에 들어간 숫자보다 작은 쪽으로는 뻗어나가지 않음.
                continue;
            }

            stack.add(i);
            used[i-1] = true;

            dfs(result, stack, used, n, depth-1);

            stack.pollLast();
            used[i-1] = false;
        }
    }
}
