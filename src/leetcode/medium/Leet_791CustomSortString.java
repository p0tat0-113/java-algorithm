package leetcode.medium;

import java.util.Arrays;
import java.util.HashMap;

public class Leet_791CustomSortString {
    /*
    Input: order = "cba", s = "abcd"
    Output: "cbad"

    input으로 들어온 order을 보고 문자 간의 우열 관계를 파악한 후, 문자열 s도 마찬가지로 정렬해야 한다.
    order의 문자들은 중복되지 않는다고 함.

    order의 각 문자를 map에 넣고 value로 정렬된 순서, 즉 우선순위를 넣어주면 될 것 같다.
    그리고 s의 각 문자들을 map에 저장한 문자들의 우선순위를 바탕으로 정렬하면 될 듯. 그리 어려워 보이지 않음.

    문제 설명을 보니까 order에 업는 문자는 대충 아무렇게나 놓아도 상관 없다고 한다.
    order의 길이는 1 <= order.length <= 26 이라고 함.
    */

    public String customSortString(String order, String s) {
        String[] orderCharArray = order.split("");
        HashMap<String, Integer> orderMap = new HashMap<>();

        for (int i = 0; i < orderCharArray.length; i++) {//각 문자의 우선순위를 저장
            orderMap.put(orderCharArray[i], i);
        }

        String[] sCharArray = s.split("");
        Arrays.sort(sCharArray, (c1, c2) -> {//map에서 꺼내온 문자별 우선순위에 맞게 정렬하도록 Comparator 재정의, 람다표현식 사용
            int c1Order = orderMap.getOrDefault(c1, 1000);//order에 해당 문자가 없다면 우선순위를 1000으로 정해서 아무렇게나 뒤로 빠지게 함.
            int c2Order = orderMap.getOrDefault(c2, 1000);
            return Integer.compare(c1Order, c2Order);
        });

        return String.join("",sCharArray);
    }

    public static void main(String[] args) {
        Leet_791CustomSortString leet791CustomSortString = new Leet_791CustomSortString();

        System.out.println(leet791CustomSortString.customSortString("cba", "abcd"));
    }
}
