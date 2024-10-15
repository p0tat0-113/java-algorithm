package leetcode.review;

/*각 알파벳과 단어를 매칭을 시켜야 한다.
알파벳이 기존 맵에 존재하지 않고, 단어도 존재하지 않던 단어이면(셋에 없으면)새로 등장한 패턴이다.
알파벳을 맵에 존재하면, 밸류인 단어가 지금 입력으로 들어온 단어와 일치하는지를 확인한다.*/


import java.util.HashMap;
import java.util.HashSet;

public class Leet_290WordPattern {
    public static void main(String[] args) {
        Leet_290WordPattern leet = new Leet_290WordPattern();
        System.out.println(leet.wordPattern("abba", "dog cat cat fish"));
        System.out.println(leet.wordPattern("abba", "dog cat cat dog"));
        System.out.println(leet.wordPattern("aaaa", "dog cat cat dog"));
    }

    public boolean wordPattern(String pattern, String s) {
        String[] patternArr = pattern.split("");
        String[] wordArr = s.split(" ");
        HashMap<String, String> patternMap = new HashMap<>();//알파벳이 키다. 단어가 밸류
        HashSet<String> wordSet = new HashSet<>();//등장한 단어들을 기록한다.

        if (patternArr.length != wordArr.length) {
            return false;
        }

        for (int i = 0; i < patternArr.length; i++) {
            if (patternMap.containsKey(patternArr[i])) {//map에 해당 알파벳이 존재
                if (!patternMap.get(patternArr[i]).equals(wordArr[i])) {
                    return false;
                }
            } else {//map에 해당 알파벳이 존재하지 않는데
                if (!wordSet.contains(wordArr[i])) {//set에도 해당 단어가 존재하지 않으면 -> 새로운 패턴이다.
                    patternMap.put(patternArr[i], wordArr[i]);
                    wordSet.add(wordArr[i]);
                    continue;//continue가 빠져있는게 문제였음.
                }
                return false;
            }
        }

        return true;
    }
}
