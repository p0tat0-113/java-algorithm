package leetcode.easy;

/*
Input: pattern = "abba", s = "dog cat cat dog"
Output: true

input으로 들어오는 문자열의 패턴을 파악해서 s의 단어들이 그 패턴과 맞는지 파악하는 문제다.
패턴의 길이는 1~300이다. 패턴이 1이면 그냥 true를 반환하면 될 듯.

그리고 패턴의 길이보다 하나 적게 반복문을 돌려서 인덱스 i와 i+1의 관계를 String.compare()로 얻고, 이것이 s에 있는 단어들의 관계와 일치하는지 보면 될 듯 함.
이 과정을 하기 전에 split()으로 얘네 각각을 다 배열에 집어넣어야 한다.

아 문제 파악 잘못함.
map에 해당 인덱스의 패턴 알파벳과, 단어를 키 값 형태로 집어넣음. 그리고 이후에 map에 없는 새로운 패턴 알파벳이 나오면 map에 새롭게 등록을 하고, map에 있으면 단어가 맞는지 비교.

pattern = "abba" s = "dog dog dog dog", 이 경우에서 false가 나와야 하는데 true가 나온다. 키와 값을 바꿔야 함. 단어가 키, 알파벳이 값
이러니까 패턴에 맞지 않는 새로운 단어가 나오는 경우 틀림.

이미 나온 알파벳들만 저장하는 set을 하나 운용, 새로운 단어가 나왔을 때 알파벳이 여기 없다면 추가를 하게 하고, 아니면 false를 반환하게 해야할 듯. <- 이렇게 까지 하니까 풀렸음.
대충 1~2ms 왔다 갔다 하네.
*/

import java.util.*;

public class Leet_290WordPattern {
    public static void main(String[] args) {
        System.out.println(wordPattern("abba", "dog cat cat dog"));
    }

    public static boolean wordPattern(String pattern, String s) {
        String[] patternArr = pattern.split("");//pattern과 s를 각각의 배열로 변환
        String[] sArr = s.split(" ");

        if (patternArr.length != sArr.length) {//둘의 길이가 다른면 무조건 false를 반환함.
            return false;
        }

        HashMap<String, String> map = new HashMap<>();
        HashSet<String> set = new HashSet<>();

        for (int i = 0; i < patternArr.length; i++) {
            if (!map.containsKey(sArr[i])) {//map에 해당 단어가 없으면, 두가지 경우의 수: 1.패턴에 맞지 않는 이상한 단어가 들어옴 2.새로운 패턴이 등장함.
                if (!set.contains(patternArr[i])) {//set에도 해당 알파벳이 없다면, 즉 완전히 새롭게 등장하는 패턴일 때만 추가하는 것.
                    map.put(sArr[i], patternArr[i]);
                    set.add(patternArr[i]);
                } else {
                    return false;
                }

            } else {//map에 해당 단어가 있으면
                if (!map.get(sArr[i]).equals(patternArr[i])) {//map에 해당 단어와 매칭되어있는 알파벳과 다르면 false를 반환
                    return false;
                }
            }
        }

        return true;
    }
}
