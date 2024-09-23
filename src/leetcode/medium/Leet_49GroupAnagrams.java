package leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]

Explanation:
There is no string in strs that can be rearranged to form "bat".
The strings "nat" and "tan" are anagrams as they can be rearranged to form each other.
The strings "ate", "eat", and "tea" are anagrams as they can be rearranged to form each other.

이런 문제다. 나는 우선 각 단어를 char배열로 만들고 정렬한 후 다시 String으로 만들어서 map을 이용해 이걸 비교하는 식으로 문제를 풀었다.
간단한 코드로 해결하긴 했는데, 실행시간이 10ms로 그닥 좋지는 못하다. 6ms까지 단축시켜야 함.

정렬하지 않고 같은 알파벳들을 가진 단어를 찾아낼 수 있는 방법이 없을까?
*/

public class Leet_49GroupAnagrams {
    public static void main(String[] args) {
        System.out.println(groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
    }

    public static List<List<String>> groupAnagrams(String[] strs) {

        HashMap<String, ArrayList<String>> map = new HashMap<>(strs.length);

        char[] charArray;
        String sortedStr;

        for (String str : strs) {
            charArray = str.toCharArray();
            Arrays.sort(charArray);
            sortedStr = Arrays.toString(charArray);

            if (!map.containsKey(sortedStr)) {
                map.put(sortedStr, new ArrayList<>());
            }
            map.get(sortedStr).add(str);
        }

        return new ArrayList<>(map.values());
    }
}
