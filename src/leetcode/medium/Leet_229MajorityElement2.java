package leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
크기 n의 배열에서 Math.floor(n/3)+1 번 이상 등장한 수들을 모두 찾아내야 하는 문제다.
문제만 보면 그렇게 어려워보이지는 않음. 그런데 문제는 follow up으로 이 문제를 선형시간, constant space로 해결하라고 한다.

우선은 해시맵을 사용해서 풀어봤음. 선형시간이긴 하지만 공간은 O(n)이다.
솔루션을 보니까 이 문제를 선형시간, constant space로 풀려면 '보이어-무어 다수결' 알고리즘 이라는걸 써야한다고 한다.
*/

public class Leet_229MajorityElement2 {
    public static void main(String[] args) {

    }

    public List<Integer> majorityElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int cut = nums.length/3;

        //map을 이용해서 각 숫자가 등장한 횟수를 센다.
        for (int num : nums) {
            map.put(num, map.getOrDefault(num,0)+1);
        }

        //map에서 entreySet을 꺼내서 등장 횟수가 cut이상인 것만 result리스트에 담는다.
        ArrayList<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > cut) {
                result.add(entry.getKey());
            }
        }

        return result;
    }
}
