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
        Leet_77Combinations leet = new Leet_77Combinations();
        System.out.println(leet.combine(4, 2));
    }

    //이번에는 dfs가 아닌 큐를 사용하는 bfs방식으로 구현해보자!!! 이 문제는 bfs로 구현하는게 효율적인지는 모르겠음. 숫자 조합을 경계조건까지 계속 끌고가야 해서....
    public List<List<Integer>> combine(int n, int k) {
        ArrayList<List<Integer>> result = new ArrayList<>();
        ArrayDeque<ArrayList<Integer>> queue = new ArrayDeque<>();

        bfs(result, queue, n, k);
        return result;
    }

    private void bfs(List<List<Integer>> result, ArrayDeque<ArrayList<Integer>> queue, int n, int k){
        //queue를 초기화. 각 숫자들을 가지고 있는 n개의 리스트를 큐에 집어넣는다.
        for (int num = 1; num <= n; num++) {
            ArrayList<Integer> innerList = new ArrayList<>();
            innerList.add(num);
            queue.add(innerList);
        }

        //큐가 빌 때까지 반복
        while(!queue.isEmpty()) {//큐에 값들이 들어있는 동안
            ArrayList<Integer> innerList = queue.pollFirst();//큐의 가장 앞에 있는 리스트를 꺼낸다.

            if (innerList.size() == k) {//만약 innerList의 크기가 k, 즉 목표에 도달했으면 result에 추가하고 continue한다. 더 이상 뻗어나가지 않음.
                result.add(innerList);
                continue;
            }

            //해당 리스트의 가장 뒤에 있는 숫자를 가져옴. 가장 최근에 추가된 가장 큰 숫자다.
            int lastNum = innerList.getLast();
            for (int num = lastNum+1; num <= n; num++) {//맨뒤의 숫자+1 ~ n 범위에서 innerList에 해당 숫자를 추가한 리스트를 만들어 큐에 집어넣는다.
                queue.add(new ArrayList<>(innerList));
                queue.getLast().add(num);
            }
        }
    }
}
