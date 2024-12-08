package leetcode.review.finalExam;

import java.util.HashMap;
import java.util.HashSet;

public class Leet_128LongestConsecutiveSequence {
    public static void main(String[] args) {
        Leet_128LongestConsecutiveSequence leet = new Leet_128LongestConsecutiveSequence();
        System.out.println(leet.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
        System.out.println(leet.longestConsecutive(new int[]{0,3,7,2,5,8,4,6,0,1}));
    }

    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int max = Integer.MIN_VALUE;

        HashMap<Integer, Integer> used = new HashMap<>();
        for (int num : nums) {
            used.put(num, 0);
        }

        HashMap<Integer, Integer> lengthOfStartFrom = new HashMap<>();

        for (Integer integer : used.keySet()) {
            if (used.get(integer) == 1) {//이 부분이 없어서 계속 시간초과가 발생하고 있었다. 이미 수열을 만드는데 쓰인 숫자는 다시 검사하지 않는 것이 중요함.
                continue;
            }

            used.put(integer, 1);
            lengthOfStartFrom.put(integer, 1);
            int nextNum = integer + 1;

            while (true) {
                if (!used.containsKey(nextNum)) {
                    break;
                }

                if (lengthOfStartFrom.containsKey(nextNum)) {
                    lengthOfStartFrom.put(integer, lengthOfStartFrom.get(integer) + lengthOfStartFrom.get(nextNum));
                    break;
                }

                used.put(nextNum, 1);
                lengthOfStartFrom.put(integer, lengthOfStartFrom.get(integer) + 1);
                nextNum++;
            }
            if (max < lengthOfStartFrom.get(integer)) {
                max = lengthOfStartFrom.get(integer);
            }
        }

        return max;
    }
}
