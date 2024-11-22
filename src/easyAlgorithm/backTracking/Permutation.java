package easyAlgorithm.backTracking;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Permutation {
    public static void main(String[] args) {
        //nums에서 k개의 원소만을 사용해서 순열을 만드는 알고리즘

        int[] nums = {1,2,3,4};
        int k = 2;
        boolean[] used = new boolean[nums.length];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        ArrayList<List<Integer>> result = new ArrayList<>();

        permutation_dfs(result, used, nums, stack, k);
        System.out.println(result);
    }

    private static void permutation_dfs(List<List<Integer>> result, boolean[] used, int[] nums, ArrayDeque<Integer> stack, int k){
        if (stack.size() == k) {
            result.add(new ArrayList<>(stack));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i] || (!stack.isEmpty() && nums[i] < stack.peekLast())) {
                continue;
            }

            used[i] = true;
            stack.add(nums[i]);

            permutation_dfs(result, used, nums, stack,k);

            used[i] = false;
            stack.pollLast();
        }
    }
}
