package leetcode.review.finalExam;

import java.util.HashMap;

public class Leet_128LongestConsecutiveSequence {
    public static void main(String[] args) {
        Leet_128LongestConsecutiveSequence leet = new Leet_128LongestConsecutiveSequence();
        System.out.println(leet.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
        System.out.println(leet.longestConsecutive(new int[]{0,3,7,2,5,8,4,6,0,1}));
    }

    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        //이 문제를 풀기 위해서는 2개의 HashMap이 필요하다.
        //하나는 어떤 숫자가 nums에 존재하는지 확인하고, 그 숫자가 어떤 수열을 만들기 위해 사용되었는지 확인하기 위해 필요하다.
        //다른 하나는 어떤 숫자로 시작되는 수열의 길이를 기록하기 위해 필요하다.

        HashMap<Integer, Boolean> used = new HashMap<>();
        for (int num : nums) {
            used.put(num, false);//num이 아직 수열을 만들기 위해 사용되지 않았음을 의미한다.
        }
        HashMap<Integer, Integer> lengthOfSequence = new HashMap<>();

        for (int num : nums) {
            if (used.get(num)) {//이미 사용된 숫자이므로 continue
                continue;
            }

            used.put(num, true);
            lengthOfSequence.put(num, 1);

            int nextNum = num+1;
            while (true) {
                if (!used.containsKey(nextNum)) {
                    break;
                }
                if (used.get(nextNum)) {//이미 사용된 숫자를 만나게 된다면 분명히 이 숫자로 시작하는 수열이 있을 것이다.
                    lengthOfSequence.put(num, lengthOfSequence.get(num) + lengthOfSequence.get(nextNum));
                    break;
                }

                //여기까지 오면 nextNum이 nums에 존재하고, 아직 사용되지도 않은 상태다. 그대로 num으로 시작하는 수열에 포함시키면 됨.
                used.put(nextNum, true);
                lengthOfSequence.put(num, lengthOfSequence.get(num) + 1);

                nextNum++;
            }
        }

        //System.out.println(lengthOfSequence);
        int max = Integer.MIN_VALUE;
        for (Integer value : lengthOfSequence.values()) {
            if (max < value) {
                max = value;
            }
        }

        return max;
    }
}
