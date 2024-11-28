package leetcode.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/*
Input: nums = [100,4,200,1,3,2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.

set하나 두고,
아직 시작점으로서 검사하지 않은 set하나 둠.
해당 숫자를 시작점으로 했을 때의 길이를 HashMap으로 저장.
어떤 검사를 하다가 해당 숫자를 시작점으로 했을 때의 길이가 기록된게 있으면 그것과 연결시킴.
*/
public class Leet_128LongestConsecutiveSequence {
    public static void main(String[] args) {
        Leet_128LongestConsecutiveSequence leet = new Leet_128LongestConsecutiveSequence();
        System.out.println(leet.longestConsecutive(new int[]{0, -1}));
        System.out.println(leet.longestConsecutive(new int[]{100,4,200,1,3,2}));
        System.out.println(leet.longestConsecutive(new int[]{0,3,7,2,5,8,4,6,0,1}));
    }

    public int longestConsecutive(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }

        HashMap<Integer, Integer> used = new HashMap<>();//어떤 숫자가 nums에 존재하는지, 그리고 이 숫자가 수열을 만드는게 사용되었는지 확인하는 기능을 둘 다 수행한다.
        HashMap<Integer, Integer> lengthOfSequenceStartedFrom = new HashMap<>();

        for (int num : nums) {
            used.put(num, 0);//0은 사용되지 않았음을 의미한다.
        }

        /*System.out.println("초기화");
        System.out.println(Arrays.toString(nums));
        System.out.println(used);
        System.out.println(lengthOfSequenceStartedFrom);

        System.out.println("순회시작");*/
        for (int i = 0; i < nums.length; i++) {
            int headNum = nums[i];

            /*System.out.println(headNum);
            System.out.println(used);*/

            if (used.get(headNum) == 1) {//이미 사용된 숫자라면 패스함.
                /*System.out.println("continue");*/
                continue;
            }

            /*System.out.println(lengthOfSequenceStartedFrom);*/

            lengthOfSequenceStartedFrom.put(nums[i],1);//lengthOfSequenceStartedFrom에 headNum으로 시작하는 수열의 길이가 1임을 입력
            int nextNum = headNum + 1;
            used.put(headNum, 1);//headNum이 사용되었음을 표시


            while (true) {
                if (!used.containsKey(nextNum)) {//nextNum이 nums에 존재하지 않는 숫자라면 바로 탈출한다.
                    break;
                }
                if (used.get(nextNum) == 1) {//nextNum이 사용된 숫자라면 이 숫자를 시작하는 수열이 반드시 있을 것이다. 이 수열의 길이를 가져와서 더하고 탈출한다.
                    lengthOfSequenceStartedFrom.put(headNum, lengthOfSequenceStartedFrom.get(headNum) + lengthOfSequenceStartedFrom.get(nextNum));
                    break;
                }

                //nextNum이 nums에 존재하고, 아직 사용되지 않은 경우
                used.put(nextNum, 1);//nextNum이 사용되었음을 표시
                lengthOfSequenceStartedFrom.put(headNum, lengthOfSequenceStartedFrom.get(headNum) + 1);//headNum으로 시작하는 수열의 길이 1증가
                nextNum += 1;//다음 연산을 위해 nextNum 1증가
            }
        }

        //마지막에 lengthOfSequenceStartedFrom의 values를 꺼내서 그 중 가장 큰 것을 반환한다.
        int max = Integer.MIN_VALUE;
        for (Integer length : lengthOfSequenceStartedFrom.values()) {
            if (length > max) {
                max = length;
            }
        }
        return max;
    }
}
