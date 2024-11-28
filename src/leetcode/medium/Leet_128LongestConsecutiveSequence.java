package leetcode.medium;

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
    }

    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();//검사한 숫자는 set에서 삭제를 해버리자.
        HashMap<Integer, Integer> lengthOfSequenceStartedFrom = new HashMap<>();
        HashSet<Integer> contained = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        //우선 자기자신을 set에서 삭제하고, map에 (num,1)쌍을 등록한다. num으로 시작하는 시퀀스의 길이는 1이라는 뜻이다.
        //그리고 num에 1씩 더해나가면서 해당 숫자가 set에 있는지 확인,
        //해당 숫자가 set, map 둘 다 없다면 거기서 정지.
        //해당 숫자가 set에만 있다면 set에서 해당 숫자 삭제, map에서 num value값에 1더함.
        //해당 숫자가 map에만 있다면 map에서 num value에 해당숫자 value를 더하고 끝냄.
        for (Integer num : set) {
            contained.add(num);
            lengthOfSequenceStartedFrom.put(num,1);

            System.out.println(contained);
            System.out.println(lengthOfSequenceStartedFrom);

            int nextNum = num+1;
            while (contained.size() != set.size()+1) {
                //nextNum이라는 숫자가 존재하지 않거나 이미 사용되었고, 그러면서 nextNum으로 시작되는 수열이 기록된 것도 없는 경우
                if ((!set.contains(nextNum) || contained.contains(nextNum)) && !lengthOfSequenceStartedFrom.containsKey(nextNum)) {
                    break;
                }
                //nextNum이라는 숫자가 존재하지만 아직 사용되지 않은 경우
                else if (set.contains(nextNum) && !contained.contains(nextNum)) {
                    contained.add(num);
                    lengthOfSequenceStartedFrom.put(num, lengthOfSequenceStartedFrom.get(num)+1);
                }
                //nextNum이라는 숫자가 이미 사용되었고, 해당 숫자로 시작되는 수열이 기록된 경우
                else if (contained.contains(nextNum) && lengthOfSequenceStartedFrom.containsKey(nextNum)) {
                    lengthOfSequenceStartedFrom.put(num, lengthOfSequenceStartedFrom.get(num) + lengthOfSequenceStartedFrom.get(nextNum));
                    break;
                }
                nextNum++;
            }

            System.out.println(lengthOfSequenceStartedFrom);
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
