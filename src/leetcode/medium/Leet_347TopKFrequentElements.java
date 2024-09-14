package leetcode.medium;

import java.util.*;

/*
Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]

nums에서 가장 많이 등장한 상위 k개의 숫자를 배열에 담아서 반환해야 하는 문제다.

그냥 자바 컬렉션을 사용하면 간단하게 풀 수 있는 문제였음.
map으로 각 숫자들이 등장한 횟수를 세고, entrySet을 뽑아서 우선순위큐에 넣어서 등장횟수가 많은 순으로 정렬,
k번 poll하면 풀림. 실행시간도 13~14ms로 50~70%를 따는 수준이라서 나쁘지 않음.
*/

public class Leet_347TopKFrequentElements {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2)));
    }

    public static int[] topKFrequent(int[] nums, int k) {

        //map을 만들고, 각 숫자의 등장 횟수를 센다.
        HashMap<Integer, Integer> map = new HashMap<>(nums.length);
        for (int num : nums) {
            /*map.putIfAbsent(num,0);
            map.put(num,map.get(num)+1);*/
            //속도 개선
            map.put(num,map.getOrDefault(num,0)+1);
        }

        //entry객체의 value를 기준으로 내림차순으로 정렬하게끔 구현된 Comparator를 인수로 넣은 priorityQueue가 entry객체들을 정렬함.
        PriorityQueue<Map.Entry<Integer, Integer>> priorityQueue = new PriorityQueue<>(map.size(), (o1, o2) -> Integer.compare(o1.getValue(),o2.getValue())*-1);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            priorityQueue.offer(entry);
        }

        //k번 만큼 queue에서 entry객체를 꺼내서 result에 key를 담아 반환한다.
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = priorityQueue.poll().getKey();
        }

        return result;
    }
}
